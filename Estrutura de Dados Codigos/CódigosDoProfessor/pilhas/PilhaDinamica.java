package upe.poli.ecomp.pilhas;

import upe.poli.ecomp.listas.NoLista;

public class PilhaDinamica<T> implements TADPilha<T> {

	private NoLista<T> topo;
	private int qtd;
	
	public void push(T e) {
		NoLista<T> novo = new NoLista<T>(e);
		if (topo == null) {
			topo = novo;
		} else {
			novo.proximo = topo;
			topo = novo;
		}
		qtd++;
	}

	public T pop() {
		T r = null;
		if (topo != null) {
			r = topo.elemento;
			topo = topo.anterior;
			qtd--;
		}
		return r;
	}

	public T top() {
		T r = null;
		if (topo != null) {
			r = topo.elemento;
		}
		return r;
	}

	public void print() {
		System.out.println(this);
	}
	
	public String toString() {
		String s = "topo ";
		NoLista<T> aux = topo;
		while (aux != null) {
			s += ("-> " + aux.elemento);
			aux = aux.proximo;
		}
		return s;
	}

	public int size() {
		return qtd;
	}

}
