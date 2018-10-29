/**
 *
 *  @author Dylewska Marta PD1957
 *
 */
package zad3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class Main {
	

	public static void main(String[] args) {
		
	
		class guzik extends JFrame {
		    JButton button;
		    int colorKey = 0;
		    public Color[] colors = new Color[] { Color.CYAN, Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE };
		
		    guzik() {
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        setPreferredSize(new Dimension(200, 100));
		        setLocation(200, 200);
		
		        button = new JButton("Klik!");
		        
		//w celu obs³u¿enia zdarzenia stosujemy klasê
		//ActionListener
		        button.addActionListener(new ActionListener() {
		            @Override
		//pokazuje co siê dzieje jeœli u¿ytkownik wykona jakieœ dzia³anie
		            public void actionPerformed(ActionEvent e) {
		                if (colorKey >= colors.length) {
		                    colorKey = 0;
		                }
		//zwiêkszamy colorKey dopóki nie jest wiêkszy lub równy
		//d³ugoœci macierzy kolorów
		//wtedy wracamy do pocz¹tku macierzy (czyli colorKey=0)
		                button.setBackground(colors[colorKey]);
		                colorKey++;
		            }
		        });
		        add(button);
		
		        pack();
		        setVisible(true);
		    }}
	
	      SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	              new guzik();
	          }
	      });
	  }
}
