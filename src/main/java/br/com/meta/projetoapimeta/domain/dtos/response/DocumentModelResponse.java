package br.com.meta.projetoapimeta.domain.dtos.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.meta.projetoapimeta.domain.enumerator.TipoSituacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentModelResponse {

	private Long id;

	private String nomeDocumento;

	private Integer numeroProcesso;

	private LocalDate dataCadastro;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataEstimadaConclusao;

	private TipoSituacao situacao;

}
