/**
 *
 *  @author Dylewska Marta PD1957
 *Daty w pliku tekstowym  o nazwie{user.home}/test/daty.txt maja postać YYYY-MM-DD (rok- miesiac- dzien). Oprócz dat, plik zawiera inne informacje.  Wczytać plik i wypisać w jednym wierszu wszystkie poprawne daty - czyli: po wyróżnieniu wszystkich fragmentów, które mogą być datami sprawdzić ich poprawnośc (np. czy właściwa jest liczba dni w miesiącu)

Program powininn w specyficzny sposób traktować niejednozanczną informację (np. 2009-01-111 - czy to błędna data czy potraktowac ją jako 2009-01-11).
Sposób traktowania takich sytuacji wyjaśnia poniższy przykład.

Dane w pliku:

2007-01-12Jakis txt2008-01-31 xxx 2008-02-29 2008-15-10 2008-19-45 2009-05-01 
20999-11-11 pppp 2001-00-01 09-01-01 2001-01-00 2009-01-111 2009-02-29 1998-11-11

Program powinien wyprowadzić:

2007-01-12 2008-01-31 2008-02-29 2009-05-01 1998-11-11

Podpowiedź: użyć skanera, wyrażeń regularnych (ale same wyrażenia reg. nie wystarczą).

Uwaga: nazwa i lokalizacja pliku jest obowiązkowa. O katalogu user.home - zob. poprzednie zadania. Proszę nie dostarczać  z projektem swoich własnych plików

 */

package zad2;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

public class Main {
	
	private static Scanner scan;
	
  public static void main(String[] args) {
	 String fname = System.getProperty("user.home") + "/test/daty.txt";            
	  //String fname = "C:/Users/ps_user/home/daty.txt";
  		StringBuffer sb = new StringBuffer(); //do przechowywania tego co przeczytalismy z pliku
  		
  		FileReader f = null;
  		try {
  			f = new FileReader(fname);	//czytamy z pliku o nazwie fname
  			scan = new Scanner(f);
	  
	  
  			while (scan.hasNext()) {	//dopoki jest cos oddzielone bialym znakiem w pliku
  				String firm = scan.next();
  				
  				sb.append(firm + " ");	//dodaj do przechowywanego stringa sb)
  				}

  			//System.out.println(sb);
  			String sc = sb.toString();
  			//System.out.println(sc);
  			String regex = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])\\D";	//jakiego rodzaju informacji ma szukac (data w formacie YYYY-MM-DD)
  			
  			Pattern pattern = Pattern.compile(regex);
  			
  			Matcher matcher = pattern.matcher(sc);
  			
  			String year = "";
  			String month = "";
  			String day = "";
  			String result2 = "";
  			String yearV = "";
  			String monthV = "";
  			String dayV = "";
  			
  			while (matcher.find()){
  				
  				year = matcher.group(1);
  				month = matcher.group(3);
  				day = matcher.group(4);
  				int yearI = Integer.parseInt(year);
  				
  				
  				if(day.equals("31")&&(month.equals("02")||(month.equals("04")||month.equals("06")||(month.equals("09")||month.equals("11")))))
  						{
  					yearV="";
  					monthV="";
  					dayV="";
  				}
  				else if(day.equals("30")&&month.equals("02")){
  					yearV="";
  					monthV="";
  					dayV="";
  				}
  				else if(yearI%4!=0 && month.equals("02")&& day.equals("29")){
  					yearV="";
  					monthV="";
  					dayV="";
  				}
  				else{
  					yearV=year;
  					monthV=month;
  					dayV=day;
  					result2 += yearV+"-"+monthV+"-"+dayV+" ";
  				}
  				
  				
  			}
  			System.out.println(result2);
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
  }
}
