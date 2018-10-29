package zad2;

import java.beans.*;

public class PriceLimitator implements VetoableChangeListener {
	// minimalna wartoœæ ceny
	private Double min;

	PriceLimitator(Double minLim)  {
	  min = minLim;
	  
	}

	// Obs³uga zdarzenia vetoableChange
	// metoda mo¿e sygnalizowaæ PropertyVetoException
	public void vetoableChange(PropertyChangeEvent e)
	            throws PropertyVetoException {
	   Double newVal = (Double) e.getNewValue();
	   Double val = newVal.doubleValue();
	   // Sprawdzamy, czy zmiana  ceny jest dopuszczalna,
	   // jeœli nie – sygnalizujemy wyjatek  PropertyVetoException
	   if (val < min )
	      throw new PropertyVetoException("Price change to: " + newVal + " not allowed", e);
	   }
}
