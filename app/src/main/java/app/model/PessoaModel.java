package app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import app.dto.groupsequence.PessoaGroupSequenceProvider;
import app.dto.interfaces.CnpjGroup;
import app.dto.interfaces.CpfGroup;
import app.enums.EnumTipoPessoa;
import app.model.util.formatador.NrDocumentoFormatado;

@Entity
@Table(name = "tb_pessoa")
@GroupSequenceProvider(PessoaGroupSequenceProvider.class)
public class PessoaModel  implements Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPessoa;

	@NotEmpty(message = "O campo nome não pode ser nulo ou vazio.")
	@Length(min = 5, message = "Preencher o campo nome com ao menos 5 caracteres.")
	@Column(name = "nome")
	private String nome;

	@Column(name = "tp_pessoa")
	@Enumerated(EnumType.STRING)
	private EnumTipoPessoa tipoPessoa;

	@NotEmpty(message = "O campo documento não pode ser nulo ou vazio.")
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	@Column(name = "nr_documento")
	private String nrDocumento;	
	
	@Email
	@Column(name = "email")
	private String email;		
	
	public PessoaModel() {
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnumTipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(EnumTipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	public String getnrDocumentoFormatado() {
		
		NrDocumentoFormatado formatador =  new NrDocumentoFormatado();
		
		String documento = formatador.formatadorDeNrDocumento(this.getNrDocumento(), this.tipoPessoa);
		
		return documento;
	}
	
	
}
