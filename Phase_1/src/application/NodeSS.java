package application;

public class NodeSS {
	
    private Martyr data;
    private NodeSS next;

    public NodeSS(Martyr data) {
        this.data = data;
    }

    public Martyr getData() {
        return data;
    }

    public void setData(Martyr data) {
        this.data = data;
    }

    public NodeSS getNext() {
        return next;
    }

    public void setNext(NodeSS next) {
        this.next = next;
    }

}
