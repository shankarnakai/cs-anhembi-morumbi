package Atividade_Fila_20661118_21015809;

public class Client implements INo {
	private int entry;
	private Client next;
	private IOperation operation;

	public Client(int sec) {
		this.entry = sec;
	}

	public int getValue() {
		return this.entry;
	}

	public void setValue(int value){
		this.entry = value;
	}

	public Client getNext() {
		return next;
	}

	public void setNext(Client next) {
		this.next = next;
	}
	
	public void setOperation(IOperation operation) {
		this.operation = operation;
	}

	public IOperation getOperation() {
		return this.operation; 
	}
}
