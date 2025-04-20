
 package application;

/*
Power class
*/
public class Power {
   private double layoutX;
   private double layoutY;
   private int index;
   private Led poweredLed;

   public Power() {
   }

   public Power(int index) {
       this.index = index;
   }

   public double getLayoutX() {
       return layoutX;
   }

   public void setLayoutX(int layoutX) {
       this.layoutX = layoutX;
   }

   public double getLayoutY() {
       return layoutY;
   }

   public void setLayoutY(double layoutY) {
       this.layoutY = layoutY;
   }

   public int getIndex() {
       return index;
   }

   public void setIndex(int index) {
       this.index = index;
   }

   public Led getPoweredLed() {
       return poweredLed;
   }

   public void setPoweredLed(Led poweredLed) {
       this.poweredLed = poweredLed;
   }
}
