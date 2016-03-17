import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

/**
 * Generate the check box frame for price ranges.
 * @author Greyson Hill
 *
 */

@SuppressWarnings("serial")
public class PriceCheckBoxes extends JPanel {

	protected static ArrayList<String> priceList = new ArrayList<String>();
	protected static JCheckBox chckbxCheap = new JCheckBox("Cheap");
	protected static JCheckBox chckbxModerate = new JCheckBox("Moderate");
	protected static JCheckBox chckbxNice = new JCheckBox("Nice");
	
	/**
	 * Create the panel.
	 */
	public PriceCheckBoxes() {
		setBorder(new TitledBorder(null, "Price", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		setLayout(new GridLayout(3, 1, 0, 0));
		
		chckbxCheap.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					priceList.add("Cheap");
				} else {
					priceList.remove("Cheap");
				}
			}
		});
		chckbxCheap.setHorizontalAlignment(SwingConstants.CENTER);
		add(chckbxCheap);
		
		chckbxModerate.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					priceList.add("Moderate");
				} else {
					priceList.remove("Moderate");
				}
			}
		});
		chckbxModerate.setHorizontalAlignment(SwingConstants.CENTER);
		add(chckbxModerate);
		
		chckbxNice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					priceList.add("Nice");
				} else {
					priceList.remove("Nice");
				}
			}
		});
		chckbxNice.setHorizontalAlignment(SwingConstants.CENTER);
		add(chckbxNice);

	}
	public int getListSize() {
		return priceList.size();
	}

}
