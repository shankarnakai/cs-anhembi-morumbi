package Atividade_Fila_20661118_21015809;

import java.util.Random;

public class Atividade_Fila_20661118_21015809 {

	public static void main(String[] args) {
		int qtdsaques = 0;
		int qtddepositos = 0;
		int qtdpagamentos = 0;

		int cronometro = 0;
		int maxSpentTime = 21600;
		int maxMesas = 3;
		int spentTimeFila = 0;

		Gliche[] gliches = new Gliche[3];

		FilaClient fila = new FilaClient();
		FilaClient atendidos = new FilaClient();

		for (int mesa = 0; mesa < maxMesas; mesa += 1) {
			if (gliches[mesa] == null) {
				gliches[mesa] = new Gliche();
			}
		}

		System.out.println("Iniciando o dia no banco...");

		// Condição para sair é : fila deve estar vazia, o horario do banco
		// acabou e todas as mesas estão vazias
		while (!fila.isEmpty() || cronometro < maxSpentTime || !isAllMesasFree(gliches)) {
			if (isArriveClient() && (cronometro < maxSpentTime)) {
				Client client = new Client(cronometro); // set the entry time
				// sort the Operation to client
				client.setOperation(FactoryOperations.randomOperation());
				fila.Enqueue(client);// put client in queue
			}

			for (int mesa = 0; mesa < maxMesas; mesa += 1) {
				if (gliches[mesa].inUse() && (cronometro >= gliches[mesa].expectedEndTime())) {
					atendidos.Enqueue(gliches[mesa].getClient());
					gliches[mesa].free(cronometro);
				}

				if (!gliches[mesa].inUse() && !fila.isEmpty()) {
					Client client = fila.Dequeue();
					gliches[mesa].setClient(cronometro, client);

					// getValue return the entry time in seconds
					spentTimeFila += cronometro - client.getValue();

					String operation = client.getOperation().getName();

					switch (operation) {
					case "Deposito":
						qtddepositos += 1;
						break;
					case "Saque":
						qtdsaques += 1;
						break;
					case "Pagamento":
						qtdpagamentos += 1;
						break;
					}
				}
			}

			cronometro += 1;
		}

		int tempoMediaFila = (spentTimeFila / atendidos.Size());
		int horaExtras = (cronometro - maxSpentTime);

		System.out.println("Número total de clientes atendidos : " + atendidos.Size());
		System.out.println("Número de clientes que fizeram saque : " + qtdsaques);
		System.out.println("Número de clientes que fizeram depósito : " + qtddepositos);
		System.out.println("Número de clientes que fizeram pagamento : " + qtdpagamentos);
		System.out.println("Tempo médio de espera na fila : " + toTime(tempoMediaFila));
		System.out.println("Tempo extra de expediente : " + toTime(horaExtras));
	}

	public static boolean isArriveClient() {
		Random rand = new Random();
		int random = rand.nextInt(29);
		return (random == 0);
	}

	public static String toTime(int time) {
		int hour = time / 3600;
		int minutes = (time - (hour * 3600)) / 60;
		int sec = time - (minutes * 60 + (hour * 3600));

		return String.format("%02d horas ,%02d minutos e %02d segundos", hour, minutes, sec);
	}

	public static boolean isAllMesasFree(Gliche[] gliches) {
		for (int mesa = 0; mesa < gliches.length; mesa++) {
			if (gliches[mesa].inUse()) {
				return false;
			}
		}

		return true;
	}
}
