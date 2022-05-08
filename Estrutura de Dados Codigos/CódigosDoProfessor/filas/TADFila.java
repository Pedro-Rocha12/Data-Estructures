package upe.poli.ecomp.filas;

public interface TADFila<T> {
	
	public void inserir(T novo);
	
	public T remover();
	
	public T consultar();
	
	public void print();
	
	public void esvaziar();

}
