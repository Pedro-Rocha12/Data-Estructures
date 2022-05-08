package upe.poli.ecomp.arvore;

public interface TADArvore<T> {

	public boolean existe(T elemento);
	
	public void inserir(T novo);
	
	public boolean remover(T elemento);
	
	public void print();
	
	public void esvaziar();
	
	public int size();
	
}
