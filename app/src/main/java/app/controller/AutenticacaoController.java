package app.controller;

import org.springframework.security.core.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.config.security.TokenService;
import app.dto.LoginFormDTO;
import app.dto.TokenDto;


/**
 * CONTROLA OS ACESSOS DO FRONT END
 * DEVE AUTENTICAR O USUARIO E SENHA PARA CONSEGUIR ACESSAR OS END-POINTS
 */
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginFormDTO form){
		
		UsernamePasswordAuthenticationToken dadosLogin = form.convert();
		
		try {
			Authentication authenticate = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(authenticate);
			
			return ResponseEntity.ok(new TokenDto(token,"Bearer"));	
			
		} catch (AuthenticationException e) {
			return   ResponseEntity.badRequest().build();
		}		
	}

}
