package application;

public class CNode {

	Object key;
	int next;
	
	public CNode() {}
	
	public CNode(Object key , int next) {
		this.key = key;
		this.next = next;
	}
	
	public CNode(Object key) {
		this.key = key;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}
	

	@Override
	public String toString() {
		return "CNode [data=" + key + ", next=" + next + "]";
	}
	
}
