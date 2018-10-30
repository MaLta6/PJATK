/*
Stworzyć tabelę (JTable), pokazującą książki.
Książka ma:
•	autora
•	tytul
•	cenę
•	okładkę (obrazek)
 
Informacja o książkach ma być wczytana z pliku. (a obrazki z plkiów graficznych  z tego samego katalogu)
Należy zapewnić:
•	możliwość edycji cen w tabeli (w komórce)
•	możlwiość dodawania i usuwania wierszy do/z  tabeli z poziomu GUI.

*/

package zad3;



import java.util.*;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


import java.awt.*;
import javax.swing.*;


/** 
 * 

 *
 */
class Book {

	private String author;
	private String title;
	private double price;
	//private JLabel lab;
	
	
	/** Konstruktor tworz¹cy
	 * @param author autor
	 * @param title tuty³ ksi¹¿ki
	 * @param price cena ksi¹¿ki
	 * @param JLabel ok³adka ksi¹¿ki
	 */
	public Book(String author, String title, double price){
			//, JLabel lab) {
		super();
		this.author = author;
		this.title = title;
		this.price = price;
		//this.lab=lab;
	}
	
	public Book() {
		super();
		this.author="";
		this.title="";
		this.price=0;
		//this.lab=null;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	//public JLabel getLabel(){
	//	return lab;
	//}
	//public void setLabel(JLabel label){
	//	this.lab=label;
	//}
	
}



class MyRenderer  extends DefaultTableCellRenderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8571898492631089521L;
	private String s;
	private Icon icon;
	
	public MyRenderer(String s){
		super();
		this.s = s;
		setOpaque(true);
		
	};
	 
	  public Component getTableCellRendererComponent(JTable table, Object value,
	                                                 boolean isSelected, boolean hasFocus, 
	                                                 int row, int column) {
		  
		  ImageIcon imageIcon = new ImageIcon(s);
		  
	        setIcon(imageIcon);
	        return this;
	 
	   	
	  }
	}

class InteractiveTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 50152112062425292L;	

	public static final int AUTHOR_INDEX = 0;
    public static final int TITLE_INDEX = 1;
    public static final int PRICE_INDEX = 2;
    public static final int IMAGE_INDEX = 3;
    public static final int HIDDEN_INDEX = 4;
	
    protected String[] columnNames;
    protected Vector<Book> dataVector;

    public InteractiveTableModel(String[] columnNames) {
        this.columnNames = columnNames;
        dataVector = new Vector<Book>();
    }    
    
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public boolean isCellEditable(int row, int column) {
        if (column == HIDDEN_INDEX) return false;
        else return true;
    }
        
	public Class<?> getColumnClass(int column) {
        switch (column) {
            case AUTHOR_INDEX:
            case TITLE_INDEX:            
               return String.class;
               
            case PRICE_INDEX:
            	return Double.class;
            default:
               return Object.class;
        }
    }
    
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return dataVector.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
        Book book = dataVector.get(row);
        switch (column) {
            case AUTHOR_INDEX:
               return book.getAuthor();
            case TITLE_INDEX:
               return book.getTitle();
            case PRICE_INDEX:
               return book.getPrice();
            //case IMAGE_INDEX:
            //	return book.getLabel();
            default:
               return new Object();
        }        		
	}
	
	public void setValueAt(Object value, int row, int column) {
        Book book = dataVector.get(row);
        switch (column) {
            case AUTHOR_INDEX:
               book.setAuthor((String)value);
               break;
            case TITLE_INDEX:
               book.setTitle((String)value);
               break;
            case PRICE_INDEX:
               book.setPrice((Double)value);
               break;
           // case IMAGE_INDEX:
            //	book.setLabel((JLabel)value);
            //	break;
            default:
               System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);		
	}
	
	public boolean hasEmptyRow() {
        if (dataVector.size() == 0) return false;
        Book book = dataVector.get(dataVector.size() - 1);
        if (book.getAuthor().trim().equals("") &&
           book.getTitle().trim().equals("") &&
           book.getPrice() == 0 )
          // book.getLabel()==null) 
        
        {
           return true;
        }
        else return false;
    }

    public void addEmptyRow() {
        dataVector.add(new Book());
        fireTableRowsInserted(
           dataVector.size() - 1,
           dataVector.size() - 1);
    }
    
    public void addRow(Book book, Icon icon) {
    	dataVector.add(book);
        fireTableRowsInserted(
                dataVector.size() - 1,
                dataVector.size() - 1);
    	
    }
    
    public void deleteRowAt(int index) {
    	dataVector.remove(index);
    	fireTableRowsDeleted(index, index);
    }
    
    public void deleteAllRows() {
    	dataVector.removeAllElements();
    	addEmptyRow();
    	fireTableRowsDeleted(0, getRowCount());       	
    }
	
}


class JTableDemo extends JFrame {
	/**
	 * 
	 */

	private static final long serialVersionUID = -7724610014099235711L;

	private	JPanel						topPanel;
	private JTable						table;	
	private InteractiveTableModel 		tableModel;
	private JScrollPane					scroller;
	private JMenuBar 					mnuBar;
	private JMenu						mnuFile;
	private JMenuItem					mnuFileOpen, mnuFileSave, mnuFileExit;	

	public static final String[] columnNames = {
        "Autor", "Tytu³", "Cena","Okladka",""
    };
	
	public JTableDemo() {		
		setTitle("JTable Demo");
		
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
		
		tableModel = new InteractiveTableModel(columnNames);
		
		tableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
	                 int column = e.getColumn();
	                 int row = e.getFirstRow();
	                 table.setColumnSelectionInterval(column + 1, column + 1);
	                 table.setRowSelectionInterval(row, row);
	             }				
			}
			
		} );
        table = new JTable();
        table.setModel(tableModel);
        table.setSurrendersFocusOnKeystroke(true);
        if (!tableModel.hasEmptyRow()) {
            tableModel.addEmptyRow();
        }
        
        // obs³uga usuwania - przez klikniêcie myszk¹ z przyciœniêtym Alt'em
        table.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (
						// wymagane wciœniêcie ALT'a
						e.isAltDown() 
						
						// zabezpieczenie przed prób¹ usuwania z pustej listy lub spoza listy
						&& table.getSelectedRow() >= 0
						) 
				{
				
					tableModel.deleteRowAt(table.getSelectedRow());
					
				}
			}
		});
        
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        TableColumn hidden = table.getColumnModel().getColumn(InteractiveTableModel.HIDDEN_INDEX);
        hidden.setMinWidth(2);
        hidden.setPreferredWidth(2);
        hidden.setMaxWidth(2);
        hidden.setCellRenderer( new InteractiveRenderer(InteractiveTableModel.HIDDEN_INDEX) );

        
        mnuBar = new JMenuBar();
        mnuFile = new JMenu("Plik");
        mnuFile.setMnemonic(KeyEvent.VK_P);
        mnuFileOpen = new JMenuItem("Otwórz");
        mnuFileOpen.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {								
				
				JFileChooser fileDialog = new JFileChooser(System.getProperty("user.dir"));	
								
				int returnVal = fileDialog.showOpenDialog((Component) e.getSource());
				File file=null;
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fileDialog.getSelectedFile();
					
					tableModel.deleteAllRows();
					
					try {
						BufferedReader in = new BufferedReader(new FileReader(file));
						String line = in.readLine();
						String pathname = "";
						JLabel label=new JLabel();
						String[] listStr; 
						List<String> lista_autor = new ArrayList<String>();
						List<String> lista_sciezka = new ArrayList<String>();
						List<String> lista_tytul = new ArrayList<String>();
						List<String> lista_cena = new ArrayList<String>();
						
						while(!line.equals("")) {							
							listStr = line.split("\\|");
							pathname = fileDialog.getCurrentDirectory()+"\\"+listStr[1]+".jpg";
							ImageIcon myLabel = new ImageIcon(pathname);
							label.setIcon(myLabel);
							lista_autor.add(listStr[0]);
							lista_tytul.add(listStr[1]);
							lista_cena.add(listStr[2]);
							lista_sciezka.add(pathname);
							tableModel.addRow(new Book(listStr[0], listStr[1], Double.parseDouble(listStr[2])), myLabel);
							//table.getColumnModel().getColumn(3).setCellRenderer(new MyRenderer(pathname));
							
						   line = in.readLine();
						   
						}
						//System.out.println(lista_autor);
						for(int j=0; j<lista_autor.size();j++){
							table.getColumnModel().getColumn(3).setCellRenderer(new MyRenderer(lista_sciezka.get(j)));
						}
						
						//System.out.println(lista);
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					 
					 
					
					tableModel.deleteRowAt(0);
					tableModel.addEmptyRow();
				}
				
			}});
        mnuFileOpen.setMnemonic(KeyEvent.VK_O);
        mnuFileSave = new JMenuItem("Zapisz jako");
        mnuFileSave.setMnemonic(KeyEvent.VK_Z);
        mnuFileSave.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileDialog = new JFileChooser(System.getProperty("user.dir"));
				
				int retVal = fileDialog.showSaveDialog((Component) e.getSource());
				File fileToSave=null;
				if (retVal == JFileChooser.APPROVE_OPTION) {
					fileToSave = fileDialog.getSelectedFile();				
				}
				
				try {
		            if (fileToSave == null) {
		                return;
		            }
		            BufferedWriter outFile = new BufferedWriter(new FileWriter(fileToSave));
		            for(int a=0; a<tableModel.getRowCount()-1; a++) {
		            	outFile.write(
		            			tableModel.getValueAt(a, InteractiveTableModel.AUTHOR_INDEX)+"|"+
		            			tableModel.getValueAt(a, InteractiveTableModel.TITLE_INDEX)+"|"+
		            			tableModel.getValueAt(a, InteractiveTableModel.PRICE_INDEX)+
		            			tableModel.getValueAt(a, InteractiveTableModel.IMAGE_INDEX)+
		            			"\n"
		            			);
		            }

		            outFile.close();
		            setTitle(fileToSave.getName());
		        } catch (IOException ex) {
		        	ex.printStackTrace();
		        }
				
			}
        	
        });
        mnuFileExit = new JMenuItem("Wyjœcie");
        mnuFileExit.setMnemonic(KeyEvent.VK_W);
        mnuFileExit.setAccelerator(KeyStroke.getKeyStroke("control X"));
        mnuFileExit.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
				
			}
        	
        });
        
        mnuBar.add(mnuFile);
        mnuFile.add(mnuFileOpen);
        mnuFile.add(mnuFileSave);
        mnuFile.addSeparator();
        mnuFile.add(mnuFileExit);
        
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
        add(mnuBar, BorderLayout.NORTH);
	}
	
    public void highlightLastRow(int row) {
        int lastrow = tableModel.getRowCount();
        if (row == lastrow - 1) {
            table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
        } else {
            table.setRowSelectionInterval(row + 1, row + 1);
        }

        table.setColumnSelectionInterval(0, 0);
    }

    class InteractiveRenderer extends DefaultTableCellRenderer {

    	private static final long serialVersionUID = 1981623364435822203L;
		protected int interactiveColumn;

        public InteractiveRenderer(int interactiveColumn) {
            this.interactiveColumn = interactiveColumn;
        }

        public Component getTableCellRendererComponent(JTable table,
           Object value, boolean isSelected, boolean hasFocus, int row,
           int column)
        {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == interactiveColumn && hasFocus) {
                if ((JTableDemo.this.tableModel.getRowCount() - 1) == row &&
                   !JTableDemo.this.tableModel.hasEmptyRow())
                {
                    JTableDemo.this.tableModel.addEmptyRow();
                }

                highlightLastRow(row);
            }

            return c;
        }
    }
}

public class Main {

  public static void main(String[] args) {
	  JTableDemo mainFrame = new JTableDemo();
	  
	  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
      mainFrame.pack();
      mainFrame.setLocationByPlatform(true);
	  mainFrame.setVisible(true);

  }
}


