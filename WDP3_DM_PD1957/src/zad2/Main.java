/**
 *
 *  @author Dylewska Marta PD1957
 *Napisać program, który z pliku {user.home}/tab.txt  wczytuje liczby całkowite (liczby w pliku są rozdzielone dowolnymi białymi znakami)  i tworzy tablicę, zawierającą te liczby .
W tak zainicjowanej tablicy program znajduje wartość maksymalną oraz wszystkie indeksy w tablicy gdzie taka wartość się znajduje.
Program wypisuje na konsoli: 
w pierwszym wierszu - wszystkie elementy tablicy, rozdzielone spacjami
w drugim wierszu - wartość  maksymalną,
w trzecim wierszu - indeksy tablicy gdzie ta wartość się znajduje. 

Przykładowo, jeśli plik {user.home}/tab.txt zawiera:
1 5 5 3
-1 2 5 4
to na konsoli otrzymujemy:
1 5 5 3 -1 2 5 4 
5
1 2 6

W przypadku wystąpienia błędów (np. brak pliku, wadliwe dane) należy obsłużyć błąd -  wypisać na konsoli TYLKO trzy gwiazdki:

***
i zakończyć działanie programu.

Nazwa pliku, jego umiejscowienie i postać oraz postać wyniku na konsoli jest obowiązkowa. Rozwiązanie nie spełniające tych wymagań otrzymują 0 punktów.
Uwaga: proszę w żadnym razie nie linkować pliku jako zasobu do projektu Eclipse.

Uwagi dodatkowe: 

•	należy się przygotować na to, że w pliku jest dowolna ilość liczb,
•	wobec tego należy użyć klasy ArrayList do wczytywania danych, ale wymaganie stworzenia tablicy też jest obowiązkowe.
Utworzona przez generator projektów klasa Main zawiera fragment pomocny dla uzyskania wymaganej nazwy pliku.
Klasę Main należy uzupełnić, tak, aby uzyskać właściwe rozwiązanie.

 */

package zad2;

import java.util.*;
import java.io.*;

public class Main {

  private static Scanner scan;

public static void main(String[] args) {
    String fname = System.getProperty("user.home") + "/tab.txt";            
	  //String fname = "C:/Users/ps_user/home/tab.txt";
  		StringBuffer sb = new StringBuffer(); //do przechowywania pierwszego wiersza
  		StringBuffer sc = new StringBuffer(); //do przechowywania trzeciego wiersza
  		FileReader f = null;
  		
  		try {
  			f = new FileReader(fname);	//czytamy z pliku o nazwie fname
  			scan = new Scanner(f);
	  
  			ArrayList<Integer> lista = new ArrayList<Integer>();	//tworzymy listę zawierającą integery
	  
	  
  			while (scan.hasNext()) {	//dopoki jest cos oddzielone bialym znakiem w pliku
  				String firm = scan.next();
  				int firmInt = Integer.parseInt(firm);	//zamien to na integer
  				lista.add(firmInt);	//dodaj do listy (fizycznie wpis do macierzy)
  				sb.append(firmInt + " ");	//dodaj do przechowywanego stringa sb)
  				}

  			System.out.println(sb);	//wypisz wszystkie elementy swojej listy
	  
  			String a = lista.get(0).toString();	//wez pierwszy element listy
  			int b = Integer.parseInt(a);	//zamien go na integer (teraz juz niepotrzebne)
	  
  			for(int i = 0; i<lista.size(); i++){	//wyszukiwanie wartosci max
  				a = lista.get(i).toString();
  				int c = Integer.parseInt(a);
  				if(c>=b){
  					b=c;
  				}  
  			
  			}
  			//System.out.println(lista.get(0));
  			String maxNum = Integer.toString(b);	//wez liczbe max i zamien ja na string
  			//System.out.println(maxNum);
  				for(int j=0; j<lista.size(); j++){	//porownaj czy nowa liczba z listy jest rowna twojemu b
  					String x = lista.get(j).toString();	//jesli tak to dodaj jej index do listy sc
  					
  					if(maxNum.equals(x)){
  						
  						sc.append(j + " ");
  						//System.out.println(sc);
  					
  					}
  				}
  			System.out.println(b);
  			
  			System.out.println(sc);
	  
  		}
  		catch(Exception exc) {
    		System.out.println("***");
    		System.exit(1);
    	}
    	finally {
    		try {
    			if(f != null) f.close();
    		} catch(IOException exc) {
    			System.out.println("***");
    			System.exit(2);
    		}
    } 
  }}
