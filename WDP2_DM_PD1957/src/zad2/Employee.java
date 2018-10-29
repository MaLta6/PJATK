/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad2;


public class Employee {
	private String firstName;
	private String lastName;
	private int age;
	private double salary;
	
	
	
	public Employee (String fName, String lName, int a, double s) {
		firstName = fName;
		lastName = lName;
		age = a;
		salary = s;
	}
	
	void setSalary(double s) {
		salary = s;
	}
	
	String getFirstName() {
		return firstName;
	}
	String getLastName() {
		return lastName;
	}
	
	int getAge() {
		return age;
	}
	
	double getSalary() {
		return salary;	
	}
	
	
	public double chgSalary(double procent){
		salary = salary * (1+procent/100);
		return salary;
	}
	
	
	
	public String toString() {
		return firstName + " " + lastName + " " + age + " " + salary;
	}
}
