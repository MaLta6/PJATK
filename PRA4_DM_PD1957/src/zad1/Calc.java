/**
 *
 *  @author Dylewska Marta PD1957
 *
 */

package zad1;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;



public class Calc {
	//pary obiekt�w typu String i method - wykorzystanie kolekcji HashMap
    private HashMap<String,Method> metody = new HashMap<String,Method>();
    //liczba miejsc po przecinku
    private MathContext mathCon = new MathContext(7);
    //------------------------
    public Calc()
    {
        try
        {
            //dost�p do obiekt�w - klas uzyskujemy jedynie poprzez u�ycie odpowiednich metod
        	//np. forName(String NazwaKlasy)
        	//uzyskujemy odno�nik do danej klasy
        	//obiekt�w-klas nie mo�na tworzy� za pomoc� wyra�enia new
        	Class<?> kl = Class.forName("java.math.BigDecimal");
        	//metodzie method odpowiada metoda add
            Method method = kl.getMethod("add",BigDecimal.class);
            //wstawianie wartosci do Hashmap -czyli stringowi"+" odpowiada metoda add z klasy BigDecimal
            metody.put("+",method);
            method = kl.getMethod("subtract",BigDecimal.class);
            metody.put("-",method);
            method = kl.getMethod("multiply",BigDecimal.class);
            metody.put("*",method);
            method = kl.getMethod("divide",BigDecimal.class,MathContext.class);
            metody.put("/",method);              
        }
        catch(ClassNotFoundException | NoSuchMethodException e)
        {
 
        }
    }
    
    //metoda doCalc klasy Calc
    public String doCalc(String zestaw)
    {
        //rozbi�r na argumenty - tworzenie macierzy
    	String[] znaki = zestaw.split("\\s+");
    	//arg1 jest obiektem klasy BigDecimal
        BigDecimal bd1 = null;
        BigDecimal bd2 = null;
        Method method = null;
        BigDecimal result = null;
        try
        {
            bd1 = new BigDecimal(znaki[0]);
            bd2 = new BigDecimal(znaki[2]);
            method = metody.get(znaki[1]);
            //wywo�anie metody method (zdefiniowanej powy�ej) na potrzeby argument�w klasy BigDecimal
            result = (BigDecimal)method.invoke(bd1,bd2);
            //zwraca obiekt klasy String
            return result.toString();
        }
        catch(IllegalArgumentException e)
        {
            try
            {
                result = (BigDecimal)method.invoke(bd1,bd2,mathCon);
                return result.toString();
            }
            catch(Exception e2)
            {
                return "Invalid command to calc";                
            }
        }
        catch (Exception e)
        {
            return "Invalid command to calc";
        }
    }}
