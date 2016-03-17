package balloonfight;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import balloonfight.entities.Entity;
import balloonfight.entities.movingentities.EnemyEntity;
import balloonfight.entities.movingentities.Player1Entity;
import balloonfight.entities.movingentities.PlayerEntity;
import balloonfight.utilities.BoundingBox;

/**
 * Builds the game and sets it into the frame to play.
 * 
 * @author Patrick Emery, Greyson Hill
 *
 */
public class Game extends JPanel{
	// Obligatory Serial Number
	private static final long serialVersionUID = 1L;
	
	// Array of all entity objects within the class. For ease of management.
	public ArrayList<Entity> ents;
	
	// Player x and y
	int pX;
	int pY;
	
	// Some Tokens to determine if this should still exist, and if it should, what to do.
	public boolean gameOver;
	boolean nullSession;
	
	// Points and levels
	public int points;
	int level;
	
	// Token to prevent spawn overlap or redundancy
	public boolean initialized;
	
	// Useful instance variables
	Game currentGame;
	Player1Entity player;
	/**
	 * Constructor for the game class. Initializes instance variables and
	 * listeners.
	 */
	public Game(){
		this.removeAll();
		gameOver = false;
		initialized = false;
		level = 1;
		currentGame = this;
		ActionListener jump = new ActionListener(){
			/**
			 * Handles jumping detection.
			 * @param e The Action Event
			 */
			public void actionPerformed(ActionEvent e){
				if(gameOver)return;
				for(Entity i:ents){
					if(i instanceof Player1Entity){
						pY = i.getY();
						pX = i.getX();
						if(Main.jumpDown){((Player1Entity) i).jump();System.out.println("Jumping...");((Player1Entity) i).setFlap();}
					}else if(i instanceof EnemyEntity)
					{
						// Random Movement
					}
				}
			}
		};
		ActionListener move = new ActionListener(){
			/**
			 * The movement events and collision detection
			 * @param e The Action Event
			 */
			public void actionPerformed(ActionEvent e){
				if(nullSession)return;
				if(gameOver){
					if(Main.jumpDown){
						currentGame.removeAll();
						Main.restart();
						gameOver = false;
						nullSession = true;
					}
					return;
				}
				boolean enemies = false;
				try{
				for(Entity i:ents){
					for(Entity j:ents){
						if(j==i)break;
						BoundingBox jb = j.getBoundingBox();
						BoundingBox ib = i.getBoundingBox();
						int Ax1 = jb.minX + j.getX();
						int Ax2 = jb.maxX += j.getX();
						int Ay1 = jb.minY += j.getY();
						int Ay2 = jb.maxY += j.getY();
						int Bx1 = ib.minX += i.getX();
						int Bx2 = ib.maxX += i.getX();
						int By1 = ib.minY += i.getY();
						int By2 = ib.maxY += i.getY();
						if(Ax1<Bx2&Ax2>Bx1&Ay1<By2&Ay2>By1){
							i.onCollision(j);
							j.onCollision(i);
						}
					}
					i.updatePosition();
					currentGame.repaint();
					if(i instanceof PlayerEntity){
						if(((PlayerEntity) i).isDead()){
							if(i instanceof EnemyEntity){
							points+=i.points;
							currentGame.remove(i);
							currentGame.ents.remove(i);
							} else {
								((Player1Entity)i).kill(currentGame);
							}
						}else if(i instanceof Player1Entity){
						pX = i.getX();
						pY = i.getY();
						if(Main.leftDown){
							((Player1Entity) i).moveLeft();
							i.setFacingRight(false);
							if(((Player1Entity) i).onGround()){
								((Player1Entity) i).setRun();
							}
							}
						else if(Main.rightDown){
							((Player1Entity) i).moveRight();
							i.setFacingRight(true);
							if(((Player1Entity) i).onGround()) {
								((Player1Entity) i).setRun();
								}
							}
						else{
							if(((Player1Entity) i).onGround()){
								((Player1Entity) i).setIdle();
							}
						}
					}else if(i instanceof EnemyEntity)
					{
						enemies=true;
						randomMovement(i);
					}}
				}
				if(!enemies&initialized){
					nextLevel();
				}
				} catch(Exception error)
				{
					
				}
			}
		};
		new Timer(125, jump).start();
		new Timer(50, move).start();
		ents = new ArrayList<Entity>();
		setVisible(true);
		setLayout(null);
	}
	/**
	 * Adds any instances of the Entities to an array list, and to the list of displayed objects.
	 * 
	 * @param j component entity to add.
	 */
	public Component add(Component j){
		if(j instanceof Entity)ents.add((Entity)j);
		j.setVisible(true);
		if(j instanceof Player1Entity){
			player = (Player1Entity) j;
		}
		return super.add(j);
	}
	/**
	 * Draws all of the entities on the game panel.
	 * 
	 * @param g source of graphics.
	 */
	public void paintComponent(Graphics g){
		if(nullSession)return;
		if(gameOver){
			g.setColor(new Color(255,255,255));
			g.drawString("Game Over", 300, 220);
			g.drawString("Press Space to Restart", 270, 230);
			return;
		}
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, 640, 480);
		g.setColor(new Color(255,255,255));
		g.drawString("PI:"+points, 20, 20);
		System.out.println(player.lives);
		if(player.lives==1)
			g.drawImage(Entity.image, 20, 30, 32, 45, 295, 40, 300, 45, null);
		else if(player.lives==2){
			g.drawImage(Entity.image, 20, 30, 32, 45, 295, 40, 300, 45, null);
			g.drawImage(Entity.image, 20+14, 30, 32+14, 45, 295, 40, 300, 45, null);
		}if(player.lives==3){
			g.drawImage(Entity.image, 20, 30, 32, 45, 295, 40, 300, 45, null);
			g.drawImage(Entity.image, 20+14, 30, 32+14, 45, 295, 40, 300, 45, null);
			g.drawImage(Entity.image, 20+28, 30, 32+28, 45, 295, 40, 300, 45, null);
		}for(Entity i:ents){
			i.paintComponent(g);
		}
	}
	/**
	 * Updates and essentially repaints the game panel.
	 * 
	 * @param g source of graphics.
	 */
	public void update(Graphics g){
		paintComponent(g);
	}
	/**
	 * AI for the enemies to move. Will follow the player if within a certain
	 * radius occasionally. Made by Torrance and Greyson.
	 * 
	 * @param i enemy entity to move.
	 */
	public void randomMovement(Entity i) {
		Random rand = new Random();
		EnemyEntity enemy = (EnemyEntity) i;
		
		int eX = enemy.getX();
		int eY = enemy.getY();
		
		int xDiff = 0;
		int yDiff = 0;
		
		if(pX > (eX - 50) && pX < (eX + 50) && pY > (eY - 50) && pY < (eY + 50)) //Player is within +10 and -10 of the enemy
		{
			int doNothing = rand.nextInt(10);
			xDiff = pX - eX;
			yDiff = pY - eY;
			
			if(doNothing == 9)
			{
				if(xDiff < 0 && yDiff > 0) //Player is to the left and above
				{
					enemy.moveLeft();
					enemy.jump();
				}
				else if(xDiff > 0 && yDiff < 0) //Player is to the right and below
				{
					enemy.moveRight();
				}
				else if(xDiff < 0 && yDiff < 0) //Player is to the left and below
				{
					enemy.moveLeft();
				}
				else //Player is to the right and above
				{
					enemy.moveRight();
					enemy.jump();
				}
			}
		}
		else //Player not nearby, do random things
		{
			int x = rand.nextInt(45);
			if(eY < 50)
			{
				enemy.jump();
				enemy.jump();
			} 
			else if(x == 0)
			{
				enemy.moveRight();
			}
			else if(x == 1)
			{
				enemy.moveLeft();
			}
			else if(x == 2)
			{
				enemy.jump();
			}
			/*
			else if(x == 3)
			{
				enemy.moveRight();
				//enemy.jump();
			}
			else if(x == 4)
			{
				enemy.moveLeft();
				//enemy.jump();
			}*/
		}
	}
	/**
	* If all of the enemies have been killed then it moves to the next level with more enemies.
	*/
	private void nextLevel(){
		for(Entity i:ents){
			if(i instanceof EnemyEntity)
				return;
		}
		player.setPosition(32, 64);
		level++;
		MapLayout.spawnEnts(this, level);
	}

}
