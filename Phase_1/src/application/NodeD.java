package application;

public class NodeD {
    private District data;
    private NodeD next;
    private NodeD prev;
    private NodeDS nextLoc;

    public NodeD() {};
    
    public NodeDS getNextLoc() {
		return nextLoc;
	}

	public void setNextLoc(NodeDS nextLoc) {
		this.nextLoc = nextLoc;
	}

	public NodeD(District data) {
        this.data = data;
    }

    public District getData() {
        return data;
    }

    public void setData(District data) {
        this.data = data;
    }

    public NodeD getNext() {
        return next;
    }

    public void setNext(NodeD next) {
        this.next = next;
    }

    public NodeD getPrev() {
        return prev;
    }

    public void setPrev(NodeD prev) {
        this.prev = prev;
    }
   
	@Override
	public String toString() {
		return "NodeD [data=" + data + ", next=" + next + ", prev=" + prev +"]";
	}
    
    
}
