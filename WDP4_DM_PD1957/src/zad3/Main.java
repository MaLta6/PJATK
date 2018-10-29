/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import java.util.GregorianCalendar;


public class Main {

	private static Scanner scan;
	
  public static void main(String[] args) {
	  
	  String msg = "Wprowadź datę 'od' i datę 'do' rozdzielone spacjami";	//wiadomosc w oknie Input Dialog
	  String dane = JOptionPane.showInputDialog(null, msg);	//z jakiego stringa mamy pobierac info
	  try {
		scan = new Scanner(dane);
		
		scan.useLocale(new Locale("en"));
		
		String date1 = scan.next();	//data1 - pierwsze wskazanie skanera
		String date2 = scan.next();	//data2 - drugie wskazanie skanera
		
		
		//--------------------------------------------------------
		//------WPISYWANIE POZYCJI ZE SKANERA W LISTĘ 1 (ROK, MIESIĄC, DZIEŃ)
		StringTokenizer st1 = new StringTokenizer(date1,"-");
		ArrayList<String> lista1 = new ArrayList<String>();
		
		while(st1.hasMoreTokens()){
			lista1.add(st1.nextToken());
		}
		
		//--------------------------------------------------------
		//------WPISYWANIE POZYCJI ZE SKANERA W LISTĘ 2 (ROK, MIESIĄC, DZIEŃ)
		StringTokenizer st2 = new StringTokenizer(date2,"-");
		ArrayList<String> lista2 = new ArrayList<String>();
		
		while(st2.hasMoreTokens()){
			lista2.add(st2.nextToken());
		}
		
		//---------------------------------------------------------
		//------USTAWIANIE DATY 1-------
		int year1 = Integer.parseInt(lista1.get(0));
		int month1 = Integer.parseInt(lista1.get(1));
		int day1 = Integer.parseInt(lista1.get(2));
		
		Calendar c1 = GregorianCalendar.getInstance();	//ustawiamy date nr 1
		c1.set(year1, month1-1, day1);
		//System.out.println(c1.getTime());
		//System.out.printf("%td.%<tm.%<tY",c1);	//data pojawia sie w odpowiednim formacie
		
		//---------------------------------------------------------
		//------USTAWIANIE DATY 2-------
		int year2 = Integer.parseInt(lista2.get(0));
		int month2 = Integer.parseInt(lista2.get(1));
		int day2 = Integer.parseInt(lista2.get(2));
		
		Calendar c2 = GregorianCalendar.getInstance();	//ustawiamy date nr 2
		c2.set(year2, month2-1, day2);
		
		
		//---------------------------------------------------------
		//------SPRAWDZANIE CZY DATA 2 JEST PÓŹNIEJSZA-------
		
		
		int dzien1 =c1.get(Calendar.DAY_OF_WEEK);
		String dz1;
		switch (dzien1){
		case 1: dz1="N";
		break;
		case 2: dz1="Pn";
		break;
		case 3: dz1="Wt";
		break;
		case 4: dz1="Śr";
		break;
		case 5: dz1="Cz";
		break;
		case 6: dz1="Pt";
		break;
		case 7: dz1="So";
		break;
		default: dz1 = "Invalid month";
		break;
		}
		
		
		//int dzien2 =c2.get(Calendar.DAY_OF_WEEK);
		
		System.out.printf("%td.%<tm.%<tY",c1);
		System.out.println(" " + dz1);
		int a = c1.compareTo(c2);	//sprawdzenie która data jest wcześniejsza (-1 oznacza, że c1, 1 oznacza, że c2, 0 oznacza, że są takie same)
		
		//System.out.println(a);
		while(a<0){
		c1.add(GregorianCalendar.DATE, 1);
		System.out.printf("%td.%<tm.%<tY",c1);
		int dzien2 =c1.get(Calendar.DAY_OF_WEEK);
		String dz2;
		switch (dzien2){
		case 1: dz2="N";
		break;
		case 2: dz2="Pn";
		break;
		case 3: dz2="Wt";
		break;
		case 4: dz2="Śr";
		break;
		case 5: dz2="Cz";
		break;
		case 6: dz2="Pt";
		break;
		case 7: dz2="So";
		break;
		default: dz2 = "Invalid month";
		break;
		}
		System.out.println(" " + dz2);
		int compDates = c1.compareTo(c2);
		a=compDates;
		}
	  }
	  catch(Exception exc) {
  		System.out.println("Blad wprowadzonej daty");
  		System.exit(1);
  	}
  		
  }
}
