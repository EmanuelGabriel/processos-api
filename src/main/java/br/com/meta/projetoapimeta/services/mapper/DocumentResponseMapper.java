package br.com.meta.projetoapimeta.services.mapper;

import org.springframework.stereotype.Component;

import br.com.meta.projetoapimeta.domain.dtos.response.DocumentModelResponse;
import br.com.meta.projetoapimeta.domain.entity.Document;
import br.com.meta.projetoapimeta.services.exception.EntityNaoEncontradaException;

@Component
public class DocumentResponseMapper implements Mapper<Document, DocumentModelResponse> {

	@Override
	public DocumentModelResponse map(Document input) {
		if (input == null) {
			throw new EntityNaoEncontradaException("Document não encontrado");
		}

		DocumentModelResponse response = new DocumentModelResponse();
		response.setId(input.getId());
		response.setNomeDocumento(input.getNomeDocumento());
		response.setNumeroProcesso(input.getNumeroProcesso());
		response.setDataEstimadaConclusao(input.getDataEstimadaConclusao());
		response.setDataCadastro(input.getDataCadastro());
		response.setSituacao(input.getSituacao());

		return response;
	}

}
