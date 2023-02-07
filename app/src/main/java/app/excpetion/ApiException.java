package app.excpetion;

import java.util.Arrays;
import java.util.List;

public class ApiException {

	private List<String> erros;
	
	public ApiException(List<String> erros){
		this.erros = erros;
	}
	
	public ApiException(String erros){
		this.erros = Arrays.asList(erros);
	}

	public List<String> getErros() {
		return erros;
	}
	
	
	
}
