package app.dto;

import java.util.List;
import java.util.stream.Collectors;

import app.enums.EnumTipoPessoa;
import app.model.PessoaModel;


public class PessoaModelDTO {
	
	
	private String nome;	
		
	private EnumTipoPessoa tipoPessoa;
		
	private String nrDocumento;	
	
	private String email;
	
	public PessoaModelDTO(PessoaModel pessoa) {
		this.nome = pessoa.getNome();
		this.nrDocumento = pessoa.getNrDocumento();
		this.tipoPessoa = pessoa.getTipoPessoa();
		this.email = pessoa.getEmail();
	}
	
	public PessoaModelDTO() {
		
	}
		
	public EnumTipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}


	public String getNrDocumento() {
		return nrDocumento;
	}

	public static PessoaModelDTO converterEmDTO(PessoaModel pess) {

		return new PessoaModelDTO(pess);
	}

	public static List<PessoaModelDTO> converterListaEmDTO(List<PessoaModel> listPessoa) {
		
		return listPessoa.stream()
				.map(PessoaModelDTO::new)
				.collect(Collectors.toList());
	}

}
