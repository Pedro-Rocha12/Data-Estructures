import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class Node{
    private Node left;
    private Node right;
    private Node dad;
    private int key;
    private int balance;

    public Node(int k) {
        setLeft(setRight(setDad(null)));
        setBalance(0);
        setKey(k);
    }

    public String toString() {
        return Integer.toString(getKey());
    }

    public int getKey() {
        return key;
    }

    public void setKey(int newKey) {
        this.key = newKey;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int newBalance) {
        this.balance = newBalance;
    }

    public Node getDad() {
        return dad;
    }

    public Node setDad(Node newDad) {
        this.dad = newDad;
        return dad;
    }

    public Node getRight() {
        return right;
    }

    public Node setRight(Node newRight) {
        this.right = newRight;
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node newLeft) {
        this.left = newLeft;
    }
}

class BinaryTree{

    private Node root;

    public void add(int elem) {
        Node n = new Node(elem);
        addAVL(this.root, n);
    }
    
    public void addAVL(Node toCompare, Node toInsert){
        if(toCompare == null){
            this.root = toInsert;
        }else{
            if(toInsert.getKey() < toCompare.getKey()){
                
                if(toCompare.getLeft() == null){
                    toCompare.setLeft(toInsert);
                    toInsert.setDad(toCompare);
                    verifyBalance(toCompare);
                }else{
                    addAVL(toCompare.getLeft(), toInsert);
                }
                
            }else if(toInsert.getKey() > toCompare.getKey()){
                
                if(toCompare.getRight() == null){
                    toCompare.setRight(toInsert);
                    toInsert.setDad(toCompare);
                    verifyBalance(toCompare); 
                }else{
                     addAVL(toCompare.getRight(), toInsert);
                }
                
            }else{
                //node exist
            }   
        }
    }
    
    private int height(Node actual) {
        if (actual == null) {
            return -1;
        }

        if (actual.getLeft() == null && actual.getRight() == null) {
            return 0;
       
        } else if (actual.getLeft() == null) {
            return 1 + height(actual.getRight());
        
        } else if (actual.getRight() == null) {
            return 1 + height(actual.getLeft());
        
        } else {
            return 1 + Math.max(height(actual.getLeft()), height(actual.getRight()));
        }
    }
    
    public void verifyBalance(Node actual) {
        setBalance(actual);
        int balance = actual.getBalance();

        if (balance == -2) {

            if (height(actual.getLeft().getLeft()) >= height(actual.getLeft().getRight())) {
                actual = rightRotation(actual);

            } else {
                actual = doubleLeftRightRotation(actual);
            }

        } else if (balance == 2) {

            if (height(actual.getRight().getRight()) >= height(actual.getRight().getLeft())) {
                actual = leftRotation(actual);

            } else {
                actual = doubleRightLeftRotation(actual);
            }
        }

        if (actual.getDad() != null) {
            verifyBalance(actual.getDad());
        } else {
            this.root = actual;
        }
    }
    
    private void setBalance(Node node) {
        node.setBalance(height(node.getRight()) - height(node.getLeft()));
    }
    
    public Node leftRotation(Node initial) {

        Node right = initial.getRight();
        right.setDad(initial.getDad());

        initial.setRight(right.getLeft());

        if (initial.getRight() != null) {
            initial.getRight().setDad(initial);
        }

        right.setLeft(initial);
        initial.setDad(right);

        if (right.getDad() != null) {

            if (right.getDad().getRight() == initial) {
                right.getDad().setRight(right);
            
            } else if (right.getDad().getLeft() == initial) {
                right.getDad().setLeft(right);
            }
        }

        setBalance(initial);
        setBalance(right);

        return right;
    }

    public Node rightRotation(Node initial) {

        Node left = initial.getLeft();
        left.setDad(initial.getDad());

        initial.setLeft(left.getRight());

        if (initial.getLeft() != null) {
            initial.getLeft().setDad(initial);
        }

        left.setRight(initial);
        initial.setDad(left);

        if (left.getDad() != null) {

            if (left.getDad().getRight() == initial) {
                left.getDad().setRight(left);
            
            } else if (left.getDad().getLeft() == initial) {
                left.getDad().setLeft(left);
            }
        }

        setBalance(initial);
        setBalance(left);

        return left;
    }

    public Node doubleLeftRightRotation(Node initial) {
        initial.setLeft(leftRotation(initial.getLeft()));
        return rightRotation(initial);
    }

    public Node doubleRightLeftRotation(Node initial) {
        initial.setRight(rightRotation(initial.getRight()));
        return leftRotation(initial);
    }
    
	private String treeSearch(Node actual) {
		if (actual == null) {
			return "(null)";
		}
		if(actual.getLeft() == null && actual.getRight() == null )
			return actual.toString();
		return String.format("l(%s) - %s - r(%s)", treeSearch(actual.getLeft()), actual.toString(),                   treeSearch(actual.getRight()));
	}


    public String toString() {
        return treeSearch(root);
    }
}

class Result {
    
    private static BinaryTree tree;

    public static void addToTree(String values) {
        
        tree = new BinaryTree();
        
        for(String inputValues: values.split(" ")){
            tree.add(Integer.parseInt(inputValues));
        }   

    }

    public static String printTree() {
        
        return tree.toString();
    
    }

}

public class ÁrvoreAVL {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String addValues = bufferedReader.readLine();

        Result.addToTree(addValues);

        String result = Result.printTree();

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}