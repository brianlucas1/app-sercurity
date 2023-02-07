package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.PessoaModel;

public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {



	PessoaModel findByNrDocumento(String cpf);

}
