package application;

public class Student {
	private String name;
	private int age;
	private double grade;
	private Address address;
	
	// For Lab1
	
	public Student(String name, int age, double grade, Address address) {
		super();
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.address = address;
	}
	
	public Student(String name , int age){
		super();
		this.name = name;
		this.age = age;
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
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}


	//  For Lab0 || Lab1
//	@Override
//	public String toString() {
//		return "Student [name=" + name + ", age=" + age + ", grade=" + grade + ", address=" + address + "]";
//	}
	
	
	//For Lab2 || 3
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
	
	
	
	
}