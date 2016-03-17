import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("serial")
public class IntroGUI extends JFrame {

	private ArrayList<Restaurant> rList;
	private FoodGUI fg;
	private JButton search, random;
	private JTextField searchBox, nameBox, typeBox, priceBox;
	private JPanel buttons, results;
	private JLabel name, type, price, blank, nil;
	
	@SuppressWarnings("resource")
	public IntroGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 350);
		setLocation(400, 250);
		
		rList = new ArrayList<Restaurant>();
		searchBox = new JTextField(10);
		search = new JButton("Search");
		random = new JButton("Random Select");
		
		results = new JPanel();
		results.setPreferredSize(new Dimension(300,225));
		
		buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(100,50));
		buttons.setLayout(new GridLayout(2,1));
		buttons.add(search);
		buttons.add(random);
		
		nameBox = new JTextField(15);
		nameBox.setEditable(false);
		typeBox = new JTextField(15);
		typeBox.setEditable(false);
		priceBox = new JTextField(15);
		priceBox.setEditable(false);
		name = new JLabel("Name");
		name.setPreferredSize(new Dimension(60, 30));
		type = new JLabel("Type");
		type.setPreferredSize(new Dimension(60, 30));
		price = new JLabel("Price");
		price.setPreferredSize(new Dimension(60, 30));
		blank = new JLabel("");
		nil = new JLabel("That entry does not exist");
		
		
		add(searchBox, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
		add(results, BorderLayout.SOUTH);
		
		try {
			FileInputStream file = new FileInputStream(new File("res/Restaurants.xlsx"));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIt = sheet.iterator();
			while(rowIt.hasNext()) {
				String name = "";
				String type = "";
				String price = "";
				String map = "";
				String menu = "";
				Row row = rowIt.next();
				Iterator<Cell> cellIt = row.cellIterator();
				int cnt = 0;
				while(cellIt.hasNext()) {
					Cell cell = cellIt.next();
					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_STRING:
						if(cnt == 0) {
							name = cell.getStringCellValue();
						} else if(cnt == 1) {
							type = cell.getStringCellValue();
						} else if(cnt == 2) {
							price = cell.getStringCellValue();
						} else if(cnt == 3) {
							map = cell.getStringCellValue();
						} else {
							menu = cell.getStringCellValue();
						}
						break;
					}
					cnt++;
				}
				rList.add(new Restaurant(name, type, price, map, menu));
				
			}
			file.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		search.addActionListener(new Search(this, buttons));
		searchBox.addKeyListener(new Search(this, buttons));
		random.addActionListener(new Random(this));
		
		setVisible(true);
		
	}	// end of IntroGUI constructor
	
	class Search implements ActionListener, KeyListener {
		private IntroGUI i;
		private JPanel b;
		
		public Search(IntroGUI intro, JPanel buttons) {
			i = intro;
			b = buttons;
			
		}	// end of constructor
		
		public void actionPerformed(ActionEvent e) {
			action(e, null);
			
		}	// end of actionPerformed
		
		public void keyPressed(KeyEvent e1) {
			if(e1.getKeyCode() == KeyEvent.VK_ENTER) {
				action(null, e1);
				System.out.println("Enter Pressed");
			}
			
		}	// end of keyPressed
		
		public void action(ActionEvent e, KeyEvent e1) {
			String searching = searchBox.getText();
			Restaurant found = null;
			for(Restaurant r : rList) {
				if(searching.equals(r.getName().replaceAll("\\s+", ""))) {
					found = r;
					break;
				}
			}
			if(found == null) {
				nameBox.setText("");
				typeBox.setText("");
				priceBox.setText("");
				nil.setText("" + searching + " was not found");
				results.add(nil, BorderLayout.BEFORE_FIRST_LINE);
				results.revalidate();
				
				i.revalidate();
			} else {
				System.out.println(searching);
				results.remove(nil);
				nameBox.setText(found.getName());
				typeBox.setText(found.getType());
				priceBox.setText(found.getPrice());
				
				results.add(name);
				results.add(nameBox);
				results.add(type);
				results.add(typeBox);
				results.add(price);
				results.add(priceBox);
				
				b.remove(random);
				b.add(blank);
				i.revalidate();
			}
			
		}	// end of action

		@Override
		public void keyTyped(KeyEvent e) {
			// Ignored.
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Ignored.
			
		}
		
	}	// end of class Search
	
	class Random implements ActionListener {
		protected IntroGUI i;
		
		public Random(IntroGUI intro) {
			i = intro;
			
		}	// end of constructor
		
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {
			i.dispose();
			FoodGUI fg = new FoodGUI(i);
			
		}	// end of actionPerformed
		
	}	// end of class Random
	
	public ArrayList<Restaurant> getRList() {
		return rList;
		
	}	// end of getRList
	
	public FoodGUI getFG() {
		return fg;
		
	}	// end of getFG
	
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		IntroGUI i = new IntroGUI();
		
	}	// end of main
	
}	// end of class IntroGUI
