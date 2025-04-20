package application;

//this class to comparable the repetition and save ascii and make tree 
public class HeapEntry implements Comparable<HeapEntry> {

	// attributes
	private int ascii;
	private int repetition;
	private HeapEntry left;
	private HeapEntry right;

	// constructor by ascii code and repetition
	public HeapEntry(int ascii, int repetition) {
		this.ascii = ascii;
		this.repetition = repetition;
	}

	public HeapEntry() {
	}

	public int getAscii() {
		return ascii;
	}

	public void setAscii(int ascii) {
		this.ascii = ascii;
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public HeapEntry getLeft() {
		return left;
	}

	public void setLeft(HeapEntry left) {
		this.left = left;
	}

	public HeapEntry getRight() {
		return right;
	}

	public void setRight(HeapEntry right) {
		this.right = right;
	}

	@Override
	public int compareTo(HeapEntry o) {
		return Integer.compare(repetition, o.repetition);
	}

	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
}
