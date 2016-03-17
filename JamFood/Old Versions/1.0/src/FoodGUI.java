import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class FoodGUI extends JFrame{

	private ButtonPanel bp;
	private CheckBoxPanel boxPanel;
	private JPanel blank1, blank2;
	private JPanel buttons;
	private IntroGUI i;
	
	@SuppressWarnings({ })
	public FoodGUI(IntroGUI intro) {
		i = intro;
		setTitle("Restaurant Selector");
		setLocation(250, 250);
		setSize(new Dimension(650, 400));
		setMinimumSize(new Dimension(650, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		boxPanel = new CheckBoxPanel();
		bp = new ButtonPanel(this, boxPanel);
		
		// Creating blank panels to pad the center button panel.
		blank1 = new JPanel();
		blank1.setPreferredSize(new Dimension(200,200));
		blank1.setVisible(true);
		blank2 = new JPanel();
		blank2.setPreferredSize(new Dimension(200,200));
		blank2.setVisible(true);
		buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(650,150));
		buttons.setLayout(new BorderLayout());
		buttons.add(blank1, BorderLayout.WEST);
		buttons.add(bp, BorderLayout.CENTER);
		buttons.add(blank2, BorderLayout.EAST);
		buttons.setVisible(true);
		
		add(boxPanel, BorderLayout.NORTH);
		add(buttons, BorderLayout.SOUTH);
		
		
		setVisible(true);
		//pack();
		
		
	}	// end of constructor
	
	public ArrayList<Restaurant> getRList() {
		return i.getRList();
		
	}
	public ButtonPanel getBP() {
		return bp;
		
	}	// end of getBP
	
	public CheckBoxPanel getBoxPanel() {
		return boxPanel;
		
	}	// end of getBoxPanel
	

	
}
