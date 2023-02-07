package app.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.Length;

import app.model.UsuarioModel;

@JsonIgnoreProperties(ignoreUnknown =  true)
public class UsuarioDTO {
	
	
	private String usuario;	

	private String password;	
	
	private String email;	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UsuarioDTO(UsuarioModel user) {
		this.usuario = user.getUsuario();
	}

	public String getUsuario() {
		return usuario;
	}


	public String getPassword() {
		return password;
	}


	public static UsuarioDTO convertEmDTO(UsuarioModel user) {
		// TODO Auto-generated method stub
		return new UsuarioDTO(user.getUsuario(),user.getPassword(),user.getEmail());
	}

	public static List<UsuarioDTO> convertEmListDTO(List<UsuarioModel> findAll) {
		
		return findAll.stream()
				.map(UsuarioDTO::new)
				.collect(Collectors.toList());		
	}	
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(String usuario, String password, String email) {
		this.usuario = usuario;
		this.password = password;
		this.email = email;
	}


}
