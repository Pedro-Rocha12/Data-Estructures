package CódigosListas;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class No{
    Integer inteiro;
    No proximo;
    public No(Integer inteiro){
        this.inteiro = inteiro;
    }
}    
class Lista{
    No inicial;
    public void add(Integer inteiro){
      
      if(inicial == null){
          inicial = new No(inteiro);
      }
      else{
          No novoNo = inicial;
          while(novoNo.proximo != null){
              novoNo = novoNo.proximo;
          }
          novoNo.proximo = new No(inteiro);
          novaLista(novoNo.proximo);
          }
      }
  public No novaLista(No n) {
      
      for(No i = inicial; i.proximo != null; i = i.proximo ) {
           No menor = i;
           for (No j = i.proximo; j != null; j = j.proximo) {
               if (j.inteiro < menor.inteiro) {
                   menor=j;
               }
           }
           int x = i.inteiro;
           i.inteiro = menor.inteiro;
           menor.inteiro = x;
          }
    return n;
      }
  
  
public String toString(){
      No novoNo = inicial;
      String str = "";
      
      while(novoNo != null) {
          str += novoNo.inteiro + " ";
          novoNo = novoNo.proximo;
      }
      
       return str;
        }
}

public class Solution3 {
    public static void main(String[] args) throws IOException {
          Lista lista = new Lista();
        Scanner scan = new Scanner(System.in);
          while(scan.hasNextLine()) {
            String nextLine = scan.nextLine();
            if(nextLine.isEmpty()) {
                break;
            }
            Integer n = Integer.parseInt(nextLine);
            lista.add(n);
        }
        String result = lista.toString();
        System.out.println(result);
    }
}