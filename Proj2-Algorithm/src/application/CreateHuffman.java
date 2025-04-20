package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Queue;

public class CreateHuffman {

	/*
	 * Read File and Create Huffman Codes
	 */

	private static Huffman[] table = new Huffman[256];
	private static HeapEntry root;
	private int[] array;
	private int size;

	// Traverse Huffman Tree to get huffman codes
	public static void Traversal(HeapEntry node, String code, Huffman[] huffmanCodeArray) {

		if (node == null) // if the file is empty ==> the root can be zero
			return;

		if (!node.isLeaf()) {

			Traversal(node.getLeft(), code + '0', huffmanCodeArray);
			Traversal(node.getRight(), code + '1', huffmanCodeArray);

		} else {
			Huffman huffmanResult = new Huffman(node.getAscii(), code, node.getRepetition(), code.length());
			huffmanCodeArray[node.getAscii()] = huffmanResult;

		}

	}

	// Create Header
	public static void createHeader(ArrayList<Integer> list, ArrayList<Integer> finalList, HeapEntry root) {

		// "preorder"
		if (root == null)
			return;

		if (root.isLeaf()) { // If Node is leaf

			list.add(1);
//            System.out.println((char)root.getAscii()+" : 1 leaf ");
			finalList.add(root.getAscii());
			return;

		}

		list.add(0); // If it is not leaf
//        System.out.println((char)root.getAscii()+" : 0 not leaf ");
		createHeader(list, finalList, root.getLeft());
		createHeader(list, finalList, root.getRight());

	}

	// Build huffman Tree After Reading Header
	public static HeapEntry build(Queue<HeapEntry> queue, Queue<HeapEntry> finalQueue, HeapEntry root) {

		// preorder
		if (!queue.isEmpty()) {

			HeapEntry temp = queue.poll();

			if (temp.getAscii() == 0) { // It is not leaf

				root = temp;

				root.setLeft(build(queue, finalQueue, root.getLeft()));
				root.setRight(build(queue, finalQueue, root.getRight()));

				return root;

			} else { // If its leaf

				HeapEntry newTemp = finalQueue.poll();

				return newTemp;

			}

		}

		return root;

	}

	// Read File before compressing to calculate the frequency by buffer (8 byte)
	// using channel
	public void readFile(File file) throws IOException { // which is just to get the "frequencies"
		array = new int[256];// to calculate the frequency
		FileInputStream inputStream = new FileInputStream(file);
		FileChannel channel = inputStream.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(8);

		int numOfBytes = 0;

		while (numOfBytes != -1) {

			numOfBytes = channel.read(buffer);
			buffer.flip();
			for (int i = 0; i < numOfBytes; i++) {
				int b = buffer.get();
				if (b < 0) {
					b += 256;
				}
				array[b]++;
			}
		}
		inputStream.close();
	}

	// by filling the heap, then we make huffman tree
	public void buildHuffManTree() {

		if (array == null) {
			return;
		}

		MinHeap heap = new MinHeap(257);

		// Insert Characters to the heap
		for (int i = 0; i < array.length; i++) { // add the "existing" chars of the file ==> to the "heap"

			if (array[i] != 0) {
				heap.add(new HeapEntry(i, array[i]));
				size++; // heap.getSize()

			}
		}

		// Creating huffman tree
		for (int i = 1; i < size; i++) {

			HeapEntry z = new HeapEntry();

			HeapEntry a = heap.removeMin();
			HeapEntry b = heap.removeMin();

			z.setLeft(a);
			z.setRight(b);

			z.setRepetition(a.getRepetition() + b.getRepetition());

			heap.add(z);

		}

		root = heap.getMin();

		Traversal(root, "", table); // to get and save the codes in the huffman table

	}

	public Huffman[] getTable() {
		return table;
	}

	public HeapEntry getRoot() {
		return root;
	}

	public void setRoot(HeapEntry root) {
		this.root = root;
	}

}
