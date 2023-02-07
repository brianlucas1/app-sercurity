package app.config.validacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.config.validacao.errors.ErroCampos;
import app.excpetion.PassowrdNotEqualsException;

@RestControllerAdvice
public class ErroBeanValidationHandler {
	
	@Autowired
	private MessageSource messageSoruce;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroCampos> handle(MethodArgumentNotValidException exception) {
		
		List<ErroCampos> dto = new ArrayList<ErroCampos>();
		
		List<FieldError> fieldsErrors = exception.getBindingResult().getFieldErrors();
		
		fieldsErrors.forEach(e -> {
			String menssagem = messageSoruce.getMessage(e, LocaleContextHolder.getLocale());
			ErroCampos erro = new ErroCampos
							(e.getField(),
							menssagem,
							LocalDateTime.now(),
							exception.getClass().getName()
							);
					dto.add(erro);
					});
		
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PassowrdNotEqualsException.class)
	public List<ErroCampos> handle(PassowrdNotEqualsException exception) {
		
		List<ErroCampos> dto = new ArrayList<ErroCampos>();
		
			ErroCampos erro = new ErroCampos
							("passwordConfirm",
							exception.getMessage(),
							LocalDateTime.now(),
							exception.getClass().getName()
							);
					dto.add(erro);
					
		
		
		return dto;
	}
	
}
