package balloonfight.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import balloonfight.entities.movingentities.PlayerEntity;
import balloonfight.utilities.BoundingBox;

public class EntityPlatform extends Entity {
	private static final long serialVersionUID = 1L;
	private int length;
	public EntityPlatform(int len, int x, int y){
		length = len;
		X = x;
		Y = y;
	}
	@Override
	public Point tilePosition() {
		return new Point(222,24);
	}
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
	@Override
	public Point tileSize() {
		return new Point(8,8);
	}

	@Override
	public void updatePosition() {
	}

	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(0,0,length*8,16);
	}

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
