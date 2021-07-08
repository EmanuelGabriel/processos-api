package br.com.meta.projetoapimeta.config.exceptionHandler;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ProblemaResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@DateTimeFormat(iso = ISO.TIME)
	private LocalDateTime timestamp;
	private String tipo;
	private String titulo;
	private String mensagem;
	private List<Campos> campos;
	private String path;

	@Getter
	@Builder
	public static class Campos {

		private String nome;
		private String mensagem;

	}

}
