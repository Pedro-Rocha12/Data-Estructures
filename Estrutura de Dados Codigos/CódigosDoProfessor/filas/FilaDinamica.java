package upe.poli.ecomp.filas;

import upe.poli.ecomp.listas.NoLista;

public class FilaDinamica<T> implements TADFila<T> {

	private NoLista<T> inicio, fim;
	
	public void inserir(T novo) {
		NoLista<T> novoNo = new NoLista(novo);
		if (inicio == null) {
			inicio = novoNo;
			fim = novoNo;
		} else {
			fim.anterior = novoNo;
			fim = novoNo;
		}
	}

	public T remover() {
		T r = null;
		if (inicio != null) {
			r = inicio.elemento;
			inicio = inicio.anterior;
			// o garbage collector vai limpar a memoria do primeiro elemento
			if (inicio == null) {
				// trata a remo��o no caso de uma fila com apenas 1 elemento
				fim = null;
			}
		}
		return r;
	}

	public T consultar() {
		T r = null;
		if (inicio != null) {
			r = inicio.elemento;
		}
		return r;
	}

	public void print() {
		System.out.println();
		System.out.print(this);
		System.out.println();
	}
	
	public String toString() {
		String s = "";
		NoLista<T> aux = inicio;
		while (aux != null) {
			s += (" -> " + aux.elemento);
			aux = aux.proximo;
		}
		return s;
	}

	public void esvaziar() {
		inicio = null;
		fim = null;
	}

}
