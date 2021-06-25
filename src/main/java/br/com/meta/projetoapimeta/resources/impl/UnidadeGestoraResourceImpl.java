package br.com.meta.projetoapimeta.resources.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meta.projetoapimeta.domain.dtos.request.UnidadeGestoraModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.UnidadeGestoraModelResponse;
import br.com.meta.projetoapimeta.resources.UnidadeGestoraResourceService;
import br.com.meta.projetoapimeta.services.UnidadeGestoraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Tag(name = "Unidade Gestora", description = "Recurso de unidade gestora")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/ugs", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnidadeGestoraResourceImpl implements UnidadeGestoraResourceService {

	private UnidadeGestoraService unidadeGestoraService;

	@Override
	public ResponseEntity<UnidadeGestoraModelResponse> criar(UnidadeGestoraModelInputRequest request) {
		return null;
	}

	@Override
	public ResponseEntity<UnidadeGestoraModelResponse> buscarPorId(Long idUnidadeGestora) {
		log.info("GET /v1/unidades-gestora/{}", idUnidadeGestora);
		UnidadeGestoraModelResponse unidadeGestoraResponse = this.unidadeGestoraService.buscarPorID(idUnidadeGestora);
		return unidadeGestoraResponse != null ? ResponseEntity.ok(unidadeGestoraResponse)
				: ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Page<UnidadeGestoraModelResponse>> getAll(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		log.info("GET /v1/unidades-gestora page: {} - size: {}", page, size);
		Page<UnidadeGestoraModelResponse> pageUnidadeGestora = this.unidadeGestoraService.getAll(PageRequest.of(page, size, Sort.by("nome")));
		return pageUnidadeGestora != null ? ResponseEntity.ok(pageUnidadeGestora) : ResponseEntity.ok().build();
	}

}
