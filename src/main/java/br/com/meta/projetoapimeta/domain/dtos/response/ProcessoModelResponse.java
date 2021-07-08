package br.com.meta.projetoapimeta.domain.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoModelResponse {

	private Long id;
	private Integer numeroProtocolo;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime dataInicioProcesso;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime dataAtualizacaoProcesso;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime dataPrazoEstimado;

	private Integer numeroProcessoSei;
	private String cpf;
	private String faseProcessual;
	private SituacaoProcessoModelResponse situacaoProcesso;
	private List<ImageModelResponse> imagensProcesso;

}
