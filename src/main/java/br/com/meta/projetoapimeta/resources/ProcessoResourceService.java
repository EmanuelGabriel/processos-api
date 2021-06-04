package br.com.meta.projetoapimeta.resources;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.meta.projetoapimeta.domain.dtos.request.ProcessoModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.ProcessoModelResponse;

public interface ProcessoResourceService {

	ResponseEntity<ProcessoModelResponse> criar(@RequestBody ProcessoModelInputRequest request);
	
	ResponseEntity<Page<ProcessoModelResponse>> getAll();
	
	ResponseEntity<ProcessoModelResponse> buscarPorId(@PathVariable Long idProcesso);

}
