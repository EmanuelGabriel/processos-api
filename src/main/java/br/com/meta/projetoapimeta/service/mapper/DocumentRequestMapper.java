package br.com.meta.projetoapimeta.service.mapper;

import org.springframework.stereotype.Component;

import br.com.meta.projetoapimeta.domain.dtos.request.DocumentModelInputRequest;
import br.com.meta.projetoapimeta.domain.entity.Document;
import br.com.meta.projetoapimeta.service.exception.EntityNaoEncontradaException;

@Component
public class DocumentRequestMapper implements Mapper<DocumentModelInputRequest, Document> {

	@Override
	public Document map(DocumentModelInputRequest input) {
		if (input == null) {
			throw new EntityNaoEncontradaException("Documento não encontrado");
		}

		Document document = new Document();
		document.setNomeDocumento(input.getNomeDocumento());
		document.setNumeroProcesso(input.getNumeroProcesso());

		return document;
	}

}
