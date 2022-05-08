import java.io.*;
import java.util.*;

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

class Result{
    // Adicione elementos extras/metodos de suporte caso necessario
  
  // Os Materiais que chegam são: Tijolos, Areia, Cimento, Canos e Valvulas.
  // Canos e Valvulas podem sem empilhados em cima de qualquer outro material.
  // Tijolos apenas podem ser empilhados em cima de outros Tijolos.
  // Areias e Cimentos podem sem empilhados em cima de qualquer outro material, exceto Canos e Valvulas.
  // Se um material não pode ser empilhado na Pilha atual, uma nova Pilha é criada.
  // Os materiais sempre são empilhados na primeira pilha que podem ser empilhados.
    private CustomStack<CustomStack<String>> pilhaDePilha;
  
    public Result(){
      pilhaDePilha = new CustomStack<CustomStack<String>>();
    }

    public void empilhar(String tipoDoMaterial){

        CustomStack<CustomStack<String>> pilhaDePilha2 = new CustomStack<CustomStack<String>>();
        boolean materialEscolhido = false;
        CustomStack<String> pilhaDeMaterial;

        while(pilhaDePilha.peek() != null && !materialEscolhido){
            pilhaDeMaterial = pilhaDePilha.peek();
            if(addPilha(tipoDoMaterial, pilhaDeMaterial)){
                materialEscolhido = true;
            }else{
                pilhaDePilha2.push(pilhaDePilha.pop());
            }
        }

        if(!materialEscolhido){
            pilhaDeMaterial = new CustomStack<String>();
            pilhaDeMaterial.push(tipoDoMaterial);
            pilhaDePilha.push(pilhaDePilha2.pop());
        }

        while(pilhaDePilha2.peek() != null){
            pilhaDePilha.push(pilhaDePilha2.pop());
        }
    }

    private boolean addPilha(String material, CustomStack<String> pilha){
        String a = "Tijolo";
        String b = "Areia";
        String c = "Cimento";
        String d = "Canos";
        String e = "Valvulas";
        String topProvisorio = pilha.peek();
        
        if(topProvisorio == null || !((material.equals(a) && !(topProvisorio.equals(a))) || 
            ((material.equals(b) || material.equals(c)) && (topProvisorio.equals(d) || topProvisorio.equals(e))))){
            pilha.push(material);
            return true;
            }
        return false;
    }

    public String toString(){
        String str = "";
        CustomStack<CustomStack<String>> pilhaProvisoria = new CustomStack<CustomStack<String>>();
        CustomStack<String> pilhaMateriaisAtual = pilhaDePilha.pop();

        while(pilhaMateriaisAtual != null){
            str += (pilhaMateriaisAtual.toString() + "\n");
            pilhaProvisoria.push(pilhaMateriaisAtual);
            pilhaMateriaisAtual = pilhaDePilha.pop();
        }
        while(pilhaProvisoria.peek() != null){
            pilhaDePilha.push(pilhaProvisoria.pop());
        }

        return str;
    }
}



public class SolutionConstrucao {

  public static void main(String[] args) throws IOException {
    
      Scanner scan = new Scanner(System.in);
      Result result = new Result();
        String nextLine = "";
      while(scan.hasNextLine()) {
            nextLine = scan.nextLine();
          if(nextLine.isEmpty()) {
              break;
          }else{
              result.empilhar(nextLine);
          }
      }
      System.out.println(result);
  }
}