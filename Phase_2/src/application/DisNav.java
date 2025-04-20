package application;

public class DisNav {
    private Stack inOrderStack = new Stack();
    private Stack previousStack = new Stack();


    public void pushLeftNodes(DisNode node) {
        while (node != null) {
            inOrderStack.push(node);
            node = node.getLeft();
        }
    }

    public DisNode next() {
        if (!inOrderStack.isEmpty()) {
            DisNode current = (DisNode) inOrderStack.pop();
            previousStack.push(current);
            pushLeftNodes(current.getRight());
            return current;
        }
        return null; 
    }

    public DisNode previous() {
        if (!previousStack.isEmpty()) {
            DisNode current = (DisNode) previousStack.pop();
            if (!inOrderStack.isEmpty() && current != inOrderStack.peek()) {
                inOrderStack.push(current);
            }
            return current;
        }
        return null;
    }
}
