
package application;

import java.util.ArrayList;
/*
  two arrayList
 */

public class LedsBoard {
    private ArrayList<Led> leds;
    private ArrayList<Power> power;


    public ArrayList<Led> getLeds() {
        return leds;
    }

    public void setLeds(ArrayList<Led> leds) {
        this.leds = leds;
    }

    public ArrayList<Power> getPower() {
        return power;
    }

    public void setPower(ArrayList<Power> power) {
        this.power = power;
    }
}
