/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ********** LEIA COM ATENÇÃO AS INSTRUÇÕES **********
//CRIAR UM PROJETO COM O NOME BUSCASEQUENCIALBINARIA_RAALUNO onde 
//RAALUNO é o RA do proprietário do exercício!!
package buscasequencialbinaria_21015809.shankarnakai;

import java.util.Arrays;
import java.lang.Exception;
import java.lang.Integer;

/**
 * @author SHANKAR NAKAI GONÇALVES DOS SANTOS
 */

public class BuscaSequencialBinaria_21015809 {
	public static boolean useANSI = false;
	public static boolean useHELP = false;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BOLD = "\033[0;1m";

	public static enum Method {
		Sequencial, BinariaIterativa, BinariaRecursiva
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			for (int i = 0; i < args.length; i += 1) {
				switch (args[i]) {
				case "--help":
				case "-h":
					useHELP = true;
					break;
				case "-a":
				case "--ANSI":
					useANSI = true;
					break;
				default:
					break;
				}
			}
		}

		if (useHELP) {
			System.out.println(
					"Usage: java buscasequencialbinaria_21015809.shankarnakai.BuscaSequencialBinaria_21015809 [OPTION]... \n");
			System.out.println("Exercício Busca Sequencial e Binária");
			System.out.println(
					"-   Implementa os 3 algoritmos contidos nos slides e utilizar os vetores DO ARQUIVO como massa de testes, há um vetor para cada exercício.");
			System.out.println(
					"-   Faz duas chamadas para cada método, uma em que encontra a posição do elemento e outra em que o elemento buscado não está presente na lista.");
			System.out.println("-   Imprime a resposta de todas as chamadas aos métodos.");

			System.out.println("\n");
			System.out.println("Opções que podem ser utilizadas :");
			System.out.println("-a, --ANSI    Mostra o output com cores ANSI.");
			System.out.println("-h, --help    Exibe mensagem de help da aplicação.");
			return;
		}

		TestBuscaSequencialSuccess();
		System.out.println("\n");
		TestBuscaSequencialFail();
		System.out.println("\n");

		TestBuscaBinariaIterativaSuccess();
		System.out.println("\n");
		TestBuscaBinariaIterativaFail();
		System.out.println("\n");

		TestBuscaBinariaRecursivaSuccess();
		System.out.println("\n");
		TestBuscaBinariaRecursivaFail();
	}

	public static int BuscaSequencial(int[] vetor, int valorBuscado) {
		for (int i = 0; i < vetor.length; i += 1) {
			if (vetor[i] == valorBuscado) {
				return i;
			}
		}
		return -1;
	}

	public static int BuscaBinariaIterativa(int[] vetor, int valorBuscado) {
		int meio = 0;
		int esq = 0;
		int dir = vetor.length - 1;
		while (esq <= dir) {
			meio = (esq + dir) / 2;
			if (valorBuscado == vetor[meio]) {
				return meio;
			}

			if (vetor[meio] < valorBuscado) {
				esq = meio + 1;
			} else {
				dir = meio - 1;
			}
		}

		return -1;
	}

	public static int BuscaBinariaRecursiva(int[] vetor, int valorBuscado, int esq, int dir) {
		if (esq > dir) {
			return -1;
		}

		int meio = (esq + dir) / 2;

		if (vetor[meio] == valorBuscado) {
			return meio;
		}

		if (vetor[meio] < valorBuscado) {
			return BuscaBinariaRecursiva(vetor, valorBuscado, esq + 1, meio);
		} else {
			return BuscaBinariaRecursiva(vetor, valorBuscado, meio, dir - 1);
		}
	}

	// ************************* TESTS ******************************
	public static void TestBuscaSequencialSuccess() {
		bold("********** INICIALIZANDO BUSCA SEQUENCIAL COM VALOR EXISTENTE *************");
		int[] vetorBuscaSequencial = { 10, 25, 75, 85, 1, -1, 61, 80 };
		int buscar = 61;
		int expectedPosicao = 6;
		assertBusca(vetorBuscaSequencial, buscar, expectedPosicao, Method.Sequencial);
		bold("********** FINALIZANDO BUSCA SEQUENCIAL COM VALOR EXISTENTE *************");
	}

	public static void TestBuscaSequencialFail() {
		bold("********** INICIALIZANDO BUSCA SEQUENCIAL COM VALOR INEXISTENTE *************");
		int[] vetorBuscaSequencial = { 10, 25, 75, 85, 1, -1, 61, 80 };
		int buscar = 100;
		int expectedPosicao = -1;
		assertBusca(vetorBuscaSequencial, buscar, expectedPosicao, Method.Sequencial);
		bold("********** FINALIZANDO BUSCA SEQUENCIAL COM VALOR INEXISTENTE *************");
	}

	public static void TestBuscaBinariaIterativaSuccess() {
		bold("********** INICIALIZANDO BUSCA BINARIA ITERATIVA COM VALOR EXISTENTE *************");
		int[] vetorBuscaBinariaIterativa = { 1, 2, 30, 41, 73, 81, 90, 101 };
		int buscar = 73;
		int expectedPosicao = 4;
		assertBusca(vetorBuscaBinariaIterativa, buscar, expectedPosicao, Method.BinariaIterativa);
		bold("********** FINALIZANDO BUSCA BINARIA ITERATIVA COM VALOR EXISTENTE *************");
	}

	public static void TestBuscaBinariaIterativaFail() {
		bold("********** INICIALIZANDO BUSCA BINARIA ITERATIVA COM VALOR INEXISTENTE *************");
		int[] vetorBuscaBinariaIterativa = { 1, 2, 30, 41, 73, 81, 90, 101 };
		int buscar = 100;
		int expectedPosicao = -1;
		assertBusca(vetorBuscaBinariaIterativa, buscar, expectedPosicao, Method.BinariaIterativa);
		bold("********** FINALIZANDO BUSCA BINARIA ITERATIVA COM VALOR INEXISTENTE *************");
	}

	public static void TestBuscaBinariaRecursivaSuccess() {
		bold("********** INICIALIZANDO BUSCA BINARIA RECURSIVA COM VALOR EXISTENTE *************");
		int[] vetorBuscaBinariaRecursiva = { 10, 25, 35, 40, 70, 80, 95, 101 };
		int buscar = 40;
		int expectedPosicao = 3;
		assertBusca(vetorBuscaBinariaRecursiva, buscar, expectedPosicao, Method.BinariaRecursiva);
		bold("********** FINALIZANDO BUSCA BINARIA RECURSIVA COM VALOR EXISTENTE *************");
	}

	public static void TestBuscaBinariaRecursivaFail() {
		bold("********** INICIALIZANDO BUSCA BINARIA RECURSIVA COM VALOR INEXISTENTE *************");
		int[] vetorBuscaBinariaRecursiva = { 10, 25, 35, 40, 70, 80, 95, 101 };
		int buscar = 100;
		int expectedPosicao = -1;
		assertBusca(vetorBuscaBinariaRecursiva, buscar, expectedPosicao, Method.BinariaRecursiva);
		bold("********** FINALIZANDO BUSCA BINARIA RECURSIVA COM VALOR INEXISTENTE *************");
	}

	// *************************** ASSERTS *****************************
	public static void assertBusca(int[] vetor, int buscar, int expected, Method method) {
		bold("Vetor utilizado para teste ", Arrays.toString(vetor));
		bold("Valor a ser buscado        ", Integer.toString(buscar));

		int posicao = 0;
		try {
			posicao = Busca(vetor, buscar, method);
		} catch (Exception e) {
			fail(String.format("Erro inesperado %s", e));
			return;
		}

		if (posicao == expected) {
			if (posicao == -1) {
				success(String.format("Valor %d não encontrado como esperado", buscar));
			} else {
				success(String.format("Valor %d encontrado com sucesso na posição %d", buscar, posicao));
			}
		} else {
			fail(String.format("Posicao %d inesperada, posicao espera %d", posicao, expected));
		}
	}

	public static int Busca(int[] vetor, int buscar, Method method) throws Exception {
		if (vetor.length == 0) {
			return -1;
		}

		switch (method) {
		case Sequencial:
			return BuscaSequencial(vetor, buscar);

		case BinariaIterativa:
			return BuscaSequencial(vetor, buscar);

		case BinariaRecursiva:
			return BuscaSequencial(vetor, buscar);

		default:
			throw new Exception("Method inválido, por favor use um dos methodos disponíveis.");
		}
	}

	// ************* HELPER **********
	public static void bold(String message) {
		if (useANSI) {
			System.out.println(ANSI_BOLD + ANSI_CYAN + message + ANSI_RESET);
		} else {
			System.out.println(message);
		}
	}

	public static void bold(String title, String message) {
		if (useANSI) {
			System.out.println(ANSI_BOLD + title + ANSI_RESET + " : " + message);
		} else {
			System.out.println(title + " : " + message);
		}
	}

	public static void success(String message) {
		if (useANSI) {
			System.out.println(ANSI_GREEN + message + ANSI_RESET);
		} else {
			System.out.println(message);
		}
	}

	public static void fail(String message) {
		if (useANSI) {
			System.out.println(ANSI_RED + message + ANSI_RESET);
		} else {
			System.out.println(message);
		}
	}
}