package app.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaNaoEncontradoExecption  extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradoExecption(String string) {
		super(string);
	}

}