import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


@SuppressWarnings("serial")
public class CheckBoxPanel extends JPanel {
	
	private TypePanel tp;
	private PricePanel pp;
	private Border bf = BorderFactory.createLineBorder(Color.BLACK, 1, true);
	private Border tBF;
	
	public CheckBoxPanel() {
		tp = new TypePanel(bf, tBF);
		pp = new PricePanel(bf, tBF);
		
		setPreferredSize(new Dimension(400, 180));
		
		add(tp, BorderLayout.WEST);
		add(pp, BorderLayout.EAST);
		 
	}	// end of constructor
	
	public TypePanel getTP() {
		return tp;
		
	}	// end of getTP
	
	public PricePanel getPP() {
		return pp;
		
	}	// end of getPP
		
}	// end of class CheckBoxPanel
