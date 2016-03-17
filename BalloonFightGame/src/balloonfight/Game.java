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

public class Game extends JPanel{
	private static final long serialVersionUID = 1L;
	public ArrayList<Entity> ents;
	int pX;
	public boolean gameOver;
	public int points;
	int pY;
	int level;
	public boolean initialized;
	Game currentGame;
	Player1Entity player;
	public Game(){
		this.removeAll();
		gameOver = false;
		initialized = false;
		level = 1;
		currentGame = this;
		ActionListener jump = new ActionListener(){
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
			public void actionPerformed(ActionEvent e){
				if(gameOver){
					if(Main.jumpDown){
						Main.restart();
						gameOver = false;
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
	public void reInit(){
		this.removeAll();
		gameOver = false;
		initialized = false;
		level = 1;
		currentGame = this;
		ActionListener jump = new ActionListener(){
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
			public void actionPerformed(ActionEvent e){
				if(gameOver){
					if(Main.jumpDown){
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
	public Component add(Component j){
		if(j instanceof Entity)ents.add((Entity)j);
		j.setVisible(true);
		if(j instanceof Player1Entity){
			player = (Player1Entity) j;
		}
		return super.add(j);
	}
	public void paintComponent(Graphics g){
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
	public void update(Graphics g){
		paintComponent(g);
	}
	public void randomMovement(Entity i)
	{
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
