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
 
    public int verificarAncestral(T ancestral, T descendente) {
        int grau = 0;
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
            if(flagAncestral == true){
                grau++;
            }
        }
        
        if(flagAncestral == true && r == true){
            return grau;
        }else{
            return -1;
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
    
        int grauAncestral = arvore.verificarAncestral(nodeA, nodeB);
        
        if(grauAncestral == -1){
            grauAncestral = arvore.verificarAncestral(nodeB, nodeA);
        }
          
        String str = "Pai";
        
        if(grauAncestral == -1){
            return "nao ha parentesco";
        }else if(grauAncestral == 1){
            return str;
        }else{
            while(grauAncestral != 1){
                str += " do Pai";
                grauAncestral--;
            }
            return str;
        }
    }
}

public class IntAncestralÁrvore {
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