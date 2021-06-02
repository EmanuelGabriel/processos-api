package br.com.meta.projetoapimeta.resource;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.meta.projetoapimeta.model.request.LocationModelInputRequest;
import br.com.meta.projetoapimeta.model.response.LocationModelResponse;

public interface LocationResourceService {

	ResponseEntity<LocationModelResponse> criar(@Valid @RequestBody LocationModelInputRequest request);

	ResponseEntity<Page<LocationModelResponse>> findAll(Pageable pageable);

}
