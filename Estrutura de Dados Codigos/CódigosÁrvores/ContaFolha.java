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


class BinaryNode<T>{
    T elemento;
    BinaryNode<T> left, right;
    
    public BinaryNode(T e) {
        this.elemento = e;
    }
    
    public String toString() {
        return "" + elemento;
    }
}

class BinaryThree<T>{
    private BinaryNode<T> raiz;
    private int qtd = 0;
    
    
    private int comparar(T t1, T t2) {
        int r = -1;
        if (t1 instanceof Comparable) {
            r = ((Comparable)t1).compareTo(t2);
        }
        return r;
    }
    
    public boolean existe(T elementoProcurado) {
        boolean r = false;
        BinaryNode<T> p = raiz;
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
        BinaryNode<T> novoNo = new BinaryNode<T>(novo);
        if (raiz == null) {
            raiz = novoNo;
            qtd++;
        } else {
            BinaryNode<T> p = raiz;
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
    public int qtdFolhas(){
        return contarFolhas(raiz);
    }
    
     public int contarFolhas(BinaryNode<T> teste){
         
        if(teste == null){
            return 0;
        }
        
        if(teste.left == null && teste.right == null){
            return 1;
        }else{
            return contarFolhas(teste.left) + contarFolhas(teste.right);
        }
        
    }
}

class Result {
    public static int numberOfLeafs(String inputValues) {
        
        BinaryThree<Integer> arvore = new BinaryThree();
        String[] elemento = inputValues.split(" ");
        
        for(int i = 0; i < elemento.length; i++){
            arvore.inserir(Integer.parseInt(elemento[i]));
        }
        int numero = arvore.qtdFolhas();
        
		return numero;
    }
}


public class ContaFolha {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));


        String a = bufferedReader.readLine();
        int result = Result.numberOfLeafs(a);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}