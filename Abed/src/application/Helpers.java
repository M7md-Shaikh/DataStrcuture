
package application;

import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Helpers {

    char[][] arrowTable;
    LedsBoard ledsBoard;
    LedsBoard random;
    ArrayList<Integer> ledLight = new ArrayList<>();
    int dynamicTable[][];




 // to put values in led array randomly
 

    public LedsBoard random(int numberOfLED) {
        ArrayList<Led> leds = new ArrayList<>();
        ArrayList<Power> powers = new ArrayList<>();
        int i = 0;
        int j =0;
        while (numberOfLED !=0) {
            leds.add(i++,new Led(i));
            powers.add(j++, new Power(j));
            numberOfLED--;
        }

        leds = reset(leds);
        random  = new LedsBoard();
        random.setLeds(leds);
        random.setPower(powers);
        return random;

    }

        public ArrayList<Led> reset(ArrayList<Led> leds){

            Random rand = new Random();

            for (int i = 0; i < leds.size(); i++) {
                int nextInt = rand.nextInt(leds.size());
                Led temp = leds.get(i);
                leds.set(i,leds.get(nextInt));
                leds.set(nextInt,temp);
            }
            return leds;
    }


    
   // from file
     
    public  LedsBoard readFile() {
        // the file contains list of numbers which represents the indexes of each led
        ArrayList<Led> leds = new ArrayList<>();
        ArrayList<Power> powers = new ArrayList<>();
        int i = 0;
        int j =0;
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        try {
            if (selectedFile != null) {
                File file = new File(selectedFile.getAbsolutePath());
                Scanner input = new Scanner(file);
                while (input.hasNext()) {
                    leds.add(i++,new Led(input.nextInt()));
                    powers.add(j++, new Power(j));
                }
                input.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ledsBoard = new LedsBoard();
        ledsBoard.setLeds(leds);
        ledsBoard.setPower(powers);
        return ledsBoard;
    }

    public  int solveLedsBoard(LedsBoard ledsBoard) {
        int sizeOfLeds = ledsBoard.getLeds().size();
        int sizeOfPower = ledsBoard.getPower().size();
        arrowTable = new char[sizeOfLeds + 1][sizeOfLeds + 1];
         dynamicTable= new int[sizeOfLeds + 1][sizeOfPower + 1];
        for (int i = 0; i <= sizeOfPower; i++) {
            for (int j = 0; j<= sizeOfLeds; j++) {
                if (i == 0 || j == 0) {
                    dynamicTable[i][j] = 0;
                }
                else if (ledsBoard.getPower().get(i - 1).getIndex() == ledsBoard.getLeds().get(j-1).getIndex()) {
                    dynamicTable[i][j] = dynamicTable[i - 1][j - 1] + 1;
                    arrowTable[i][j] = '/'; 

                } else {
                    if(dynamicTable[i][j-1] > dynamicTable[i-1][j]){
                        dynamicTable[i][j]=dynamicTable[i][j-1];
                        arrowTable[i][j] = '-';
                    } else {
                        dynamicTable[i][j]=dynamicTable[i-1][j];
                        arrowTable[i][j] = '|';
                    }
                }
            }
        }
        return dynamicTable[sizeOfLeds][sizeOfPower];
    }

    public ArrayList<Integer> print(char[][] arrowTrack, ArrayList<Led> arrayList,int i, int j) {

        int index=0;
        if (i == 0 || j== 0) {
            return null;
        } else {
            if (arrowTrack[i][j] == '/') {
                ledLight.add(index++,arrayList.get(j - 1).getIndex());
                print(arrowTrack, arrayList, i - 1, j - 1);
            } else {
                if (arrowTrack[i][j] == '|') {
                    print(arrowTrack, arrayList, i - 1, j);

                } else {
                    print(arrowTrack, arrayList, i, j - 1);

                }
            }
        }

        return ledLight;
    }
   
}
