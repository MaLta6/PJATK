/**
 *
 *  @author Dylewska Marta PD1957
 *
 */


package zad3;
import zad2.*;

public class Emps {
	public Emps next = null;
	public Emps prev = null;
	public Employee Emp1 = null;
	
	
	public Emps(Emps a, Employee b, Emps c){
		this.prev = a;
		this.Emp1 = b;
		this.next = c;
	}
}
