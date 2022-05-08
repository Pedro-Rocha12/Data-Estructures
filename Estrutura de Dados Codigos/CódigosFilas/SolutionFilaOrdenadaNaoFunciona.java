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
        
    	String[] string = str.split(" ");
        
        boolean isNumeric = true;
        for (int i = 0; i < string.length; i++) {
            if (!Character.isDigit(string[i].charAt(i))) {
                isNumeric = false;
            }
        }
        
        String STR = "";
        for(int i = 0; i< string.length; i++){
            STR += string[i];
        }   
        char[] StringtoChar = STR.toCharArray();
        if(isNumeric == true){   
             for(int i = 0; i < string.length;i++){
                for(int j = 0; j < string.length - 1; j++){
                    if(Integer.parseInt(string[i]) < Integer.parseInt(string[j])){
                    int aux = Integer.parseInt(string[i]);
                    string[i] = string[j];
                    string[j] = "" + aux; 
                    }

                }
            }
        }else{
            for(int i=0;i<(StringtoChar.length-1);i++){
                for(int j=i+1;j>0;j--){
                    if(StringtoChar[j]<StringtoChar[j-1]){
                        char Temp = StringtoChar[j-1];
                        StringtoChar[j-1] = StringtoChar[j];
                        StringtoChar[j] = Temp;
                    }
                }
            }
        }
        
        String SortedString = "";
        if( isNumeric == true){
            SortedString = STR;      
        }else{
            SortedString = new String(StringtoChar);
        }
        
        
    	CustomQueue queue = new CustomQueue();
        String[] elementos = SortedString.split("");
        
        for(int i = 0; i < elementos.length; i++){
      	queue.enqueue(elementos[i]);
        }
        
    	return queue.toString();
    }
}

public class SolutionFilaOrdenadaNaoFunciona {

    public static void main(String[] args) {
        try(Scanner scan = new Scanner(System.in)){
        	String result = Result.compileString(scan.nextLine());
        	System.out.println(result);
      	};
    }
}