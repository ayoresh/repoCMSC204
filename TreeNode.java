package sample;

public class TreeNode<T> {

    public TreeNode<T> right, left;
    public T data;



    public TreeNode(TreeNode<T> n){
        data = n.data;
        left = n.left;
        right = n.right;
    }

    public TreeNode(T x){
        data = x;
        left = null;
        right = null;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData(){
        T toReturn;
        toReturn = data;
        return toReturn;
    }

}
