package CódigosFilas;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node {
  	public String nome;
    public Node next;

    public Node(String novoNome) {
        this.nome = novoNome;
    }
}

class CustomQueue {
    
    private Node primeiro;
    private Node ultimo;
   
	public void enqueue(String nome) {
        
        Node novaCaixinha = new Node(nome);
        
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
            remover = primeiro.nome;
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
}

class Bank {
	private CustomQueue usualQueue = new CustomQueue();
    private CustomQueue preferentialQueue = new CustomQueue();
  
	public void add(String name, boolean isPreferential){
      	if(isPreferential == true){
            preferentialQueue.enqueue(name);
        }else{
            usualQueue.enqueue(name);
        }
    }
  
    public String callSomeone(){
       if(preferentialQueue.getPrimeiro() != null){
           return preferentialQueue.dequeue();
       }else{
        return usualQueue.dequeue();
        }
    }
}

public class SolutionBankList {
    public static void main(String[] args) throws IOException {
        try(Scanner scan = new Scanner(System.in)){
          String str = "";
          Bank dinheiroFacil = new Bank();
          
          while(scan.hasNextLine()) {
              String[] nextLine = scan.nextLine().split(";");
            
              if(nextLine.length == 0) 
                  break;
              else if(nextLine[0].equals("chamada"))
                  str += (str.isEmpty()? "" : " - ") + dinheiroFacil.callSomeone();    
              else
                  dinheiroFacil.add(nextLine[0], nextLine[1].toLowerCase().equals("true"));
          }
          
          System.out.println(str);
        }
    }
}
