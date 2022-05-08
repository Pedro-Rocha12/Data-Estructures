package CódigosPilhas;
import java.io.*;
import java.util.*;

class Node<T> {
    public T vegetal;
    public Node<T> vegetalAnterior;
    
    public Node(T veg){
        this.vegetal = veg;
    }
    
    public String toString(){
        return vegetal.toString();
    }
}

class Vegetal {
    public String nomeVegetal;
    public int peso;
    
    public Vegetal(String novoVegetal, int novoPeso){
        this.nomeVegetal = novoVegetal;
        this.peso = novoPeso;
    }
    
    public String toString(){
        return this.nomeVegetal;
    }
    
   
}

class CustomStack<T> {
	private Node<T> top;
    private int qtd;
    
    public void add(T veg){
        Node<T> novoNode = new Node(veg);
        if (top == null) {
            top = novoNode;
        } else {
            novoNode.vegetalAnterior = top;
            top = novoNode;
        }
        qtd++;
    }
    
    public T remove() {
        T removido = null;
        if (top != null) {
            removido = top.vegetal;
            top = top.vegetalAnterior;
            qtd--;
        }
        return removido;
    }
    
    public T getTop(){
        T vegetalDoTopo = null;
        if(top != null){
            vegetalDoTopo = top.vegetal;        
        }
        return vegetalDoTopo;
    }
    
    public String toString(){
        String str = "";
        String vazio = "Empty";
        Node<T> nodeAtual;
        
        if(top == null){
            return vazio;
        }else{
            nodeAtual = top;
            while(nodeAtual != null){
                str += nodeAtual.toString();
                nodeAtual = nodeAtual.vegetalAnterior;
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
    private CustomStack<Vegetal> pilhaVegetais = new CustomStack();
    
	public void empilhar(String entrada){
    	/* Complete o metodo empilhar aqui. 		  
        *  "entrada" sera uma String que possui todos os elementos informados separados por " " (espaco).
        *  Cada elemento possui duas informacoes a serem consideradas, o nome do vegetal e seu peso, separados por ';'.
        */
        for(String parString: entrada.split(" ")){
            String[] parArray = parString.split(";");
            addVegetal(parArray[0], Integer.parseInt(parArray[1]));
        }
    }
    
    public void addVegetal(String nomeVegetal, int pesoVegetal){
        Vegetal vegetal = new Vegetal(nomeVegetal, pesoVegetal);
        CustomStack<Vegetal> pilhaVegetaisProvisoria = new CustomStack();
        
        while(pilhaVegetais.getTop() != null && pilhaVegetais.getTop().peso < vegetal.peso){
            pilhaVegetaisProvisoria.add(pilhaVegetais.remove());
        }
        
        pilhaVegetais.add(vegetal);
        
        while(pilhaVegetaisProvisoria.getTop()!=null){
            pilhaVegetais.add(pilhaVegetaisProvisoria.remove());
        }
    }
    
  	public String toString(){
      	// Deve retornar a Pilha em formato de String
        return pilhaVegetais.toString();
    }
}

public class SolutionVegetais {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        Result result = new Result();
      	String entrada = "";
        while(scan.hasNextLine()) {
          	String nextLine = scan.nextLine();
            if(nextLine.isEmpty()) {
                break;
            }else if(entrada == ""){
            	entrada = nextLine;
            }else{
            	entrada += " " + nextLine;
            }
        }
      	result.empilhar(entrada);
        System.out.println(result);
    }
}