package balloonfight;

import balloonfight.entities.EntityPlatform;
import balloonfight.entities.movingentities.EnemyEntity;
import balloonfight.entities.movingentities.Player1Entity;

/**
 * Generates the map layout for the game.
 * 
 * @author Patrick Emery
 *
 */
public class MapLayout {
	/**
	 * Initializes the enemies and platforms for the levels.
	 * 
	 * @param game the current game panel.
	 */
	public static void initialize(Game game){
		game.add(new EntityPlatform(28,0,48));
		game.add(new EntityPlatform(28,640-(29*8),48));
		game.add(new EntityPlatform(40,160,224));
		game.add(new EntityPlatform(12,32,352));
		game.add(new EntityPlatform(12,640-(18*8),352));
		game.add(new Player1Entity());
	}
	/**
	 * Dictates how many enemies are to be spawned based on the level.
	 * 
	 * @param game the current game panel.
	 * @param level the level that the user is currently on.
	 */
	public static void spawnEnts(Game game, int level){
		game.initialized = true;
		for(int i = 0;i<level&&i<=5;i++){
			switch(i){
			case(1):
				game.add(new EnemyEntity(640-(14*8),356));
				break;
			case(2):
				game.add(new EnemyEntity(32, 356));
				break;
			case(3):
				game.add(new EnemyEntity(280, 256));
				break;
			case(4):
				game.add(new EnemyEntity(360, 256));
				break;
			case(5):
				game.add(new EnemyEntity(620, 64));
				break;
			}
		}
	}
}
