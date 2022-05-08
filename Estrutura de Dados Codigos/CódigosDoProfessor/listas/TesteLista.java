package upe.poli.ecomp.listas;

public class TesteLista {

	public static void main(String[] args) {
//		TADLista<Integer> lista = new ListaEstaticaOrdenada<Integer>();
//		lista.inserir(4);
//		lista.inserir(2);
//		lista.inserir(5);
//		lista.inserir(7);
//		lista.inserir(1);
//		lista.print();
//		lista.remover(5);
//		lista.print();
		
//		TADLista<String> lista = new ListaEstaticaOrdenada<String>();
//		lista.inserir("Pedro");
//		lista.inserir("Ana");
//		lista.inserir("Carlos");
//		lista.inserir("Bruno");
//		lista.print();
		
		TADLista<Usuario> lista = new ListaEstaticaOrdenada<Usuario>();
		lista.inserir(new Usuario("Pedro","123"));
		lista.inserir(new Usuario("Ana","456"));
		lista.inserir(new Usuario("Carlos","789"));
		lista.inserir(new Usuario("Bruno","444"));
		lista.print();
	}

}
