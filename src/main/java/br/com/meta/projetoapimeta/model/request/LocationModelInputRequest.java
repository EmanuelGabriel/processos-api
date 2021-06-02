package br.com.meta.projetoapimeta.model.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationModelInputRequest {

	@NotBlank(message = "Campo addressStreet não pode ser vazio")
	private String addressStreet;

	private double codePostal;

	@NotBlank(message = "Campo city não pode ser vazio")
	private String city;

	@NotBlank(message = "Campo stateProvince não pode ser vazio")
	private String stateProvince;

}
