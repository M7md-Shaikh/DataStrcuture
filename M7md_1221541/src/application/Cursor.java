package application;

public class Cursor {

	CNode [] CS ;
	final static int SIZE =100;
	int size;

	public Cursor(){
		this(SIZE);
	}

	public Cursor(int size) {
		CS = new CNode[size];
		for (int i =0 ; i<size-1; i++)
			CS[i] = new CNode(null , i+1);
		CS[size-1] = new CNode(null,0);
		size = 0;
	}

	public boolean isFull() {
		return CS[0].next == 0;
	}

	public int cursorAlloc () {
		if (isFull()) {
			System.out.println("The list is full ");
			return -1;
		}
		int p = CS[0].next;
		CS[0].next = CS[p].next;
		CS[p].next = 0;
		return p;
	}

	public void cursorFree ( int cell ) {
		CS[cell].key = null;
		CS[cell].next = CS[0].next;
		CS[0].next = cell;
	}

	public boolean isEmptey(int header) {
		return CS[header].next == 0;
	}

	public boolean isEmpteyList() {
		return CS[0].next == 0;
	}

	public int createList() {
		return cursorAlloc();
	}


	public void addFirst(int header , Object key) {
		if (isFull())
			throw new IllegalArgumentException("Cursor Space Is Full");
		int cell = cursorAlloc();
		CS[cell].setKey(key);
		CS[cell].next=CS[header].next;
		CS[header].next = cell;
	}


	public boolean removeFirst(int header) {
		if(isEmptey(header))
			return false;
		int temp = CS[header].next;
		CS[header].next = CS[temp].next;
		cursorFree(temp);
		return true;
	}

	public int find(int header , Object key) {
		int current = CS[header].next;
		while(current !=0 && !(CS[current].getKey().equals(key)))
			current = CS[current].next;
		if(current == 0)
			return -1;

		return current;
	}


	private int findPrev(int header , Object key) {
		int current = header ;
		while(CS[current].next != 0 && !(CS[CS[current].getNext()].getKey().equals(key)))
			current = CS[current].next;
		if(CS[current].next ==0 )
			return -1;
		return current;
	}


	public boolean removeObject(int header , Object key) {
		int current = find(header,key);
		if(current != -1) {
			int prev = findPrev(header,key);
			CS[prev].next = CS[current].next;
			cursorFree(current);
			return true;
		}
		return false;
	}

	public void freeList(int header) {
		while (!(isEmptey(header)))
				removeFirst(header);
	}


	public void addLast(Object key , int header) {
		if (isFull())
			throw new IllegalArgumentException("Cursor Space Is Full");
		int current = header;
		while(CS[current].next != 0 )
			current = CS[current].next;
		int cell = cursorAlloc();
		CS[cell].setKey(key);
		CS[cell].next=0;
		CS[current].next = cell;

	}


	public boolean removeLast(int header) {
		if(isEmptey(header))
			return false;
		int prev = header;
		int current = CS[header].next;
		while(CS[current].next != 0){
			prev = current;
			current = CS[current].next;
		}
		CS[prev].next=0;
		cursorFree(current);
		return true;
	}


	public void addObjectAtPosition(Object key, int position) {
	    if (isFull())
	        throw new IllegalArgumentException("Cursor Space Is Full");
	    int current = 0;
	    int count = 1;
	    while (current != 0 && count < position) {
	        current = CS[current].next;
	        count++;
	    }
	    if (count != position)
	        throw new IllegalArgumentException("Invalid position");

	    int cell = cursorAlloc();
	    CS[cell].setKey(key);
	    CS[cell].next = CS[current].next;
	    CS[current].next = cell;
	}


	public String printPrev(Object key, int header) {
	    int prev = findPrev(header, key);
	    if (prev != -1) {
	        return ("Previous node of key "+key + " is: " + CS[prev].getKey());
	    } else {
	      return ("Key "+key+" not found or it is the first element in the list.");
	    }
	}

}