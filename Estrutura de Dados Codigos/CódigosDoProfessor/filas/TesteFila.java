package upe.poli.ecomp.filas;

public class TesteFila {

	public static void main(String[] args) {
		TADFila<Integer> fila = new FilaEstatica<Integer>();
		fila.inserir(1);
		fila.inserir(2);
		fila.inserir(3);
		fila.inserir(4);
		fila.inserir(5);
		fila.print();
		
		System.out.println("Removendo elemento " + fila.remover());
		System.out.println("Removendo elemento " + fila.remover());
		
		fila.inserir(6);
		fila.inserir(7);
		
		fila.print();
		
		fila.inserir(8);
		
		fila.print();
	}

}
