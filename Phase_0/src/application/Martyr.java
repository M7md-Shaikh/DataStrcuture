package application;

import java.util.Date;

public class Martyr {

	private String name;
	private int age;
	private String eventLoc;
	private String date;
	private String gender;
	
	
	public Martyr() {};
	
	public Martyr(String name, int age, String eventLoc, String date, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.eventLoc = eventLoc;
		this.date = date;
		this.gender = gender;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getEventLoc() {
		return eventLoc;
	}


	public void setEventLoc(String eventLoc) {
		this.eventLoc = eventLoc;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "Martyr [name=" + name + ", age=" + age + ", eventLoc=" + eventLoc + ", date=" + date + ", gender="
				+ gender + "]";
	}
	
	
	
	
}
