/**
 *
 *  @author Dylewska Marta PD1957
 * Zdefiniować klasę Zbiornik, której obiekty będą stanowić zbiorniki wody. Każdy zbiornik ma numer,  zadaną pojemność oraz aktualny stan wody (który może być mniejszy od pojemności, gdy zbiornik nie jest pełen).  Inicjalnie stan wody w zbiorniku równy jest 0.
W klasie zdefiniować metody dolej(double woda) i odlej(double woda) pozwalające zmieniać stan wody poprzez dolewanie i odlewanie. 
Numery są nadawane zbiornikom automatycznie - pierwszy utworzony w programie zbiornik uzyskuje numer 1, drugi - numer 2 itd.

Zapewnić, aby następujący fragment programu (umieszczony w metodzie main()):

Zbiornik z1 = new Zbiornik(200);
System.out.println(z1);  
z1.dolej(100);
System.out.println(z1);
z1.odlej(50);
System.out.println(z1);
Zbiornik z2 = new Zbiornik(1000);  
z2.dolej(500);
System.out.println(z2);

wyprowadził na konsolę następującą informację:

Zbiornik 1, pojemność 200.0, stan wody 0.0
Zbiornik 1, pojemność 200.0, stan wody 100.0
Zbiornik 1, pojemność 200.0, stan wody 50.0
Zbiornik 2, pojemność 1000.0, stan wody 500.0

Proszę uważać, aby stan wody w zbiorniku nie miał ujemnych wartości, ani nie przekraczał zadanej pojemności.
W tym kontekście potrzebna będzie instrukcja if, która ma postać:

if (warunek) instrukcja_do_wykonania.

Np.
int a = 1;
int b = 2;

if (b > a)  b--;
System.out.println(b); // wyprowadzi wartość 1

Pomoc:  

•	gdyby ktoś zastanawiał się jak to się dzieje, że System.out.println(z1) wyprowadza na konsolę pokazaną informację tekstową, to w wykładach jest to dokładnie opisane w podpunkcie, mówiącym o metodzei public String toString().
•	pomysł na automatyczną numerację zbiorników przyjdzie na pewno do głowy po zapoznaniu się z ideą pól statycznych.

Uwaga: nie wolno w żaden sposób modyfikować klasy Main, utworzonej przez generator, a wydruk na konsoli jest obowiązkowy. Rozwiązania nie spełniające tych wymagań otrzymują 0 punktów

 */

package zad1;


public class Main {
  public static void main(String ... args) {    
    Zbiornik z1 = new Zbiornik(200);
    System.out.println(z1);  
    z1.dolej(100);
    System.out.println(z1);
    z1.odlej(50);
    System.out.println(z1);
    Zbiornik z2 = new Zbiornik(1000);
    z2.dolej(500);
    System.out.println(z2);
  }
}
