/**
 *
 *  @author Dylewska Marta PD1957
 *Napisać program, który pobiera w dialogu wejściowym showInputDialog  łańcuch znakowy i:
1.	podaje na konsoli długość łańcucha, 
2.	wyprowadza pierwszy i ostatni znak (rozdzielone spacją) 
3.	wyprowadza podłańcuch od 4 znaku do ostatniego znaku, 
4.	wyprowadza podłańcuch od 4 znaku do przedostatniego znaku, 
5.	wyprowadza informacje o tym ile razy w wejściowym łańcuchu występuje zadany (podany w drugim dialogu wejściowymshowInputDialog)  podłańcuch (np. ile razy w łańcuchu "podstoleczne tramwaje mialy postoje po sto razy dziennie" wystepuje podlancuch "sto" - w tym przypadku 3) 
6.	tworzy tablicę wszystkich słów  łańcucha (tu slowa niech oznaczają zestawy znaków rozdzielone spacjami, przecinkami, kropkami i średnikami) i wyprowadza jej elementy w jednym wierszu, rozdzielone jedną spacją 
7.	stwierdzia czy pierwsze i ostatnie słowo łańcucha jest takie samo. 
Działanie programu winno wyglądać w następujący sposób.
- w dialogu wejściwym podajemy napis np. "ala ma kota, ale ala nie ma psa; kota ma ala"
-  w dialogu wejściowym podajemy szukany tekst np. "al"

Dla podanych przykładowych danych na konsoli powinniśmy uzyskać wynik:


1) 44
2) a a
3)  ma kota, ale ala nie ma psa; kota ma ala
4)  ma kota, ale ala nie ma psa; kota ma al
5) 4
6) ala ma kota ale ala nie ma psa kota ma ala
7) true

Sposób działania programu i forma wydruku są obowiązkowe (proszę zwrócić uwagę na wyprowadzanie numeracji kolejnych punktów zadania na wydruku oraz na to w jaki sposób podawane sa wynik).


Punktacja:
1-4 = 1 punkt
5 = 2 punkty
6-7 = 2 punkty


 */

package zad1;

import static javax.swing.JOptionPane.*;
import java.util.regex.*;

public class Main {

  public static void main(String[] args) {
	  String msg1 = "Wprowadź łańcuch znaków";
	  String msg2 = "Wprowadź zadany podłańcuch znaków";
	  
	  String lanc1;	//przechowywanie lancucha znakow z pierwszego InputDialog
	  String podl1;	//przechowywanie lancucha znakow z drugiego InputDialog

	  lanc1 = showInputDialog(msg1);	//wprowadzanie lancucha znakow 
	  
	  podl1 = showInputDialog(msg2);	//wprowadzanie podlancucha znakow
		  
	System.out.println("1) "+lanc1.length());	//podaje na konsoli dlugosc lancucha
	System.out.println("2) "+lanc1.charAt(0)+" "+lanc1.charAt(lanc1.length()-1));	//wyprowadza pierwszy i ostatni znak rozdzielone spacja
	System.out.println("3) "+lanc1.substring(3,lanc1.length()));	//wyprowadza podlancuch od 4-tego do ostatniego znaku
	System.out.println("4) "+lanc1.substring(3,lanc1.length()-1));	//wyprowadza podlancuch od 4-tego do przedostatniego znaku
	
	Pattern pattern = Pattern.compile(podl1);	//kompilacja wyrazenia regularnego (przed wykorzystaniem w klasie matcher)
	
	Matcher matcher = pattern.matcher(lanc1);	//uzyskanie obiektu matchera, ktory szuka pattern'u w lanc1
	
	int i = 0;	//liczba trafien matchera (dopasowania fragmentu lanc1 do wyrazenia podl1)
	while (matcher.find()){	//dopoki matcher znajduje pattern w lanc1, ma sie zwiekszac i
		i++;
	}
	
	System.out.println("5) "+i);
	
	String[] s = lanc1.split("[,; .]+");	//tworzymy macierz elementow lancucha oddzielonych znakami w nawiasie wystepujacymi przynajmniej jednokrotnie (stad znak +)
	String res = "";	//tutaj bedziemy przechowywac rozwiazanie - nowo powstaly ciag znakow
	 
	for (int j=0;j<s.length;j++){	//dodawanie kolejnych symboli do res
		 res+=s[j]+" ";
	}
	
	System.out.println("6) "+res);
	 
	boolean isEq = s[0].equals(s[s.length-1]);	//sprawdzanie czy ostatni symbol i pierwszy sa takie same
	
	System.out.println("7) "+isEq);
	
  }
     
  }

