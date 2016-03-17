package balloonfight;

import balloonfight.entities.EntityPlatform;
import balloonfight.entities.movingentities.EnemyEntity;
import balloonfight.entities.movingentities.Player1Entity;

public class MapLayout {
public static void initialize(Game game){
	game.add(new EntityPlatform(28,0,48));
	game.add(new EntityPlatform(28,640-(29*8),48));
	game.add(new EntityPlatform(40,160,224));
	game.add(new EntityPlatform(12,32,352));
	game.add(new EntityPlatform(12,640-(18*8),352));
	game.add(new Player1Entity());
}
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
