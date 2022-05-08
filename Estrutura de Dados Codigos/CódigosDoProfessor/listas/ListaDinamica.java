package upe.poli.ecomp.listas;

public class ListaDinamica<T> implements TADLista<T> {

	private NoLista<T> inicio;
	private int qtd;
	
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
		// apos a linha acima, o garbage collector limpa todas as outras caixinhas NoLista
		qtd = 0;
	}

	public void inserir(T novo) {
		NoLista<T> novaCaixinha = new NoLista<T>(novo); 
		if (inicio == null) {
			inicio = novaCaixinha;
		} else {
			NoLista<T> aux = inicio;
			while (aux.proximo != null) {
				aux = aux.proximo;
			}
			aux.proximo = novaCaixinha;
		}
		qtd++;
	}

	public T procurarIesimo(int i) {
		if (i < qtd) {
			int k = 0;
			NoLista<T> aux = inicio;
			while (k < i) {
				aux = aux.proximo;
				k++;
			}
			return aux.elemento;
		} else {
			return null;
		}
	}

	public void remover(T elemento) {
		if (inicio != null) {
			if (elemento.equals(inicio.elemento)) {
				// remover o primeiro elemento da lista
				inicio = inicio.proximo;
				qtd--;
			} else {
				NoLista<T> aux = inicio;
				while (aux.proximo != null) {
					if (aux.proximo.elemento.equals(elemento)) {
						// encontrei o elemento que deve ser removido
						aux.proximo = aux.proximo.proximo;
						qtd--;
						break;
					} else {
						aux = aux.proximo;
					}
				}
			}
		}
	}

}
