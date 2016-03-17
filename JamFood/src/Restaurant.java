
public class Restaurant {

	private String name;
	private String price;
	private String type;
	private String map;
	private String menu;
	
	public Restaurant(String n, String p, String t, String m, String me) {
		name = n;
		price = p;
		type = t;
		map = m;
		menu = me;
		
	}	// end of constructor
	
	public String getName() {
		return name;
		
	}	// end of getName
	
	public String getPrice() {
		return price;
		
	}	// end of getPrice
	
	public String getType() {
		return type;
		
	}	// end of getType
	
	public String getMap() {
		return map;
		
	}	// end of getMap
	
	public String getMenu() {
		return menu;
		
	}	// end of get Menu
	
}	// end of class Restaurant
