package atividade_pilha_21015809_20661118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * ATIVIDADE DE ESTRUTURA DE DADOS Conversão de Expressão Infix para Pós-Fixa
 * 
 * @author Shankar Nakai Gonçalves dos Santos RA : 21015809
 * @author Jonas Felipe da Silva RA : 20661118
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		System.out.print("Insira a sua expressão aqui:  ");
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String input;
		try {
			input = buffer.readLine();
		} catch (IOException e) {
			System.out.println(
					"Houve um erro inesperado ao tentar processar a sua entrada, por favor tente rodar o programa novamente \n Error : "
							+ e.getMessage());
			return;
		}

		if (input.length() == 0) {
			System.out.println("Sua expressão esta vazia, infelizmente não vai ser possível processa-la");
			return;
		}

		input = input.replaceAll("[.:,\\s]", "");

		if (!isValid(input)) {
			System.out.println("Sua expressão é inválida, por favor verificar se ela está nos padrões corretos");
			return;
		}

		// monta a pilha
		Pilha main = inverse(toPostFix(input));
		main.display();
	}

	/**
	 * Transforma um expressão InFixa para PosFixa 
	 *
	 * @param String 
	 * @return Pilha returna uma pilha contendo os valores da expressão em modo PosFix 
	 */
	public static Pilha toPostFix(String input) {
		if (input.length() == 0) {
			return new Pilha();
		}

		Pilha main = new Pilha(); // Pilha que irá conter o formato final
		Pilha sinais = new Pilha(); // irá empilhar apenas sinais

		// irá empilhar prioritariamente os
		// números, sendo empilhado os sinais a
		// medida que for necessário devido a
		// sua prioridade na operação
		Pilha numbers = new Pilha();

		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			char next = ' ';

			if (i + 1 < input.length()) {
				next = input.charAt(i + 1);
			}

			if (isOpenWrapper(current) || (isSinal(current) && isOpenWrapper(next))) {
				String newInput = "";

				boolean prioritySinal = isPrioritySinal(current);
				if (isSinal(current)) {
					sinais.push(current);
					i += 1;
				}

				current = input.charAt(i);
				while (isOpenWrapper(current)) {
					i += 1;
					current = input.charAt(i);
				}

				while (!isCloseWrapper(current) && i < input.length()) {
					newInput += current;
					i++;
					current = input.charAt(i);
				}
				Pilha aux = toPostFix(newInput);

				aux = aux.inverse();
				while (!aux.isEmpty()) {
					numbers.push(aux.pop());
				}

				if (prioritySinal) {
					while (!sinais.isEmpty()) {
						numbers.push(sinais.pop());
					}
				}
			} else if (isSinal(current)) {
				sinais.push(current);
				if (next != ' ') {
					i += 1;
					numbers.push(next);
				}

				if (isPrioritySinal(current)) {
					while (!sinais.isEmpty()) {
						numbers.push(sinais.pop());
					}
				}
			} else if (!isCloseWrapper(current)) {
				numbers.push(current);
			}
		}

		// insere na fila de numeros os sinais restantes
		while (!sinais.isEmpty()) {
			numbers.push(sinais.pop());
		}

		// inverte os numeros para que quando eles sejam colocados na pilha main
		// eles entrem na ordem correta
		numbers = inverse(numbers);
		while (!numbers.isEmpty()) {
			main.push(numbers.pop());
		}

		return main;
	}

	/**
	 * Inverte os valores de uma Pilha
	 *
	 * @param pilha
	 * @return Pilha returna uma pilha com os valores na ordem inversa
	 */
	public static Pilha inverse(Pilha pilha) {
		if (pilha.isEmpty()) {
			return new Pilha();
		}

		NodeChar atual = pilha.topo;
		Pilha inverse = new Pilha();
		while (atual != null) {
			inverse.push(atual.getValue());
			atual = atual.getNext();
		}

		return inverse;
	}

	/**
	 * Realiza uma simples verificação para validar se existindo parenteses ele
	 * sempre abre e fecha corretamente
	 *
	 * @param String
	 * @return boolean true em caso de expressão valida, false em caso contrário 
	 */
	public static boolean isValid(String exp) {
		// monta a pilha
		Pilha pilha = new Pilha();

		// verifica o conteúdo
		for (int i = 0; i < exp.length(); i += 1) {
			char value = exp.charAt(i);

			if ("{([".contains("" + value)) {
				pilha.push(value);
			} else if ("}])".contains("" + value)) {
				char top = pilha.pop();
				boolean valid = ((top == '{' && value == '}') || (top == '(' && value == ')')
						|| (top == '[' && value == ']'));
				if (!valid) {
					return false;
				}
			}
		}

		// se true ficou algo sem fechar
		if (!pilha.isEmpty()) {
			return false;
		}

		return true;
	}


	/**
	 * Verifica se existe algum caracter de abertura de expressão 
	 *
	 * @param char 
	 * @return boolean true caso seja um caracter de abertura de expressão , false em caso contrário 
	 */
	public static boolean isOpenWrapper(char str) {
		return "({[".contains(str + "");
	}


	/**
	 * Verifica se existe algum caracter de fechamento de expressão 
	 *
	 * @param char 
	 * @return boolean true caso seja um caracter de fechameto de expressão, false em caso contrário 
	 */
	public static boolean isCloseWrapper(char str) {
		return "]})".contains(str + "");
	}

	/**
	 * Verifica se é um operator matemático 
	 *
	 * @param char 
	 * @return boolean true caso seja um operator matemático, false em caso contrário 
	 */
	public static boolean isSinal(char str) {
		return "+-*/^".contains(str + "");
	}

	/**
	 * Verifica se é um operador matemático que seja prioritária, tal como *(multiplicação) /(divisão) ^(exponencial) 
	 *
	 * @param char 
	 * @return boolean true caso seja um operator matemático, false em caso contrário 
	 */
	public static boolean isPrioritySinal(char str) {
		return "*/^".contains(str + "");
	}
}
