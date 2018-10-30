/**
 *
 *  @author Dylewska Marta PD1957
 *Stworzyć klasę Employee (pracownik), opisującą wspólne atrybuty danych o pracownikach: 
- imię (pole firstName), 
- nazwisko (pole lastName), 
- wiek (pole age), 
- pensja (pole salary).
oraz zawierającą odpowiednie metody, które pozwolą uzyskać informacje o danym pracowniku oraz ustalić pensję i zmienić  pensję o podany procent.
Nazwy metod zwracajacych informacje winny zaczynać się od get.. (np. getSalary()), ustalających od set (tylko dla pensji, więc będzie to metoda setSalary(...)).
Zmianę pensji  o podany procent  proszę zrealizować jako metodę chgSalary, z parametrem, okreslającym o ile procent ma się zmienić pensja.
Użyć tej klasy  w innej klasie o nazwie EmpManager, w której dostarczymy:
1.  metody definiowania danych o  pracownikach: 
Employee defEmp(String msg), 
która w dialogu wejściowym
•	pyta użytkownika o dane pracownika (argument msg jest komunikatem  np. "Podaj dane o pracowniku"),
•	tworzy obiekt, reprezentujący wprowadzone dane o pracowniku,
•	i zwraca referencję do niego.
2. metody showInfo(Employee), która wyświetla w okenku komunikatów dane o podanym jako argument pracowniku
3. metody changeSalary(Employee), która pyta o procent o jaki chcemy zmienić pensję podanego jako argument pracownika i zmienia tę pensję. 
Do wprowadzania danych użyć dialogu i skanera.  
Przetestować cały program na podstawie następującego fragmentu zapisanego w metodzie main klasy Main
    Employee janek = new Employee("Jan", "Janek", 30, 4000);
    System.out.println(janek);
    System.out.println(janek.getFirstName() + " " + janek.getLastName());
    System.out.println("Za rok bedzie mial: " + (janek.getAge() + 1) + " lat.");
    janek.chgSalary(3.5);
    System.out.println("I bedzie zarabial: " + janek.getSalary() );
        
    Employee e = defEmp("Podaj dane pracownika");
    if (e == null) showMessageDialog(null, "Nie podano danych");
    else {
      showInfo(e);
      changeSalary(e);
      showInfo(e);
    }
który winien wyprowadzić na konsolę następujące dane:
Jan Janek 30 4000.0
Jan Janek
Za rok bedzie mial: 31 lat.
I bedzie zarabial: 4140.0
a nastgępnie umożliwić podanie  danych dla innego pracownika, zmianę jego pensji i wyświetlenie informacji o nim w okienku komunikatów.
Uwaga: utworzona przez generator klasa Main nie może być modyfikowana
Bardzo ważne: w metodzie defEmp uzywamy tylko jednego dialogu wejściowego, w którym podajemy rozdzielone spacjami dane pracownika  - imię nazwisko wiek  pensję.
czyli  mamy coś takiego:
String dane = JOptionPane.showInputDialog(""Podaj dane pracownika");
i gdy wprowadzimy np. Jan Kowalski 20 3000
to zmienna dane  będzie oznaczać napis "Jan Kowalski 20 3000"
Z tego napisu nalezy pobrać odpowiednie części (imię, nazwisko, wiek, pensję).

 */

package zad2;


import javax.swing.JOptionPane;

public class Main {

  public static void main(String[] args) {
 
    Employee janek = new Employee("Jan", "Janek", 30, 4000);
    System.out.println(janek);
    System.out.println(janek.getFirstName() + " " + janek.getLastName());
    System.out.println("Za rok bedzie mial: " + (janek.getAge() + 1) + " lat.");
    janek.chgSalary(3.5);
    System.out.println("I bedzie zarabial: " + janek.getSalary() );
        
    Employee e = EmpManager.defEmp("Podaj dane pracownika");
    if (e == null) JOptionPane.showMessageDialog(null, "Nie podano danych");
   else {
     EmpManager.showInfo(e);
     EmpManager.changeSalary(e);
     EmpManager.showInfo(e);
    }
  }
}
