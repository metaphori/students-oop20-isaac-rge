
package ryleh.controller.levels;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import ryleh.common.P2d;
import ryleh.common.Pair;
import ryleh.common.Rectangle2d;
import ryleh.controller.Entity;
import ryleh.controller.events.GameOverEvent;
import ryleh.core.GameEngine;
import ryleh.core.GameState;
import ryleh.core.factories.BasicFactory;
import ryleh.core.factories.EnemyFactory;
import ryleh.model.Type;
import ryleh.model.World;


//to determine where to spawn the entities generated by Level Designer
public class LevelHandler {

    /**
     * This values represent the minimum distances from player's spawn point of each type of entity.
     * Level Handler uses this values when generating level entities. 
     */
    public static final double DRUNK_SPAWN_DISTANCE = 2;
    public static final double LURKER_SPAWN_DISTANCE = 3;
    public static final double SHOOTER_SPAWN_DISTANCE = 4;
    public static final double SPINNER_SPAWN_DISTANCE = 3;

    public static final double ROCK_SPAWN_DISTANCE = 2;
    public static final double FIRE_SPAWN_DISTANCE = 1;
    public static final double ITEM_SPAWN_DISTANCE = 1;
    private int entityCounter;

    /**
     * The number of levels required to advance and win the game.
     */
    private static final int LAST_LEVEL = 30;
//the number of spawn points is determined
    private final Map<Pair<Integer, Integer>, Entity> spawnPoints;
    private static final int COLUMNS = 9;
    private static final int ROWS = 5;
    private int nEnemies;
    private boolean hasItem;
    private int nRooms;
    private final LevelDesigner designer;
    private final GameState gameState;
    private final P2d boundsCoord;
    private final double boundsWidth;
    private final double boundsHeight;
    private final Pair<Integer, Integer> playerSpawn;
    private final Pair<Integer, Integer> doorSpawn;

	public LevelHandler(final GameState gameState) {
	    this.gameState = gameState;
	    final World world = gameState.getWorld();
	    this.designer = new LevelDesigner();
	    spawnPoints = new HashMap<>();
	    boundsCoord = ((Rectangle2d) world.getBounds()).upperLeft;
	    boundsWidth = world.getWidthBound();
	    boundsHeight = world.getHeightBound();
	    playerSpawn = new Pair<>(COLUMNS / 2, ROWS - 1);
	    doorSpawn = new Pair<>(COLUMNS / 2, 0);
	    nEnemies = 0;
	    hasItem = false;
	    entityCounter = 0;
	    nRooms = 0;
	}
	/**
	 * 
	 * @return The number of rooms currently visited. 
	 */
	public int getnRooms() {
	    return nRooms;
	}
	/**
	 * Generates a new Level, clearing the previous entities from the list.
	 */
	public void generateNewLevel() {
		spawnPoints.clear();
		nEnemies = 1;
		designer.clearLevel();
		hasItem = false;
		nRooms++;
		if (nRooms >= LAST_LEVEL + 1) {
		    gameState.callGameOver(true);
		    return;
		}
		entityCounter = 0;
		final List<Type> entityList = designer.generateLevelEntities();
		for (final Type elem : entityList) {
			Pair<Integer, Integer> temp = new Pair<>(0, 0);
			Entity entity = new Entity();
			boolean skip = false;
			switch (elem) {
			case ENEMY_DRUNK:
				nEnemies++;
				temp = getRandomSpawnPoint(DRUNK_SPAWN_DISTANCE);
				entity = EnemyFactory.getInstance().createEnemyDrunk(this.gameState, getPosition(temp));
				break;
			case ENEMY_LURKER:
				nEnemies++;
				temp = getRandomSpawnPoint(LURKER_SPAWN_DISTANCE);
				entity = EnemyFactory.getInstance().createEnemyLurker(this.gameState, getPosition(temp));
				break;
			case ENEMY_SHOOTER:
				nEnemies++;
				temp = getRandomSpawnPoint(SHOOTER_SPAWN_DISTANCE);
				entity = EnemyFactory.getInstance().createEnemyShooter(this.gameState, getPosition(temp));
				break;
			case ENEMY_SPINNER:
				nEnemies++;
				temp = getRandomSpawnPoint(SPINNER_SPAWN_DISTANCE);
				entity = EnemyFactory.getInstance().createEnemySpinner(this.gameState, getPosition(temp));
				break;
			case ENEMY_DRUNKSPINNER:
				nEnemies++;
				temp = getRandomSpawnPoint(DRUNK_SPAWN_DISTANCE);
				entity = EnemyFactory.getInstance().createEnemyDrunkSpinner(this.gameState, getPosition(temp));
				break;
			case ROCK:
				temp = getRandomSpawnPoint(ROCK_SPAWN_DISTANCE);
				entity = BasicFactory.getInstance().createRock(this.gameState, getPosition(temp));
				break;	
			case FIRE:
				temp = getRandomSpawnPoint(FIRE_SPAWN_DISTANCE);
				entity = BasicFactory.getInstance().createFire(this.gameState, getPosition(temp));
				break;	
			case ITEM: //Spawn mechanics when all enemies are defeated 
				skip = true;
				hasItem = true;
				break;
			default:
				skip = true;
				break;
			}
			if (!skip) {
			    addEntity(temp, entity);
			}
		}
		decreaseEnemies(); //this calls is necessary to check if enemies have spawned in current level
	}
	
	/**
	 * Converts the coordinates taken from the grid of spawn points into world coordinates. 
	 * @param position
	 * @return P2d representing position converted into world's coordinates
	 */
	public P2d getPosition(final Pair<Integer, Integer> position) {
		return new P2d(((boundsWidth / COLUMNS)) * position.getX() + boundsCoord.x + (boundsWidth / (COLUMNS * 2)),
				((boundsHeight / ROWS)) * position.getY() + boundsCoord.y + (boundsHeight / (ROWS * 2)));
	}
	/**
	 * @return a random position inside the map of SpawnPoints
	 * It automatically excludes player's spawn position and doesn't generate duplicates.
	 */
	public Pair<Integer, Integer> getRandomSpawnPoint() {
		//TODO 
		final Random generator = new Random();
		Pair<Integer, Integer> random;
		do {
			 random = new Pair<>(generator.nextInt(COLUMNS), generator.nextInt(ROWS));
		} while (spawnPoints.containsKey(random) && entityCounter < ROWS * COLUMNS || random.equals(playerSpawn)
		        || random.equals(doorSpawn));
		entityCounter++;
		return random;
	}
	/**
	 * 
	 * @param minDistance
	 * @return a random position inside the map of SpawnPoints with a given minimum distance.
	 */
	public Pair<Integer, Integer> getRandomSpawnPoint(final double minDistance) {
		//TODO 
		final Random generator = new Random();
		Pair<Integer, Integer> random;
		do {
				random = new Pair<>(generator.nextInt(COLUMNS), generator.nextInt(ROWS));
		} while (spawnPoints.containsKey(random) && entityCounter < ROWS * COLUMNS || random.equals(playerSpawn)
				 || getDistanceFromSpawn(random) < minDistance);
		entityCounter++;
		return random;
	}
	/**
	 * 
	 * @param point
	 * @return distance from player's spawn point
	 */
	private double getDistanceFromSpawn(final Pair<Integer, Integer> point) {
		return Math.sqrt(Math.pow(point.getX() - playerSpawn.getX(), 2) 
		        + Math.pow(point.getY() - playerSpawn.getY(), 2));
	}
	/**
	 * 
	 * @param temp
	 * @param entity
	 * Add the given entity to the list of entities of current level.
	 */
	private void addEntity(final Pair<Integer, Integer> temp, final Entity entity) {
		spawnPoints.put(temp, entity);
		entity.getGameObject().setPosition(getPosition(temp));
	}

	/**
	 * @return player's spawn point
	 */
	public Pair<Integer, Integer> getPlayerSpawn() {
		return playerSpawn;
	}
	/**
	 * Spawns an item only if current level contains an item (boolean flag "hasItem" is set inside the
	 * generation of a new level.
	 */
	public void spawnItem() {
		if (hasItem) {
		    gameState.addEntity(BasicFactory.getInstance()
		            .createItem(this.gameState, this.getPosition(this.playerSpawn)));
		}
	}
	/**
	 * Spawns a door. This method is called at the end of the level, when all enemies are defeated.
	 */
	public void spawnDoor() {
		gameState.addEntity(BasicFactory.getInstance()
				.createDoor(this.gameState, this.getPosition(new Pair<>(0,2))));
	}
	/**
	 * Decreases enemies. If nEnemies equals 0, then we are at the end of the level. 
	 */
	public void decreaseEnemies() {
		nEnemies--;
		GameEngine.runDebugger(() -> System.out.println(nEnemies));
		if (noEnemies()) {
		    GameEngine.runDebugger(() ->  {
		        System.out.println("nEnemies e'" + nEnemies + " quindi spawno l'item");
		    });
		    spawnItem();
		    spawnDoor();
		}
	}
	/**
	 * @return true if all enemies are defeated or the level contains no enemies.
	 */
	public boolean noEnemies() {
	    return nEnemies == 0;
	}
	/**
	 * 
	 * @return the list of entities inside the current level.
	 */
	public Collection<Entity> getEntities() {
		return spawnPoints.values();
	}
	/**
	 * Sets current level.
	 * @param level Level to be set.
	 */
	public void setLevel(final int level) {
	    this.nRooms = level;
	    this.designer.setLevel(level);
	}
	/**
	 * Sets current level to last level.
	 * @return Max Level attribute.
	 */
	public static int getMaxLevel() {
	    return LAST_LEVEL;
	}
	/**
	 * Prints current spawnPoints map. Used for debugging purposes.
	 */
	public void printSpawnPoints() {
	    spawnPoints.forEach((k, v) -> System.out.println("chiave" + k + "\t valore" + v));
	}
	
}
