package CódigosFilas;

import java.io.*;
import java.util.*;

class Node {
    public int numero;
    public Node next;

    public Node(int novoNumero) {
        this.numero = novoNumero;
    }
    
    public String toString() {
        String str = Integer.toString(this.numero);
        return str;
    }
}

class CustomQueue {

	private Node primeiro;
    private Node ultimo;
    
    public void enqueue(Integer value) {
        
        Node novaCaixinha = new Node(value);
        
        if (primeiro == null) {
            primeiro = novaCaixinha;
            ultimo = novaCaixinha;
        } else {
            ultimo.next = novaCaixinha;
            ultimo = novaCaixinha;
        }
    }

    public Integer dequeue() {
        
        Integer remover = null;
        
        if (primeiro != null) {
            remover = primeiro.numero;
            primeiro = primeiro.next;
            if(primeiro == null){
                ultimo = null;
            }
        } 
        return remover;      
    }
    

    public String toString() {
	   
        String str = "";
        String vazia = "Empty";
            
        if(primeiro == null){
            return vazia;
        }else{
            Node nodeAtual = primeiro;
            while(nodeAtual != null){
                str += nodeAtual;
                if(nodeAtual.next != null){
                    str += " ";
                }
                nodeAtual = nodeAtual.next;
            }
        }
        return str;
    }
}


public class SolutionInsertRemove {

    public static void main(String[] args) throws IOException {

        try(Scanner scan = new Scanner(System.in)){
          CustomQueue queue = new CustomQueue();

          while(scan.hasNextLine()) {
              String nextLine = scan.nextLine();

              if(nextLine.isEmpty()) {
                  break;
              }

              Integer n = Integer.parseInt(nextLine);

              if (n < 0) {
                  queue.dequeue();
                  System.out.println("Remove: " + queue);

              } else {
                  queue.enqueue(n);
                  System.out.println("Insert: " + queue);
              }
          }
        }
    }
}