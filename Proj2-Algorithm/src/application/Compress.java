package application;

import java.io.*;
import java.util.ArrayList;

public class Compress {

    Huffman[] table;
    double ratio;
    String stat;
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<Integer> finalList;
    //private MinHeap heap;
    private HeapEntry root;
    private int index;

    public Compress(Huffman[] table, HeapEntry root) {

        this.table = table;
        this.root = root;

    }

    public int getIndex() {
        return index;
    }


    public void compress(File inFile) throws IOException {

        String path = inFile.getAbsolutePath().substring(0, inFile.getAbsolutePath().lastIndexOf('\\') + 1);

        FileInputStream inputStream = new FileInputStream(inFile);
        int index = inFile.getName().lastIndexOf('.');
        String extensionOfInputFile = inFile.getName().substring(index + 1);
        File outFile = new File(path + inFile.getName().substring(0, index) + ".huf");

        FileOutputStream outputStream = new FileOutputStream(outFile);

        BufferedInputStream bufferIn = new BufferedInputStream(inputStream); // from the file
        BufferedOutputStream bufferOut = new BufferedOutputStream(outputStream); // to the file


        long previousSize = inFile.length();


        writeHeader(bufferOut, extensionOfInputFile);   //Write Header To file
        

        int available;
        String s = "", temp = "";

        available = bufferIn.available();

        byte[] array;
        ArrayList<Integer> output = new ArrayList<>();

        if (available > 8) {
            array = new byte[8];

        } else {

            array = new byte[available];

        }


        while (available > 0) { //Reading All file


            bufferIn.read(array);

            for (int i = 0; i < array.length; i++) { //Read Array unsigned And get the huffman code from table then add it to string,
                // which makes the whole file data into one (compressed(huffman codes)) string

                int value = Operations.unsignedToBytes(array[i]);

                s += table[value].getHuffman(); // get the code of each byte(char) from the generated table;

            }


            if (s.length() >= 64) {  //If we reach 8 bytes


                // int count = 0;

                while (s.length() >= 8) { // Substring bits to 8 by 8 then add it to output list

                    temp = s.substring(0, 8);
                    s = s.substring(8);


                    int integerValue = Integer.parseInt(temp, 2);   //get the integer value from binary's
                    output.add(integerValue);

                }


                for (int i = 0; i < output.size(); i++) {   //Write list to output


                    bufferOut.write(output.get(i));

                }

                output.clear();

            }


            available = bufferIn.available();

            if (available > 8) {
                array = new byte[8];

            } else {
                array = new byte[available];

            }

        }


        //If there still bits in string
        if (!s.equals("")) {

            while (s.length() >= 8) { //substring bits 8 by 8 then to output


                temp = s.substring(0, 8);
                s = s.substring(8);


                int integerValue = Integer.parseInt(temp, 2);

                output.add(integerValue);


            }

            //If there still bits add them .... Then add the length of bits of the last char
            if (!s.equals("")) {

                temp = s;

                int integerValue = Integer.parseInt(temp, 2);

                output.add(integerValue);

                output.add(s.length());

            } else
                output.add(8);


            if (!output.isEmpty()) //Add output list to output
                for (int i = 0; i < output.size(); i++) {

                    bufferOut.write(output.get(i));

                }
        }


        bufferIn.close();
        bufferOut.close();
        inputStream.close();
        outputStream.close();

        this.ratio = (float) previousSize / outFile.length();  //Ratio of Compressing


        stat = "Previous Size: " + previousSize + " Bytes"+"\t" + "After Compression: " + outFile.length() + " Bytes"+ "\t" +
                "Ratio of compression (previous/new): " +
                this.ratio;


    }

  
    private void writeHeader(BufferedOutputStream bufferOut, String extensionOfInputFile) throws IOException {


        ArrayList<Integer> listBinary = new ArrayList<>(); //Tree Nodes :exp) [0,1,0,0,1,1,0,0,1,1,1]
        ArrayList<Integer> finalList = new ArrayList<>();  //leaf characters:exp) [A,C,B,F,E,D]

        CreateHuffman.createHeader(listBinary, finalList, root);  //Read Tree to binary and leafs
        this.finalList = finalList;
//        System.out.println(extensionOfInputFile);/////
        list.add(extensionOfInputFile.length() - 1); //Add it to list
//     
        for (int i = 0; i < extensionOfInputFile.length(); i++) { //Add the char of extension name

            list.add((int) (extensionOfInputFile.charAt(i)));
            System.out.println(extensionOfInputFile.charAt(i) +"  extensionOfInputFile.charAt(i)");

        }

        int counter = 0;
        this.index = list.size(); // the index the size of the nodes (0s,1s)
        String b = "", temp;

        // this is to fill the listBinary(from huffman "createHeader()" method)
        for (int i = 0; i < listBinary.size(); i++) {    //Get All binary's in one string
            b += listBinary.get(i);

        }

        //from the above loop ==> as each 8 numbers(0s,1s) => are put into one integer
        while (b.length() >= 8) {    //substring this list to 8 by 8 bits

            temp = b.substring(0, 8);
            b = b.substring(8);
            counter++;

            list.add(Integer.parseInt(temp, 2)); // take and convert the temp string into a binary "byte"
            System.out.println(temp+" convert temp into binary");
        }


        //If there still bits !
        if (!b.equals("")) {

            temp = b;

            ++counter;


            list.add(Integer.parseInt(temp, 2));
            System.out.println(temp+" temp");

            list.add(b.length());  //add the last char bits length
            System.out.println(b.length()+" b.length");

        } else {

            list.add(8);
//            System.out.println(8);

        }


        list.add(index, counter - 1);       //Add the number of char to read (Tree binary's Nodes)
        list.add(finalList.size() - 1);    //Add the number of char to read(Tree Leafs char)
        System.out.println(counter - 1);
        System.out.println(finalList.size() - 1+" number of character");

        for (int i = 0; i < finalList.size(); i++) {

            list.add(finalList.get(i));
            System.out.println(finalList.get(i));


        }


        for (int i = 0; i < list.size(); i++) {   //Write it to output
            bufferOut.write(list.get(i));
            //System.out.println(list.get(i).intValue() );
        }


    }


}


