package br.com.meta.projetoapimeta.resources;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.meta.projetoapimeta.domain.dtos.request.DepartmentModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.DepartmentModelResponse;

public interface DepartmentResourceService {

	ResponseEntity<DepartmentModelResponse> criar(@Valid @RequestBody DepartmentModelInputRequest request);

	ResponseEntity<Page<DepartmentModelResponse>> findAll(Pageable pageable);

}
