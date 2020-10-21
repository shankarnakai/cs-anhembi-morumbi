package Atividade_Fila_20661118_21015809;

public class Gliche {
	private Client client;
	private int time;

	public void setClient(int time, Client client) {
		this.time = time;
		this.client = client;
	}

	public Client getClient() {
		return this.client;
	}

	public boolean inUse() {
		if (this.client == null) {
			return false;
		}

		return true;
	}

	public int expectedEndTime() {
		if(!this.inUse()) {
			return -1;
		}

		return this.client.getOperation().timeSpent() + this.time;
	}

	public int free(int currentTime) {
		if(!this.inUse()) {
			return -1;
		}

		int endtime =  currentTime - this.client.getOperation().timeSpent();
		this.client = null;
		return endtime;
	}
}
