package CódigosListas;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class No{
  	private String str;
    
    public No(String s){
        this.str = s;
    }
    
    public String getString(){
        return str;
    }
}

class Lista{
    
	private No[] head;
    private int qtd;
    
    public Lista(){
        head = new No[5];
        qtd = 0;
    }
    
    public void aumentarMemoria(){
        No[] headTemporario = new No[qtd+1];
        for (int i = 0; i < qtd; i++) {
            headTemporario[i] = head[i];
        }
        head = headTemporario;
    }
    
    public void add(String entrada){
        if (qtd == head.length) {
            aumentarMemoria();
        }   
        head[qtd] = new No(entrada);
        qtd++;
    }
    
	public void remove(int num){
        ordenarHead();
        int i = 0;
        while (i < qtd) {
            if (head[i].equals(head[num])) {
                break;
            }
            i++;
        }
        if (i < qtd) {
            while (i < qtd-1) {
                head[i] = head[i+1];
                i++;
            }
            head[i] = null;
            qtd--;
        }
    }
    
    public No[] ordenarHead(){
        String[] temp = new String[qtd];
        for(int i = 0; i < temp.length; i++){
            temp[i] = String.valueOf(head[i].getString());
        }
        String[] novaTemp = temp;
        Arrays.sort(novaTemp);
        for(int i = 0; i < novaTemp.length;i++){
            if(novaTemp[0].equals(temp[i])){
              temp[i] = null;
            }
        }
        esvaziar();
        for(int i = 0; i < head.length; i++){
            add(temp[i]);
        }
        return head;
    }
    
    public void esvaziar() {
        head = (No[]) new No[5]; 
        qtd = 0;
    }
    
	public String toString(){
        
        String resposta = "";
        for(int i = 0; i < qtd; i++){
            resposta += head[i].getString();
            if(i != qtd-1){
                resposta += " - ";
            }
        }
        return resposta;
    }
}


public class SolutionErrado {
    public static void main(String[] args) throws IOException {
      	Lista lista = new Lista();
        Scanner scan = new Scanner(System.in);
      	while(scan.hasNextLine()) {
            String nextLine = scan.nextLine();
            if(nextLine.isEmpty()) {
                break;
            }else if(nextLine.charAt(0) != '-'){
            	lista.add(nextLine);
            }else{
            	lista.remove(Integer.parseInt(scan.nextLine()));
                break;
            }  
        }
        String result = lista.toString();
        System.out.println(result);
    }
}