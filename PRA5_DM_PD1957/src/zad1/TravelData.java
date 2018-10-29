package zad1;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TravelData {
	private static File dataDirectory;
	
	public TravelData(File file){
		dataDirectory = file;
	}
	
	public static List<List<String>> printContentOfFilesInDirectory() throws IOException {
		
		List<List<String>> listaWLiscie = new ArrayList<>();
		//File directory = new File(dirPathname);
		//lista plików w danej ścieżce
		File[] files = dataDirectory.listFiles();
		String line = null;			
		Scanner scan;
		int n = 0;
		
		if(!dataDirectory.isDirectory()){

			System.out.println("zła ścieżka");

		}
		else{
		
		
		//dla każdego pliku w files jest sprawdzenie czy ten plik jest rzeczywiście plikiem 
		//jeśli tak to jego zawartość jest drukowana
		//jeśli nie to uznawany jest on za katalog i metoda jest powtarzana ale z wykorzystaniem go jako ścieżki
		for (File file : files) {

			if(file.isFile()){
				
				BufferedReader reader = new BufferedReader(new FileReader(file));
					
				while((line = reader.readLine()) !=null) {
					//lista do zapisu zawartości poszczególnego pliku
					List<String> lista = new ArrayList<String>();
					//pobieramy wartosci oddzielone znakiem tabulacji
					StringTokenizer st = new StringTokenizer(line, "\t");
					
					while(st.hasMoreElements()) {
						//element staje się tym co wysupłał tokenizer
						String element = st.nextElement().toString();
						//element jest dodawany do listy
						lista.add(element);
						
					}
					//dodanie tego co znajduje się w liście do listyWLiscie (macierzy 2D)
					listaWLiscie.add(lista);
					
				}
				reader.close();
									
			} else {
				System.out.println("Not a directory");;

			}
			
		}
				
	}
		return listaWLiscie;
	}
	
	public static List<String> getOffersDescriptionsList(String loc, String formatDaty) {
    	//OBSŁUGA NAZW KRAJÓW
		List<String> result = new ArrayList<String>();
		//result = null;
		try {
    	List<String> l1 = new ArrayList<String>();
    	
    	List<List<String>> listaWLiscie1 = new ArrayList<>();
    	listaWLiscie1 = printContentOfFilesInDirectory();
    	int i;
    	int j = listaWLiscie1.size();
    	
    	String countryCode;
    	String kraj ;
	    String in;
	    String in1;
	    Locale savedLoc;
	    String rep ;
	    Locale lang;
	    
	    //OBSŁUGA KONWERSJI DAT
	  //obecny format daty - jak w pliku txt
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	String dataOdString;
    	String dataDoString;
    	String dataOdPoFormacie;
    	String dataDoPoFormacie;
    	String dateFromNew;
    	String dateToNew;
    	Date dateFrom;
    	Date dateTo;
	    //nowy - pożądany - format daty; do testowania wybrałam inny niż podany w zadaniu
	    SimpleDateFormat sm = new SimpleDateFormat(formatDaty);
	    
	    //OBSŁUGA WALUT
	    String walutaKraju;
	    
	    //obsługa CEN
	    String cena;
	    Double cenaD;
	    NumberFormat nf;
	    String nowaCena;
	    Number cenaN;
	    NumberFormat nf2;
	    String cenaLokal;
	    
    	try{
    	for(i=0; i<j; i++ ){
    		l1 = listaWLiscie1.get(i);
    		countryCode = l1.get(0).substring(0, 2);
    		//pobieramy zawartość zmiennej prezentującej datę od
    		dataOdString = l1.get(2);
    		//i datę do
    		dataDoString = l1.get(3);
    		Locale.setDefault(new Locale(countryCode));
    		
    		Locale[] loc1 = Locale.getAvailableLocales();
		    Map<String, Locale> map = new HashMap<String, Locale>();
		    
			    for (int ii=0; ii<loc1.length; ii++) {
			        String countryCode1 = loc1[ii].getCountry();  // kod kraju
			        if (countryCode1.equals("")) continue;
			        kraj =  loc1[ii].getDisplayCountry();
			        map.put(kraj, loc1[ii]);
			        
			    }
		    kraj = l1.get(1).toString();		   
    		    		
		    savedLoc = (Locale) map.get(kraj);
		    		    
		    in = loc.substring(0, 2);
		    		    
		    in1 = loc.toString().substring(3, 5);
		    		    
		    
		    lang = new Locale(in,in1);
		    rep = savedLoc.getDisplayCountry(lang) ;
		    //najpierw konwertujemy ze stringa na datę
		    dateFrom = formatter.parse(dataOdString);
		    dateTo = formatter.parse(dataDoString);
		    //potem znowu na string
		    dataOdPoFormacie= formatter.format(dateFrom);
		    dataDoPoFormacie = formatter.format(dateTo);
		    //a w międzyczasie zmienną typu date konwertujemy na nowy string w pożądanym formacie
		    dateFromNew = sm.format(dateFrom);
		    dateToNew = sm.format(dateTo);
		    
		    //OBSŁUGA WALUT
		    walutaKraju = l1.get(6);
		    
		    //OBSŁUGA CEN - analogicznie jak w metodzie insertData
		    //dla zmiennej typu Double
		    //1. pobieramy cenę z macierzy stringów
		    cena = l1.get(5);
		    //2.metodę getInstance dla numberFormat robimy dla lokalizacji
		    //z pierwszej pozycji w pliku txt
		    nf = NumberFormat.getInstance(new Locale(countryCode));
		    //3.parsujemy string do formatu number
		    cenaN = nf.parse(cena);
		    //4.z number robimy wartość double
		    cenaD = cenaN.doubleValue();
		    //5.teraz ustawiamy lokalizację na lang, czyli taką jak podaną
		    //w argumencie metody
		    nf2 = NumberFormat.getInstance(lang);
		    //6. i odpowiednio wyświetlamy cenę w zależności od potrzeb
		    cenaLokal = nf2.format(cenaD);
		    
		    //OBSŁUGA TYPU ATRAKCJI (jezioro/morze/góry)
		    String atrakJez = "lake";
		    String atrakMorz = "sea";
		    String atrakGor = "mountains";
		    String atrakcjaTyp = l1.get(4);
		    if(atrakcjaTyp.equals(atrakJez)){
		    	atrakcjaTyp = "jezioro";
		    }
		    else if(atrakcjaTyp.equals(atrakGor)){
		    	atrakcjaTyp = "góry";
		    }
		    else if(atrakcjaTyp.equals(atrakMorz)){
		    	atrakcjaTyp = "morze";
		    }
		    ResourceBundle msgs = ResourceBundle.getBundle("zad1.typAtrakcji", lang);
		    String atrakcja = msgs.getString(atrakcjaTyp);
		    		    
         //System.out.println(rep + " " + dateFromNew + ", "+dateToNew  + ", " +  cenaLokal+ ", "+ walutaKraju+ ", "+atrakcja);
         String calosc = l1.get(0) + " " + rep + " " + dateFromNew +" "+dateToNew+" "+atrakcja+" "+cenaLokal+" "+walutaKraju;
         
         result.add(calosc);
    	}
    	}
    	catch (ParseException e) {
    		System.out.println("Parse Exception. Odczytany string najprawdopodobniej nie może być przekonwertowany na Date");
    	}
    	catch (Exception exc){
    		exc.printStackTrace();
    	}
		
    }
		catch (IOException exc){
			exc.printStackTrace();
		}
		return result;
	}
	
}
