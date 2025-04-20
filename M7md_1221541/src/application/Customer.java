package application;

public class Customer {
	private String name;
	private int arrivalTime;
	public Customer(String name, int arrivalTime) {
		super();
		this.name = name;
		this.arrivalTime = arrivalTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrialTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	
}
