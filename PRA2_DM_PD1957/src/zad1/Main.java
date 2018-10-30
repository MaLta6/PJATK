/**
 *
 *  @author Dylewska Marta PD1957
 *Właściwości komponentów ( 2 p.)

Rozmieścić w oknie  pięć etykiet w układzie BorderLayout.
Etykiety powinny mieć:
a) różne kolory tła,
b) różne kolory pisma,
c) różne wielkości pisma i różne teksty,
d) podpowiedzi (tooltip)
c) różne ramki 

 */

package zad1;

import java.awt.*;
import javax.swing.*;

public class Main {


		public static void main(String[] args) {
			final int CNUM = 5; // liczba komponent�w w panelach
			 
			  String lmNames = "Rozkład Border"; // opisy rozk�ad�w
			  
			  LayoutManager lm = new BorderLayout(); //obiekt Border Layout - nowy rozk�ad
			  
			  String gborders[] = { "West", "North", "East", "South", "Center" }; //argumenty dla rozk�adu BorderLayout
			  
			// Kolory paneli
			    Color color =  new Color(191, 225, 255);
			//Kolory przyciskow - b�d� przydzielane w p�tli    
			    Color kolorTla[] = {new Color(255,0,127), new Color(102,0,204), new Color(102,255,255), new Color(204,102,0), new Color(192,192,192)};
			//Kolory czcionki
			    Color kolorCzcionki[] = {new Color(102,0,0), new Color(102,51,0), new Color(0,102,0), new Color(0,0,102), new Color(102,0,51)};
			//Wielko�ci pisma
			    int[] rozmiar = {12, 13, 14, 15, 16};
			 //Teksty na przyciskach   
			    String[] teksty = {"T1", "T2", "T3", "T4", "T5"};
			 //podpowiedzi
			    String[] podpowiedzi = {"P1", "P2", "P3", "P4", "P5"};
			 //ramki
			    
			    JFrame frame = new JFrame("Pokaz layout'u"); // okno i contentPane
			    frame.setPreferredSize(new Dimension(400,350));
			    frame.setLayout(new BorderLayout(5, 5));
			    
			    JPanel p = new JPanel();
			      p.setBackground(color); // kolor t�a panelu
			      p.setBorder(BorderFactory.createTitledBorder(lmNames)); // ramka
			      p.setLayout(lm); // ustalenie rozk�adu
			      //Icon icon = null;
			      
			      for (int j = 0; j < CNUM; j++) { // dodajemy przyciski do paneli
				        
			    	  	JLabel l = new JLabel(teksty[j], SwingConstants.CENTER);
			    	  	p.add(l, gborders[j]);
			    	  	l.setForeground(kolorCzcionki[j]);
			    	  	l.setOpaque(true);
			    	  	l.setFont(new Font("Dialog", Font.PLAIN, rozmiar[j]));
			    	  	l.setBackground(kolorTla[j]);
			    	  	l.setToolTipText(podpowiedzi[j]);
			    	  	l.setBorder(BorderFactory.createLineBorder(kolorCzcionki[j], 4, false));
			    	  	
			    	  	
				      }
				      frame.add(p);
				      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					  frame.pack();
					  frame.setVisible(true);
		  			
		}
		}
