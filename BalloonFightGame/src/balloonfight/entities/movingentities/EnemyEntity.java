package balloonfight.entities.movingentities;

import java.awt.Point;


public class EnemyEntity extends PlayerEntity{
	private static final long serialVersionUID = 1L;

	public EnemyEntity(int x, int y)
	{
		X = x;
		Y = y;	
		balloons = 1;
		points = 50;
	}
	@Override
	public Point tilePosition() {
		return new Point(116,30);
	}

	@Override
	public Point tileSize() {
		return new Point(17,27);
	}

}
