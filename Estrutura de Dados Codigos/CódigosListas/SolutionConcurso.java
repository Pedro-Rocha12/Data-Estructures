package CódigosListas;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node {
	private int data;
 
    public Node(int d) {
        this.data = d;   
    }
    
    public int getData(){
        return this.data;
    }
}

class CustomList {
    
	private Node[] numeros;
    private int qtd;
    
    public CustomList(String string){
        String[] arrayNumero = string.split(";");
        for(int i = 0; i < arrayNumero.length; i++){
            inserir(Integer.parseInt(arrayNumero[i]));
        }
        qtd = 0;
    }
    
    public void inserir(int novo) {
        if (qtd == numeros.length) {
            expandirMemoria();
        }
        numeros[qtd] = new Node(novo);
        qtd++;
    }

    private void expandirMemoria(){
        Node[] tempo= new Node[qtd+1];
        for (int i = 0; i < qtd; i++) {
            tempo[i] = numeros[i];
        }
        numeros = tempo;
    }
    
    public int compararNotas(){
        if(this.numeros == null){
            return -1;
        }
        Node nodeAtual = this.numeros[0];
        return qtd-1;
    }

    public String toString() {
        String str = "";
        
        for(int i = 0; i < qtd; i++){
            str += numeros[i].getData();
            if(i!= qtd-1){
                str += ";";
            }
        }
        return str;
    }
}

class Result {
    public static String aprovados(CustomList resultados, int numeroDeVagas, int notaMinima) {
        
        
        
        return resultados.toString();
    }
}


public class SolutionConcurso {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        
        CustomList resultados = new CustomList(bufferedReader.readLine());
        String result = Result.aprovados(
            resultados,
            Integer.parseInt(bufferedReader.readLine()),
            Integer.parseInt(bufferedReader.readLine())
        );
        bufferedWriter.write(result);
        bufferedReader.close();
        bufferedWriter.close();
    }
}