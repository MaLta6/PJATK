package zad1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Database {
	
	public static String url;
	public static TravelData travelData;
	public static String[][] tabela=new String[10][6];
	private static DefaultTableModel tblModel;
	public static List<List<String>> listaWLiscie = new ArrayList<>();
	public static List<String> l1 = new ArrayList<>();
	public static List<String> l2 = new ArrayList<>();
	public static int row;
	
	public Database(String url_bis, TravelData travelData_bis){
		url = url_bis;
		travelData = travelData_bis;
	}
public static void create(){
	String tableName = "WYJAZDY";
    // jdbc Connection
    Connection conn = null;
    //statement do rozpoczęcia komend SQL
    Statement stmt = null;
    //List<List<String>> listaWLiscie = new ArrayList<>();
    try {
		listaWLiscie = TravelData.printContentOfFilesInDirectory();
		//Rejestracja sterownika JDBC
    	//Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        //tworzenie połączenia
        conn = DriverManager.getConnection(url+";create=true");
        
        if(conn!=null)
        {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, tableName.toUpperCase(),null);
            if(rs.next())
            {
                System.out.println("Table "+rs.getString("TABLE_NAME")+"already exists !!");
            }
            else
            {
            	stmt = conn.createStatement();
                stmt.executeUpdate("CREATE TABLE " + tableName + " (LOKALIZACJA CHAR(10), COUNTRY CHAR(30), DATA_OD DATE, DATA_DO DATE, TYP_MIEJSCA CHAR(20), CENA DOUBLE, WALUTA CHAR(10) )");
                rs.close();
                stmt.close();
            }
         }
        PreparedStatement stmt1;
        
        stmt1 = conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)");
    	
    	//List<String> l1 = new ArrayList<String>();
    	int i;
    	int j = listaWLiscie.size();
    	
    	
    	for(i=0; i<j; i++ ){
    		l1 = listaWLiscie.get(i);
    		String el0 = l1.get(0);
            String el1 = l1.get(1);
            String el2 = l1.get(2);
            String el3 = l1.get(3);
            String el4 = l1.get(4);
            String el5 = l1.get(5);
            
            //do obsługi wyświetlania cen - przerabiamy to co wczytuje się jako pierwsza zmienna (el0) i podajemy ją do el_C obcinając wszystko poza pierwszymi
            //dwoma znakami, określającymi lokalizację zgodnie z tabelą zawartą w wykładzie 9.
            String el_C = el0.substring(0, 2);
            
            //ustawiamy, żeby pobrał liczbę zgodnie z lokalizacją zawartą w zmiennej el_C
            NumberFormat format = NumberFormat.getNumberInstance(new Locale(el_C));
            
            //parsowanie tekstu zawartego w el5 tak, aby w wyniku otrzymać numer
            Number el55 = format.parse(el5);
            
            Double el555 = el55.doubleValue();
            String el6 = l1.get(6);
            
            
            stmt1.setString(1, el0);
            stmt1.setString(2, el1);
            stmt1.setString(3, el2);
            stmt1.setString(4, el3);
            stmt1.setString(5, el4);
            stmt1.setDouble(6, el555);
            stmt1.setString(7, el6);
            
            stmt1.executeUpdate();
            
            
    	}
    	
       
        stmt1.close();
    
    }
 //OD TEGO MOMENTU MOŻNA WYWALIĆ AŻ DO CATCH
 //BO TO JEST TYLKO SPRAWDZENIE ZAWARTOŚCI TABLIC
        /*
        stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("select * from " + tableName);
        ResultSetMetaData rsmd = results.getMetaData();
        int numberCols = rsmd.getColumnCount();
        for (int a=1; a<=numberCols; a++)
        {
            //wydruk nazw kolumn
            System.out.print(rsmd.getColumnLabel(a)+"\t\t");  
        }

        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------");

        while(results.next())
        {
        	
            String lokalizacja = results.getString(1);
            String kraj = results.getString(2);
            String data_od = results.getString(3);
            String data_do = results.getString(4);
            String typ_miejsca = results.getString(5);
            String cena = results.getString(6);
            String waluta = results.getString(7);
            System.out.println(lokalizacja + "\t\t" + kraj + "\t\t" + data_od + "\t\t" + data_do + "\t\t" + typ_miejsca + "\t\t" + cena + "\t\t" + waluta);
        }
        results.close();
        stmt.close();
    
	}*/ 
    
    catch (Exception e) {
		
		e.printStackTrace();
	}
}


public List<List<String>> forGUILoc (String In1, String In2){
	List<List<String>> result1 = new ArrayList();
	for(int ij = 0; ij<listaWLiscie.size(); ij++){
		List<String> listaPom = new ArrayList();
		l2 = listaWLiscie.get(ij);
		
	   String lok = l2.get(0);
	   
	   String countryCode = l2.get(0).substring(0, 2);
	   Locale.setDefault(new Locale(countryCode));
	   Locale[] loc1 = Locale.getAvailableLocales();
	    Map<String, Locale> map = new HashMap<String, Locale>();
	    String kraj;
		    for (int ii=0; ii<loc1.length; ii++) {
		        String countryCode1 = loc1[ii].getCountry();  // kod kraju
		        if (countryCode1.equals("")) continue;
		        kraj =  loc1[ii].getDisplayCountry();
		        map.put(kraj, loc1[ii]);
		        
		    }
	    kraj = l2.get(1).toString();
	    Locale savedLoc = (Locale) map.get(kraj);
	    
	    
	    		    
	    
	    Locale lang = new Locale(In1,In2);
	    
	    
	    String rep = savedLoc.getDisplayCountry(lang) ;
		
		String dataOdString = l2.get(2);
		
		String dataDoString = l2.get(3);
		
		
		String atrakJez = "lake";
	    String atrakMorz = "sea";
	    String atrakGor = "mountains";
	    String atrakcjaTyp = l2.get(4);
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
	    
	    
	    String cena = l2.get(5);
	    
	    NumberFormat nf = NumberFormat.getInstance(new Locale(countryCode));
	    
	    Number cenaN = 0;
		try {
			cenaN = nf.parse(cena);
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
	    
		Double cenaD = 0.0;
	    cenaD = cenaN.doubleValue();
	    
	    NumberFormat nf2 = NumberFormat.getInstance(lang);
	    
	    String cenaLokal = nf2.format(cenaD);
	    
	    
	    String walutaKraju = l2.get(6);
	    listaPom.add(rep);
	    listaPom.add(dataOdString);
	    listaPom.add(dataDoString);
	    listaPom.add(atrakcja);
	    listaPom.add(cenaLokal);
	    listaPom.add(walutaKraju);
	    result1.add(listaPom);
	}
	
	return result1;
}
   



public void showGui(){
    Vector rowData = new Vector();
    String[] nazwykolumn = { "lokalizacja","KRAJ","Data_od","Data_do","Kierunek","Cena","Waluta"};
     
     
    try {
     
    listaWLiscie = TravelData.printContentOfFilesInDirectory();
     
    }
    catch (IOException e ) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
    int i;
       int j = listaWLiscie.size();
      
    for(i=0; i<j; i++ ){
    l1 = listaWLiscie.get(i);
       Vector colData = new Vector(l1);
       rowData.add(colData);
              
    }
    Vector columnNamesV = new Vector(Arrays.asList(nazwykolumn));
    
    final int DEFAULT_WIDTH = 2000;
    final int DEFAULT_HEIGHT = 2000;
    String kier[] = { "West", "North", "East", "South", "Center" };  
    

   //budowanie JFrame
       JFrame frame = new JFrame("OFERTY WYJAZDOWE BIURA");
        frame.setLayout(new BorderLayout());
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // menu: Regiony
                JMenu region = new JMenu("Region");

                JMenuItem regionPl = new JMenuItem("Polska");
                regionPl.setToolTipText("Zmień opcje regionalne na polskie");
                regionPl.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                        ActionEvent.CTRL_MASK));
                regionPl.addActionListener(new ActionListener(){
                      public void actionPerformed(ActionEvent e) {
                    	  String[] en_colNames = { "Lokalizacja","Kraj","Przyjazd","Wyjazd","Kierunek","Cena","Waluta"};
                          tblModel.setColumnIdentifiers(en_colNames);
                          List<String> listaTD = new ArrayList<String>();
   					   
   					   for (row = 0; row < tblModel.getRowCount(); row++) {
   						   listaTD = forGUILoc("pl", "PL").get(row);
   						   
   						   tblModel.setValueAt(listaTD.get(0),row,1);
   				    		tblModel.setValueAt(listaTD.get(1), row, 2);
   				    		tblModel.setValueAt(listaTD.get(2), row, 3);
   				    		tblModel.setValueAt(listaTD.get(3), row, 4);
   				    		tblModel.setValueAt(listaTD.get(4), row, 5);
   				    		tblModel.setValueAt(listaTD.get(5), row, 6);
   					   
   				    		
   					   }
   			         }
   			   });


                JMenuItem regionNz = new JMenuItem("Nowa Zelandia");
                regionNz.setToolTipText("Zmień opcje regionalne na nowo zelandzkie");
                regionNz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                        ActionEvent.CTRL_MASK));
                regionNz.addActionListener(new ActionListener(){
                      public void actionPerformed(ActionEvent e) {
                          String[] en_colNames = { "Localisation","Country","Date_From","Date_To","Direction","Price","Currency"};
                          tblModel.setColumnIdentifiers(en_colNames);
                		
					   
List<String> listaTD = new ArrayList<String>();
					   
					   for (row = 0; row < tblModel.getRowCount(); row++) {
						   listaTD = forGUILoc("en", "NZ").get(row);
						   
						   tblModel.setValueAt(listaTD.get(0),row,1);
				    		tblModel.setValueAt(listaTD.get(1), row, 2);
				    		tblModel.setValueAt(listaTD.get(2), row, 3);
				    		tblModel.setValueAt(listaTD.get(3), row, 4);
				    		tblModel.setValueAt(listaTD.get(4), row, 5);
				    		tblModel.setValueAt(listaTD.get(5), row, 6);
					   }}
			   });

                JMenuItem regionPo = new JMenuItem("Portugalia");
                regionPo.setToolTipText("Zmień opcje regionalne na portugalskie");
                regionPo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                        ActionEvent.CTRL_MASK));
                regionPo.addActionListener(new ActionListener(){
                      public void actionPerformed(ActionEvent e) {
                         
                          String[] no_colNames = { "Localização", "País", "Data De", "Data Para", "Direção", "Preço", "Moeda"};
                          tblModel.setColumnIdentifiers(no_colNames);
                         
                          List<String> listaTD = new ArrayList<String>();
   					   
   					   for (row = 0; row < tblModel.getRowCount(); row++) {
   						   listaTD = forGUILoc("pt", "PT").get(row);
   						   
   						   tblModel.setValueAt(listaTD.get(0),row,1);
   				    		tblModel.setValueAt(listaTD.get(1), row, 2);
   				    		tblModel.setValueAt(listaTD.get(2), row, 3);
   				    		tblModel.setValueAt(listaTD.get(3), row, 4);
   				    		tblModel.setValueAt(listaTD.get(4), row, 5);
   				    		tblModel.setValueAt(listaTD.get(5), row, 6);
   					   }
   					 
   			         }
   			   }
   					   );

       
       
       
                region.add(regionPo);
                region.add(regionPl);
                region.add(regionNz);
       
       
                JMenuBar menu = new JMenuBar();
                menu.add(region);
                setJMenuBar(menu);
                frame.add(menu, kier[1]);     

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
  
                setTblModel(new DefaultTableModel(rowData, columnNamesV));
                JTable table = new JTable(getTblModel());
                int rowNo= getTblModel().getRowCount();
  
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
  
   				frame.add(new JScrollPane(table));
   				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   				frame.pack();
   
   				frame.setVisible(true);
   }

private void setJMenuBar(JMenuBar menu) {
    
   
}

public static void setvalue(String position,int record,int number){
    getTblModel().setValueAt(position, record, number);
}
public static DefaultTableModel getTblModel() {
    return tblModel;
}

public void setTblModel(DefaultTableModel tblModel) {
    this.tblModel = tblModel;
}
   
   
   
     
   }

