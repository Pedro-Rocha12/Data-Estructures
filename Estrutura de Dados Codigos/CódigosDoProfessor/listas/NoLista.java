package upe.poli.ecomp.listas;

public class NoLista<T> {
	
	public T elemento;
	public NoLista<T> proximo;

	public NoLista(T novo) {
		elemento = novo;
		proximo = null; // redundante, pois o Java ja faz isso
	}
	
	public String toString() {
		return "" + elemento;
	}
}
