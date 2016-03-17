import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class TypePanel extends JPanel {

	private ArrayList<JCheckBox> typeList;
	private JCheckBox asian, burgers, chicken, deli, breakfast, mexican, pizza, seafood, pub, bbq, any;
	private Border bf;
	private Border tBF;
	private boolean isBurgers = false;
	private boolean isChicken = false;
	private boolean isAsian = false;
	private boolean isDeli = false;
	private boolean isBreak = false;
	private boolean isMex = false;
	private boolean isAny = false;
	private boolean isPizza = false;
	private boolean isSeafood = false;
	private boolean isPub = false;
	private boolean isBBQ = false;
	
	public TypePanel(Border bf, Border tBF) {
		this.bf = bf;
		this.tBF = tBF;
		asian = new JCheckBox("Asian");
		burgers = new JCheckBox("Burgers");
		chicken = new JCheckBox("Chicken");
		deli = new JCheckBox("Deli");
		breakfast = new JCheckBox("Breakfast");
		mexican = new JCheckBox("Mexican");
		pizza = new JCheckBox("Pizza");
		seafood = new JCheckBox("Seafood");
		pub = new JCheckBox("Pub");
		bbq = new JCheckBox("BBQ");
		any = new JCheckBox("Any");
		typeList = new ArrayList<JCheckBox>();
		
		this.tBF = BorderFactory.createTitledBorder(this.bf, "Type of Food", TitledBorder.LEFT, TitledBorder.CENTER);
		setPreferredSize(new Dimension(450, 150));
		setLayout(new GridLayout(3, 3));
		
		setBorder(this.tBF);
		add(asian);
		add(burgers);
		add(chicken);
		add(deli);
		add(breakfast);
		add(mexican);
		add(pub);
		add(pizza);
		add(seafood);
		add(bbq);
		add(any);
		
		asian.addItemListener(new CheckListener());
		burgers.addItemListener(new CheckListener());
		chicken.addItemListener(new CheckListener());
		deli.addItemListener(new CheckListener());
		breakfast.addItemListener(new CheckListener());
		mexican.addItemListener(new CheckListener());
		pizza.addItemListener(new CheckListener());
		pub.addItemListener(new CheckListener());
		seafood.addItemListener(new CheckListener());
		bbq.addItemListener(new CheckListener());
		any.addItemListener(new CheckListener());
		
		setVisible(true);
		
	}	// end of constructor
	
	private class CheckListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if(((AbstractButton) e.getSource()).isSelected()) {
				if(e.getSource() == asian) {
					isAsian = true;
					typeList.add(asian);
				} else if(e.getSource() == burgers) {
					isBurgers = true;
					typeList.add(burgers);
				} else if(e.getSource() == any) {
					isAny = true;
					typeList.add(any);
				} else if(e.getSource() == chicken) {
					isChicken = true;
					typeList.add(chicken);
				} else if(e.getSource() == deli) {
					isDeli = true;
					typeList.add(deli);
				} else if(e.getSource() == breakfast) {
					isBreak = true;
					typeList.add(breakfast);
				} else if(e.getSource() == mexican) {
					isMex = true;
					typeList.add(mexican);
				} else if(e.getSource() == pub) {
					isPub = true;
					typeList.add(pub);
				} else if(e.getSource() == pizza) {
					isPizza = true;
					typeList.add(pizza);
				} else if(e.getSource() == seafood) {
					isSeafood = true;
					typeList.add(seafood);
				} else if(e.getSource() == bbq) {
					isBBQ = true;
					typeList.add(bbq);
				}
			} else {
				if(e.getSource() == asian) {
					isAsian = false;
					typeList.remove(asian);
				} else if(e.getSource() == burgers) {
					isBurgers = false;
					typeList.remove(burgers);
				} else if(e.getSource() == any) {
					isAny = false;
					typeList.remove(any);
				} else if(e.getSource() == chicken) {
					isChicken = false;
					typeList.remove(chicken);
				} else if(e.getSource() == deli) {
					isDeli = false;
					typeList.remove(deli);
				} else if(e.getSource() == breakfast) {
					isBreak = false;
					typeList.remove(breakfast);
				} else if(e.getSource() == mexican) {
					isMex = false;
					typeList.remove(mexican);
				} else if(e.getSource() == pub) {
					isPub = false;
					typeList.remove(pub);
				} else if(e.getSource() == pizza) {
					isPizza = false;
					typeList.remove(pizza);
				} else if(e.getSource() == seafood) {
					isSeafood = false;
					typeList.remove(seafood);
				} else if(e.getSource() == bbq) {
					isBBQ = false;
					typeList.remove(bbq);
				}
			}
			
		}	// end of actionPerformed
		
	}	// end of CheckListener
	
	public boolean getIsAsian() {
		return isAsian;
		
	}	// end of getIsAsian
	
	public boolean getIsBurgers() {
		return isBurgers;
		
	}	// end of getIsBurgers
	
	public boolean getIsChicken() {
		return isChicken;
		
	}	// end of getIsChicken
	
	public boolean getIsDeli() {
		return isDeli;
		
	}	// end of getIsDeli
	
	public boolean getIsBreak() {
		return isBreak;
		
	}	// end of getIsBreak
	
	public boolean getIsMex() {
		return isMex;
		
	}	// end of getIsMex
	
	public boolean getIsAny() {
		return isAny;
		
	}	// end of getIsAny
	
	public boolean getIsPizza() {
		return isPizza;
		
	}	// end of getIsPizza

	public boolean getIsSeafood() {
		return isSeafood;
		
	}	// end of getIsSeafood

	public boolean getIsPub() {
		return isPub;
		
	}	// end of getIsPub

	public boolean getIsBBQ() {
		return isBBQ;
		
	}	// end of getIsBBQ

	public ArrayList<JCheckBox> getTypeList() {
		return typeList;
		
	}	// end of getTypeList

}	// end of class TypePanel
