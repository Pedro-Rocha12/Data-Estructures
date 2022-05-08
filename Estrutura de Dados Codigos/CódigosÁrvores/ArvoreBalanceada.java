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
                if (c < 0) {
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
        return (calcularAltura(raiz));
    }
    
    public int calcularAltura(Node<T> novoNode){
        int leftAltura = 0;
        int rightAltura = 0;
        if(novoNode == null){
            return -1;
        }else{
            leftAltura = calcularAltura(novoNode.left);
            rightAltura = calcularAltura(novoNode.right);
            
            if(leftAltura > rightAltura){
                return leftAltura + 1;
            }else{
                return rightAltura + 1;
            }
        }
    }
    
    public boolean isBalanceado(Node novoNode){
        int leftAltura = 0;
        int rightAltura = 0;
        
        if(novoNode == null){
            return true;
        }
        
        leftAltura = calcularAltura(novoNode.left);
        rightAltura = calcularAltura(novoNode.right);
        
        if((leftAltura - rightAltura <= 1) && rightAltura - leftAltura <= 1 && isBalanceado(novoNode.left) && isBalanceado(novoNode.right)){
            return true;
        }else{
            return false;
        }
    }
    
    public String isArvoreBalanceada(){
        if(isBalanceado(raiz) == true){
            return "E balanceada";
        }else{
            return "Nao e balanceada";
        }
    }
}

class Result {

    /*
     * Complete the 'NodeInformation' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING values as parameter.
     */

    public static String NodeInformation(String values) {
    Arvore<String> arvore = new Arvore();
        
    for(String elemento: values.split(" ")){
        arvore.inserir(elemento);
    }
    
    return arvore.isArvoreBalanceada();
    
    

    }

}

public class ArvoreBalanceada {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String treeNodes = bufferedReader.readLine();

        String result = Result.NodeInformation(treeNodes);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}