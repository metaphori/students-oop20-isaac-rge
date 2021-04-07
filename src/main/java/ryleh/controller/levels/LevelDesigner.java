package ryleh.controller.levels;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ryleh.model.Type;
//to determine WHICH entities are going to be spawned

public final class LevelDesigner {
	
	private final int TROUBLE_INCREASER = 4;
	private final int TROUBLE_DECREASER = 7;
	private int levelNum = 0;
	private List<Type> entities = new ArrayList<>();
	private Random random = new Random();
	
	public List<Type> generateLevelEntities() {
		//generatePlayer();
		levelNum++; 
		this.generateObstacles();
		this.generateItems();
		this.generateEnemies();
		return entities;
	}
	private void generateEnemies() {	
		final int enemyNumber = (int) (random.nextGaussian() + 3);
		int difficultyRange = (int) (200 * Math.pow(Math.E,  -(double) (enemyNumber) / 7) - 80);
		difficultyRange = difficultyRange / TROUBLE_DECREASER + levelNum * TROUBLE_INCREASER;
		final int difficulty = (int) (random.nextGaussian() + difficultyRange);
		//int difficultyLevel = 0;
		for (int i = 0; i < enemyNumber; i++) {
			if (difficulty >= 0 && difficulty <= 20) {
				//difficultyLevel = 1;
				if (random.nextInt(2) == 0) {
					entities.add(Type.ENEMY_DRUNK);
				} else {
					entities.add(Type.ENEMY_LURKER);
				}
			} else if (difficulty > 20 && difficulty <= 50) {
				//difficultyLevel = 2;
				if (random.nextInt(2) == 0) {
					entities.add(Type.ENEMY_SHOOTER);
				} else {
					entities.add(Type.ENEMY_SPINNER);
				}
			} else if (difficulty > 50) {
				//difficultyLevel = 3;
				if (random.nextInt(2) == 0) {
					entities.add(Type.ENEMY_DRUNKSPINNER);
				} else {
					entities.add(Type.ENEMY_DRUNKSPINNER);
				}
			}
		}
		/*System.out.println("questa � la lista dei nemici");
		System.out.print(entities + "\n");
		System.out.println("questo � il numero dei nemici");
		System.out.print(enemyNumber + "\n");
		System.out.println("questo � il range di difficolt�");
		System.out.print(difficultyRange + "\n");
		System.out.println("questo � il livello definitivo di difficolt�");
		System.out.print(difficulty + "\n");
		System.out.println("questo � il livello definitivo di difficolt�");
		System.out.print(difficultyLevel + "\n");*/
	}
	private void generateItems() {
		if (levelNum % 3 == 0) {
			entities.add(Type.ITEM);
		}
	}
	private void generateObstacles() {
		final int obstacleNum = (int) random.nextGaussian() + 2;
		for (int i = 0; i < obstacleNum; i++) {
			if (random.nextInt(2) == 0) {
				entities.add(Type.FIRE);
			} else {
				entities.add(Type.ROCK);
			}
		}
	}

	public void clearLevel() {
		this.entities.clear();
	}
}
