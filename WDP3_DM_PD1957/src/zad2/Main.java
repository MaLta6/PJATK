/**
 *
 *  @author Dylewska Marta PD1957
 *
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