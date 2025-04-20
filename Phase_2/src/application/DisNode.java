package application;

public class DisNode {
    private District data;
    private DisNode left;
    private DisNode right;
    private LocTree locTree;

    public DisNode() {}

    public DisNode(District data) {
        this.data = data;
        this.locTree = new LocTree(); 
    }

    public District getData() {
        return data;
    }

    public void setData(District data) {
        this.data = data;
    }

    public DisNode getLeft() {
        return left;
    }

    public void setLeft(DisNode left) {
        this.left = left;
    }

    public DisNode getRight() {
        return right;
    }

    public void setRight(DisNode right) {
        this.right = right;
    }

    public LocTree getLocTree() {
        return locTree;
    }

    public void setLocTree(LocTree locTree) {
        this.locTree = locTree;
    }
}
