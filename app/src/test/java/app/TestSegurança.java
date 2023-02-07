package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.gson.Gson;

import app.builder.UsuarioBuilder;
import app.controller.UsuarioController;
import app.excpetion.PassowrdNotEqualsException;
import app.repository.UsuarioRepository;
import app.service.UsuarioService;

@SpringBootTest
public class TestSegurança {
	
	private static final String URL_BASE = "/api/usuario";
	
	private MockMvc mockMvc;
	
	@BeforeEach
	 void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usercontroller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

	@InjectMocks
	private UsuarioController usercontroller;
	
	
	@Mock
	private UsuarioRepository userRepo;
	
	@InjectMocks
	private UsuarioService userService;
	
	
	@Test
	public void createUsuarioSucess() throws Exception {
		
		Gson g = new Gson();
				
		 mockMvc.perform(post(URL_BASE+"/create/test")
		.content(g.toJson(UsuarioBuilder.usuarioCorreto().agora()))
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());					
	}
	

	
	@Test
	public void lancaExcessaoSenhasNaoIguais() throws Exception {
		
		Gson g = new Gson();		
		try {
			mockMvc.perform(post(URL_BASE+"/create/test")
					.content(g.toJson(UsuarioBuilder.usuarioComSenhaErrada().agora()))
					.contentType(MediaType.APPLICATION_JSON));							
		}catch (PassowrdNotEqualsException e) {			
			assertEquals("As senhas digitadas não são iguais.", e.getMessage());
		}
	}
	
	
	@Test
	public void createUsuarioComSenhaMenos5Caracteres() throws Exception {
		
		Gson g = new Gson();
				
		 mockMvc.perform(post(URL_BASE+"/create/test")
		.content(g.toJson(UsuarioBuilder.usuarioComSenhaMenosCaracteres().agora()))
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());					
	}
	
	
}
