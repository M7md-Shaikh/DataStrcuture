package application;

public class CircularSLL {
    Node first;

    public CircularSLL() {
        first = null;
    }

    public Object getFirst() {
        if (first == null)
            return null;
        return first.getData();
    }

    public void addFirst(Object data) {
        Node newNode = new Node(data);
        if (first == null) {
            first = newNode;
            first.setNext(first);
        } else {
            newNode.setNext(first);
            Node last = getLastNode();
            last.setNext(newNode);
            first = newNode;
        }
    }

    public void addLast(Object data) {
        Node newNode = new Node(data);
        if (first == null) {
            first = newNode;
            first.setNext(first);
        } else {
            Node last = getLastNode();
            last.setNext(newNode);
            newNode.setNext(first);
        }
    }

    public boolean removeFirst() {
        if (first == null)
            return false;
        else {
            Node last = getLastNode();
            last.setNext(first.getNext());
            first = first.getNext();
            return true;
        }
    }

    public boolean removeLast() {
        if (first == null)
            return false;
        else if (first.getNext() == first) {
            first = null;
        } else {
            Node current = first;
            while (current.getNext().getNext() != first) {
                current = current.getNext();
            }
            current.setNext(first);
        }
        return true;
    }


    private Node getLastNode() {
        Node current = first;
        while (current.getNext() != first) {
            current = current.getNext();
        }
        return current;
    }

}