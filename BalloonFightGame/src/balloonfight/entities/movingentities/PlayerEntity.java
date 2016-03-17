package balloonfight.entities.movingentities;

import java.util.Random;

import balloonfight.entities.Entity;
import balloonfight.utilities.BoundingBox;

public abstract class PlayerEntity extends Entity{
	private static final long serialVersionUID = 1L;
	protected int changeX;
	protected int changeY;
	protected int balloons;
	protected boolean onGround;
	protected int velX;
	protected int velY;
	protected int JQ;
	protected boolean renderOnGround;
	public PlayerEntity(){
		super();
		onGround = false;
		renderOnGround = false;
		JQ = 0;
		velX = 0;
		velY = 0;
	}
	public PlayerEntity(int x, int y){
		super();
		onGround = false;
		JQ = 0;
		velX = 0;
		velY = 0;
		X = x;
		Y = y;
	}
	public void setPosition(int x, int y){
		X = x;
		Y = y;
	}
	public void updatePosition(){
		if(balloons<=0){
			Y-=12;
		}else{
		if(Y>=479)velY=-8-(new Random()).nextInt(8);
		Y+=velY;
		velY-=Math.signum(velY);
		X+=velX;
		velX-=Math.signum(velX);
		if(this.onGround){
		Y += (1.5*(2 - (changeY^2)/4)>0)?3*(2 - (changeY^2)/4):0;
		renderOnGround = true;
		onGround = false;
		}else{
		Y += 1.5*(2 - (changeY^2)/4);
		renderOnGround = false;
		}
		if(changeY<16)changeY++;
		X += 1*changeX;
		changeX-=Math.signum(changeX);
		if(X<0)X=639;else if(X>640)X=1;
		if(Y<0)System.out.println("Death placeholder");
		}
	}
	public void jump(){
		changeY = 0;
		onGround = false;
	}
	public void queueJump(){
		JQ++;
	}
	public boolean isJumpQueued(){
		if(JQ>0){
			JQ--;
			return true;
		} else {
			return false;
		}
	}
	public void moveLeft(){
		changeX = -8;
	}
	public void moveRight(){
		changeX = 8;
	}
	public boolean onGround(){
		return renderOnGround;
	}
	public void setGrounded(boolean b){
		onGround = b;
	}
	public void setVelocity(int x, int y){
		velX = x;
		velY = y;
	}
	public BoundingBox getBoundingBox() {
		return new BoundingBox(0-tileSize().x/2,0-tileSize().y/2,tileSize().x/2,this.tileSize().y/2);
	}
	public void onCollision(Entity collided) {
		if(collided instanceof PlayerEntity){
			if(collided.getY()-11>Y){
				balloons--;
			}
			((PlayerEntity)collided).setVelocity((collided.getX()-X)/2 ,(collided.getY()-Y)/2);
			if(collided instanceof Player1Entity)((Player1Entity) collided).setFlap();
		}
	}
	public boolean isDead(){
		return Y<=0;
	}
}
