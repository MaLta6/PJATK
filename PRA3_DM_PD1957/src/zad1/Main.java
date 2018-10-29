/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad1;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


class CTFConverter {

	
	
	public static double convertCelsiusToFahrenheit(double degC) {
			
		return (degC * 9 / 5) + 32;
	}
	
}

class CFJList extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private	JPanel						topPanel;
	private	JList<String>				listbox;
	
	
	public CFJList() {	
		setTitle("Celsius -> Fahrenheit");
		
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
						
		listbox = new JList<String>();
		listbox.setModel(Z51CreateListModel(-70, 60));
		
		topPanel.add(new JScrollPane(listbox));
		
	}
	
	
	
	private DefaultListModel<String> Z51CreateListModel(double minTemp, double maxTemp) {
		
		DefaultListModel<String> 	listModel;
		
		listModel = new DefaultListModel<String>();
		
		for(int k=(int) minTemp; k<=maxTemp; k++) {
			listModel.addElement(k+" stopni C = "+String.format("%1$,.2f", CTFConverter.convertCelsiusToFahrenheit(k))+" stopni F");	
		}

		return listModel;
	}
	
	
}


public class Main {

  public static void main(String[] args) {
	 
	  CFJList mainFrame = new CFJList();
	  
	  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
      mainFrame.pack();
      mainFrame.setLocationByPlatform(true);
	  mainFrame.setVisible(true);

  }
}
