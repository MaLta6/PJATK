/**
 *
 *  @author Dylewska Marta PD1957
 *Prosty edytor


Stworzyć prosty edytor tekstu z opcjami umieszczonymi w menu rozwijalnym 
File 
    Open - otwiera plik wybrany w dialogu wyboru plików i wczytuje plik do edytora
    Save - zapisuje zawartość edytora do bieżącego pliku (jeśli nie ma pliku, to dialog wyboru plików)
    Save As... zapisuje zawartość do pliku wybranego z dialogu
    Exit   - zamknięcie okna i zakończenie działania aplikacji 

Edit 

   Adresy 
        Dom      - dopisuje do edytora w miejscu kursora adres domowy 
        Szkoła    - dopisuje do edytora w miejscu kursora adres szkoły 
        Firma     - dopisuje do edytora w miejscu kursora adres służbowy
Options 
    Foreground     - zmienia kolor pisma na wybraną opcję 
           kolor1 
           ... 
           kolorN 
    Background    - zmienia kolor tła na wybraną opcję 
          kolor1 
          ... 
          kolorN 
    Font size          - zmienia rozmiar pisma na wybraną opcję 
                8 
              10 
               ... 
              24
Zapewnić:
•	mnemoniki i akceleratory dla opcji Open, Save, Save As..., Exit, Dom, Szkoła, Firma
•	pokazywanie kolorów w opcjach wyboru koloru (tła i pierwszego planu) w postaci kolorowych kółek
•	pokazywanie nazwy edytowanego pliku na pasku tytułu (jeślie nie ma pliku - "bez tytułu"),
•	dialog wyboru pliku ma się otwierać w ostatnio odwiedzonym katalogu; na początku ma to być bieżący katalog.

Przykład realizacji. 
 
Wygląd edytora - menu File (warto zwrócić uwagę na separator - czerwoną kreskę - jak ją zrobić???) 
 
 
Po otwarciu menu Adresy (jak widać wcześniej został wczytany plik - jego nazwa jest na pasku tytułu; zauważmy też, że wszędzie opcje menu są "wypukłe" - mają kształt przycisków - jak to zrobić ?):
 

Po wyborze opcji szkoła widoczny dopisany tekst z adresem )zostal dopisany w miejscu kursora - jak to zrobić?). Otwarte menu Background (menu Foreground jest takie samo):

 

 Otwarte menu Font size :
 
 
 
Uwagi:
•	łatwe umieszczenie kolorów = własna klasa implementująca Icon
•	należy napisać kilka metod uniwersalnych (np. tworzące opcje menu z zadanymi charakterystykami), w przeciwnym razie kod będzie duży i słabo czytelny

 */

package zad4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class Main {

	public static void main(String[] args) {

	class FManager {
	    JFileChooser chooser;
	    JPanel panel;
	    JTextArea textArea;
	    File storage;
	
	    public FManager(JTextArea textArea) {
	
	        this.textArea = textArea;
	        chooser = new JFileChooser();
	        chooser.addChoosableFileFilter(new FileNameExtensionFilter(
	                "pliki tekstowe", "txt"));
	        panel = new JPanel();
	    }
	
	    public String getName() {
	        return storage.getName();
	    }
	
	    public void read() {
	    	int ret = chooser.showDialog(panel, "Otwórz plik");
	        if (ret == JFileChooser.APPROVE_OPTION) {
	            try {
	                storage = chooser.getSelectedFile();
	                BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()));
	                String line;
	                textArea.setText("");
	
	                while ((line = br.readLine()) != null) {
	                    textArea.append(line + "\n");
	                }
	
	                br.close();
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	    }
	
	    public void save() {
	
	        if (storage == null) {
	            this.saveAs();
	            return;
	        } else {
	            _save(storage);
	        }
	    }
	
	    public void saveAs() {
	
	        int ret = chooser.showDialog(panel, "Zapisz jako");
	        if (ret == JFileChooser.APPROVE_OPTION) {
	            storage = chooser.getSelectedFile();
	            _save(chooser.getSelectedFile());
	        }
	    }
	
	    private void _save(File file) {
	
	        try {
	            BufferedWriter bw = new BufferedWriter(new FileWriter(storage));
	            bw.write(textArea.getText());
	            bw.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	}

	class MIcon implements javax.swing.Icon {
	    protected Color color;
	    protected int widht, height;
	
	    public MIcon() {
	        this(10, 10, Color.GREEN);
	    }
	
	    public MIcon(Color color) {
	        this(10, 10, color);
	    }
	
	    public MIcon(int width, int height) {
	        this(width, height, Color.GREEN);
	    }
	
	    public MIcon(int width, int height, Color color) {
	        this.widht = width;
	        this.height = height;
	        this.color = color;
	    }
	
	    public Color getColor() {
	        return color;
	    }
	
	    @Override
	    public int getIconHeight() {
	        return height;
	    }
	
	    @Override
	    public int getIconWidth() {
	        return widht;
	    }
	

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			g.setColor(color);
	        g.fillOval(x, y, widht, height);
		}
	}
	
	class ListForeground implements ActionListener {
		    protected JTextArea handler;
	
		    public ListForeground(JTextArea handler) {
		        this.handler = handler;
		    }
	
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JMenuItem item = (JMenuItem) e.getSource();
		        MIcon icon = (MIcon) item.getIcon();
		        handler.setForeground(icon.getColor());
		    }
		}

	class ListBackground implements ActionListener {
	    protected JTextArea handler;
	
	    public ListBackground(JTextArea handler) {
	        this.handler = handler;
	    }
	
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JMenuItem item = (JMenuItem) e.getSource();
	        MIcon icon = (MIcon) item.getIcon();
	        handler.setBackground(icon.getColor());
	    }
	}

	class ListFontSize implements ActionListener {
	    protected JTextArea handler;
	    protected int fontSize;
	
	    public ListFontSize(JTextArea handler, int size) {
	        this.handler = handler;
	        this.fontSize = size;
	    }
	
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        handler.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
	    }
	}

	class Mechanism extends JFrame {
	    boolean isChange = false;
	    JTextArea textArea;
	    String titlePrefix = "Word processor - ", titleSufix,
	            titleSufixDefault = "bez tytułu";
	    FManager fileManager;
	    Hashtable<String, Color> colors;
	    HashMap<Integer, String> fonts;
	    {
	        colors = new Hashtable<String, Color>();
	
	        colors.put("Blue", Color.BLUE);
	        colors.put("Yellow", Color.YELLOW);
	        colors.put("Orange", Color.ORANGE);
	        colors.put("Red", Color.RED);
	        colors.put("White", Color.WHITE);
	        colors.put("Black", Color.BLACK);
	        colors.put("Green", Color.GREEN);
	
	        fonts = new HashMap<Integer, String>();
	
	        fonts.put(8, "8 pts");
	        fonts.put(10, "10 pts");
	        fonts.put(12, "12 pts");
	        fonts.put(14, "14 pts");
	        fonts.put(16, "16 pts");
	        fonts.put(18, "18 pts");
	        fonts.put(20, "20 pts");
	        fonts.put(22, "22 pts");
	        fonts.put(24, "24 pts");
	    }
	   
	
	    Mechanism() {
	
	        initElements();
	        initUI();
	    }
	
	    protected void initElements() {
	
	        textArea = new JTextArea();
	        textArea.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void changedUpdate(DocumentEvent e) {
	            }
	
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                isChange = true;
	            }
	
	            @Override
	            public void removeUpdate(DocumentEvent e) {
	            }
	        });
	        add(new JScrollPane(textArea));
	        
	        fileManager = new FManager(textArea);
	
	        //Pierwsze menu
	        JMenu file = new JMenu("File");
	        JMenuItem fileOpen = new JMenuItem("Open");
	        fileOpen.setToolTipText("Otwórz plik");
	        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
	                ActionEvent.CTRL_MASK));
	        fileOpen.setDisplayedMnemonicIndex(0);
	        fileOpen.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                fileManager.read();
	                setTitleText(fileManager.getName());
	            }
	        });
	
	        JMenuItem fileSave = new JMenuItem("Save");
	        fileSave.setToolTipText("Zapisz do pliku");
	        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
	                ActionEvent.CTRL_MASK));
	        fileSave.setDisplayedMnemonicIndex(0);
	        fileSave.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                fileManager.save();
	            }
	        });
	
	        JMenuItem fileSaveas = new JMenuItem("Save As");
	        fileSaveas.setToolTipText("Zapisz do nowego pliku");
	        fileSaveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
	                ActionEvent.CTRL_MASK));
	        fileSaveas.setDisplayedMnemonicIndex(0);
	        fileSaveas.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                fileManager.saveAs();
	                setTitleText(fileManager.getName());
	            }
	        });
	
	        JMenuItem fileExit = new JMenuItem("Exit");
	        fileExit.setToolTipText("Wyjście z edytora");
	        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
	                ActionEvent.CTRL_MASK));
	        fileExit.setDisplayedMnemonicIndex(0);
	        fileExit.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (isChange == false)
	                    System.exit(0);
	
	                int choice = JOptionPane.showConfirmDialog(null,
	                        "Zakończyć pracę? Niezapisane zmiany zostaną utracone",
	                        "Wyjście z programu", 2);
	                if (choice == JOptionPane.OK_OPTION) {
	                    System.exit(0);
	                } else {
	                    return;
	                }
	            }
	        });
	
	        file.add(fileOpen);
	        fileOpen.setBorder(BorderFactory.createRaisedBevelBorder());
	        file.add(fileSave);
	        fileSave.setBorder(BorderFactory.createRaisedBevelBorder());
	        file.add(fileSaveas);
	        fileSaveas.setBorder(BorderFactory.createRaisedBevelBorder());
	        file.addSeparator();
	        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
	        sep.setBackground(Color.RED);
	        //sep.setPreferredSize(new Dimension(20,2));
	        file.add(sep);
	        file.addSeparator();
	        file.add(fileExit);
	        fileExit.setBorder(BorderFactory.createRaisedBevelBorder());
	
	        // Drugie menu
	        JMenu edit = new JMenu("Edit");
	        JMenu editAddresses = new JMenu("Adresy");
	        editAddresses.setBorder(BorderFactory.createRaisedBevelBorder());
	        JMenuItem editAddressesWork = new JMenuItem("Praca");
	        editAddressesWork.setToolTipText("Wstaw adres miejsca zatrudnienia");
	        editAddressesWork.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
	                ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	        editAddressesWork.setDisplayedMnemonicIndex(0);
	        editAddressesWork.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                textArea.insert("Warszawa, Domaniewska 39", textArea
	                        .getCaretPosition());
	            }
	        });
	
	        JMenuItem editAddressesHome = new JMenuItem("Dom");
	        editAddressesHome.setToolTipText("Wstaw adres miejsca zamieszkania");
	        editAddressesHome.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
	                ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	        editAddressesHome.setDisplayedMnemonicIndex(0);
	        editAddressesHome.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                textArea.insert("Warszawa, Pory 60", textArea
	                        .getCaretPosition());
	            }
	        });
	
	        JMenuItem editAddressesSchool = new JMenuItem("Szkoła");
	        editAddressesSchool.setToolTipText("Wstaw adres uczelni");
	        editAddressesSchool.setAccelerator(KeyStroke.getKeyStroke(
	                KeyEvent.VK_D, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	        editAddressesSchool.setDisplayedMnemonicIndex(0);
	        editAddressesSchool.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                textArea.insert("Warszawa, Koszykowa 86", textArea
	                        .getCaretPosition());
	            }
	        });
	
	        editAddresses.add(editAddressesWork);
	        editAddressesWork.setBorder(BorderFactory.createRaisedBevelBorder());
	        editAddresses.add(editAddressesHome);
	        editAddressesHome.setBorder(BorderFactory.createRaisedBevelBorder());
	        editAddresses.add(editAddressesSchool);
	        editAddressesSchool.setBorder(BorderFactory.createRaisedBevelBorder());
	        edit.add(editAddresses);
	
	        //Trzecie menu
	        JMenu options = new JMenu("Options");
	        JMenuItem menuItem;
	        JMenu optionsForeground = new JMenu("Foreground");
	        editAddressesHome.setDisplayedMnemonicIndex(0);
	        ListForeground listenerForeground = new ListForeground(textArea);
	        for (String key : colors.keySet()) {
	            menuItem = new JMenuItem(key, new MIcon(colors.get(key)));
	            menuItem.addActionListener(listenerForeground);
	            optionsForeground.add(menuItem);
	        }
	
	        JMenu optionsBackground = new JMenu("Background");
	        ListBackground listenerBackground = new ListBackground(textArea);
	        for (String key : colors.keySet()) {
	            menuItem = new JMenuItem(key, new MIcon(colors.get(key)));
	            menuItem.addActionListener(listenerBackground);
	            optionsBackground.add(menuItem);
	        }
	
	        JMenu optionsFontsize = new JMenu("Font size");
	        TreeSet<Integer> sortedSet = new TreeSet<Integer>(
	                new ArrayList<Integer>(fonts.keySet()));
	        for (Object key : sortedSet.toArray()) {
	            menuItem = new JMenuItem(fonts.get(key));
	            menuItem.addActionListener(new ListFontSize(textArea,
	                    (Integer) key));
	            optionsFontsize.add(menuItem);
	        }
	
	        options.add(optionsForeground);
	        optionsForeground.setBorder(BorderFactory.createRaisedBevelBorder());
	        options.add(optionsBackground);
	        optionsBackground.setBorder(BorderFactory.createRaisedBevelBorder());
	        options.add(optionsFontsize);
	        optionsFontsize.setBorder(BorderFactory.createRaisedBevelBorder());
	
	        JMenuBar menu = new JMenuBar();
	        menu.add(file);
	        menu.add(edit);
	        menu.add(options);
	
	        setJMenuBar(menu);
	    }
	
	    protected void initUI() {
	
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(new Dimension(300, 300));
	        setLocationRelativeTo(null);
	        setTitle(titlePrefix + titleSufixDefault);
	
	        setVisible(true);
	    }
	
	    protected void setTitleText(String sufix) {
	        titleSufix = sufix;
	        setTitle(titlePrefix + titleSufix);
	    }
	}
 
	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Mechanism();
            }
        });
    }
}
