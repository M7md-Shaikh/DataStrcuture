package one;

public class Node {
	
	
	Object data;
	Node next , prev;
	
	
	public Node() {
		
	}
	
	public Node(Object data) {
		this.data=data;
	}

	public Object getData() {
		return data;
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
