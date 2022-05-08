package upe.poli.ecomp.arvore;

public class ArvoreBinaria<T> implements TADArvore<T> {

	private NoBinario<T> raiz;
	private int qtd=0;
	
	private int comparar(T t1, T t2) {
		int r = -1;
		if (t1 instanceof Comparable) {
			r = ((Comparable)t1).compareTo(t2);
		}
		return r;
	}
	
	public boolean existe(T elementoProcurado) {
		boolean r = false;
		NoBinario<T> p = raiz;
		while (p != null) {
			int c = comparar(elementoProcurado, p.elemento);
			if (c==0) {
				r = true;
				break;
			} else if (c < 0) {
				p = p.left;
			} else {
				p = p.right;
			}
		}
		return r;
	}
	
	private void imprimirElementosProfundidadeRecursivo(NoBinario<T> no, int p) {
		if (no != null) {
			if (p==0) {
				System.out.print(no + " ");
			} else {
				imprimirElementosProfundidadeRecursivo(no.left, p-1);
				imprimirElementosProfundidadeRecursivo(no.right, p-1);
			}
		}
	}
	
	public void imprimirElementosProfundidade(int p) {
		System.out.println("Elementos da profundidade " + p + ":");
		imprimirElementosProfundidadeRecursivo(raiz, p);
		System.out.println("");
	}
	
	public void imprimirElementosArvore() {
		System.out.println("Elementos da arvore em pré-ordem: ");
		imprimirElementosArvorePreOrdem(raiz);
		System.out.println("");
		
		System.out.println("Elementos da arvore em ordem: ");
		imprimirElementosArvoreEmOrdem(raiz);
		System.out.println("");
		
		System.out.println("Elementos da arvore em pós-ordem: ");
		imprimirElementosArvorePosOrdem(raiz);
		System.out.println("");
	}
	
	private void imprimirElementosArvorePreOrdem(NoBinario<T> no) {
		if (no != null) {
			System.out.print(no.elemento + " ");
			imprimirElementosArvorePreOrdem(no.left);
			imprimirElementosArvorePreOrdem(no.right);
		}
	}
	
	private void imprimirElementosArvoreEmOrdem(NoBinario<T> no) {
		if (no != null) {
			imprimirElementosArvoreEmOrdem(no.left);
			System.out.print(no.elemento + " ");
			imprimirElementosArvoreEmOrdem(no.right);
		}
	}
	
	private void imprimirElementosArvorePosOrdem(NoBinario<T> no) {
		if (no != null) {
			imprimirElementosArvorePosOrdem(no.left);
			imprimirElementosArvorePosOrdem(no.right);
			System.out.print(no.elemento + " ");
		}
	}

	public boolean verificarAncestral(T ancestral, T descendente) {
		boolean r = false, flagAncestral = false;
		NoBinario<T> p = raiz;
		
		while (p != null) {
			if (ancestral.equals(p.elemento)) {
				flagAncestral = true;
			}
			int c = comparar(descendente, p.elemento);
			if (c==0) {
				r = true;
				break;
			} else if (c < 0) {
				p = p.left;
			} else {
				p = p.right;
			}
		}
		return flagAncestral && r;
	}

	public void inserir(T novo) {
		NoBinario<T> novoNo = new NoBinario<T>(novo);
		if (raiz == null) {
			raiz = novoNo;
			qtd++;
		} else {
			NoBinario<T> p = raiz;
			while (p != null) {
				int c = comparar(novo, p.elemento);
				if (c==0) {
					break; // evita elementos repetidos
				} else if (c < 0) {
					if (p.left != null) {
						p = p.left;
					} else {
						p.left = novoNo;
						qtd++;
						break;
					}
				} else {
					if (p.right != null) {
						p = p.right;
					} else {
						p.right = novoNo;
						qtd++;
						break;
					}
				}
			}
		}
	}

	public boolean remover(T e) {
		boolean r = false;
		if (raiz != null) {
			if (raiz.elemento.equals(e)) {
				if (raiz.left == null) {
					raiz = raiz.right;
				} else if (raiz.right == null) {
					raiz = raiz.left;
				} else {
					substituiRaizSubarvore(raiz);
				}
				r = true;
			} else {
				// o no que preciso remover, não é a raiz
				// localizar o nó na árvore
				NoBinario<T> p = raiz;
				NoBinario<T> paiP = p;
				while (p != null) {
					int flag = comparar(p.elemento,e);
					if (flag == 0) {
						// encontrei o nó que deve ser removido!
						break;
					} else if (flag < 0) {
						paiP = p;
						p = p.right;
					} else {
						paiP = p;
						p = p.left;
					}
				}
				if (p != null) {
					// trata a remoção do nó p da árvore
					r = true;
					if (p.left == null) {
						if (paiP.left == p) {
							paiP.left = p.right;
						} else {
							paiP.right = p.right;
						}
					} else if (p.right == null) {
						if (paiP.left == p) {
							paiP.left = p.left;
						} else {
							paiP.right = p.left;
						}
					} else {
						substituiRaizSubarvore(p);
					}
				}
			}
		}
		if (r) {
			qtd--;
		}
		return r;
	}

	private void substituiRaizSubarvore(NoBinario<T> raizSubArvore) {
		// localizando o menor descendente à direita da raiz
		NoBinario<T> aux = raizSubArvore.right;
		NoBinario<T> paiAux = aux;
		while (aux.left != null) {
			paiAux = aux;
			aux = aux.left;
		}
		// substitui a informação da raiz
		raizSubArvore.elemento = aux.elemento;
		if (aux != paiAux) {
			// remove o nó que substituiu a raiz
			paiAux.left = aux.right;
		} else {
			raizSubArvore.right = aux.right;
		}
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

	public void esvaziar() {
		raiz = null;
		qtd = 0;
	}

	public int size() {
		return qtd;
	}

}
