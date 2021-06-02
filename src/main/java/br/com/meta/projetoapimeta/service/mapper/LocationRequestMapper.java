package br.com.meta.projetoapimeta.service.mapper;

import org.springframework.stereotype.Component;

import br.com.meta.projetoapimeta.model.request.LocationModelInputRequest;
import br.com.meta.projetoapimeta.persistence.entity.Location;

@Component
public class LocationRequestMapper implements Mapper<LocationModelInputRequest, Location> {

	@Override
	public Location map(LocationModelInputRequest input) {
		if (input == null) {
			return null;
		}

		Location location = new Location();
		location.setAddressStreet(input.getAddressStreet());
		location.setCity(input.getCity());
		location.setCodePostal(input.getCodePostal());
		location.setStateProvince(input.getStateProvince());
		
		return location;
	}

}
