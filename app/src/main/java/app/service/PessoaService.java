package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.enums.EnumTipoPessoa;
import app.excpetion.PessoaNaoEncontradoExecption;
import app.model.PessoaModel;
import app.repository.PessoaRepository;

@Service
public class PessoaService {

	
	@Autowired
	private PessoaRepository pessRepo;

	public PessoaModel salvaPessoa(PessoaModel pessoaModel) {
		
		PessoaModel pess = findPessoaPorNrDocumento(pessoaModel.getNrDocumento());
			
		if(pess !=null) {
			return pess;
		}else {
			return pessRepo.save(pessoaModel);
		}
		
		
	}

	private PessoaModel findPessoaPorNrDocumento(String cpf) {
		// TODO Auto-generated method stub
		return pessRepo.findByNrDocumento(cpf);
	}

	public PessoaModel findById(Integer id) throws PessoaNaoEncontradoExecption {
		PessoaModel pessoa  = pessRepo	
				.findById(id)
				.orElseThrow( () ->  new PessoaNaoEncontradoExecption("No find any Course with this Id"));		
	return pessoa;
}

	public List<PessoaModel> findAllPess() {
		// TODO Auto-generated method stub
		return pessRepo.findAll();
	}


	
}
