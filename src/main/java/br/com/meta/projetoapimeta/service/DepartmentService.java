package br.com.meta.projetoapimeta.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.meta.projetoapimeta.model.request.DepartmentModelInputRequest;
import br.com.meta.projetoapimeta.model.response.DepartmentModelResponse;

public interface DepartmentService {

	DepartmentModelResponse criar(DepartmentModelInputRequest request);

	Page<DepartmentModelResponse> findAll(Pageable pageable);

}
