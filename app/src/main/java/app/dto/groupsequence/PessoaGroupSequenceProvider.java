package app.dto.groupsequence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import app.model.PessoaModel;

public class PessoaGroupSequenceProvider implements DefaultGroupSequenceProvider<PessoaModel> {
	
	
	@Override
	public List<Class<?>> getValidationGroups(PessoaModel pessoa){
		List<Class<?>> groups = new ArrayList<>();
		groups.add(PessoaModel.class);
		
		if(isPessoaSelecionada(pessoa)) {
			groups.add(pessoa.getTipoPessoa().getGroup());
		}
		return groups;
	}

	private boolean isPessoaSelecionada(PessoaModel pessoa) {
		return pessoa != null && pessoa.getTipoPessoa() != null;
	}

}
