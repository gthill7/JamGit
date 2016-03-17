import java.awt.EventQueue;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

/**
 * Generates the main frame and executes database queries.
 * @author Greyson Hill
 *
 */

public class JamFood {

	private JFrame frame;
	private JLabel lblSelect;
	private TypeCheckBoxes typeCheckBoxes;
	private PriceCheckBoxes priceCheckBoxes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JamFood window = new JamFood();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JButton btnReset;
	/**
	 * Create the application.
	 */
	public JamFood() {
		initialize();
		conn = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		typeCheckBoxes = new TypeCheckBoxes();
		typeCheckBoxes.setBounds(6, 6, 380, 200);
		frame.getContentPane().add(typeCheckBoxes);
		
		priceCheckBoxes = new PriceCheckBoxes();
		priceCheckBoxes.setBounds(409, 6, 175, 200);
		frame.getContentPane().add(priceCheckBoxes);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			/**
			 * Queries restaurants table to find possible selections for user.
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					Random rand = new Random();
					//list of possibilities
					ArrayList<String> temp = new ArrayList<String>();
					String query = "";
					query = "select * from restaurants where type in (?";
					for(int i = 1; i < TypeCheckBoxes.typeList.size()+PriceCheckBoxes.priceList.size(); i++) {
						//switching over to the price conditions
						if(i == TypeCheckBoxes.typeList.size()) {
							query += ") and price in (?";
							if(PriceCheckBoxes.priceList.size() == 1) break;
						} else {
							query += ",";
							query += "?";
						}
					}
					query += ");";
					System.out.println(query);
					PreparedStatement pst = conn.prepareStatement(query);
					for(int y = 0; y < TypeCheckBoxes.typeList.size(); y++) {
						pst.setString(y+1, TypeCheckBoxes.typeList.get(y));
					}
					for(int x = 1; x <= PriceCheckBoxes.priceList.size(); x++) {
						pst.setString(x+TypeCheckBoxes.typeList.size(), PriceCheckBoxes.priceList.get(x-1));
					}
					//executes the above query
					ResultSet res = pst.executeQuery();
					while(res.next()) {
						temp.add(res.getString("name"));
					}
					//selects restaurant based on random number
					lblSelect.setText(temp.get(rand.nextInt(temp.size())));
				} catch(Exception e1) {
					lblSelect.setText("Sorry there are no options");
				}
			}
		});
		btnSelect.setBounds(162, 291, 117, 29);
		frame.getContentPane().add(btnSelect);
		
		lblSelect = new JLabel("");
		lblSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelect.setBounds(200, 263, 175, 16);
		frame.getContentPane().add(lblSelect);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			/**
			 * Resets the check boxes and the label
			 */
			public void actionPerformed(ActionEvent e) {
				PriceCheckBoxes.chckbxCheap.setSelected(false);
				PriceCheckBoxes.chckbxModerate.setSelected(false);
				PriceCheckBoxes.chckbxNice.setSelected(false);
				TypeCheckBoxes.chckbxAny.setSelected(false);
				TypeCheckBoxes.chckbxAsian.setSelected(false);
				TypeCheckBoxes.chckbxBBQ.setSelected(false);
				TypeCheckBoxes.chckbxBreakfast.setSelected(false);
				TypeCheckBoxes.chckbxBurgers.setSelected(false);
				TypeCheckBoxes.chckbxChicken.setSelected(false);
				TypeCheckBoxes.chckbxDeli.setSelected(false);
				TypeCheckBoxes.chckbxMexican.setSelected(false);
				TypeCheckBoxes.chckbxPizza.setSelected(false);
				TypeCheckBoxes.chckbxPub.setSelected(false);
				TypeCheckBoxes.chckbxSeafood.setSelected(false);
				lblSelect.setText("");
			}
		});
		btnReset.setBounds(291, 291, 117, 29);
		frame.getContentPane().add(btnReset);
		
	}
}
