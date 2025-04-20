package application;

public class Stack {

    final static int SIZE = 25;
    Date[] stack;
    int top;

    public Stack() {
        this(SIZE);
    }

    public Stack(int size) {
        stack = new Date[size];
        top = -1;
    }

    public boolean isFull() {
        return top == SIZE - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean push(Date obj) {
        if (isFull()) {
            return false;
        }
        stack[++top] = obj;
        return true;
    }

    public Object pop() {
        if (isEmpty())
            return null;
        return stack[top--];
    }

    public Date peek() {
        if (isEmpty())
            return null;
        return stack[top];
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i].toString());
        }
    }
}
