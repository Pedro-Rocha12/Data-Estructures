package upe.poli.ecomp.pilhas;

public class TestePilha {
	
	public static int calcularExpressaoPosfixa(String s) {
		String[] termos = s.split(" ");
		int r = 0;
		TADPilha<Integer> pilha = new PilhaDinamica<Integer>();
		for (String t:termos) {
			if (t.length() == 1 && isOperador(t.charAt(0))) {
				if (pilha.size() >= 2) {
					int v2 = pilha.pop();
					int v1 = pilha.pop();
					int total = calcular(v1, v2, t.charAt(0));
					pilha.push(total);
				} else {
					throw new RuntimeException("Expressao com erro!");
				}
			} else {
				pilha.push(Integer.parseInt(t));
			}
		}
		if (pilha.size() == 1) {
			r = pilha.pop();
		} else {
			throw new RuntimeException("Expressao com erro!");
		}
		return r;
	}

	private static int calcular(int v1, int v2, char op) {
		int r = 0;
		switch (op) {
			case '+': r = v1+v2; break;
			case '-': r = v1-v2; break;
			case '*': r = v1*v2; break;
			case '/': r = v1/v2; break;
		}
		return r;
	}

	private static boolean isOperador(char c) {
		if (c=='+' || c=='-' || c=='*' || c=='/')
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
//		TADPilha<Integer> pilha = new PilhaDinamica<Integer>();
//		pilha.push(3);
//		pilha.push(2);
//		pilha.push(7);
//		pilha.push(1);
//		pilha.print();
//		System.out.println( "Pop -> " + pilha.pop() );
//		pilha.print();
//		System.out.println( "Pop -> " + pilha.pop() );
//		pilha.push(10);
//		pilha.print();
		
		String exp = "4 5 * 5 + 6 5 * +";
		int total = calcularExpressaoPosfixa(exp);
		System.out.println(exp + " = " + total);
	}

}
