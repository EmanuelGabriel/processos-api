package br.com.meta.projetoapimeta.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.meta.projetoapimeta.domain.dtos.response.DocumentModelResponse;
import br.com.meta.projetoapimeta.domain.entity.Document;

@Component
public class DocumentMapper {

	@Autowired
	private ModelMapper modelMapper;

	public DocumentModelResponse entityToDTO(Document entity) {
		return this.modelMapper.map(entity, DocumentModelResponse.class);
	}

	public Document dtoToEntity(DocumentModelResponse dto) {
		return this.modelMapper.map(dto, Document.class);
	}

	public List<DocumentModelResponse> listEntityToDTO(List<Document> documentos) {
		return documentos.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

}
