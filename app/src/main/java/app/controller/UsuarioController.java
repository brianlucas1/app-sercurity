package app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dto.UsuarioDTO;
import app.excpetion.PassowrdNotEqualsException;
import app.excpetion.UsuarioNaoEncontradoExecption;
import app.model.UsuarioModel;
import app.service.UsuarioService;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	

	@Autowired
	private UsuarioService userService;	
	
	
	/**
	 * Metodo responsável pela primeira chamada da requisição, ele que vai fazer o save no banco de dados para conseguir seguir com
	 * as outras etapas do app.
	 *  Dados que podem ser enviados no post.
	 *  {
	    "usuario":"brian123",
	    "senha": "teste123",
	    "passwordConfirm": "teste123",
	    "email": "a@a.com"   
		}
		
	 */
	
	@PostMapping("/create")
	public ResponseEntity<UsuarioDTO> create(@RequestBody @Valid UsuarioModel user) throws PassowrdNotEqualsException {				
	
		UsuarioDTO userCriado = userService.saveUser(user);		
		return new ResponseEntity<UsuarioDTO>(userCriado, HttpStatus.CREATED);
	}	
	
	
	@GetMapping("/find/{id}")
	public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Integer id) throws UsuarioNaoEncontradoExecption {
		
		UsuarioDTO user = userService.findById(id);
		
		return new ResponseEntity<UsuarioDTO> (user,HttpStatus.OK);
	}
	
	@GetMapping("/find}")
	public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
		
		List<UsuarioDTO> users = userService.findAllUsers();
		
		return new ResponseEntity<List<UsuarioDTO>>(users, HttpStatus.CREATED);
	}
	

}
