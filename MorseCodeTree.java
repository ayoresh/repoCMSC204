package sample;
import com.sun.source.tree.Tree;

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{

    public TreeNode<String> root;

    public TreeNode<String> getRoot(){
        return root;
    }

    public void setRoot(TreeNode<String> x){
        root = new TreeNode<String>(x);
    }

    public MorseCodeTree(){
        root = new TreeNode<String>("");
        buildTree();
    }

    @Override
    public void buildTree(){
        String[] letters = {"e", "t", "i","a","n","m","s","u","r","w","d","k","g","o","h","v","f","l","p","j","b","x","c","y","z","q"};
        String[] code = {".","-","..",".-","-.","--","...","..-",".-.",".--","-..","-.-","--.","---","....","...-","..-.",".-..",".--.",".---","-...","-..-","-.-.","-.--","--..","--.-"};
        int lettersLength = letters.length;
        int counter = 0;

        while(counter < lettersLength){
            setRoot(insert(code[counter], letters[counter]).getRoot());
            counter++;
        }

    }

    public void addNode(TreeNode<String> r, String c, String l){
        if(c.equals("")){
            return;
        }

        if(c.charAt(0) == '.'){
            if(r.left == null){
                r.left = new TreeNode<String>(l);
            }

            addNode(r.left, c.substring(1), l);
            return;
        }

        if(r.right == null){
            r.right = new TreeNode<String>(l);
            addNode(r.right, c.substring(1),l);
            return;
        }
    }

    public LinkedConverterTreeInterface<String> insert(String c, String r){
        TreeNode<String> temp = root;
        addNode(temp, c, r);
        return this;
    }

    public String fetchNode(TreeNode<String> root, String c){
        if(c.equals("")){
            return root.data;
        }
        if(c.charAt(0) == '.'){
            return fetchNode(root.left, c.substring(1));
        }
        if(c.charAt(0) == '-'){
            return fetchNode(root.right, c.substring(1));
        }
        return " ";

    }

    @Override
    public ArrayList<String> toArrayList(){
        ArrayList<String> toReturn = new ArrayList<String>();
        LNRoutputTraversal(root, toReturn);
        return toReturn;
    }

    @Override
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> l){
        if(root != null){
            LNRoutputTraversal(root.getLeft(), l);
            l.add(root.getData());
            LNRoutputTraversal(root.getRight(), l);
        }
    }

    public LinkedConverterTreeInterface<String> delete(String d) throws UnsupportedOperationException{
        return null;
    }
    public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException{
        return null;
    }

    public String fetch(String c){
        TreeNode<String> temp = root;
        return fetchNode(temp, c);
    }

}
