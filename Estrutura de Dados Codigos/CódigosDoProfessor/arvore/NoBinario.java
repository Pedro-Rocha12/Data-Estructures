package upe.poli.ecomp.arvore;

public class NoBinario<T> {

	T elemento;
	NoBinario<T> left, right;
	
	public NoBinario(T e) {
		this.elemento = e;
	}
	
	public String toString() {
		return "" + elemento;
	}
}
