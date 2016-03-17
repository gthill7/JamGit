package balloonfight;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Main {
public static JFrame frame;
public static Game panel;
public static boolean jumpDown;
public static boolean leftDown;
public static boolean rightDown;
public static void main(String[] args){
	frame = new JFrame();
	frame.setSize(640,480);
	frame.setTitle("Balloon Fight");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	panel = new Game();
	MapLayout.initialize((Game) panel);
	MapLayout.spawnEnts((Game) panel, 1);
	frame.add(panel);
	frame.setVisible(true);
	KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

        @Override
        public boolean dispatchKeyEvent(KeyEvent ke) {
            synchronized (Main.class) {
                switch (ke.getID()) {
                case KeyEvent.KEY_PRESSED:
                    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                        jumpDown = true;
                    } else if(ke.getKeyCode() == KeyEvent.VK_LEFT){
                    	leftDown = true;
                    } else if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
                    	rightDown = true;
                    }
                    break;

                case KeyEvent.KEY_RELEASED:
                    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                        jumpDown = false;
                    } else if(ke.getKeyCode() == KeyEvent.VK_LEFT){
                    	leftDown = false;
                    } else if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
                    	rightDown = false;
                    }
                    break;
                }
                return false;
            }
        }
    });
}
public static void restart(){
	panel = new Game();
	MapLayout.initialize((Game) panel);
	MapLayout.spawnEnts((Game) panel, 1);
	frame.add(panel);
	frame.setVisible(true);
}
}
