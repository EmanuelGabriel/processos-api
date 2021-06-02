package br.com.meta.projetoapimeta.service.exception;

public class EntityNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNaoEncontradaException(String msg) {
		super(msg);
	}

}
