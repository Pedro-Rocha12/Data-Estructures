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
    T elemento;
    Node<T> left, right;
    
    public Node(T e) {
        this.elemento = e;
    }
    
    public String toString() {
        return "" + elemento;
    }
}

class BinaryTree<T>{
    
    private Node<T> raiz;
    private int qtd=0;
    
    private int comparar(T t1, T t2) {
        int r = -1;
        if (t1 instanceof Comparable) {
            r = ((Comparable)t1).compareTo(t2);
        }
        return r;
    }
    
    public boolean existe(T elementoProcurado) {
        boolean r = false;
        Node<T> p = raiz;
        while (p != null) {
            int c = comparar(elementoProcurado, p.elemento);
            if (c==0) {
                r = true;
                break;
            } else if (c < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return r;
    }
    
    public void inserir(T novo) {
        Node<T> novoNo = new Node<T>(novo);
        if (raiz == null) {
            raiz = novoNo;
            qtd++;
        } else {
            Node<T> p = raiz;
            while (p != null) {
                int c = comparar(novo, p.elemento);
                if (c==0) {
                    break;
                } else if (c < 0) {
                    if (p.left != null) {
                        p = p.left;
                    } else {
                        p.left = novoNo;
                        qtd++;
                        break;
                    }
                } else {
                    if (p.right != null) {
                        p = p.right;
                    } else {
                        p.right = novoNo;
                        qtd++;
                        break;
                    }
                }
            }
        }
    }
    
    public int getAltura(){
        return AlturaArvore(raiz)-1;
    }
    
    public int AlturaArvore(Node folha){
        int qtdLeft = 0; 
        int qtdRight = 0;
        
        if(folha == null){
            return 0;
        }else if(folha != null && folha.left == null && folha.right == null){
            return 1;
        }else{
            qtdLeft += AlturaArvore(folha.left);
            qtdRight += AlturaArvore(folha.right);
            
            if(qtdLeft < qtdRight){
                 return qtdRight+1;
            }else if(qtdLeft > qtdRight){
                return qtdLeft+1;
            }else if(qtdLeft == qtdRight){
                return qtdRight+1;
            }else{
                return 0;
            }
       }   
   }
}

class Result {
    public static int treeHeight(String inputValues) {
        BinaryTree<Integer> arvore = new BinaryTree();
        
        String[] elementos = inputValues.split(" ");
        
        for(int i = 0; i < elementos.length; i++){
            arvore.inserir(Integer.parseInt(elementos[i]));
        }
        
		return arvore.getAltura();
    }
}


public class AlturaÁrvore {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));


        String inputValues = bufferedReader.readLine();
        int result = Result.treeHeight(inputValues);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
