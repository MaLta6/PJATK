/**
 *
 *  @author Dylewska Marta PD1957
 *
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
