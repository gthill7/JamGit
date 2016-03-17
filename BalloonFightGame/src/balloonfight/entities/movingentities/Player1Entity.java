package balloonfight.entities.movingentities;

import java.awt.Point;

import javax.swing.JPanel;

import balloonfight.Game;
import balloonfight.Main;


public class Player1Entity extends PlayerEntity{
	public enum EnumPlayerState {
		Idle1, Idle2, Idle3, IdleOne1, IdleOne2, IdleOne3,
		RunOne1, RunOne2, RunOne3, Run1, Run2, Run3,
		Flap1, Flap2, Flap3, FlapOne1, FlapOne2, FlapOne3,Fall;
		}
	private static final long serialVersionUID = 1L;
	public int lives;
	EnumPlayerState animState;
	public Player1Entity(){
		super(32,64);
		onGround = false;
		animState = EnumPlayerState.IdleOne1;
		lives = 3;
		balloons = 2;
	}
	@Override      
	public Point tilePosition() {
		switch(animState){
		case Idle1:
			return new Point(1,1);
		case Idle2:
			return new Point(20,1);
		case Idle3:
			return new Point(39,1);
		case IdleOne1:
			return new Point(60,1);
		case IdleOne2:
			return new Point(73,1);
		case IdleOne3:
			return new Point(86,1);
		case RunOne1:
			return new Point(59,30);
		case RunOne2:
			return new Point(78,30);
		case RunOne3:
			return new Point(96,30);
		case Run1:
			return new Point(1,30);
		case Run2:
			return new Point(20,30);
		case Run3:
			return new Point(39,30);
		case Flap1:
			return new Point(1,59);
		case Flap2:
			return new Point(20,59);
		case Flap3:
			return new Point(37,59);
		case FlapOne1:
			return new Point(60,59);
		case FlapOne2:
			return new Point(75,59);
		case FlapOne3:
			return new Point(88,59);
		case Fall:
			return new Point(1,146);
		default:
			return new Point(1,1);
		}
	}
	public void setIdle(){
		if(balloons==2)
		switch(animState){
		case Idle1:
			animState = EnumPlayerState.Idle2;
		case Idle2:
			animState = EnumPlayerState.Idle3;
		default:
			animState = EnumPlayerState.Idle1;
		}
		else
		switch(animState){
		case IdleOne1:
			animState = EnumPlayerState.IdleOne2;
		case IdleOne2:
			animState = EnumPlayerState.IdleOne3;
		default:
			animState = EnumPlayerState.IdleOne1;
		}
	}
	public void setRun(){
		if(balloons==2)
		switch(animState){
		case Run1:
			animState = EnumPlayerState.Run2;
		case Run2:
			animState = EnumPlayerState.Run3;
		default:
			animState = EnumPlayerState.Run1;
		}
		else
		switch(animState){
		case RunOne1:
			animState = EnumPlayerState.RunOne2;
		case RunOne2:
			animState = EnumPlayerState.RunOne3;
		default:
			animState = EnumPlayerState.RunOne1;
		}
	}
	public void setFlap(){
		if(balloons==0){
			animState = EnumPlayerState.Fall;
		}else if(balloons==2)
		switch(animState){
		case Flap1:
			animState = EnumPlayerState.Flap2;
		case Flap2:
			animState = EnumPlayerState.Flap3;
		default:
			animState = EnumPlayerState.Flap1;
		}
		else
		switch(animState){
		case FlapOne1:
			animState = EnumPlayerState.FlapOne2;
		case FlapOne2:
			animState = EnumPlayerState.FlapOne3;
		default:
			animState = EnumPlayerState.FlapOne1;
		}
	}
	@Override
	public Point tileSize() {
		if(animState==EnumPlayerState.Flap1|animState==EnumPlayerState.Flap2|animState==EnumPlayerState.Flap3|animState==EnumPlayerState.Idle1|animState==EnumPlayerState.Idle2|animState==EnumPlayerState.Idle3|animState==EnumPlayerState.Run1|animState==EnumPlayerState.Run2|animState==EnumPlayerState.Run3)
		return new Point(19,27);
		else if(animState==EnumPlayerState.Fall)
		return new Point(16,16);
		else if(animState==EnumPlayerState.FlapOne1|animState==EnumPlayerState.FlapOne2|animState==EnumPlayerState.FlapOne3)
		return new Point(13,27);
		else
		return new Point(13,27);
	}
	public void kill(Game game){
		lives--;
		X = 32;
		Y = 64;
		this.setIdle();
		balloons = 2;
		if(lives<=0){
			Main.panel.gameOver = true;	
			Main.panel.repaint();
		}
	}

}
