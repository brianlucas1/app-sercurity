package app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import app.excpetion.PassowrdNotEqualsException;
import app.excpetion.SenhaNullExecption;

@Entity
@Table(name = "tb_usuario")
public class UsuarioModel implements UserDetails{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idUsuario;
	
	@Column	
	@NotEmpty(message = "O campo usuario não pode ser nulo ou vazio.")
	@Length(min = 5, message = "Preencher o campo com ao menos 5 caracteres")
	private String usuario;
	
	@Column	
	@NotEmpty(message = "O campo senha não pode ser nulo ou vazio.")
	@Length(min = 5, message = "Preencher o senha com ao menos 5 caracteres")
	private String senha;	
	
	@Column
	@NotEmpty(message = "O campo passwordConfirm não pode ser nulo ou vazio.")
	@Length(min = 5, message = "Preencher o passwordConfirm com ao menos 5 caracteres")
	private String passwordConfirm;
	
	@Column	
	@NotEmpty(message = "O campo e-mail não pode ser nulo ou vazio.")
	@Email
	private String email;	
	
	//JÁ CARREGA O PERFIL ASSIM QUE CARREGAR O USUARIO
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfils = new ArrayList<Perfil>();
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) throws PassowrdNotEqualsException, SenhaNullExecption {
		
		if(!isNull(passwordConfirm,this.senha)) {			
			if(this.senha.equals(passwordConfirm)) {
				this.passwordConfirm = passwordConfirm;
			}else{
				throw new PassowrdNotEqualsException("As senhas digitadas não são iguais.");
			}
		}
		
	}

	private boolean isNull(String passwordConfirm, String passoword) throws SenhaNullExecption {
		
		if(passwordConfirm == null || passoword == null) {
			throw new SenhaNullExecption("Preencher o campo passoword ou passordConfirm.");
		}

		return false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public UsuarioModel() {		
	}

	public UsuarioModel(Integer idUsuario,
			@NotEmpty(message = "O campo usuario não pode ser nulo ou vazio.") @Length(min = 5, message = "Preencher o campo com ao menos 5 caracteres") String usuario,
			@NotEmpty(message = "O campo password não pode ser nulo ou vazio.") @Length(min = 5, message = "Preencher o password com ao menos 5 caracteres") String password,
			@NotEmpty(message = "O campo passwordConfirm não pode ser nulo ou vazio.") @Length(min = 5, message = "Preencher o passwordConfirm com ao menos 5 caracteres") String passwordConfirm,
			@NotEmpty(message = "O campo e-mail não pode ser nulo ou vazio.") @Email String email) {
		super();
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.passwordConfirm = passwordConfirm;
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.perfils;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}
	
	
	
}
