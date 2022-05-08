package CódigosFilas;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Node {
    public String str;
    public Node next;

    public Node(String novo) {
        this.str = novo;
    }
}

class Queue {
    
    private Node primeiro;
    private Node ultimo;
    
    public void enqueue(String texto) {
        
        Node novaCaixinha = new Node(texto);
        
        if (primeiro == null) {
            primeiro = novaCaixinha;
            ultimo = novaCaixinha;
        } else {
            ultimo.next = novaCaixinha;
            ultimo = novaCaixinha;
        }
    }
    
    public Node getPrimeiro(){
        return this.primeiro;
    }

    public String dequeue() {
        
        String remover = null;
        
        if (primeiro != null) {
            remover = primeiro.str;
            primeiro = primeiro.next;
            if(primeiro == null){
                ultimo = null;
            }
        } 
        return remover;      
    }
}

class Result {

    /*
     * Complete the 'invertQueue' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING input as parameter.
     */

    public static Queue invertQueue(String input) {
        Queue fila = new Queue();
        String[] texto = input.split(" ");
    
        for(int i = texto.length-1; i >= 0; i--){
          fila.enqueue(texto[i]);
        }
       return fila;
    }

}

public class SolutionInvertString {
    public static void main(String[] args) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {
                String input = bufferedReader.readLine();
                Queue queue = Result.invertQueue(input.trim());
                String result = "";
                while(queue.getPrimeiro() != null)
                    result += queue.dequeue() + " ";
                bufferedWriter.write(result.trim());
                bufferedWriter.newLine();
            }
        }
   }
}