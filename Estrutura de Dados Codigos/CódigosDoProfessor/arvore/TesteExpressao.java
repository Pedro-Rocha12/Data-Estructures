package upe.poli.ecomp.arvore;

public class TesteExpressao {

	public static void main(String[] args) {
		String exp = "( ( ( 4 + 3 ) * ( 5 - 2 ) ) + -10 )";
		ArvoreExpressao arvore = new ArvoreExpressao(exp);
		System.out.println(exp + " = " + arvore.calcular());
	}

}
