package br.com.meta.projetoapimeta.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.meta.projetoapimeta.model.request.LocationModelInputRequest;
import br.com.meta.projetoapimeta.model.response.LocationModelResponse;

public interface LocationService {

	LocationModelResponse criar(LocationModelInputRequest request);

	Page<LocationModelResponse> findAll(Pageable pageable);
}
