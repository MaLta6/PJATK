package zad2;

import java.beans.*;

public class PriceLimitator implements VetoableChangeListener {
	// minimalna warto�� ceny
	private Double min;

	PriceLimitator(Double minLim)  {
	  min = minLim;
	  
	}

	// Obs�uga zdarzenia vetoableChange
	// metoda mo�e sygnalizowa� PropertyVetoException
	public void vetoableChange(PropertyChangeEvent e)
	            throws PropertyVetoException {
	   Double newVal = (Double) e.getNewValue();
	   Double val = newVal.doubleValue();
	   // Sprawdzamy, czy zmiana  ceny jest dopuszczalna,
	   // je�li nie � sygnalizujemy wyjatek  PropertyVetoException
	   if (val < min )
	      throw new PropertyVetoException("Price change to: " + newVal + " not allowed", e);
	   }
}
