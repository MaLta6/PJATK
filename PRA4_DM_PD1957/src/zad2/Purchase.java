/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad2;

import java.beans.*;

import java.io.Serializable;

public class Purchase implements Serializable, PropertyChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6730125885521081147L;
	// Pomocniczy obiekt do prowadzenia listy s�uchaczy zmian w�a�ciwo�ci oraz
	  // propagowania zmian  w�r�d zarejestrowanych s�uchaczy
	private PropertyChangeSupport chg = new PropertyChangeSupport(this);
	private VetoableChangeSupport veto = new VetoableChangeSupport(this);
	
	
	//w�a�ciwo�ci obiektu purchase
	String prod;
	String data;
	Double price;
	
	//public Purchase(String prod, String data, Double price){
	//	this.prod = prod;
	//	this.data = data;
	//	this.price = price;
	//	
	//}
	public Purchase(String prod, String data, Double price){
		chg.addPropertyChangeListener(this);
		this.prod = prod;
		this.data = data;
		this.price = price;
	}
	
	 // Metody przy��czania i od��czania s�uchaczy zmian w�a�ciwo�ci

	  public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
	    chg.addPropertyChangeListener(listener);
	  }

	  public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
	    chg.removePropertyChangeListener(l);
	  }
	  
	  public synchronized void addVetoableChangeListener(VetoableChangeListener l1) {
		  veto.addVetoableChangeListener(l1);
		  }

	  public synchronized void removeVetoableChangeListener(VetoableChangeListener l2) {
		  veto.addVetoableChangeListener(l2);
		  }
	  
	// ob�uga zdarzenia PropertyChange
	  public synchronized void propertyChange(PropertyChangeEvent e)  {
	    
	    System.out.println( "Change value of:  " + e.getPropertyName() +" from: " + e.getOldValue() + " to: " + e.getNewValue());
	    
	   }
	  
	  //metoda dla ustawiania nowej nazwy Prod
	  synchronized void setProd(String newProd) { // pami�tamy o wielow�tkowo�ci!

		    String oldProd =  prod;   // stara warto��
		    prod = newProd;           // ustalenie nowej warto�ci
		     // powiadomienie
		    chg.firePropertyChange("prod", oldProd, newProd);
		}
	  
	  synchronized void setData(String newData) { // pami�tamy o wielow�tkowo�ci!

		    String oldData =  data;   // stara warto��
		    data = newData;           // ustalenie nowej warto�ci
		     // powiadomienie
		    chg.firePropertyChange("data", oldData, newData);
		}
	  
	  synchronized void setPrice(Double newPrice)  throws PropertyVetoException
	  {
	     Double oldPrice =  price;  // stara warto��

	      // wywo�ujemy metod� fireVotoableChange, kt�ra z kolei
	      // wywo�uje metody vetoableChange zarejestrowanych s�uchaczy
	      // je�li kt�ra� z nich zg�asza veto, setter ko�czy dzia�anie
	      // a wyj�tek PropertyVetoException jest przekazywany do obs�ugi
	      //  przez metod� wywo�uj�c� setText

	      veto.fireVetoableChange("price", oldPrice, newPrice);

	      // Tylko je�li nikt nie zawetowa� zmiany:

	      price = newPrice;   // ustalenie nowej warto�ci
	      
	      chg.firePropertyChange("price", new Double(oldPrice),
                  new Double(newPrice));
	      
	 }
	  
	 


	  @Override public String toString(){
		  
		return "Purchase [prod=" + prod +", data=" + data +", price=" + price +"]  ";
		  
	  }

	
	
}  
