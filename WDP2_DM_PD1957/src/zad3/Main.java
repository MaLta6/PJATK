/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad3;


import zad2.*;

public class Main {

  public static void main(String[] a) {
    EmpsList elist = new EmpsList();
    elist.add(new Employee("A", "Aaa", 20, 2000));
    elist.add(new Employee("B", "Bbb", 30, 4000));
    elist.add(new Employee("C", "CCC", 40, 5000));

    elist.showForward();
    elist.showBackward();
    elist.changeAllSalaries(10.0);
    elist.showForward();
  }

}    
