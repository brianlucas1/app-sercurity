package app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.dto.UsuarioDTO;
import app.excpetion.PassowrdNotEqualsException;
import app.excpetion.UsuarioNaoEncontradoExecption;
import app.model.UsuarioModel;
import app.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	
	@Autowired
	private UsuarioRepository userRepo;

	 /**
	 *  1 . 
	 */
	
	public UsuarioDTO saveUser(@Valid UsuarioModel user) throws PassowrdNotEqualsException {
				
		UsuarioModel usuario  = buscaUsuarioPorUserESenha(user);
						
			if(usuario != null) {				
				return UsuarioDTO.convertEmDTO(usuario);
			}else {
				BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
				user.setSenha(crypt.encode(user.getSenha()));
				UsuarioModel userCriado = userRepo.save(user);
				return UsuarioDTO.convertEmDTO(userCriado);
			}		
	}	

	public UsuarioDTO findById(Integer id) throws UsuarioNaoEncontradoExecption {

		UsuarioModel usuario  = userRepo	
					.findById(id)
					.orElseThrow( () ->  new UsuarioNaoEncontradoExecption("No find any Usuario with this Id"));		
		return UsuarioDTO.convertEmDTO(usuario);
	}

	
	private UsuarioModel buscaUsuarioPorUserESenha(UsuarioModel userModel) {
		UsuarioModel user =  userRepo
			.findByUsuarioAndSenha(userModel.getUsuario(),userModel.getPassword());
		return user;
	}

	public List<UsuarioDTO> findAllUsers() {
		// TODO Auto-generated method stub
		return UsuarioDTO.convertEmListDTO(userRepo.findAll());
	}
	
}
