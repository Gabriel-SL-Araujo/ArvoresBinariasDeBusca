package RBTreeGenerica;

public class RBNode<T extends Comparable<T>>{
    private RBNode<T> previous;
    private RBNode<T> left;
    private RBNode<T> right;
    private T info;
    private int color = 0; //0 is red and 1 is black 
    private boolean isNil = false;

    public RBNode() {
        this.info = null;
        this.color = 1; // NIL é sempre preto
        this.previous = null;
        this.left = null;
        this.right = null;
        this.isNil = true;
    }

    public RBNode(T info) {
        this.info = info;
        this.color = 0;
        this.previous = null;
        this.left = null;
        this.right = null;
        this.isNil = false;
    }

    public RBNode<T> getPrevious() {
        return previous;
    }
    public void setPrevious(RBNode<T> previous) {
        this.previous = previous;
    }
    public RBNode<T> getLeft() {
        return left;
    }
    public void setLeft(RBNode<T> left) {
        this.left = left;
    }
    public RBNode<T> getRight() {
        return right;
    }
    public void setRight(RBNode<T> right) {
        this.right = right;
    }
    public T getInfo() {
        return info;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public boolean isNull() {
        return isNil || info == null;
    }

}