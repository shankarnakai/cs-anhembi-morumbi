package Atividade_Fila_20661118_21015809;

public class FilaClient implements IFilaInt {
	private int size;
	private Client front, back;

	public void Enqueue(Client value) {
		if (this.front == null) {
			this.front = value;
			this.back = this.front;
		} else {
			this.back.setNext(value);
			this.back = value;
		}
		this.size += 1;
	}

	public Client Dequeue() {
		if (this.isEmpty()) {
			return null;
		}

		Client first = this.front;
		this.front = this.front.getNext();
		if (front == null) {
			this.back = null;
		}
		this.size -= 1;
		return first;
	}

	public Client Front() {
		return this.front;
	}

	public boolean isEmpty() {
		return (this.front == null);
	}

	public int Size() {
		return this.size;
	}

	public String Display() {
		if (this.isEmpty()) {
			return "";
		}

		String str = "";

		Client aux = this.front;
		while (aux != null) {
			str += aux.getValue();
			aux = aux.getNext();
		}

		return str;
	}

}
