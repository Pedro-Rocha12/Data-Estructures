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

    T elemento;
    Node<T> left, right;
    
    public Node(T e) {
        this.elemento = e;
    }
    
    public String toString() {
        return "" + elemento;
    }
}

class Arvore<T>{

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
 
    public boolean verificarAncestral(T ancestral, T descendente) {
        boolean r = false;
        boolean flagAncestral = false;
        Node<T> p = raiz;
        
        while (p != null) {
            if (ancestral.equals(p.elemento)) {
                flagAncestral = true;
            }
            int c = comparar(descendente, p.elemento);
            if (c==0) {
                r = true;
                break;
            } else if (c < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return flagAncestral && r;
    }
    
    public int getAltura(T texto){
        
        boolean r = false;
        Node<T> p = raiz;
        
        if(existe(texto) == true){
            if(p == null){
                return 0;
            }
            while (p != null) {
             int c = comparar(texto, p.elemento);
               if (c==0) {
                    r = true;
                    break;
                } else if (c < 0) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
        }
        return AlturaArvore(p);
    }
    
    public int AlturaArvore(Node<T> novoNode){
        int qtdLeft = 0; 
        int qtdRight = 0;
        
        if(novoNode == null){
            return 0;
        }else if(novoNode != null && novoNode.left == null && novoNode.right == null){
            return 0;
        }else{
            qtdLeft += AlturaArvore(novoNode.left);
            qtdRight += AlturaArvore(novoNode.right);
            
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

    /*
     * Complete the 'nodeGenealogy' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING values
     *  2. STRING nodeA
     *  3. STRING nodeB
     */

    public static String nodeGenealogy(String values, String nodeA, String nodeB) {
        Arvore<String> arvore = new Arvore<String>();
        
        String[] valores = values.split(" ");
        
        for(int i = 0; i < valores.length; i++){
            arvore.inserir(valores[i]);
        }
    
        boolean verificar1 = arvore.verificarAncestral(nodeA, nodeB);
        boolean verificar2 = arvore.verificarAncestral(nodeB, nodeA);
        
        if(arvore.existe(nodeA) == true){
            
        }
               
        int altura1 = arvore.getAltura(nodeA);
        int altura2 = arvore.getAltura(nodeB);
        int diferenca = altura1 - altura2;
        
        if(diferenca < 0){
            diferenca *= -1;
        }
        
        String str = "Pai";
        if(arvore == null){
            return "nao ha parentesco";
        }
        if(verificar1 == false && verificar2 == false){
            return "nao ha parentesco";
        }else{
            if(diferenca == 1){
                return str;
            }else{
                while(diferenca != 1){
                    str += " do Pai";
                    diferenca--;
                }
                return str;
            }
        }

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String treeNodes = bufferedReader.readLine();

        String nodeA = bufferedReader.readLine();

        String nodeB = bufferedReader.readLine();

        String result = Result.nodeGenealogy(treeNodes, nodeA, nodeB);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}