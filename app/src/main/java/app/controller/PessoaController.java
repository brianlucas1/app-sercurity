package app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.excpetion.PessoaNaoEncontradoExecption;
import app.model.PessoaModel;
import app.service.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins="*")
public class PessoaController {
	
	@Autowired
	private PessoaService pessService;
	
	@PostMapping("/save")
	public ResponseEntity<PessoaModel> saveUser(@RequestBody @Valid PessoaModel pessoa) {		
			
		PessoaModel userCriado = pessService.salvaPessoa(pessoa);
				
		return new ResponseEntity<PessoaModel>(userCriado, HttpStatus.CREATED);
	}	
	
	@GetMapping("/find/{id}")
	public ResponseEntity<PessoaModel> getUsuario(@PathVariable Integer id) throws PessoaNaoEncontradoExecption {
		
		PessoaModel user = pessService.findById(id);
		
		return new ResponseEntity<PessoaModel> (user,HttpStatus.OK);
	}
	
	@GetMapping("/find")
	public ResponseEntity <List<PessoaModel>> getAllUsers() {
		
		List<PessoaModel> users = pessService.findAllPess();
		
		return new ResponseEntity<List<PessoaModel>> (users,HttpStatus.OK);
	}

}
