package br.com.meta.projetoapimeta.domain.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoProcessoModelResponse {

	private Long id;
	private String descricao;

}
