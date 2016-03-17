package balloonfight.utilities;
/**
 * Class to handle the bounding boxes for the entities in the game.
 * 
 * @author Patrick Emery
 *
 */
public class BoundingBox {
public int minX;
public int minY;
public int maxX;
public int maxY;
/**
 * Constructor for the bounding box, initializes the size of the box.
 * 
 * @param a minimum x value for the box.
 * @param b minimum y value for the box.
 * @param c maximum x value for the box.
 * @param d maximum y value for the box.
 */
public BoundingBox(int a, int b, int c, int d){
	minX = a;
	minY = b;
	maxX = c;
	maxY = d;
}
}
