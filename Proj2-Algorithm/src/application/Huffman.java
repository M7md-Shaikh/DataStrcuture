package application;

//class for table view final
public class Huffman {

	/*
	 * Huffman Node
	 */

	private int ascii;
	private String huffman;
	private int repetition;
	private int length;

	public Huffman() {

	}

	public Huffman(int ascii, String huffman, int repetition, int length) {

		this.ascii = ascii;
		this.huffman = huffman;
		this.repetition = repetition;
		this.length = length; // length of the "code"

	}

	@Override
	public String toString() {
		return "Huffman [ascii=" + ascii + ", Huffman=" + huffman + ", repetition=" + repetition + ", length=" + length
				+ "]";
	}

	public Integer getAscii() {
		return ascii;
	}

	public void setAscii(int ascii) {
		this.ascii = ascii;
	}

	public String getHuffman() {
		return huffman;
	}

	public void setHuffman(String huffman) {
		this.huffman = huffman;
	}

	public Integer getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
