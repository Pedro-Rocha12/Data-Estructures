package upe.poli.ecomp.filas;

public class FilaEstatica<T> implements TADFila<T> {
	
	private T[] elementos;
	private int inicio, fim;
	private int qtd;
	
	public FilaEstatica() {
		elementos = (T[]) new Object[5];
		fim = -1;
	}
	
	private void expandirMemoria() {
		T[] temp = (T[]) new Object[qtd*2];
		for (int i = 0; i < qtd; i++) {
			temp[i] = elementos[(i+inicio)%elementos.length];
		}
		elementos = temp;
		inicio = 0;
		fim = qtd-1;
	}

	public void inserir(T novo) {
		if (qtd == elementos.length) {
			expandirMemoria();
		}

		fim = (fim+1) % elementos.length;
		elementos[fim] = novo;
		qtd++;
	}

	public T remover() {
		T r = null;
		if (qtd > 0) {
			r = elementos[inicio];
			elementos[inicio] = null;
			inicio = (inicio+1) % elementos.length;
			qtd--;
		}
		return r;
	}

	public T consultar() {
		T r = null;
		if (qtd > 0) {
			r = elementos[inicio];
		}
		return r;
	}

	public void print() {
		System.out.println();
		for (int i = 0; i < qtd; i++) {
			System.out.print(" -> " + elementos[(i+inicio)%elementos.length]);
		}
		System.out.println();
	}

	public void esvaziar() {
		elementos = (T[]) new Object[10]; 
		// o garbage collector limpa o bloco de memoria anterior, apos executar a linha acima
		qtd = 0;
	}

}
