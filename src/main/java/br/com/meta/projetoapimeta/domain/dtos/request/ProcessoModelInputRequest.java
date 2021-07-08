package br.com.meta.projetoapimeta.domain.dtos.request;

import br.com.meta.projetoapimeta.domain.dtos.response.SituacaoProcessoModelCodigoResponse;
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
public class ProcessoModelInputRequest {

	private Integer numeroProtocolo;

	private Integer numeroProcessoSei;

	private String cpf;

	private SituacaoProcessoModelCodigoResponse situacaoProcesso;

	private ImageModelCodigoRequest imagem;

}
