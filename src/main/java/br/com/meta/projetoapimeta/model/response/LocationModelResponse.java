package br.com.meta.projetoapimeta.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationModelResponse {

	private Long id;
	private String addressStreet;
	private double codePostal;
	private String city;
	private String stateProvince;

}
