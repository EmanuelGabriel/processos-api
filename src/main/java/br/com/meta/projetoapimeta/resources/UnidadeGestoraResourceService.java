package br.com.meta.projetoapimeta.resources;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.meta.projetoapimeta.domain.dtos.request.UnidadeGestoraModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.UnidadeGestoraModelResponse;

public interface UnidadeGestoraResourceService {

	@PostMapping
	ResponseEntity<UnidadeGestoraModelResponse> criar(@RequestBody UnidadeGestoraModelInputRequest request);

	@GetMapping
	ResponseEntity<Page<UnidadeGestoraModelResponse>> getAll(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size);

	@GetMapping(value = "{idUnidadeGestora}")
	ResponseEntity<UnidadeGestoraModelResponse> buscarPorId(@PathVariable Long idUnidadeGestora);

}
