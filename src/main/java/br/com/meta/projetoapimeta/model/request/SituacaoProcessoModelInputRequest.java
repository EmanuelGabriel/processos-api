package br.com.meta.projetoapimeta.model.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SituacaoProcessoModelInputRequest {

	@NotBlank(message = "O campo descrição não pode ser vazio")
	private String descricao;

}
