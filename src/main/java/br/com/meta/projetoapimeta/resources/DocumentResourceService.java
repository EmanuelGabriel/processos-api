package br.com.meta.projetoapimeta.resources;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.meta.projetoapimeta.domain.dtos.request.DocumentModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.DocumentModelResponse;

public interface DocumentResourceService {

	ResponseEntity<DocumentModelResponse> criar(@Valid @RequestBody DocumentModelInputRequest request);

	ResponseEntity<Page<DocumentModelResponse>> findAll(Pageable pageable);

	ResponseEntity<DocumentModelResponse> buscarPorId(@PathVariable Long idDocumento);

}
