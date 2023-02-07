package app.model.util.formatador;

import app.enums.EnumTipoPessoa;

public class NrDocumentoFormatado  {
	   
	public String formatadorDeNrDocumento(String nrDocumento, EnumTipoPessoa tipoPessoa) {			
	
		nrDocumento = nrDocumento.replaceAll("\\D", ""); //Removo todos os caracteres não-numéricos
		
		    String mascara ="###.###.###-##";  		  
	   
			try {
				javax.swing.text.MaskFormatter formatadorNumero = new javax.swing.text.MaskFormatter(mascara);
				javax.swing.JFormattedTextField txtNumero = new javax.swing.JFormattedTextField(formatadorNumero);
				txtNumero.setText(nrDocumento);
				return txtNumero.getText();
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return "";
			}
	
	}
}
