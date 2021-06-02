package br.com.meta.projetoapimeta.testes;

import java.util.ArrayList;

import br.com.meta.projetoapimeta.model.Aluno;
import br.com.meta.projetoapimeta.model.Turma;

public class ArrayListTeste {

	public static void main(String[] args) {

		Turma turma1 = new Turma(1L, "A01H1", "Turma A01H1");
		Turma turma2 = new Turma(2L, "B02H1", "Turma B02H1");
		Turma turma3 = new Turma(3L, "C01H1", "Turma C01H1");

		ArrayList<Aluno> aulas = new ArrayList<>();
		aulas.add(new Aluno(1L, "Fernando", turma1));
		aulas.add(new Aluno(2L, "Leandro", turma2));
		aulas.add(new Aluno(3L, "Zélia", turma1));
		aulas.add(new Aluno(4L, "Miriam", turma3));
		aulas.add(new Aluno(5L, "Eduardo", turma3));

		for (Aluno a : aulas) {
			System.out.println(a);
		}

		System.out.println("=========");
		Aluno l = aulas.get(3);
		System.out.println(l);

		System.out.println("================");

		aulas.remove(1);

		aulas.forEach(a -> System.out.println(a));

		System.out.println("================");
		aulas.forEach(a -> System.out.println(a));

		System.out.println();
		for (int i = 0; i < aulas.size(); i++) {
			System.out.println(aulas.get(i));
		}

		boolean s = aulas.isEmpty();
		System.out.println(s);

		// quanto elesmento dentro da lista
		int tamanho = aulas.size();
		System.out.println(tamanho);

		System.out.println();

		aulas.forEach(a -> {
			System.out.println("Percorrendo " + a);
			System.out.println("Turma/Descrição: " + a.getTurma().getDescricao());
			System.out.println("Turma/Sigla: " + a.getTurma().getSigla());
		});

	}

}
