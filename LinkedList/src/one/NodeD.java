package one;

public class NodeD {

	Object element;
	NodeD next;
	NodeD prev ;
	
	
	public NodeD(Object element) {
	
		this.element = element;
	}
	
	public NodeD() {}
	
	public Object getElement() {
		return element;
	}
	public void setElement(Object element) {
		this.element = element;
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
		return "NodeD [element=" + element + ", next=" + next + ", prev=" + prev + "]";
	}
	
	
}
