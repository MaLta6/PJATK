/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad3;

import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) {
    String fname = System.getProperty("user.home") + "/pakiety.txt";            
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
    		int pack1 = Integer.parseInt(st1);
    		int space = Integer.parseInt(st2);
    		int i = 1;
    		int pack2=0;
    		int pack3=0;
    		
    		ArrayList<Integer> lista = new ArrayList<Integer>();
    		
    		
    		space = space*1000000;
    		
    		int sum = 0;
    		if((sum+pack1)<=space){
    			sum = sum+pack1;
    			lista.add(pack1);
    			if(sum+pack2<=space){
    			
    				pack2 = 2*pack1;
    				sum = sum+pack2;
    				lista.add(pack2);
    			
    			
    				while(sum<=space && i<4){
    					pack2*=2;
    					sum=sum+pack2;
    					lista.add(pack2);
    					i++;
    				}
    			
    			
    				pack3=3*pack2;
    			
    			
    				while(sum+pack3<space){
    					sum=sum+pack3;
    					lista.add(pack3);
    					i++;
    					pack3*=3;
    				}
    			
    			
    				System.out.println(i+1);
    			
    			
    				for(int j = 0; j<lista.size(); j++){
    					System.out.println((j+1)+" "+lista.get(j).toString());
    					}

    			}
    		}
    		else {
    			sum = 0;
    		}
    		
    		System.out.println(sum);
    	}
    }
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

