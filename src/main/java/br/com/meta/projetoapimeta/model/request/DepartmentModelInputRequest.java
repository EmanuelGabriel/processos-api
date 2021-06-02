package br.com.meta.projetoapimeta.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentModelInputRequest {

	@NotBlank(message = "Campo name não pode ser vazio")
	private String name;

	@Valid
	@NotNull(message = "Campo location não pode ser nulo")
	private LocationModelCodigoInputRequest location;

}
