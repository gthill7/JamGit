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
public class PricePanel extends JPanel {

	private ArrayList<JCheckBox> priceList;
	private JCheckBox cheap, moderate, nice;
	private Border bf;
	private Border tBF;
	private boolean isCheap = false;
	private boolean isModerate = false;
	private boolean isNice = false;
	
	public PricePanel(Border bf, Border tBF) {
		this.bf = bf;
		this.tBF = tBF;
		cheap = new JCheckBox("Cheap");
		moderate = new JCheckBox("Moderate");
		nice = new JCheckBox("Nice");
		priceList = new ArrayList<JCheckBox>();
		
		this.tBF = BorderFactory.createTitledBorder(this.bf, "Price", TitledBorder.LEFT, TitledBorder.CENTER);
		setPreferredSize(new Dimension(150, 150));
		setLayout(new GridLayout(3, 1));
		
		setBorder(this.tBF);
		add(cheap);
		add(moderate);
		add(nice);
		
		cheap.addItemListener(new CheckListener());
		moderate.addItemListener(new CheckListener());
		nice.addItemListener(new CheckListener());
		
	}	// end of constructor
	
	private class CheckListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if(((AbstractButton) e.getSource()).isSelected()) {
				if(e.getSource() == cheap) {
					isCheap = true;
					priceList.add(cheap);
				} else if(e.getSource() == moderate) {
					isModerate = true;
					priceList.add(moderate);
				} else if(e.getSource() == nice) {
					isNice = true;
					priceList.add(nice);
				}
			} else {
				if(e.getSource() == cheap) {
					isCheap = false;
					priceList.remove(cheap);
				} else if(e.getSource() == moderate) {
					isModerate = false;
					priceList.remove(moderate);
				} else if(e.getSource() == nice) {
					isNice = false;
					priceList.remove(nice);
				}
			}
			
		}	// end of actionPerformed
		
	}	// end of CheckListener
	
	public boolean getIsCheap() {
		return isCheap;
		
	}	// end of getIsCheap
	
	public boolean getIsModerate() {
		return isModerate;
		
	}	// end of getIsModerate
	
	public boolean getIsNice() {
		return isNice;
		
	}	// end of getIsNice
	
	public ArrayList<JCheckBox> getPriceList() {
		return priceList;
		
	}	// end of getPriceList
	
}	// end of class PricePanel
