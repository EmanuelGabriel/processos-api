package br.com.meta.projetoapimeta.resources;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.meta.projetoapimeta.domain.dtos.request.LocationModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.LocationModelResponse;

public interface LocationResourceService {

	ResponseEntity<LocationModelResponse> criar(@Valid @RequestBody LocationModelInputRequest request);

	ResponseEntity<Page<LocationModelResponse>> findAll(Pageable pageable);

}
