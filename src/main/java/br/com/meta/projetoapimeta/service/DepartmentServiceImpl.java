package br.com.meta.projetoapimeta.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.meta.projetoapimeta.model.request.DepartmentModelInputRequest;
import br.com.meta.projetoapimeta.model.response.DepartmentModelResponse;
import br.com.meta.projetoapimeta.persistence.entity.Department;
import br.com.meta.projetoapimeta.persistence.entity.Location;
import br.com.meta.projetoapimeta.persistence.repository.DepartmentRepository;
import br.com.meta.projetoapimeta.persistence.repository.LocationRepository;
import br.com.meta.projetoapimeta.service.exception.RegraNegocioException;
import br.com.meta.projetoapimeta.service.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final LocationRepository locationRepository;
	private final Mapper<DepartmentModelInputRequest, Department> departmentRequest;
	private final Mapper<Department, DepartmentModelResponse> departmentResponse;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository, LocationRepository locationRepository,
			Mapper<DepartmentModelInputRequest, Department> departmentRequest,
			Mapper<Department, DepartmentModelResponse> departmentResponse) {
		this.departmentRepository = departmentRepository;
		this.departmentRequest = departmentRequest;
		this.departmentResponse = departmentResponse;
		this.locationRepository = locationRepository;
	}

	@Override
	public DepartmentModelResponse criar(DepartmentModelInputRequest request) {
		log.info("Criando um Department {}", request);
		Assert.notNull(request, "Request inválida");

		Location location = this.locationRepository.findById(request.getLocation().getId())
				.orElseThrow(() -> new RegraNegocioException("Location não encontrado"));

		Department department = this.departmentRequest.map(request);
		department.setLocation(location);

		return this.departmentRepository.saveAndFlush(department)
				.map((Department input) -> this.departmentResponse.map(input));
	}

	@Override
	public Page<DepartmentModelResponse> findAll(Pageable pageable) {
		log.info("Buscar todos os Departments");
		Assert.notNull(pageable, "Página inválida");
		return this.departmentRepository.findAll(pageable).map(depart -> this.departmentResponse.map(depart));
	}

}
