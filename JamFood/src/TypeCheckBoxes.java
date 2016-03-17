import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;

import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

/**
 * Generate the check box frame for types of food.
 * @author Greyson Hill
 *
 */
@SuppressWarnings("serial")
public class TypeCheckBoxes extends JPanel {
	
	protected static ArrayList<String> typeList = new ArrayList<String>();
	protected static JCheckBox chckbxAny = new JCheckBox("Any");
	protected static JCheckBox chckbxAsian = new JCheckBox("Asian");
	protected static JCheckBox chckbxBBQ = new JCheckBox("BBQ");
	protected static JCheckBox chckbxBreakfast = new JCheckBox("Breakfast");
	protected static JCheckBox chckbxBurgers = new JCheckBox("Burgers");
	protected static JCheckBox chckbxChicken = new JCheckBox("Chicken");
	protected static JCheckBox chckbxDeli = new JCheckBox("Deli");
	protected static JCheckBox chckbxMexican = new JCheckBox("Mexican");
	protected static JCheckBox chckbxPizza = new JCheckBox("Pizza");
	protected static JCheckBox chckbxPub = new JCheckBox("Pub");
	protected static JCheckBox chckbxSeafood = new JCheckBox("Seafood");
	
	/**
	 * Create the panel.
	 */
	public TypeCheckBoxes() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Type", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		setLayout(new GridLayout(3, 4, 0, 0));
		
		chckbxAny.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Any");
				} else {
					typeList.remove("Any");
				}
			}
		});
		add(chckbxAny);
		
		chckbxAsian.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Asian");
				} else {
					typeList.remove("Asian");
				}
			}
		});
		add(chckbxAsian);
		
		chckbxBBQ.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("BBQ");
				} else {
					typeList.remove("BBQ");
				}
			}
		});
		add(chckbxBBQ);
		
		chckbxBreakfast.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Breakfast");
				} else {
					typeList.remove("Breakfast");
				}
			}
		});
		add(chckbxBreakfast);
		
		chckbxBurgers.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Burgers");
				} else {
					typeList.remove("Burgers");
				}
			}
		});
		add(chckbxBurgers);
		
		chckbxChicken.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Chicken");
				} else {
					typeList.remove("Chicken");
				}
			}
		});
		add(chckbxChicken);
		
		chckbxDeli.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Deli");
				} else {
					typeList.remove("Deli");
				}
			}
		});
		add(chckbxDeli);
		
		chckbxMexican.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Mexican");
				} else {
					typeList.remove("Mexican");
				}
			}
		});
		add(chckbxMexican);
		
		chckbxPizza.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Pizza");
				} else {
					typeList.remove("Pizza");
				}
			}
		});
		add(chckbxPizza);
		
		chckbxPub.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Pub");
				} else {
					typeList.remove("Pub");
				}
			}
		});
		add(chckbxPub);
		
		chckbxSeafood.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					typeList.add("Seafood");
				} else {
					typeList.remove("Seafood");
				}
			}
		});
		add(chckbxSeafood);

	}
	
	public int getListSize() {
		return typeList.size();
	}

}
