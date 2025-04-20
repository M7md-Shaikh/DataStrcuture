package application;

public class Stack {

	final static int SIZE= 25;
    Object [] Stack;
    int Top;


    public Stack(){
    	this(SIZE);
    }

    public Stack(int size) {
    	Stack= new Object[size];
    	Top= -1;
    }

    public boolean isFull(){
    	return Top== SIZE-1;
    }

    public boolean isEmpty(){
    	return Top== -1;
    }

    public boolean push( Object obj){
    	if (isFull()){
    		return false;
    	}
    	Stack [++ Top]= obj;
    	return true;
    }

    public Object pop(){
    	if(isEmpty ())
    		return null;
    	return Stack[Top--] ;
    }

    public Object peek(){
    	if(isEmpty())
    		return null;
    	return Stack[Top--];
    }

    public static int getSize() {
		return SIZE;
	}

    public void printStack( Stack s1){

    	Stack s2= new Stack(s1.getSize());
    	while(!s1.isEmpty()){
    		System.out.println(s1.peek().toString());
    		s2.push(s1.pop());
    	}

    	while(!s2.isEmpty()) {
    		s1.push(s2.pop());
    	}
    }
}