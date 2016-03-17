package balloonfight.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import balloonfight.utilities.BoundingBox;

/**
 * The parent class for all entities.
 * 
 * @author Greyson Hill, Torrance Yang
 *
 */
public abstract class Entity extends JComponent{
	private static final long serialVersionUID = 1L;
	public static BufferedImage image;
	protected int X;
	public int points;
	protected int Y;
	protected boolean facingRight;

	/**
	 * Constructor for the entities.
	 */
	public Entity(){
		facingRight = true;
		points = 0;
		try {
			image = ImageIO.read(new File("res/tiles.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public abstract Point tilePosition();
	public abstract Point tileSize();
	/**
	 * Changes the orientation of the entity based on their direction.
	 * 
	 * @param b boolean for the direction of entity.
	 */
	public void setFacingRight(boolean b){
		facingRight = b;
	}
	/**
	 * Draws the entities based on their direction.
	 * 
	 * @param g2 source of the graphics.
	 */
	public void paintComponent(Graphics g2){
		Graphics2D g = (Graphics2D) g2;
		if(!facingRight)
		g.drawImage(image, X-tileSize().x/2, -1*Y+480, X+tileSize().x-tileSize().x/2, -1*Y+tileSize().y+480,tilePosition().x,tilePosition().y,tilePosition().x+tileSize().x, tilePosition().y+tileSize().y, null);
		else
		g.drawImage(image, X+tileSize().x/2, -1*Y+480, X-tileSize().x+tileSize().x/2, -1*Y+tileSize().y+480,tilePosition().x,tilePosition().y,tilePosition().x+tileSize().x, tilePosition().y+tileSize().y, null);
	
	}
	public abstract void updatePosition();
	/**
	 * Getter for the x coordinate of entity.
	 */
	public int getX(){
		return X;
	}
	/**
	 * Getter for the y coordinate of entity.
	 */
	public int getY(){
		return Y;
	}
	public abstract BoundingBox getBoundingBox();
	public abstract void onCollision(Entity collided);
	
}