/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad2;

import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class EmpManager extends Employee{

	private static Scanner scan;

	public EmpManager(String fName, String lName, int a, double s) {
		super(fName, lName, a, s);
	
	}
	 public static Employee defEmp(String msg){
		
		String dane = JOptionPane.showInputDialog(null, msg);

		scan = new Scanner(dane);
		
		scan.useLocale(new Locale("en"));
		
		String firstN = scan.next();
		
		String lastN = scan.next();
		
		int age1 = scan.nextInt();
		
		double salary1 = scan.nextDouble();
		
		//int age1 = Integer.parseInt(ageS);

		//double salary1 = Double.parseDouble(salaryI);
				
		Employee e1 = new Employee(firstN, lastN, age1, salary1);
		
		return e1;	
	}
	

	 static double changeSalary (Employee e) {
		
		double salaryPre = e.getSalary();
		double salaryPost = 0;
		
		String prS = JOptionPane.showInputDialog("Podaj procent zmiany pensji");
		
		scan = new Scanner(prS);
		double	prD = scan.nextDouble();
		
		//double	prD = Double.parseDouble(prS);
		
		salaryPost = Math.round(salaryPre* (1+prD/100));
		
		e.setSalary(salaryPost);
		
		
		return salaryPost;		
		
	}
	 
	 static Employee showInfo(Employee e) {
		String nameE = e.getFirstName();
		String lNameE = e.getLastName();
		int ageE = e.getAge();
		double salaryE = e.getSalary();
		
		Employee e1 = new Employee(nameE, lNameE, ageE, salaryE);
		
		JOptionPane.showMessageDialog(null, nameE + " " + lNameE + " " + ageE + " " + salaryE);
		return e1;
	}
} 
