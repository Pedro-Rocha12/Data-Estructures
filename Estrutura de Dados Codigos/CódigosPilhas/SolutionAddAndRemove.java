package CódigosPilhas;

import java.io.*;
import java.util.*;

class Node {
    public int numero;
    public Node anterior;
    
    public Node(int novoNumero){
        this.numero = novoNumero;    
    }
    
    public int getNumero(){
        return this.numero;
    }

}

class CustomStack {

	private Node top;
    private int qtd;
    
    public void add(int novoNumero){
        Node novoNode = new Node(novoNumero);
        if (top == null) {
            top = novoNode;
        } else {
            novoNode.anterior = top;
            top = novoNode;
        }
        qtd++;
    }
    
    public int remove() {
        int removido = 0;
        if (top != null) {
            removido = top.numero;
            top = top.anterior;
            qtd--;
        }
        return removido;
    }
    
    public String display() {
	   String str = "";
        String vazia = "Empty";
            
        if(top == null){
            return vazia;
        }else{
            Node nodeAtual = top;
            CustomStack pilhaInvertida = new CustomStack();
            while(nodeAtual != null){
                pilhaInvertida.add(nodeAtual.numero);
                nodeAtual = nodeAtual.anterior;
                
            }
            nodeAtual = pilhaInvertida.top;
            while(nodeAtual != null){
                str += pilhaInvertida.remove();
                if(nodeAtual.anterior != null){
                    str += " ";         
                }
                nodeAtual = nodeAtual.anterior;
            }
        }
        return str;
    }
}


public class SolutionAddAndRemove {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        CustomStack stack = new CustomStack();

        while(scan.hasNextLine()) {
            String nextLine = scan.nextLine();

            if(nextLine.isEmpty()) {
                break;
            }
         
            Integer n = Integer.parseInt(nextLine);
            
            if (n < 0) {
                stack.remove();
                System.out.println("Remove: " + stack.display());

            } else {
                stack.add(n);
                System.out.println("Insert: " + stack.display());
            }
        }
    }
}