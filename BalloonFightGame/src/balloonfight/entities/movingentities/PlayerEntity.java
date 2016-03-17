package balloonfight.entities.movingentities;

import java.util.Random;

import balloonfight.entities.Entity;
import balloonfight.utilities.BoundingBox;
/**
 * Parent class to both the enemy and the player objects.
 * 
 * @author Greyson Hill, Torrance Yang
 *
 */
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
	/**
	 * Constructor for the parent player class and no coordinates are given.
	 */
	public PlayerEntity(){
		super();
		onGround = false;
		renderOnGround = false;
		JQ = 0;
		velX = 0;
		velY = 0;
	}
	/**
	 * Constructor for the parent player class with the specified coordinates.
	 * 
	 * @param x x location for the entity.
	 * @param y y location for the entity.
	 */
	public PlayerEntity(int x, int y){
		super();
		onGround = false;
		JQ = 0;
		velX = 0;
		velY = 0;
		X = x;
		Y = y;
	}
	/**
	 * Setter for the position of the entity.
	 * 
	 * @param x x location of the entity.
	 * @param y y location of the entity.
	 */
	public void setPosition(int x, int y){
		X = x;
		Y = y;
	}
	/**
	 * Updates the position of the entity every tick.
	 */
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
	/**
	 * Sets the player's jump statex	
	 */
	public void jump(){
		changeY = 0;
		onGround = false;
	}
	/**
	 * Queues the jumps so as the jump multiple times.
	 */
	public void queueJump(){
		JQ++;
	}
	/**
	 * Checks if the jump method has been called multiple times concurrently.
	 * 
	 * @return true if jump is called concurrently, false otherwise.
	 */
	public boolean isJumpQueued(){
		if(JQ>0){
			JQ--;
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Sets the player to move left.
	 */
	public void moveLeft(){
		changeX = -8;
	}
	/**
	 * Sets the player to move right.
	 */
	public void moveRight(){
		changeX = 8;
	}
	/**
	 * Checks if the entity has landed on a platform, exclusively for rendering purposes.
	 * Foregoes a bug which allowed players to walk off platforms and still appear to be on one.
	 * 
	 * @return true if it has landed, false otherwise.
	 */
	public boolean onGround(){
		return renderOnGround;
	}
	/**
	 * Setter for if the entity is on a platform.
	 * 
	 * @param b boolean value to tell if the entity is on a platform.
	 */
	public void setGrounded(boolean b){
		onGround = b;
	}
	/**
	 * Setter for the velocity of the entity.
	 * 
	 * @param x x value for the velocity.
	 * @param y y value for the velocity.
	 */
	public void setVelocity(int x, int y){
		velX = x;
		velY = y;
	}
	/**
	 * Getter for the bounding box of the player entities.
	 */
	public BoundingBox getBoundingBox() {
		return new BoundingBox(0-tileSize().x/2,0-tileSize().y/2,tileSize().x/2,this.tileSize().y/2);
	}
	/**
	 * Handles the collision of player entities. Only allows for killing if the balloon if collided.
	 */
	public void onCollision(Entity collided) {
		if(collided instanceof PlayerEntity){
			if(collided.getY()-11>Y){
				balloons--;
			}
			((PlayerEntity)collided).setVelocity((collided.getX()-X)/2 ,(collided.getY()-Y)/2);
			if(collided instanceof Player1Entity)((Player1Entity) collided).setFlap();
		}
	}
	/**
	 * Checks if the player entity is dead.
	 * 
	 * @return true if the player or enemy is dead, false otherwise.
	 */
	public boolean isDead(){
		return Y<=0;
	}
}
