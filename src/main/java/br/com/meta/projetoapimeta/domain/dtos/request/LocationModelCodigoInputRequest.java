package br.com.meta.projetoapimeta.domain.dtos.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationModelCodigoInputRequest {

	@NotNull
	private Long id;

}
