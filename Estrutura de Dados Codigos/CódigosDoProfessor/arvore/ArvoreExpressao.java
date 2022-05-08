package upe.poli.ecomp.arvore;

import java.util.StringTokenizer;

import upe.poli.ecomp.pilhas.PilhaDinamica;

public class ArvoreExpressao {
	
	private NoBinario<String> raiz;
	
	public ArvoreExpressao(String expressao) {
		String[] tokens = expressao.split(" ");
		PilhaDinamica<String> pilhaOperador = new PilhaDinamica<String>();
		PilhaDinamica<NoBinario<String>> pilhaNos = new PilhaDinamica<NoBinario<String>>();
		for (int i = 0; i < tokens.length; i++) {
			String t = tokens[i];
			char c = t.charAt(0);
			if (c >= '0' && c <= '9' 
					|| c == '-' && t.length() > 1) {
				NoBinario<String> novoNo = new NoBinario<String>(t);
				pilhaNos.push(novoNo);
			} else if (c == '+' || c == '-' || c == '*' || c == '/') {
				pilhaOperador.push(t);
			} else if (c == ')') {
				String operador = pilhaOperador.pop();
				NoBinario<String> novoNo = new NoBinario<String>(operador);
				novoNo.right = pilhaNos.pop();
				novoNo.left = pilhaNos.pop();
				pilhaNos.push(novoNo);
			}
		}
		raiz = pilhaNos.pop();
	}
	
	public int calcular() {
		return calcularPercursoPosOrdem(raiz);
	}

	private int calcularPercursoPosOrdem(NoBinario<String> no) {
		int r = 0;
		if (no != null) {
			if (no.left == null && no.right == null) {
				r = Integer.parseInt(no.elemento);
			} else {
				int valorEsquerda = calcularPercursoPosOrdem(no.left);
				int valorDireita = calcularPercursoPosOrdem(no.right);
				switch (no.elemento.charAt(0)) {
					case '+': r = valorEsquerda + valorDireita; break;
					case '-': r = valorEsquerda - valorDireita; break;
					case '*': r = valorEsquerda * valorDireita; break;
					case '/': r = valorEsquerda / valorDireita; break;
				}
			}
		}
		return r;
	}

}
