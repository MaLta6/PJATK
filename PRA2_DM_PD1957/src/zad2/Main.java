/**
 *
 *  @author Dylewska Marta PD1957
 * Uzyskać komponenty i rozkład jak na poniższym rysunku:
 
 Uwaga:

1.	przyciski A1-A3 są wyrównane w lewo i nie zmieniają swoich rozmiarów,
2.	przyciski B1-B3 są wyrównane w prawo i nie zmieniają swoich rozmiarów,
3.	przyciski numeryczne nie zmieniają swoich rozmiarów,
4.	pola tekstowe (JTextField) mogą zmieniać swoje rozmiary i położenie.

 */

package zad2;


import java.awt.*;
import javax.swing.*;
 
public class Main {
 
    public static void main(String[] args) {
 
        JFrame frame = new JFrame();
 
        frame.setLayout(new BorderLayout(2,2));
 //panel na górze po lewej stronie
        JPanel panelTL = new JPanel();
        panelTL.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelTL.add(new JButton("A1"));
        panelTL.add(new JButton("A2"));
        panelTL.add(new JButton("A3"));
 //panel na górze po prawej stronie
        JPanel panelTR = new JPanel();
        panelTR.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelTR.add(new JButton("B1"));
        panelTR.add(new JButton("B2"));
        panelTR.add(new JButton("B3"));
 //górny panel zawieraj¹cy obydwa panele
        JPanel panelTopContainer = new JPanel();
        panelTopContainer.setLayout(new GridLayout(1, 2));
        panelTopContainer.add(panelTL);
        panelTopContainer.add(panelTR);
 
        frame.add(panelTopContainer,BorderLayout.NORTH);
//text area 
        JTextArea textA = new JTextArea("To jest JTextArea",30,80);
        frame.add(new JScrollPane(textA),BorderLayout.CENTER);
 //dolny panel po lewej (z guzikami)
        JPanel panelBottomLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel = new JPanel(new GridLayout(3,3,2,2));
        for (int j = 1; j < 10; j++) {
             panel.add(new JButton("" + j));
            }
        panelBottomLeft.add(panel);
 //dolny panel po prawej (z textField)
        JPanel panelBottomRight = new JPanel();
        panelBottomRight.setLayout(new GridLayout(3,1,2,2));
        for (int j = 0; j < 3; j++) {
            JTextField textf = new JTextField("To jest JTextField");
            textf.setBorder( BorderFactory.createLineBorder(Color.RED) );
            panelBottomRight.add(textf);
        }
 
        JPanel panelBottomContainter = new JPanel();
        panelBottomContainter.setLayout(new GridLayout(1, 2));
        panelBottomContainter.add(panelBottomLeft);
        panelBottomContainter.add(panelBottomRight);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.add(panelBottomContainter,BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
 
    }
} 
