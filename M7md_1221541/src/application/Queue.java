package application;
public class Queue {
	
	private int front , rear;
	private Object [] queue ;
	private final int SIZE;
	
	public Queue() {
		this(25); // Default size
	}

	public Queue(int size) {
		SIZE = size;
		front = rear = -1;
		queue = new Object[SIZE];
	}
	
	private int nextFront() {
		front = (front + 1) % SIZE;
		return front;
	}
	
	private int nextRear() {
		rear = (rear + 1) % SIZE;
		return rear;
	}
	
	public boolean isEmpty() {
		return front == -1 && rear == -1;
	}
	
	public boolean isFull() {
		return (rear + 1) % SIZE == front;
	}
	
	public boolean enQueue(Object x) {
		if(isFull()) 
			return false;
		if (isEmpty())
			front = rear = 0;
		else
			nextRear();
		queue[rear] = x;
		return true;
	}
	
	public Object deQueue() {
		if(isEmpty())
			return null;
		Object temp = queue[front];
		if (front == rear)
			front = rear = -1;
		else
			nextFront();
		return temp;
	}
	
	public void printQueue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return;
		}
		System.out.println("Queue elements:");
		int tempFront = front;
		while (tempFront != rear) {
			System.out.println(queue[tempFront]);
			tempFront = (tempFront + 1) % SIZE;
		}
		System.out.println(queue[tempFront]); // Print the last element
	}

	public Object peek() {
		if (isEmpty())
			return null;
		return queue[front];
	}
}
