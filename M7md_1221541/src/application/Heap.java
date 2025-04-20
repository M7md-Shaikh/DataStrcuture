package application;

public class Heap {
    private static final int DEF_MAX_HEAP_SIZE = 10;
    private int size;
    private int [] element;
    public Heap() {
        setup(DEF_MAX_HEAP_SIZE);
    }

    public Heap(int maxNum) {
        setup(maxNum);
    }


    private void setup(int maxNum) {
        element = new int [maxNum];
        size = 0;
    }
    public void insert(int newElement) {
        if (isFull()) {
            System.out.println("Heap is full");
        }
        element[size] = newElement;

        int current = size;
        while (current > 0 && element[current] > (element[parent(current)])) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }


    public void maxHeapify(int index) {
        int current = index;
        while (hasLeftChild(current)) {
            int biggerChild = leftChild(current);
            if (hasRightChild(current) && element[rightChild(current)] > (element[biggerChild])) {
                biggerChild = rightChild(current);
            }
            if (element[current] >= (element[biggerChild])) {
                break;
            }
            swap(current, biggerChild);
            current = biggerChild;
        }
    }

    public int removeMax() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return 0;
        }

        int maxElement = element[0];
        element[0] = element[--size];
        maxHeapify(0);
        return maxElement;
    }
    public void clear() {
        size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == element.length;
    }

    public void showStructure() {
        showSubtree(0, 0);
    }
 private void showSubtree(int index, int level) {
if (index < size) {
showSubtree(rightChild(index), level + 1);
for (int i = 0; i < level; i++) {
	System.out.print("   ");
}
            System.out.println(element[index]);
            showSubtree(leftChild(index), level + 1);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private boolean hasLeftChild(int index) {
        return leftChild(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightChild(index) < size;
    }

    private void swap(int index1, int index2) {
        int temp = element[index1];
        element[index1] = element[index2];
        element[index2] = temp;
    }
    public boolean isLeaf(int pos){
        return pos > (size / 2);
    }


    public void minHeapify(int i) {
        int smallest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < element.length && element[leftChild] < element[smallest]) {
            smallest = leftChild;
        }

        if (rightChild < element.length && element[rightChild] < element[smallest]) {
            smallest = rightChild;
        }

        if (smallest != i) {
            swapMin(i, smallest);
            minHeapify(smallest);
        }
    }

    private void swapMin(int i, int j) {
        int temp = element[i];
        element[i] = element[j];
        element[j] = temp;
    }


}