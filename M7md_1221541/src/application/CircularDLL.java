package application;

public class CircularDLL {

    Node first;
    int count;

    public CircularDLL() {
        first = null;
        count = 0;
    }

    public Object getFirst() {
        if (first == null)
            return null;
        return first.getData();
    }

    public Object getLast() {
        if (first == null)
            return null;
        return first.getPrev().getData();
    }

    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if (first == null) {
            first = newNode;
            first.setNext(first);
            first.setPrev(first);
        } else {
            Node last = first.getPrev();
            newNode.setNext(first);
            newNode.setPrev(last);
            first.setPrev(newNode);
            last.setNext(newNode);
            first = newNode;
        }
        count++;
    }

    public void addLast(Object element) {
        if (first == null) {
            addFirst(element);
        } else {
            Node last = first.getPrev();
            Node newNode = new Node(element);
            newNode.setPrev(last);
            newNode.setNext(first);
            last.setNext(newNode);
            first.setPrev(newNode);
            count++;
        }
    }

    public boolean removeFirst() {
        if (first == null)
            return false;
        else if (count == 1) {
            first = null;
        } else {
            Node last = first.getPrev();
            Node second = first.getNext();
            last.setNext(second);
            second.setPrev(last);
            first = second;
        }
        count--;
        return true;
    }

    public boolean removeLast() {
        if (first == null)
            return false;
        else if (count == 1) {
            first = null;
        } else {
            Node last = first.getPrev().getPrev();
            Node newLast = last.getNext();
            last.setNext(first);
            first.setPrev(last);
            newLast.setPrev(null);
        }
        count--;
        return true;
    }

    public void printList() {
        if (first == null)
            return;
        Node current = first;
        do {
            System.out.println(current.getData());
            current = current.getNext();
        } while (current != first);
    }


}