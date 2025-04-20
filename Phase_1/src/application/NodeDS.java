package application;

public class NodeDS {
    private Location data;
    private NodeDS next;
    private NodeSS nextM;

	public NodeSS getNextM() {
		return nextM;
	}

	public void setNextM(NodeSS nextM) {
		this.nextM = nextM;
	}

	public NodeDS(Location data) {
        this.data = data;
    }

    public Location getData() {
        return data;
    }

    public void setData(Location data) {
        this.data = data;
    }

    public NodeDS getNext() {
        return next;
    }

    public void setNext(NodeDS next) {
        this.next = next;
    }
}
