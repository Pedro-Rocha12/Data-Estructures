package upe.poli.ecomp.listas;

public class Usuario implements Comparable {
	public String nome, cpf;
	
	public Usuario(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String toString() {
		return nome + " (" + cpf + ")";
	}

	public int compareTo(Object outro) {
		int r = -1;
		if (outro instanceof Usuario) {
			r = this.nome.compareTo(((Usuario) outro).nome);
		}
		return r;
	}
}
