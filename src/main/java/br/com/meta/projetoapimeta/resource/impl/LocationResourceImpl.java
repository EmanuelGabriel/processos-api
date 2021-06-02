package br.com.meta.projetoapimeta.resource.impl;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meta.projetoapimeta.model.request.LocationModelInputRequest;
import br.com.meta.projetoapimeta.model.response.LocationModelResponse;
import br.com.meta.projetoapimeta.resource.LocationResourceService;
import br.com.meta.projetoapimeta.service.LocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Tag(name = "Localização", description = "Recurso de localizações")
@Slf4j
@RestController
@RequestMapping(value = "/v1/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationResourceImpl implements LocationResourceService {

	private final LocationService locationService;

	public LocationResourceImpl(LocationService locationService) {
		this.locationService = locationService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Override
	public ResponseEntity<LocationModelResponse> criar(@Valid LocationModelInputRequest request) {
		log.info("POST /v1/locations request recebida {}", request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idLocation}")
				.buildAndExpand(request.getCodePostal()).toUri();
		return ResponseEntity.created(location).body(this.locationService.criar(request));
	}

	@GetMapping
	@Override
	public ResponseEntity<Page<LocationModelResponse>> findAll(Pageable pageable) {
		log.info("GET /v1/locations request recebida para buscar todos os locations");
		Page<LocationModelResponse> locationModelResponse = this.locationService.findAll(pageable);
		return ResponseEntity.ok(locationModelResponse);
	}

}
