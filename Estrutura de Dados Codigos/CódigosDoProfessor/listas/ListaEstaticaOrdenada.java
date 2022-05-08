package upe.poli.ecomp.listas;

public class ListaEstaticaOrdenada<T> implements TADLista<T> {

	private T[] elementos;
	private int qtd;
	
	public ListaEstaticaOrdenada() {
		elementos = (T[]) new Object[10];
	}
	
	public void print() {
		System.out.println();
		for (int i = 0; i < qtd; i++) {
			System.out.print(" -> " + elementos[i]);
		}
		System.out.println();
	}

	public void esvaziar() {
		elementos = (T[]) new Object[10]; 
		// o garbage collector limpa o bloco de memoria anterior, apos executar a linha acima
		qtd = 0;
	}

	public void inserir(T novo) {
		if (qtd == elementos.length) {
			expandirMemoria();
		}
		int i = qtd-1;
		while (i >= 0 && comparar(novo, elementos[i]) < 0) {
			elementos[i+1] = elementos[i];
			i--;
		}
		elementos[i+1] = novo;
		qtd++;
	}

	private int comparar(T t1, T t2) {
		int r = -1;
		if (t1 instanceof Comparable) {
			r = ((Comparable)t1).compareTo(t2);
		}
		return r;
	}

	private void expandirMemoria() {
		T[] temp = (T[]) new Object[qtd*2];
		for (int i = 0; i < qtd; i++) {
			temp[i] = elementos[i];
		}
		elementos = temp;
	}

	public T procurarIesimo(int i) {
		if (i < qtd)
			return elementos[i];
		else
			return null;
	}

	public void remover(T elemento) {
		int i = 0;
		while (i < qtd) {
			if (elemento.equals(elementos[i])) {
				break; // encontrei o elemento pra ser removido!
			}
			i++;
		}
		if (i < qtd) {
			// remove o elemento e desloca os demais para esquerda
			while (i < qtd-1) {
				elementos[i] = elementos[i+1];
				i++;
			}
			elementos[i] = null;
			qtd--;
		}
	}

}
