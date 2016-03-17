import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {

	private JButton randomSelect, listSelect, reset, mapit, menu;
	private JPanel resetP;
	private JScrollPane sp;
	private JLabel spLabel;
	private JTextField tf;
	private FoodGUI window;
	private CheckBoxPanel cbPanel;
	private ArrayList<Restaurant> possibleType;
	private ArrayList<Restaurant> possible;
	private Restaurant possibleChoice;
	
	public ButtonPanel(FoodGUI fg, CheckBoxPanel cb) {
		window = fg;
		cbPanel = cb;
		
		setPreferredSize(new Dimension(250, 140));
		randomSelect = new JButton("Select");
		listSelect = new JButton("List");
		reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(100,50));
		mapit = new JButton("Map it");
		mapit.setPreferredSize(new Dimension(100,50));
		menu = new JButton("View Menu");
		menu.setPreferredSize(new Dimension(125,50));
		possibleType = new ArrayList<Restaurant>();
		possible = new ArrayList<Restaurant>();
		tf = new JTextField(20);
		tf.setEditable(false);
		
		// Separate panel for the reset button
		resetP = new JPanel();
		resetP.setSize(new Dimension(100,50));
		resetP.add(reset);
		resetP.setVisible(true);
		
		add(randomSelect, BorderLayout.WEST);
		add(listSelect, BorderLayout.EAST);
		add(resetP, BorderLayout.SOUTH);
		
		randomSelect.addActionListener(new RandomSelect());
		listSelect.addActionListener(new ListSelect());
		reset.addActionListener(new Reset());
		
	}	// end of constructor
	
	private class RandomSelect implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Random rand = new Random();

			possible = selectType();
			int r = rand.nextInt(possible.size());
			
			possibleChoice = possible.get(r);
			
			tf.setText(possible.get(r).getName());
			add(tf, BorderLayout.CENTER);
			
			// Removes unused buttons
			remove(randomSelect);
			remove(listSelect);
			remove(resetP);
			
			randomSelect = new JButton("Select Again");
			randomSelect.setPreferredSize(new Dimension(125,50));
			add(reset, BorderLayout.SOUTH);
			add(randomSelect, BorderLayout.EAST);
			add(mapit, BorderLayout.AFTER_LAST_LINE);
			add(menu, BorderLayout.AFTER_LAST_LINE);
			window.revalidate();
			
			randomSelect.addActionListener(new RandomSelect());
			System.out.println(possibleChoice.getMap());
			mapit.addActionListener(new MapListener(possibleChoice, false));
			menu.addActionListener(new MapListener(possibleChoice, true));
			
		}	// end of actionPerformed
		
	}	// end of RandomSelect
	
	private class ListSelect implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			possible = selectType();
			setLayout(new BorderLayout());
			
			// Text area for scroll pane
			JTextArea text = new JTextArea(possible.size(),1);
			// New scroll pane
			sp = new JScrollPane(text);									
			sp.setPreferredSize(new Dimension(50,100));
			text.setEditable(false);
			
			for(Restaurant r : possible) {
				text.append(r.getName() + "\n");
			}
			
			// Removes unused buttons
			remove(randomSelect);
			remove(listSelect);
			remove(resetP);
			
			// Adds a label telling how many options the user has
			spLabel = new JLabel(possible.size() + " options");
			spLabel.setPreferredSize(new Dimension(75,25));
			
			add(spLabel, BorderLayout.NORTH);
			add(sp, BorderLayout.CENTER);
			add(resetP, BorderLayout.SOUTH);
			
			window.revalidate();
			
		}	// end of actionPerformed
		
	}	// end of ListSelect
	
	private class Reset implements ActionListener {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {
			window.dispose();
			FoodGUI food = new FoodGUI(null);
			
			
		}	// end of actionPerformed
		
	}	// end of class Reset
	
	private class MapListener implements ActionListener {
		private Restaurant pc = null;
		private boolean isMenu;
		public MapListener(Restaurant possibleChoice, boolean m) {
			pc = possibleChoice;
			isMenu = m;
		}
		public void actionPerformed(ActionEvent e) {
			URI uri = null;
			String dest = "";
			if(isMenu) {
				dest = pc.getMenu();
			} else {
				dest = pc.getMap();
			}
			if(isMenu && pc.getMenu().equals("null")) {
				JLabel l = new JLabel("Sorry, no menu available");
				l.setVisible(true);
				add(l, BorderLayout.NORTH);
				
			} else {
				try {
					uri = new URI(dest);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
			            desktop.browse(uri);
			        } catch (SecurityException ex) {
			        	JFrame j = new JFrame();
			        	j.setSize(200,200);
			        	j.setVisible(true);
			            ex.printStackTrace();
			        } catch (NullPointerException ee) {
			        	JFrame j = new JFrame();
			        	j.setSize(50,200);
			        	j.setVisible(true);
			        	ee.printStackTrace();
			        } catch (IOException e1) {
			        	JFrame j = new JFrame();
			        	j.setSize(900,200);
			        	j.setVisible(true);
						e1.printStackTrace();
					}
			    }
			}
			window.revalidate();
		}
	}
	
	public ArrayList<Restaurant> selectType() {
		possibleType.clear();
		for(Restaurant e : window.getRList()) {
			for(JCheckBox x : cbPanel.getTP().getTypeList()) {
				if(e.getType().equals(x.getText())) {
					possibleType.add(e);
				}
			}
		}
		
		return selectPrice();
	
	}	// end of selectType
	
	public ArrayList<Restaurant> selectPrice() {
		possible.clear();
		for(Restaurant r : possibleType) {
			for(JCheckBox bx : cbPanel.getPP().getPriceList()) {
				if(r.getPrice().equals(bx.getText())) {
					possible.add(r);
				}
			}
		}
		
		possible.remove(possibleChoice);
		
		return possible;
		
	}	// end of selectPrice
	
}	// end of class ButtonPanel
