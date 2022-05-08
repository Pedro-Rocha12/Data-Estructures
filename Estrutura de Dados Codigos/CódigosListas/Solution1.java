package CódigosListas;

import java.io.*;
import java.util.*;

class Node {
    
    int data;
 
    public Node(int d) {
        this.data = d;   
    } 
}

class CustomList {
    
    Node[] numeros;
    int qtd;
    
    public CustomList(){
        numeros =  new Node[10];
    }
    
    public void inserir(int novo) {
        if (qtd == numeros.length) {
            expandirMemoria();
        }
        numeros[qtd] = new Node(novo);
        qtd++;
    }
    
    public void sort(){
        for(int i = 0; i < qtd-1; i++){
            int menor = i;
            for(int j = i + 1; j < qtd; j++){
                if(numeros[j].data < numeros[menor].data){
                    menor = j;
                }
            }
            if(menor != i){
                Node tempo = numeros[i];
                numeros[i] = numeros[menor];
                numeros[menor] = tempo;
            }
        }
    }

    private void expandirMemoria(){
        Node[] tempo= new Node[qtd*2];
        for (int i = 0; i < qtd; i++) {
            tempo[i] = numeros[i];
        }
        numeros = tempo;
    }

    public String toString() {
        char aspas = '"'; 
        String str = "";
        
        for(int i = 0; i < qtd; i++){
            str += numeros[i].data;
            if(i!= qtd-1){
                str += " ";
            }
        }
        return aspas+str+aspas;
    }
}


class Result {
    
    public static String merge(String x, String y) {
        CustomList lista = new CustomList();
        String novoX = x.substring(1,x.length()-1);
        String novoY = y.substring(1,y.length()-1);
        String str = novoX+" "+novoY;
        String[] strXY = str.split(" ");
        
        for(int i = 0; i < strXY.length; i++){  
            lista.inserir(Integer.parseInt(strXY[i]));
        }
        lista.sort();
        
    return lista.toString();
    }
}

public class Solution1 {

    public static void main(String[] args) throws IOException {
        // scanner instance
        Scanner scan = new Scanner(System.in);

        // read input as string
        String x = scan.nextLine();
        String y = scan.nextLine();

        // resolution function
        String result = Result.merge(x, y);

        // print output
        System.out.println(result);
    }
}