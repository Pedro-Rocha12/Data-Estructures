package upe.poli.ecomp.pilhas;

public class PilhaEstatica<T> implements TADPilha<T> {

	private T[] elementos;
	private int topo;
	
	public PilhaEstatica() {
		elementos = (T[]) new Object[10];
		topo = -1;
	}
	
	private void expandirMemoria() {
		T[] temp = (T[]) new Object[(topo+1)*2];
		for (int i = 0; i <= topo; i++) {
			temp[i] = elementos[i];
		}
		elementos = temp;
	}
	
	public void push(T e) {
		if (topo == elementos.length - 1) {
			expandirMemoria();
		}
		topo++;
		elementos[topo] = e;
	}

	public T pop() {
		T r = null;
		if (topo >= 0) {
			r = elementos[topo];
			elementos[topo] = null;
			topo--;
		}
		return r;
	}

	public T top() {
		T r = null;
		if (topo >= 0) {
			r = elementos[topo];
		}
		return r;
	}
	
	public String toString() {
		String s = "topo ";
		for (int i = topo; i>=0; i--) {
			s += "-> " + elementos[i];
		}
		return s;
	}

	public void print() {
		System.out.println(this);
	}

	public int size() {
		return topo+1;
	}

}
