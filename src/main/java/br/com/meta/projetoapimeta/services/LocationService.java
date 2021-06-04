package br.com.meta.projetoapimeta.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.meta.projetoapimeta.domain.dtos.request.LocationModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.LocationModelResponse;

public interface LocationService {

	LocationModelResponse criar(LocationModelInputRequest request);

	Page<LocationModelResponse> findAll(Pageable pageable);
}
