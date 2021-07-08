package br.com.meta.projetoapimeta.domain.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoImageModelResponse {

	private Long id;
	private String nome;
	private String type;
	private byte[] image;
	private long tamanho;

}
