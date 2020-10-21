package Atividade_Fila_20661118_21015809;

public interface IFilaInt {
	public void Enqueue(Client value);

	public Client Dequeue();
	
	public Client Front();

	public boolean isEmpty();

	public int Size();

	public String Display();
}
