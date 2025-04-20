package application;

public class LocNode {
    private Location data;
    private LocNode left;
    private LocNode right;
    private DateTree dateTree;
    
    public LocNode(Location locName) {
        this.data = locName;
        this.dateTree = new DateTree();
    }

    public Location getData() {
        return data;
    }

    public void setData(Location data) {
        this.data = data;
    }

    public LocNode getLeft() {
        return left;
    }

    public void setLeft(LocNode left) {
        this.left = left;
    }

    public LocNode getRight() {
        return right;
    }

    public void setRight(LocNode right) {
        this.right = right;
    }

    public DateTree getDateTree() {
        return dateTree;
    }

    public void setDateTree(DateTree dateTree) {
        this.dateTree = dateTree;
    }

}
