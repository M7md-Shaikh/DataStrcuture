package application;

public class Node {
    public Martyr martyr;
    public Node left;
    public Node right;
    public int height;

    public Node(Martyr martyr) {
        this.martyr = martyr;
        this.height = 1;
    }

    public Martyr getMartyr() {
        return martyr;
    }

    public void setMartyr(Martyr martyr) {
        this.martyr = martyr;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
