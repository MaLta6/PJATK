/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad3;
import zad2.*;


public class EmpsList {
	private Emps first;
	private Emps last;
	
	public void add(Employee e){
		if(this.first==null){
			this.first = new Emps (null, e, null);
			this.last = this.first;
		}
		else {
			this.last.next = new Emps(this.last, e, null);
			this.last = last.next;
		}
	}
	
	public String showForward(){
		
		String result = "";
		Emps em=this.first;
		while(em!=null){
			result =  em.Emp1.toString();
			em=em.next;
			System.out.println(result);
		}
		
		return result;
		
	}
	public String showBackward(){
		String result = "";
		Emps em = this.last;
		while(em!=null){
			result = em.Emp1.toString();
			em = em.prev;
			System.out.println(result);
		}
		
		return result;
	}
	
	public void changeAllSalaries(double procent) {
		Emps em = this.first;
		while (em!=null){
			em.Emp1.chgSalary(procent);
			em=em.next;
			
		}
	}

}
