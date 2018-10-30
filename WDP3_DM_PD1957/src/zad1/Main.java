/**
 *
 *  @author Dylewska Marta PD1957
 *W pliku {user.home}/iter.txt podane są trzy liczby całkowite: start end limit(rozdzielone spacjami)
Napisać program wczytujący te trzy liczby i sumujący  liczby całkowite od start do end, ale przerywającą sumowanie gdy wartość sumy osiągnie lub przekroczy zadane ograniczenie limit. 
Program ma wypisać na konsoli jedną liczbę: wynik sumowania.

Dla przykładu, jeśli w pliku  podano:
1 3 6
to na konsoli uzyskamy:
6

W przypadku wystąpienia błędów (np. brak pliku, wadliwe dane) należy wypisać na konsoli TYLKO trzy gwiazdki:

***
i zakończyć działanie programu.

Nazwa pliku, jego umiejscowienie i postać oraz postać wyniku na konsoli jest obowiązkowa. Rozwiązanie nie spełniające tych wymagań otrzymują 0 punktów.

 */

package zad1;
import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) {
    String fname = System.getProperty("user.home") + "/iter.txt";  
    FileReader in = null;
    BufferedReader br = null;
    
    try {
    	String line;
    	in = new FileReader(fname);
    	br = new BufferedReader(in);
    	
    	while ((line = br.readLine())!=null) {
    		StringTokenizer st = new StringTokenizer(line);
    		String st1 = st.nextToken();
    		String st2 = st.nextToken();
    		String st3 = st.nextToken();
    		int start = Integer.parseInt(st1);
    		int end = Integer.parseInt(st2);
    		int limit = Integer.parseInt(st3);
    		//int sum = start;
    		int sum = 0;
    		int a = 0;
    		
    		while ((a+start)<=end ) {
    			
    			if (sum + a + 1> limit) break;
    			a+=1;
    			sum+=a;
    			
    			
    			
    		}
    		System.out.println(sum);
    	}}
    	catch(Exception exc) {
    		System.out.println("***");
    		System.exit(1);
    	}
    	finally {
    		try {
    			if(br != null) br.close();
    		} catch(IOException exc) {
    			System.out.println("***");
    			System.exit(2);
    		}
    } 
  }
}
