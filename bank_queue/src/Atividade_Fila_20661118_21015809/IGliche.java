package Atividade_Fila_20661118_21015809;

public interface IGliche {
	public void setClient(int time, Client client); 
	public Client getClient();
	public boolean inUse(); 
	public int free();
	public IOperation getOperation();
}
