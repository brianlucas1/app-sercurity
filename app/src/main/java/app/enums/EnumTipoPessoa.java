package app.enums;

import app.dto.interfaces.CnpjGroup;
import app.dto.interfaces.CpfGroup;

public enum EnumTipoPessoa {
	
	 FISICA("Física", "CPF","000.000.000-00", CpfGroup.class),
	 JURIDICA("Jurídica", "CNPJ", "000.000.000/0000-0", CnpjGroup.class);
	 
	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> group;	

	private EnumTipoPessoa(String descricao, String documento, String mascara, Class<?> group) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.group = group;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public String getMascara() {
		return mascara;
	}
	
	public Class<?> getGroup() {
		return group;
	}
	public void setGroup(Class<?> group) {
		this.group = group;
	}
	
	
	
    
}
