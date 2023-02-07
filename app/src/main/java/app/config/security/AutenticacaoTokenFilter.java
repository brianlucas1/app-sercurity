package app.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ReportAsSingleViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import app.model.UsuarioModel;
import app.repository.UsuarioRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter{
	
	/**
	 * Classe responsável pela Autenticação:
	 *  Ela verifica o Token recebido e faz a sua validação.
	 */
	
	private TokenService tokenService;
	
	private UsuarioRepository userRepo;
	
	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository repo) {
		this.tokenService = tokenService;
		this.userRepo = repo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String token = recuperarToken(request);
		
		boolean valido = tokenService.isTokenValido(token);
		
		if(valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void autenticarCliente(String token) {
		
		Integer idUsuario = tokenService.getIdUsuario(token);
		
		UsuarioModel user = userRepo.findById(idUsuario).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recuperarToken(HttpServletRequest request) {
	    String token = request.getHeader("Authorization");
	    if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
	        return null;
	    }

	    return token.substring(7, token.length());
	}
	
	

}
