package application;


public class Node {
	
	
	Object data;
	Node next , prev;
	CNode nextCO;
	
	public Node() {}
	
	public Node(Object data) {
		this.data=data;
	}

	public Object getData() {
		return data;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public CNode getNextCO() {
		return nextCO;
	}

	public void setNextCO(CNode nextCO) {
		this.nextCO = nextCO;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + "]";
	}

	
}