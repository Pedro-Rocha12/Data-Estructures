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

    public static CustomStack createStack(String phrase) {
        CustomStack<String> pilha = new CustomStack<String>();
        for(String element: phrase.split(" ")){
            pilha.push(element);
        }
        return pilha;
    }

    public static String stacksIntersection(CustomStack<String> stack, String elems) {
        String str1 = "Nao contem";
        String str2 = "Contem";
        for(String element: elems.split(" ")){
            CustomStack<String> pilhaProvisoria = new CustomStack<String>();
            boolean achou = false;
            while(stack.peek() != null){
                if(stack.peek().equals(element)){
                    achou = true;
                }
                pilhaProvisoria.push(stack.pop());
            }
            if(!achou){
                return str1;
            }
            stack = pilhaProvisoria;
        }
        return str2;
    }
}

public class SolutionSubConjuntos {
    public static void main(String[] args) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
        {
        	try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH"))))
            {

              CustomStack stackA = Result.createStack(bufferedReader.readLine());
              

              bufferedWriter.write(Result.stacksIntersection(stackA, bufferedReader.readLine()));
              bufferedWriter.newLine();
            }
        }
    }
}