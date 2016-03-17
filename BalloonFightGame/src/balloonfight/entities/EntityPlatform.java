package balloonfight.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import balloonfight.entities.movingentities.PlayerEntity;
import balloonfight.utilities.BoundingBox;
/**
 * Platforms that are built in the map.
 * 
 * @author Greyson Hill, Torrance Yang
 *
 */
public class EntityPlatform extends Entity {
	private static final long serialVersionUID = 1L;
	private int length;
	/**
	 * Constructor for the platform entity
	 * 
	 * @param len length of the platform.
	 * @param x x coordinate for the platform.
	 * @param y y coordinate for the platform.
	 */
	public EntityPlatform(int len, int x, int y){
		length = len;
		X = x;
		Y = y;
	}
	/**
	 * Position in the source image for the platform sprite.
	 */
	@Override
	public Point tilePosition() {
		return new Point(222,24);
	}
	/**
	 * Position in image for individual platform sections
	 * 
	 * @param a Section of platform (0=left, 1=middle, 2=right)
	 * @return Location of the platform part on the tilesheet
	 */
	public Point tilePosition(int a) {
		switch(a){
		case 0:
			return new Point(222,24);
		case 1:
			return new Point(232,24);
		default:
			return new Point(242,24);
		}
	}
	/**
	 * Size of the platform's tile.
	 */
	@Override
	public Point tileSize() {
		return new Point(8,8);
	}
	/**
	 * Updates position.
	 */
	@Override
	public void updatePosition() {
	}
	/**
	 * Gets the bounding box for the platform.
	 */
	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(0,0,length*8,16);
	}
	/**
	 * Allows for the player to walk or bounce off of the platforms.
	 * 
	 * @param collided the entity that is in a collision with the platform.
	 */
	@Override
	public void onCollision(Entity collided) {
		if(collided.getBoundingBox().minY+collided.getY()-8>this.Y){
			if(collided instanceof PlayerEntity){
				((PlayerEntity) collided).setGrounded(true);
			}
		} else {
			if(collided instanceof PlayerEntity){
				((PlayerEntity)collided).setVelocity(8*(int)Math.signum((collided.getX()-X)) , 8*(int)Math.signum((collided.getY()-Y)));
			}
		}
	}
	/**
	 * Draws the platforms.
	 * 
	 * @param g2 source of the graphics.
	 */
	public void paintComponent(Graphics g2){
		Graphics2D g = (Graphics2D) g2;
		for(int i = 0;i<=length;i++){
			if(i==0)
			g.drawImage(image, X-tileSize().x/2+i*8, -1*Y+480, X+tileSize().x-tileSize().x/2+i*8, -1*Y+tileSize().y+480,tilePosition(0).x,tilePosition(0).y,tilePosition(0).x+tileSize().x, tilePosition(0).y+tileSize().y, null);
			else if(i==length)
			g.drawImage(image, X-tileSize().x/2+i*8, -1*Y+480, X+tileSize().x-tileSize().x/2+i*8, -1*Y+tileSize().y+480,tilePosition(2).x,tilePosition(2).y,tilePosition(2).x+tileSize().x, tilePosition(2).y+tileSize().y, null);
			else
			g.drawImage(image, X-tileSize().x/2+i*8, -1*Y+480, X+tileSize().x-tileSize().x/2+i*8, -1*Y+tileSize().y+480,tilePosition(1).x,tilePosition(1).y,tilePosition(1).x+tileSize().x, tilePosition(1).y+tileSize().y, null);
		}
	}

}
