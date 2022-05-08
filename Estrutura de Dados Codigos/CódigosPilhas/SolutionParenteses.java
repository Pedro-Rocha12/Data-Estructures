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


class Node<T> {
    public T elemento;
    public Node<T> anterior;
    
    public Node(T novo){
        this.elemento = novo;
    }
    
    public String toString(){
        return elemento.toString();
    }
}

class CustomStack<T> {
    private Node<T> top;
    private int qtd;
    
    public void push(T novo){
        Node<T> novoNode = new Node(novo);
        if (top == null) {
            top = novoNode;
        } else {
            novoNode.anterior = top;
            top = novoNode;
        }
        qtd++;
    }
    
    public T pop() {
        T removido = null;
        if (top != null) {
            removido = top.elemento;
            top = top.anterior;
            qtd--;
        }
        return removido;
    }
    
    public T peek(){
        T topProvisorio = null;
        if(top != null){
            topProvisorio = top.elemento;        
        }
        return topProvisorio;
    }
    
    public int size(){
        return this.qtd;
    }
    
    public String toString(){
        String str = "";
        Node<T> nodeAtual;
        
        if(top == null){
            return "";
        }else{
            nodeAtual = top;
            while(nodeAtual != null){
                str += nodeAtual.toString();
                nodeAtual = nodeAtual.anterior;
                if(nodeAtual != null){
                    str += " ";
                }
            }
        }
        return str;
    }
}

class Result {

    public static int contBrackets(String input) {
        CustomStack<Character> pilha = new CustomStack<Character>();
        int inconsistencia = 0;
        
        for(char chr: input.toCharArray()){
            if(chr == '(' || chr == '[' || chr == '{'){
                pilha.push(chr);
            }else if(chr == ')'){
               if(pilha.peek() == null){
                   inconsistencia++;
               }else{
                   if(pilha.pop() != '('){
                       inconsistencia += 2;
                   }     
               }        
            }else if(chr == ']'){
               if(pilha.peek() == null){
                   inconsistencia++;
               }else{
                   if(pilha.pop() != '['){
                       inconsistencia += 2;
                   }
               }
            }else if(chr == '}'){
               if(pilha.peek() == null){
                   inconsistencia++;
               }else{
                   if(pilha.pop() != '{'){
                       inconsistencia += 2;
                   }
               }
            } 
       }
        
        while(pilha.peek() != null){
            inconsistencia++;
            pilha.pop();
        }
        
        return inconsistencia;

    }
}

public class SolutionParenteses {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String inputString = bufferedReader.readLine();

        int result = Result.contBrackets(inputString);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}