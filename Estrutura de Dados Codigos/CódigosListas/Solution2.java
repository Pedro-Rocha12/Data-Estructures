package CódigosListas;

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

class Node<T>{
    private T elemento;
   
    public Node(T novo){
        this.elemento = novo;
    }
    
    public T getElemento(){
        return this.elemento;
    }
}

class List<T>{
    
    private Node[] head;
    private int qtd;
    
    public List(){
        head = new Node[5];
        qtd = 0;
    }
    
    public void inserir(T novo) {
        
        if (qtd == head.length) {
            aumentarMemoria();
        }   
        head[qtd] = new Node(novo);
        qtd++;
    }
    
    private void aumentarMemoria(){
        Node[] headTemporario = new Node[qtd+1];
        for (int i = 0; i < qtd; i++) {
            headTemporario[i] = head[i];
        }
        head = headTemporario;
    }
    
    public void reverseLista(int posicao){
        int headFinal = qtd-1;
        if(posicao < 0){
            posicao = 0;
        }
        while(posicao < headFinal){
            Node headProvisorio = head[posicao];
            head[posicao] = head[headFinal];
            head[headFinal] = headProvisorio;
            posicao++;
            headFinal--;
        }
    }
    
    
    public String toString(){
        
        String str = "";
        for(int i = 0; i < qtd; i++){
            str += head[i].getElemento();
            if(i != qtd-1){
                str += ", ";
            }
        }
        String resposta = "Lista:["+str+"]";
    	return resposta;
    }
}

class Result{
    public static String reverseList(String componentes, int posicao) {
           
        List lista = new List<String>();
        for(String elemento: componentes.split(" ")){
            lista.inserir(elemento);
        }
        lista.reverseLista(posicao);
        return lista.toString();
    }   
}

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String componentes = bufferedReader.readLine();

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.reverseList(componentes, n);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}