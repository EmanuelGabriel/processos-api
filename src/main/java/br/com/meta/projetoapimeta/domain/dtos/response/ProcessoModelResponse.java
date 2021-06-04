package br.com.meta.projetoapimeta.domain.dtos.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoModelResponse {

	private Long id;

	private Integer numeroProtocolo;

	private LocalDateTime dataInicioProcesso;

	private LocalDateTime dataAtualizacaoProcesso;

	private Integer numeroProcessoSei;

	private LocalDateTime dataPrazoEstimado;

	private String cpf;

	private String faseProcessual;

	private SituacaoProcessoModelResponse situacaoProcesso;

}
