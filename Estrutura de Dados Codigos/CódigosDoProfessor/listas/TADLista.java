package upe.poli.ecomp.listas;

public interface TADLista<T> {

	public void print();
	
	public void esvaziar();
	
	public void inserir(T novo);
	
	public T procurarIesimo(int i);
	
	public void remover(T elemento);
}
