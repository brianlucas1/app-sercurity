package app.config.validacao.errors;

import java.time.LocalDateTime;

public class ErroCampos {
	
	private String campo;
	private String menssagem;
	private LocalDateTime data;
	private String erro;
	
	
	public ErroCampos(String campo, String menssagem, LocalDateTime data,String erro) {
		this.campo = campo;
		this.menssagem = menssagem;
		this.data = data;
		this.erro = erro;
	}


	public String getCampo() {
		return campo;
	}


	public String getMenssagem() {
		return menssagem;
	}


	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}


	public LocalDateTime getData() {
		return data;
	}


	public String getErro() {
		return erro;
	}




	
	
}
