package br.com.meta.projetoapimeta.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.meta.projetoapimeta.domain.dtos.request.DepartmentModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.DepartmentModelResponse;

public interface DepartmentService {

	DepartmentModelResponse criar(DepartmentModelInputRequest request);

	Page<DepartmentModelResponse> findAll(Pageable pageable);

}
