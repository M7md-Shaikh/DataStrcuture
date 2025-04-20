
package application;

/*
Led class
*/
public class Led {
  private double layoutX;
  private double layoutY;
  private int index;

  public Led(double layoutX, double layoutY, int index) {
      this.layoutX = layoutX;
      this.layoutY = layoutY;
      this.index = index;
  }

  public Led(int index) {
      this.index = index;
  }

  public double getLayoutX() {
      return layoutX;
  }

  public double getLayoutY() {
      return layoutY;
  }

  public void setLayoutX(double layoutX) {
      this.layoutX = layoutX;
  }

  public void setLayoutY(double layoutY) {
      this.layoutY = layoutY;
  }

  public int getIndex() {
      return index;
  }
}
