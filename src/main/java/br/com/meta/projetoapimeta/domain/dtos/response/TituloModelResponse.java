package br.com.meta.projetoapimeta.domain.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TituloModelResponse {

	private Long id;
	private String nome;

}
