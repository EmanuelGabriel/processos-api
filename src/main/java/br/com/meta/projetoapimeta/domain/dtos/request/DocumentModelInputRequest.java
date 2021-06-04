package br.com.meta.projetoapimeta.domain.dtos.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class DocumentModelInputRequest {

	@NotBlank(message = "Nome do documento não pode ser vazio")
	private String nomeDocumento;

	@NotNull(message = "Número do processo não pode ser nulo")
	private Integer numeroProcesso;

	private LocalDate dataEstimadaConclusao;

}
