package CódigosFilas;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node {
    public String elemento;
    public Node next;

    public Node(String novoElemento) {
        elemento = novoElemento;
    }
    
    public String toString() {
        return "" + elemento;
    }
}

class CustomQueue {

	private Node primeiro;
    private Node ultimo;
   
    public void enqueue(String elemento) {
        
        Node novaCaixinha = new Node(elemento);
        
        if (primeiro == null) {
            primeiro = novaCaixinha;
            ultimo = novaCaixinha;
        } else {
            ultimo.next = novaCaixinha;
            ultimo = novaCaixinha;
        }
    }

    public String dequeue() {
        
        String remover = null;
        
        if (primeiro != null) {
            remover = primeiro.elemento;
            primeiro = primeiro.next;
            if(primeiro == null){
                ultimo = null;
            }
        } 
        return remover;      
    }

    public Node getPrimeiro(){
        return this.primeiro;
    }

    public String toString() {
	   String str = "";
        Node aux = primeiro;
        while (aux != null) {
            str += (aux.elemento + " ");
            aux = aux.next;
        }
        return str;
    }
}


class Result {
    public static String compileString(String str) {
        
        CustomQueue lista = new CustomQueue();
        CustomQueue organizada = new CustomQueue();
        CustomQueue provisoria = new CustomQueue();

        for(String elemento: str.split(" ")){
            lista.enqueue(elemento);
        }

        while(lista.getPrimeiro() != null){
            String elementoAtual = lista.dequeue();
            if(organizada.getPrimeiro() != null){
                while(organizada.getPrimeiro() != null){
                    if(elementoAtual.compareTo(organizada.getPrimeiro().toString()) < 0){
                        provisoria.enqueue(elementoAtual);
                        while(organizada.getPrimeiro() != null){
                            provisoria.enqueue(organizada.dequeue());
                        }
                    }else{
                        provisoria.enqueue(organizada.dequeue());
                        if(organizada.getPrimeiro() == null){
                            provisoria.enqueue(elementoAtual);
                        }
                    }
                }
                organizada = provisoria;
                provisoria = new CustomQueue();
            }else{
                organizada.enqueue(elementoAtual);
            }
        }

        return organizada.toString();
    }
}

public class SolutionFilaOrdenadaFunciona {

    public static void main(String[] args) {
        try(Scanner scan = new Scanner(System.in)){
        	String result = Result.compileString(scan.nextLine());
        	System.out.println(result);
      	};
    }
}