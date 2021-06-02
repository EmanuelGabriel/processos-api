package br.com.meta.projetoapimeta.model;

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
public class Aluno {

	private Long id;
	private String nome;
	private Turma turma;
	

}
