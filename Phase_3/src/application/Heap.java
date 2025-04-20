package application;

public class Heap {
	private static final int DEF_MAX_HEAP_SIZE = 10;
	private int size;
	private Martyr[] element;

	public Heap() {
		setup(DEF_MAX_HEAP_SIZE);
	}

	public Heap(int maxNum) {
		setup(maxNum);
	}

	private void setup(int maxNum) {
		element = new Martyr[maxNum];
		size = 0;
	}

	public void insert(Martyr newElement) {
		if (isFull()) {
			System.out.println("Heap is full");
			return;
		}
		element[size] = newElement;
		int current = size;
		while (current > 0 && element[current].getAge() > element[parent(current)].getAge()) {
			swap(current, parent(current));
			current = parent(current);
		}
		size++;
	}

	public Martyr removeMax() {
		if (isEmpty()) {
			System.out.println("Heap is empty");
			return null;
		}
		Martyr maxElement = element[0];
		element[0] = element[--size];
		maxHeapify(0);
		return maxElement;
	}

	private void maxHeapify(int index) {
		int largest = index;
		int left = leftChild(index);
		int right = rightChild(index);

		if (left < size && element[left].getAge() > element[largest].getAge()) {
			largest = left;
		}

		if (right < size && element[right].getAge() > element[largest].getAge()) {
			largest = right;
		}

		if (largest != index) {
			swap(index, largest);
			maxHeapify(largest);
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == element.length;
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int leftChild(int i) {
		return 2 * i + 1;
	}

	private int rightChild(int i) {
		return 2 * i + 2;
	}

	private void swap(int i, int j) {
		Martyr temp = element[i];
		element[i] = element[j];
		element[j] = temp;
	}
}
