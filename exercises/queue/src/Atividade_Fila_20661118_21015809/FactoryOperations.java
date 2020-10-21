package Atividade_Fila_20661118_21015809;

import java.util.Random;

public class FactoryOperations {
	public static IOperation getOperation(int ID) {
		switch (ID) {
		case 0:
			return new Saque();
		case 1:
			return new Deposito();
		case 2:
			return new Pagamento();
		default:
			return null;
		}
	}

	public static IOperation getOperation(String name) {
		if (name == null) {
			return null;
		}
		if (name.equalsIgnoreCase("SAQUE")) {
			return new Saque();

		} else if (name.equalsIgnoreCase("DEPOSITO")) {
			return new Deposito();

		} else if (name.equalsIgnoreCase("PAGAMENTO")) {
			return new Pagamento();
		}

		return null;
	}

	public static IOperation randomOperation() {
		Random rand = new Random();
		int  random = rand.nextInt(3);
		return getOperation(random);
	}
}
