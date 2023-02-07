package app.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.repository.UsuarioRepository;

@Service
public class AutenticacaoService  implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserDetails> user = userRepo.findByEmail(email);
		if(user.isPresent()) {
			return user.get();
		}
			throw new UsernameNotFoundException("Não foi encontrado nenhum Usuario cadastrado com esse E-mail.");
	}

}
