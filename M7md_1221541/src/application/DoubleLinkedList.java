package application;

public class DoubleLinkedList {

	Node first , last;
	static int count = 0;

	public DoubleLinkedList() {}


	public Object getFirst() {
		if (first == null )
			return null;
		return first.getData();
	}


	public Object getLast() {
		if (last == null )
			return null;
		return last.getData();
	}


	public void addFirst(Object element) {
		Node newNode = new Node(element);
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
		Node newNode = new Node(element);
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
		Node newNode;
		if(count < 0)
			addFirst(element);
		else if (index > count )
			addLast(element);
		else{
			newNode = new Node(element);
			Node current = first;
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
			Node temp = first ;
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
			Node temp = last ;
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
			Node current = first;
			for (int i =0 ; i< index -1 ; i++)
				current = current.next;
			Node temp = current.next;
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
		else if(first.getData().equals(element))
			return removeFirst();
		else if(last.getData().equals(element))
			return removeLast();
		else {
			Node ptr = first.next;
			for(int i=0 ; i<count -1 ; i++)
				if(ptr.getData().equals(element))
					return remove(i);
				ptr=ptr.next;
		}
		return false;
	}


	public void reverseRec(Node node){
		if (node.next == null){
			first=node;
			return ;
		}
		reverseRec(node.next);
		Node temp = node.next;
		temp.next = node;
		node.prev = temp;
		node.next = null;
	}

	public void printList() {
	    Node current = first;
	    for(int i=0 ;i<count;i++ ) {
	        System.out.println(current.getData() +" ");
	        current = current.getNext();
	    }
	}
}