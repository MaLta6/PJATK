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
	// Pomocniczy obiekt do prowadzenia listy s³uchaczy zmian w³aœciwoœci oraz
	  // propagowania zmian  wœród zarejestrowanych s³uchaczy
	private PropertyChangeSupport chg = new PropertyChangeSupport(this);
	private VetoableChangeSupport veto = new VetoableChangeSupport(this);
	
	
	//w³aœciwoœci obiektu purchase
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
	
	 // Metody przy³¹czania i od³¹czania s³uchaczy zmian w³aœciwoœci

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
	  
	// ob³uga zdarzenia PropertyChange
	  public synchronized void propertyChange(PropertyChangeEvent e)  {
	    
	    System.out.println( "Change value of:  " + e.getPropertyName() +" from: " + e.getOldValue() + " to: " + e.getNewValue());
	    
	   }
	  
	  //metoda dla ustawiania nowej nazwy Prod
	  synchronized void setProd(String newProd) { // pamiêtamy o wielow¹tkowoœci!

		    String oldProd =  prod;   // stara wartoœæ
		    prod = newProd;           // ustalenie nowej wartoœci
		     // powiadomienie
		    chg.firePropertyChange("prod", oldProd, newProd);
		}
	  
	  synchronized void setData(String newData) { // pamiêtamy o wielow¹tkowoœci!

		    String oldData =  data;   // stara wartoœæ
		    data = newData;           // ustalenie nowej wartoœci
		     // powiadomienie
		    chg.firePropertyChange("data", oldData, newData);
		}
	  
	  synchronized void setPrice(Double newPrice)  throws PropertyVetoException
	  {
	     Double oldPrice =  price;  // stara wartoœæ

	      // wywo³ujemy metodê fireVotoableChange, która z kolei
	      // wywo³uje metody vetoableChange zarejestrowanych s³uchaczy
	      // jeœli któraœ z nich zg³asza veto, setter koñczy dzia³anie
	      // a wyj¹tek PropertyVetoException jest przekazywany do obs³ugi
	      //  przez metodê wywo³uj¹c¹ setText

	      veto.fireVetoableChange("price", oldPrice, newPrice);

	      // Tylko jeœli nikt nie zawetowa³ zmiany:

	      price = newPrice;   // ustalenie nowej wartoœci
	      
	      chg.firePropertyChange("price", new Double(oldPrice),
                  new Double(newPrice));
	      
	 }
	  
	 


	  @Override public String toString(){
		  
		return "Purchase [prod=" + prod +", data=" + data +", price=" + price +"]  ";
		  
	  }

	
	
}  
