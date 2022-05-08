package upe.poli.ecomp.arvore;

public class TesteArvore {

	public static void main(String[] args) {
		ArvoreBinaria<Integer> arvore = new ArvoreBinaria<Integer>();
		arvore.inserir(9);
		arvore.inserir(5);
		arvore.inserir(12);
		arvore.inserir(2);
		arvore.inserir(7);
		arvore.inserir(11);
		arvore.inserir(15);
		arvore.inserir(18);
		System.out.println("O valor 15 existe? " + arvore.existe(15));
		System.out.println("O valor 8 existe? " + arvore.existe(8));
		arvore.imprimirElementosProfundidade(2);
		arvore.imprimirElementosProfundidade(3);
		System.out.println("9 é ancestral de 11? " + arvore.verificarAncestral(9, 11));
		System.out.println("5 é ancestral de 11? " + arvore.verificarAncestral(5, 11));
		arvore.remover(11);
		arvore.imprimirElementosProfundidade(2);
		arvore.imprimirElementosArvore();
	}

}
