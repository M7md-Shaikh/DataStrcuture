package application;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Decompress {

	private String ext = "";
	private HeapEntry root = new HeapEntry();
	private Huffman[] table = new Huffman[256];
	private File newDeCompressedFile;

	// Convert integer(decimal number) to binary
	public static StringBuffer getBinary(int decNum) {

		StringBuffer sBuf = new StringBuffer();

		int temp;
		while (decNum > 0) {

			temp = decNum % 2;
			sBuf.insert(0, temp);
			decNum = decNum / 2;

		}

		return sBuf;
	}

	public File getNewDeCompressedFile() {
		return newDeCompressedFile;
	}

	public void decompress(File f) throws IOException {

		FileInputStream in = new FileInputStream(f);
		BufferedInputStream bufferIn = new BufferedInputStream(in);

		byte[] array;

		readHeader(in); // Reading header from file

		String path = f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf('\\') + 1);

		newDeCompressedFile = new File(path + f.getName().split("[.]")[0] + "(1)" + "." + ext);
		FileOutputStream out = new FileOutputStream(newDeCompressedFile);

		BufferedOutputStream bufferOut = new BufferedOutputStream(out);

		// READ FILE --> OUTPUT FILE

		String s = "";
		int available = bufferIn.available();
		ArrayList<Integer> output = new ArrayList<>();

		while (available > 0) {

			if (available > 8) {

				array = new byte[8];

				bufferIn.read(array);

				for (int i = 0; i < array.length; i++) { // Convert each char to binary then check if it's 8 bits

					int value = Operations.unsignedToBytes(array[i]);

					StringBuffer buf = getBinary(value);

					while (buf.length() < 8) { // adding 0s if it's less than 8 bits

						buf.insert(0, 0);

					}

					s += buf.toString();

				}

			} else { // If available less than 8 bytes

				array = new byte[available];

				bufferIn.read(array);

				for (int i = 0; i < array.length - 1; i++) {

					int value = Operations.unsignedToBytes(array[i]); // Last char to get number of bits

					if (i == array.length - 2) {

						int len = array[i + 1];

						StringBuffer temp = getBinary(array[i]);

						while (temp.length() < len) {
							temp.insert(0, 0);

						}

						s += temp.toString();

					} else {

						StringBuffer buf = getBinary(value);

						while (buf.length() < 8) {

							buf.insert(0, 0);

						}

						s += buf.toString();
					}

				}

			}

			if (s.length() >= 64) {// If we reach 4 bytes then to output

				StringBuffer buf = new StringBuffer(s);

				while (buf.length() > 8) { // substring to 8 by 8 bits

					int integerValue = getChar(buf); // get char value from tree

					output.add(integerValue);
				}

				s = buf.toString();

				for (int i = 0; i < output.size(); i++) {

					bufferOut.write(output.get(i));

				}

				output.clear();

			}

			available = bufferIn.available();

		}

		StringBuffer buf = new StringBuffer(s);

		// If there still bits
		if (!s.equals("")) {

			while (buf.length() >= 8) {

				int integerValue = getChar(buf);

				output.add(integerValue);

			}
		}

		// If still bits
		if (!(buf.toString().isEmpty())) {

			int integerValue = getChar(buf);

			output.add(integerValue);

		}

		for (int i = 0; i < output.size(); i++) {

			bufferOut.write((output.get(i)));

		}

		bufferIn.close();
		bufferOut.close();

		in.close();
		out.close();

	}

	// Get original char from tree
	public int getChar(StringBuffer s) {

		HeapEntry pos = root;
		int i = 0;

		while (pos.getLeft() != null && pos.getRight() != null && i < s.length()) {

			if (s.charAt(i) == '0') {
				pos = pos.getLeft();
				++i;
			} else {
				pos = pos.getRight();
				++i;
			}
		}

		s.delete(0, i);

		return pos.getAscii();

	}

	public void readHeader(FileInputStream in) throws IOException {

		int cc;

		int counter = 0;

		cc = in.read();

		byte[] array = new byte[cc + 1]; // Reading number of char in extension

		ArrayList<Integer> listBinary = new ArrayList<>();
		ArrayList<Integer> finalList = new ArrayList<>();

		while ((cc = in.read(array)) != -1) {

			if (counter == 0)
				for (int i = 0; i < array.length; i++) { // Reading Extension
					int value = Operations.unsignedToBytes(array[i]);
					ext += (char) value;
				}

			if (counter == 1) { // Reading Nodes in tree
				for (int i = 0; i < array.length; i++) {
					int value = Operations.unsignedToBytes(array[i]);
					listBinary.add(value);
				}
			}

			if (counter == 2) { // Reading char of tree

				for (int i = 0; i < array.length; i++) {

					int value = Operations.unsignedToBytes(array[i]);

					finalList.add(value);

				}
			}

			++counter;

			if (counter == 3)
				break;

			if (counter == 1) {

				cc = in.read();

				array = new byte[cc + 2];

			} else {

				cc = in.read();
				array = new byte[cc + 1];

			}
		}

		// Create tree

		Queue<HeapEntry> queue = new LinkedList<>();
		Queue<HeapEntry> finalQueue = new LinkedList<>();

		// Create Queue of char
		for (int i = 0; i < finalList.size(); i++) {

			finalQueue.add(new HeapEntry(finalList.get(i), 0));

		}

		String s = "";
		StringBuffer temp;

		// Convert Reading char to binary
		for (int i = 0; i < listBinary.size() - 1; i++) {

			if (i == listBinary.size() - 2) { // The last char show number of bits

				int len = listBinary.get(i + 1);

				temp = getBinary(listBinary.get(i));

				while (temp.length() < len) {
					temp.insert(0, 0);
				}

				s += temp.toString();

			} else {

				temp = getBinary(listBinary.get(i));

				while (temp.length() < 8) {

					temp.insert(0, 0);

				}

				s += temp.toString();

			}

		}

		// Create Queue of binary's
		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == '1')
				queue.add(new HeapEntry(1, 0));

			else if (s.charAt(i) == '0')
				queue.add(new HeapEntry(0, 0));

		}

		// Create Tree
		root = CreateHuffman.build(queue, finalQueue, null);

		// Create table
		CreateHuffman.Traversal(root, "", this.table);

	}

}
