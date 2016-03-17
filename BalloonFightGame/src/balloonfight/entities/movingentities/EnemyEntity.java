package balloonfight.entities.movingentities;

import java.awt.Point;

/**
 * Class for the enemy entity.
 * 
 * @author Greyson Hill, Torrance Yang
 *
 */
public class EnemyEntity extends PlayerEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the enemy entity.
	 * 
	 * @param x x location for the enemy.
	 * @param y y location for the enemy.
	 */
	public EnemyEntity(int x, int y)
	{
		X = x;
		Y = y;	
		balloons = 1;
		points = 50;
	}
	/**
	 * Position of the enemy sprite on the source image,
	 */
	@Override
	public Point tilePosition() {
		return new Point(116,30);
	}
	/**
	 * Size of the sprite in the source file.
	 */
	@Override
	public Point tileSize() {
		return new Point(17,27);
	}

}
