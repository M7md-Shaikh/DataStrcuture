package one;

public class DoubleLinkedList {

	NodeD first , last;
	static int count = 0;
	
	public DoubleLinkedList() {}
	
	
	public Object getFirst() {
		if (first == null )
			return null;
		return first.getElement();
	}
	
	
	public Object getLast() {
		if (last == null )
			return null;
		return last.getElement();
	}
	
	
	public void addFirst(Object element) {
		NodeD newNode = new NodeD(element);
		if(first == null)
			first = last = newNode;
		else {
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
		}
		count++;
	}
	
	
	public void addLast(Object element ) {
		NodeD newNode = new NodeD(element);
		if(last == null)
			last = first = newNode;
		else {
			newNode.prev = last;
			last.next = newNode;
			last = newNode;
		}
		count++;
	}
	
	
	public void add(Object element , int index ) {
		NodeD newNode;
		if(count < 0)
			addFirst(element);
		else if (index > count )
			addLast(element);
		else{
			newNode = new NodeD(element);
			NodeD current = first;
			for (int i =0 ; i<index-1; i++)
				current = current.next;
			newNode.next = current.next;
			newNode.prev = current;
			current.next = newNode;
			newNode.next.prev=newNode;
			count++;
		}
	}
	
	
	public boolean removeFirst() {
		if (count < 0)
			return false;
		else if (count == 0)
			first = last = null;
		else {
			NodeD temp = first ;
			first = first.next;
			first.prev = null;
			temp.next = null;
		}
		count--;
		return true;
	}
	
	
	public boolean removeLast() {
		if (count < 0)
			return false;
		else if (count == 0)
			first = last = null;
		else {
			NodeD temp = last ;
			last = last.prev;
			last.next = null;
			temp.prev = null;
		}
		count--;
		return true;
	}
	
	
	public boolean remove(int index) {
		if(index < 0 || index > count)
			return false;
		else if (count == 0)
			return removeFirst();
		else if(index == count)
			return removeLast();
		else {
			NodeD current = first;
			for (int i =0 ; i< index -1 ; i++)
				current = current.next;
			NodeD temp = current.next;
			current.next = temp.next;
			temp.next.prev = current;
			temp.next = null;
			temp.prev = null;
			count -- ;
		}
		return true;
	}
	
	
	public boolean remove(Object element) {
		if(count < 0 )
			return false;
		else if(first.getElement().equals(element))
			return removeFirst();
		else if(last.getElement().equals(element))
			return removeLast();
		else {
			NodeD ptr = first.next;
			for(int i=0 ; i<count -1 ; i++)
				if(ptr.getElement().equals(element))
					return remove(i);
				ptr=ptr.next;
		}
		return false;
	}
	
	
	public void printList() {
	    NodeD current = first;
	    for(int i=0 ;i<count;i++ ) {
	        System.out.println(current.getElement() +" ");
	        current = current.getNext();
	    }
	}
}
