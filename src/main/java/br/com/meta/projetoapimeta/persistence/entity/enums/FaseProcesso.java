package br.com.meta.projetoapimeta.persistence.entity.enums;

import lombok.Getter;

@Getter
public enum FaseProcesso {
	
	INICIAL(1, "Inicial"),
	PENDENTE(2, "Pendente"),
	EM_PROCESSO(3, "Em processo"),
	FINALIZADO(4, "Finalizado");
	
	private Integer id;
	private String descricao;
	
	FaseProcesso(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	

}
