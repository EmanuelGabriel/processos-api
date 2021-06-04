package br.com.meta.projetoapimeta.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.meta.projetoapimeta.domain.dtos.request.LocationModelInputRequest;
import br.com.meta.projetoapimeta.domain.dtos.response.LocationModelResponse;
import br.com.meta.projetoapimeta.domain.entity.Location;
import br.com.meta.projetoapimeta.domain.repository.LocationRepository;
import br.com.meta.projetoapimeta.service.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocationServiceImpl implements LocationService {

	private final LocationRepository locationRepository;
	private final Mapper<LocationModelInputRequest, Location> locationRequestMapper;
	private final Mapper<Location, LocationModelResponse> locationResponseMapper;

	public LocationServiceImpl(LocationRepository locationRepository,
			Mapper<LocationModelInputRequest, Location> locationRequestMapper,
			Mapper<Location, LocationModelResponse> locationResponseMapper) {
		this.locationRepository = locationRepository;
		this.locationRequestMapper = locationRequestMapper;
		this.locationResponseMapper = locationResponseMapper;
	}

	@Override
	public LocationModelResponse criar(LocationModelInputRequest request) {
		log.info("Criando um Location {}", request);
		Assert.notNull(request, "Request inválida");
		Location location = this.locationRequestMapper.map(request);
		return this.locationRepository.saveAndFlush(location)
				.map((Location input) -> this.locationResponseMapper.map(input));
	}

	@Override
	public Page<LocationModelResponse> findAll(Pageable pageable) {
		log.info("Buscar todos os Locations");
		Assert.notNull(pageable, "Página inválida");
		return this.locationRepository.findAll(pageable).map(locations -> this.locationResponseMapper.map(locations));
	}

}
