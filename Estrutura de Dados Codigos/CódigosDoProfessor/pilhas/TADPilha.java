package upe.poli.ecomp.pilhas;

public interface TADPilha<T> {

	public void push(T e);
	
	public T pop();
	
	public T top();
	
	public void print();
	
	public int size();
}
