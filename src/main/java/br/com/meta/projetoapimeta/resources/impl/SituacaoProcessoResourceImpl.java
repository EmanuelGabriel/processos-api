package br.com.meta.projetoapimeta.resources.impl;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meta.projetoapimeta.domain.dtos.request.SituacaoProcessoModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.SituacaoProcessoModelResponse;
import br.com.meta.projetoapimeta.services.SituacaoProcessoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Tag(name = "Situação do Processo", description = "Recurso de situação do processo")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/situacao-processo", produces = MediaType.APPLICATION_JSON_VALUE)
public class SituacaoProcessoResourceImpl {

	private SituacaoProcessoService situacaoProcessoService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<SituacaoProcessoModelResponse> criar(@Valid @RequestBody SituacaoProcessoModelInputRequest request) {
		log.info("POST /v1/situacao-processo {}", request);
		SituacaoProcessoModelResponse response = this.situacaoProcessoService.criar(request);
		URI location = getUri(response.getId());
		return ResponseEntity.created(location).build();
	}

	@GetMapping
	public ResponseEntity<Page<SituacaoProcessoModelResponse>> getAll(Pageable pageable) {
		log.info("GET /v1/situacao-processo {}", pageable);
		Page<SituacaoProcessoModelResponse> pageSituacao = this.situacaoProcessoService.getAll(pageable);
		return pageSituacao != null ? ResponseEntity.ok(pageSituacao) : ResponseEntity.ok().build();
	}

	@GetMapping(value = "{idSituacao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SituacaoProcessoModelResponse> buscarPorId(@PathVariable Long idSituacao) {
		log.info("GET /v1/situacao-processo/{}", idSituacao);
		SituacaoProcessoModelResponse situacao = this.situacaoProcessoService.buscarPorId(idSituacao);
		return situacao != null ? ResponseEntity.ok(situacao) : ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "{idSituacao}")
	public ResponseEntity<Void> remover(@PathVariable Long idSituacao) {
		log.info("DELETE /v1/situacao-processo/{}", idSituacao);
		this.situacaoProcessoService.remover(idSituacao);
		return ResponseEntity.noContent().build();
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

}
