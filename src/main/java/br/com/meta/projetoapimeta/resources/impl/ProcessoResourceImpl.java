package br.com.meta.projetoapimeta.resources.impl;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meta.projetoapimeta.domain.dtos.request.ProcessoModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.ProcessoModelResponse;
import br.com.meta.projetoapimeta.resources.ProcessoResourceService;
import br.com.meta.projetoapimeta.services.ProcessoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Processos", description = "Recurso de processos")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/processos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProcessoResourceImpl implements ProcessoResourceService {

	private ProcessoService processoService;

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<ProcessoModelResponse> criar(@RequestBody ProcessoModelInputRequest request) {
		log.info("POST /v1/processos {}", request);
		ProcessoModelResponse response = this.processoService.criar(request);
		URI location = getUri(response.getId());
		return ResponseEntity.created(location).build();
	}

	@Override
	@GetMapping
	public ResponseEntity<Page<ProcessoModelResponse>> getAll() {
		log.info("GET /v1/processos");
		Page<ProcessoModelResponse> processos = this.processoService.getAll();
		return processos != null ? ResponseEntity.ok(processos) : ResponseEntity.ok().build();
	}

	@Override
	@GetMapping(value = "{idProcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProcessoModelResponse> buscarPorId(@PathVariable Long idProcesso) {
		log.info("GET /v1/processos/{} ", idProcesso);
		return ResponseEntity.ok(this.processoService.buscarPorId(idProcesso));
	}

	@GetMapping(value = "{numeroProtocolo}/protocolo")
	public ResponseEntity<ProcessoModelResponse> buscarProcessoNumeroProtocolo(@PathVariable Integer numeroProtocolo) {
		log.info("GET /v1/processos/{}/protocolo", numeroProtocolo);
		ProcessoModelResponse numeroProtocoloProcesso = this.processoService.buscarPorNumeroProtocolo(numeroProtocolo);
		return numeroProtocoloProcesso != null ? ResponseEntity.ok(numeroProtocoloProcesso)
				: ResponseEntity.notFound().build();
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

}
