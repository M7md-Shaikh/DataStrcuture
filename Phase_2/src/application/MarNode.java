package application;

public class MarNode {
    private Martyr martyr;
    private MarNode next;

    
    public MarNode() {}
    public MarNode(Martyr martyr) {
        this.martyr = martyr;
        this.next = null;
    }

    public Martyr getMartyr() {
        return martyr;
    }

    public void setMartyr(Martyr martyr) {
        this.martyr = martyr;
    }

    public MarNode getNext() {
        return next;
    }

    public void setNext(MarNode next) {
        this.next = next;
    }
}
