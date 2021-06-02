package br.com.meta.projetoapimeta.testes;

public class VetorTeste {

	public static void main(String[] args) {

		int[] numeros = new int[10];
		numeros[0] = 1;
		numeros[1] = 10;
		numeros[2] = 290;
		numeros[3] = 33;
		numeros[4] = 89;
		numeros[5] = 5;

		// for antigo
		for (int i = 0; i < numeros.length; i++) {
			System.out.println("Posição " + i + " tem: " + numeros[i]);
		}


		System.out.println("Tamanho do vetor: " + numeros.length + " posições");
		System.out.println("Posição: " + numeros[2]);
		
		
		System.out.println("================VETOR==============");
		
		Integer[] n = new Integer[5];
		n[0] = 12;
		n[1] = 55;
		n[4] = 23;
		
		// novo for ou forEach
		for(int i = 0; i < n.length; i++) {
			System.out.println(n[i]);
		}
		
		for(int i = 0; i < 5; i++) {
			System.out.println(n[1]);
		}
		
		System.out.println("Tamanho do Vetor/Integer: " + n.length + " posições");
		System.out.println("Qual a posição? " + n[4]);
		
		

	}

}
