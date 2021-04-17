package ryleh.controller.levels;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import ryleh.controller.core.GameEngine;
import ryleh.model.Type;
//to determine WHICH entities are going to be spawned

public final class LevelDesigner {
	
	private static final int TROUBLE_INCREASER = 4;
	private static final int TROUBLE_DECREASER = 7;
	private int levelNum;
	private final List<Type> entities;
	private final Random random;
	private final Function<Integer, Integer> difficultyFunction;
	
	public LevelDesigner() {
	    levelNum = 0;
	    entities = new ArrayList<>();
	    random = new Random();
	    difficultyFunction = (x) -> (int) (200 * Math.pow(Math.E,  - (double) (x) / 7) - 80);
	}
	
	public List<Type> generateLevelEntities() {
	    levelNum++; 
	    if (levelNum == 1) {
	       this.entities.clear();
	    } else {
	        this.generateObstacles();
                this.generateItems();
                this.generateEnemies();
	    }
	    return entities;
	}
	/**
	 * This method is used to determine a range that represents the difficulty of the current level.
	 * @param enemyNumber
	 * @return the value of a function with enemy number as input.
	 */
	private int getDifficultyRange(final int enemyNumber) {
	    return difficultyFunction.apply(enemyNumber);
	}
	/**
	 * This method generates a list of Entity type, that represents the entities inside current level.
	 */
	private void generateEnemies() {	
		final int enemyNumber = (int) (random.nextGaussian() + 3);
		int difficultyRange = this.getDifficultyRange(enemyNumber);
		difficultyRange = difficultyRange / TROUBLE_DECREASER + levelNum * TROUBLE_INCREASER;
		final int difficulty = (int) (random.nextGaussian() + difficultyRange);

		for (int i = 0; i < enemyNumber; i++) {
			if (difficulty >= 0 && difficulty <= 20) {
				if (random.nextInt(2) == 0) {
					entities.add(Type.ENEMY_DRUNK);
				} else {
					entities.add(Type.ENEMY_LURKER);
				}
			} else if (difficulty > 20 && difficulty <= 50) {
				if (random.nextInt(2) == 0) {
					entities.add(Type.ENEMY_SHOOTER);
				} else {
					entities.add(Type.ENEMY_SPINNER);
				}
			} else if (difficulty > 50) {
				if (random.nextInt(2) == 0) {
					entities.add(Type.ENEMY_DRUNKSPINNER);
				} else {
					entities.add(Type.ENEMY_DRUNKSPINNER);
				}
			}
		}
		GameEngine.runDebugger(() -> System.out.println(entities));
	}
	/**
	 * This method add "ITEM" to the entity list of the current level. It does so only every three levels.
	 */
	private void generateItems() {
		if (levelNum % 3 == 0) {
			entities.add(Type.ITEM);
		}
	}
	/**
	 * Generates random obstacles choosing between "ROCK" type and "FIRE" type.
	 */
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

	/**
	 * Clears the entity list of the current level.
	 */
	public void clearLevel() {
		this.entities.clear();
	}

	/**
	 * Sets current level to level. Note: this method should be used ONLY in developer mode.
	 * @param level Level to be set.
	 */
    public void setLevel(final int level) {
        this.levelNum = level;
    }
}
