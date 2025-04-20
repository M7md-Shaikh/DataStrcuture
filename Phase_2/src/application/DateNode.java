package application;

public class DateNode {
    private Date data;
    private DateNode left;
    private DateNode right;
    private MarNode head;

    // Constructors
    public DateNode() {
        this.data = new Date(); // Initialize with default Date object
        this.head = null;  // Initialize head to null
    }

    public DateNode(Date data) {
        this.data = data;
        this.head = null;  // Initialize head to null
    }

    // Getters and setters
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public DateNode getLeft() {
        return left;
    }

    public void setLeft(DateNode left) {
        this.left = left;
    }

    public DateNode getRight() {
        return right;
    }

    public void setRight(DateNode right) {
        this.right = right;
    }

    public MarNode getHead() {
        return head;
    }

    public void setHead(MarNode head) {
        this.head = head;
    }

}
