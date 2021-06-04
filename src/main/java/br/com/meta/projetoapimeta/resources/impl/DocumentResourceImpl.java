package br.com.meta.projetoapimeta.resources.impl;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meta.projetoapimeta.domain.dtos.request.DocumentModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.DocumentModelResponse;
import br.com.meta.projetoapimeta.resources.DocumentResourceService;
import br.com.meta.projetoapimeta.services.DocumentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Tag(name = "Documentos", description = "Recurso de documentos")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/documentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentResourceImpl implements DocumentResourceService {

	private DocumentService documentService;

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DocumentModelResponse> criar(@Valid @RequestBody DocumentModelInputRequest request) {
		log.info("POST /v1/documentos {}", request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idDocument}")
				.buildAndExpand(request.getNomeDocumento()).toUri();
		return ResponseEntity.created(location).body(this.documentService.criar(request));
	}

	@Override
	@GetMapping
	public ResponseEntity<Page<DocumentModelResponse>> findAll(Pageable pageable) {
		log.info("GET /v1/documentos");
		Page<DocumentModelResponse> pageDocumentModelResponse = this.documentService.findAll(pageable);
		return ResponseEntity.ok(pageDocumentModelResponse);
	}

	@Override
	@GetMapping(value = "{idDocumento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocumentModelResponse> buscarPorId(@PathVariable Long idDocumento) {
		log.info("GET /v1/documentos/{}", idDocumento);
		Optional<DocumentModelResponse> documentoResponse = this.documentService.buscarPorId(idDocumento);
		return documentoResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping(value = "{numeroProcesso}/processo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocumentModelResponse> buscarPorNumeroProcesso(@PathVariable Integer numeroProcesso) {
		log.info("GET /v1/documentos/{}/processo", numeroProcesso);
		Optional<DocumentModelResponse> documentoOpt = this.documentService.buscarPorNumeroProcesso(numeroProcesso);
		return documentoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping(value = "buscarPorNome", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocumentModelResponse> buscarPorNomeDocumento(
			@RequestParam(value = "nomeDocumento", required = true) String nomeDocumento) {
		log.info("GET /v1/documentos/{}/buscarPorNome", nomeDocumento);
		Optional<DocumentModelResponse> documentoOpt = this.documentService.buscarPorNomeDocumento(nomeDocumento);
		return documentoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping(value = "buscarPorDataConclusao", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DocumentModelResponse>> buscarPorDocumentoPorDataConclusao(
			@RequestParam(value = "dataEstimadaConclusaoInicio", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataEstimadaConclusaoInicio,
			@RequestParam(value = "dataEstimadaConclusaoFim", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataEstimadaConclusaoFim) {
		log.info("GET /v1/documentos/{}/{}/buscarPorDataConclusao", dataEstimadaConclusaoInicio,
				dataEstimadaConclusaoFim);

		List<DocumentModelResponse> documentos = this.documentService
				.buscarPorDataEstimadaConclusao(dataEstimadaConclusaoInicio, dataEstimadaConclusaoFim);
		return documentos != null ? ResponseEntity.ok(documentos) : ResponseEntity.notFound().build();

	}

	@GetMapping(value = "buscarPorDataCadastro", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DocumentModelResponse>> buscarPorDocumentoPorDataCadastro(
			@RequestParam(value = "dataCadastroInicio", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataCadastroInicio,
			@RequestParam(value = "dataCadastroFinal", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataCadastroFinal) {
		log.info("GET /v1/documentos/{}/{}/buscarPorDataCadastro", dataCadastroInicio, dataCadastroFinal);

		List<DocumentModelResponse> documentos = this.documentService.buscarPorDataCadastro(dataCadastroInicio,
				dataCadastroFinal);
		return documentos != null ? ResponseEntity.ok(documentos) : ResponseEntity.notFound().build();

	}

}
