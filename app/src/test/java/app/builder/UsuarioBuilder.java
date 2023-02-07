package app.builder;

import app.excpetion.PassowrdNotEqualsException;
import app.excpetion.SenhaNullExecption;
import app.model.UsuarioModel;

public class UsuarioBuilder {
	
	private UsuarioModel user;
	
	private UsuarioBuilder(){		
	}
	
	public static UsuarioBuilder usuarioCorreto() throws PassowrdNotEqualsException, SenhaNullExecption {
		UsuarioBuilder builder = new UsuarioBuilder();
		
		builder.user = new UsuarioModel();
		builder.user.setEmail("a@a.com");
		builder.user.setSenha("abcdef");
		builder.user.setPasswordConfirm("abcdef");
		builder.user.setUsuario("ggggggggggg");		
		return builder;
	}
		
	public static UsuarioBuilder usuarioComSenhaErrada() throws PassowrdNotEqualsException, SenhaNullExecption {
		UsuarioBuilder builder = new UsuarioBuilder();
		
		builder.user = new UsuarioModel();
		builder.user.setEmail("a@a.com");
		builder.user.setSenha("1");
		builder.user.setPasswordConfirm("2");
		builder.user.setUsuario("ggggggggggg");		
		return builder;
	}
	
	public static UsuarioBuilder usuarioComSenhaMenosCaracteres() throws PassowrdNotEqualsException, SenhaNullExecption {
		UsuarioBuilder builder = new UsuarioBuilder();
		
		builder.user = new UsuarioModel();
		builder.user.setEmail("a@a.com");
		builder.user.setSenha("1");
		builder.user.setPasswordConfirm("1");
		builder.user.setUsuario("ggggggggggg");		
		return builder;
	}
	
	public UsuarioModel agora(){
		return user;
	}

}
