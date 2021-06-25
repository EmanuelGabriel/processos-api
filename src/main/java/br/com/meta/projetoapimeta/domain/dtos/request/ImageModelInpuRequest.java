package br.com.meta.projetoapimeta.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageModelInpuRequest {

	private Long id;
	private String nome;
	private String type;
	private byte[] image;
	private long tamanho;

	public ImageModelInpuRequest(String nome, String type, byte[] image, long tamanho) {
		this.nome = nome;
		this.type = type;
		this.image = image;
		this.tamanho = tamanho;
	}

}
