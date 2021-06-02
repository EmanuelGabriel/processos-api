package br.com.meta.projetoapimeta.testes;

import java.util.ArrayList;
import java.util.List;

import br.com.meta.projetoapimeta.model.Aluno;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestPrincipal02 {

	public static void main(String[] args) {

		List<String> carros = new ArrayList<>();
		carros.add("Toyota");
		carros.add("Fiat");
		carros.add("Chevrolet");
		carros.add("Qualquer");

		carros.forEach(c -> log.info(c));

		carros.add("Veiculo");

		System.out.println();
		carros.forEach(c -> System.out.println(c));

		boolean simOuNao = carros.contains("Fiats");
		System.out.println(simOuNao);

		Aluno fernando = new Aluno();
		fernando.setId(1L);
		fernando.setNome("Fernando da Silva");

		Aluno pedro = new Aluno();
		pedro.setId(2L);
		pedro.setNome("Pedro Nunes Alves");

		Aluno[] alunos = new Aluno[2];
		alunos[0] = fernando;
		alunos[1] = pedro;

		for (int i = 0; i < alunos.length; i++) {
			System.out.println("Aluno: " + alunos[i]);
		}
	}

}
