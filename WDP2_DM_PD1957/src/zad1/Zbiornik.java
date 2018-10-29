/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad1;


public class Zbiornik {
	private double volume;
	public static int tankNo = 0;
	private double stWater = 0;
	public Zbiornik(int v)
	{
		volume = v;
		tankNo++;
	}
			
	double dolej(double woda) {
		
		if (woda>=(volume-stWater)) {
			stWater = volume;
		}
		else {
			stWater=stWater + woda;
		}
		return(stWater);
	}

	double odlej(double woda) {
		if (woda>=stWater){
			stWater=0;
		}
		else {
			stWater = stWater-woda;
		}
		return (stWater);
	}
	public String toString() {
		return " Zbiornik "+tankNo+", pojemnosc "+volume+", stan wody "+stWater;
		
	}
}  
