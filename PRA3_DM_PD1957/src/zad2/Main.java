/**
 *
 *  @author Dylewska Marta PD1957
 * W oknie jest: JTextField i JList . 

Wpisanie tekstu w JTextField i wciśnięcie Enter powoduje wstawienie tekstu na listę.
Zaznaczone elementy mogą być z listy usuwane przez alt-kliknięcie na liście. 

 */

package zad2;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

class JLista extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 8306444463088260779L;
	private	JPanel						topPanel;
	private	JList<String>				listBox;
	private JTextField					textBox;
	private DefaultListModel<String>	listModel;

	public JLista() {		
		setTitle("JLista");
		
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
						
		listModel = new DefaultListModel<String>();
		
		listBox = new JList<String>();
		
		listBox.setModel(listModel);				
		
		listBox.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (
						
						e.isAltDown() 
						
						
						&& listBox.getSelectedIndex() >= 0
						) 
				{
				
					listModel.remove(listBox.getSelectedIndex());
					
				}
			}
		});
		
		textBox = new JTextField();
		
		textBox.addKeyListener( new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e)
            {
            	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            		listModel.addElement(textBox.getText());
            		textBox.setText("");
            	}
            }
		});
		
		topPanel.add(new JScrollPane(listBox));
		topPanel.add(textBox, BorderLayout.PAGE_END);		
	}
	


}



public class Main {

  public static void main(String[] args) {
	  
	  
	  JLista mainFrame = new JLista();
	  
	  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
      mainFrame.pack();
      mainFrame.setLocationByPlatform(true);
	  mainFrame.setVisible(true);

  }
}
