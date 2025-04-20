package one;


public class LinkedList {
	
	Node first , last;
	static int count = 0;
	
	public LinkedList() {
		
	}
	
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
	
	public void addFirst(Object data) {
		Node node = new Node(data);
		if (first == null)
			last=first=null;
		else {
			node.next = first;
			first = node;
		}
		count++;
	}
	
	
	public void addLast(Object data) {
		Node node = new Node(data);
		if (last == null)
			last=first=null;
		else {
			last.next = node;
			last = node;
		}
		count++;
	}
	
	public void add (Object data , int index ) {
		if (index == 0)
			addFirst(data);
		else if (index >= count )
			addLast(data);
		else {
			
			Node current = first;
			for(int i =0 ; i<index-1;i++)
				current = current.next;
			Node temp = current.next;
			current.next = new Node(data);
			(current.next).next = temp;
			count++;
		}
	}
	
	public boolean removeFirst() {
		if (count < 0 )
			return false;
		else if (count == 0 )
			first = last = null;
		else {
			Node temp = first;
			first= first.next;
			temp.next=null;
			count--;
		}
		return true;
	}
	
	public boolean removeLast() {
		if (count < 0 )
			return false;
		else if (count == 0 )
			last = first = null;
		else {
			Node current = first ;
			for(int i =0 ; i<count-1;i++)
				current = current .next;
			current.next =null;
			last=current;
			count--;
		}
		return true;
	}
	
	public boolean remove(int index) {
		if (count <0 || count <index)
			return false;
		else if(index ==0 )
			removeFirst();
		else if(index == count)
			removeLast();
		else {
			Node ptr = first;
			for(int i =0 ; i<index -1; i++)
				ptr = ptr.next;
			Node temp = ptr.next;
			ptr = ptr.next;   // ptr = temp.next
			temp.next = null;
			count--;
		}
		return true;
	}
	
	public boolean remove(Object data) {
		if (first == null )
			return false;
		else {
			if (first.getData().equals(data))
				return removeFirst();
			if(last.getData().equals(data))
				return removeLast();
			else {
				Node ptr = first.next;
				for (int i =0 ; i< count ; i++)
					if (ptr.getData().equals(data))
						return remove(i);
					ptr = ptr.next;
			}
		}
		return false;
	}
	
	public void printList() {
	    Node current = first;
	    for(int i=0 ;i<count;i++ ) {
	        System.out.println(current.getData() +" ");
	        current = current.getNext();
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList l = new LinkedList();
		l.addFirst("M7md");
		l.addLast("Shaikh");
		l.printList();
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public void setLast(Node last) {
		this.last = last;
	}

	
}
