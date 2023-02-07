package app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import app.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer>{

	UsuarioModel findByUsuarioAndSenha(String usuario, String passowrd);

	Optional<UserDetails> findByEmail(String username);

}
