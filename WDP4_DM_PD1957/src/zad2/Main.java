/**
 *
 *  @author Dylewska Marta PD1957
 *
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
