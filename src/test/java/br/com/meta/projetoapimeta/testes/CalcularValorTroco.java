package br.com.meta.projetoapimeta.testes;

import java.util.Scanner;

public class CalcularValorTroco {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		Double valorProduto = teclado.nextDouble();
		
		System.out.println(valorProduto);
		teclado.close();

	}

}
