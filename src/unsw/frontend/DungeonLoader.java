package unsw.frontend;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.*;
import unsw.goals.GoalAndComposite;
import unsw.goals.GoalComponent;
import unsw.goals.GoalComposite;
import unsw.goals.GoalEnemy;
import unsw.goals.GoalExit;
import unsw.goals.GoalOrComposite;
import unsw.goals.GoalSwitch;
import unsw.goals.GoalTreasure;
import unsw.item.*;
import unsw.obstacle.*;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

	private JSONObject json;

	/**
	 * Creates a new dungeon lader
	 * 
	 * @param filename - Path of dungeon file
	 * @throws FileNotFoundException
	 */
	public DungeonLoader(String filename) throws FileNotFoundException {
		json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
	}

	/**
	 * Parses the JSON to create a dungeon.
	 * 
	 * @return
	 */
	public Dungeon load() {
		int width = json.getInt("width");
		int height = json.getInt("height");

		Dungeon dungeon = new Dungeon(width, height);

		// Loads entities
		JSONArray jsonEntities = json.getJSONArray("entities");

		for (int i = 0; i < jsonEntities.length(); i++) {
			loadEntity(dungeon, jsonEntities.getJSONObject(i));
		}

		// Creates observer pattern for players interaction with enemies
		Player p = dungeon.getPlayer();
		for (Entity e : dungeon.getEntities()) {
			if (e.getName().equals("Enemy")) {
				p.addObserver((Enemy) e);
			}
		}

		// Loads goals
		JSONObject jsonGoals = json.getJSONObject("goal-condition");
		GoalComponent g = handleGoal(dungeon, jsonGoals);
		dungeon.setGoals(g);

		return dungeon;
	}

	/**
	 * Adds subgoals from JSON array to parent.
	 * 
	 * @param dungeon  - Dungeon
	 * @param parent   - Parent goal
	 * @param subGoals - JSON array of subgoals
	 */
	public void loadSubGoals(Dungeon dungeon, GoalComposite parent, JSONArray subGoals) {
		for (int i = 0; i < subGoals.length(); i++) {
			JSONObject jsonGoal = subGoals.getJSONObject(i);

			GoalComponent g = handleGoal(dungeon, jsonGoal);

			if (g != null) {
				parent.addSubGoal(g);
			}
		}
	}

	/**
	 * Handles the creation of a goal from a JSON object.
	 * 
	 * @param dungeon  - Dungeon
	 * @param jsonGoal - JSON object storing information about the goal
	 * @return g - Returns the created goal component or null if the JSON object
	 *         does not store a valid goal.
	 */
	public GoalComponent handleGoal(Dungeon dungeon, JSONObject jsonGoal) {
		String goal = jsonGoal.getString("goal");

		GoalComponent g = null;
		switch (goal) {
		case "exit":
			g = new GoalExit(dungeon);
			break;
		case "enemies":
			g = new GoalEnemy(dungeon);
			break;
		case "treasure":
			g = new GoalTreasure(dungeon);
			break;
		case "boulders":
			g = new GoalSwitch(dungeon);
			break;
		case "OR":
			g = new GoalOrComposite();
			JSONArray orSubGoals = jsonGoal.getJSONArray("subgoals");
			loadSubGoals(dungeon, (GoalOrComposite) g, orSubGoals);
			break;
		case "AND":
			g = new GoalAndComposite();
			JSONArray andSubGoals = jsonGoal.getJSONArray("subgoals");
			loadSubGoals(dungeon, (GoalAndComposite) g, andSubGoals);
			break;
		}
		return g;
	}

	/**
	 * Loads entities from JSON object
	 * 
	 * @param dungeon - Dungeon which holds entities
	 * @param json    - JSON object containing information about entity
	 */
	public void loadEntity(Dungeon dungeon, JSONObject json) {
		String type = json.getString("type");
		int x = json.getInt("x");
		int y = json.getInt("y");

		Entity entity = null;
		switch (type) {
		case "player":
			Player player = new Player(dungeon, x, y, "Player");
			dungeon.setPlayer(player);
			onLoad(player);
			entity = player;
			break;
		case "wall":
			Wall wall = new Wall(x, y, "Wall");
			onLoad(wall);
			entity = wall;
			break;
		case "boulder":
			Boulder boulder = new Boulder(x, y, "Boulder");
			onLoad(boulder);
			entity = boulder;
			break;
		case "door":
			Door door = new Door(x, y, "Door", json.getInt("id"));
			onLoad(door);
			entity = door;
			break;
		case "switch":
			Switch Switch = new Switch(x, y, "Switch");
			onLoad(Switch);
			entity = Switch;
			break;
		case "exit":
			Exit exit = new Exit(x, y, "Exit");
			onLoad(exit);
			entity = exit;
			break;
		case "sword":
			Sword sword = new Sword(x, y, "Sword");
			onLoad(sword);
			entity = sword;
			break;
		case "treasure":
			Treasure treasure = new Treasure(x, y, "Treasure");
			onLoad(treasure);
			entity = treasure;
			break;
		case "key":
			Key key = new Key(x, y, "Key", json.getInt("id"));
			onLoad(key);
			entity = key;
			break;
		case "invincibility":
			Potion potion = new Potion(x, y, "Potion");
			onLoad(potion);
			entity = potion;
			break;
		case "portal":
			Portal portal = new Portal(x, y, "Portal", json.getInt("id"));
			onLoad(portal);
			entity = portal;
			break;
		case "enemy":
			Enemy enemy = new Enemy(x, y, "Enemy");
			onLoad(enemy);
			entity = enemy;
			break;
		case "bomb":
			Bomb bomb = new Bomb(x, y, "Bomb");
			onLoad(bomb);
			entity = bomb;
			break;
		case "spike":
			Spike spike = new Spike(x, y, "Spike");
			onLoad(spike);
			entity = spike;
			break;
		}
		dungeon.addEntity(entity);
	}

	public abstract void onLoad(Entity player);

	public abstract void onLoad(Wall wall);

	public abstract void onLoad(Boulder boulder);

	public abstract void onLoad(Door door);

	public abstract void onLoad(Switch switch1);

	public abstract void onLoad(Exit exit);

	public abstract void onLoad(Sword sword);

	public abstract void onLoad(Treasure treasure);

	public abstract void onLoad(Key key);

	public abstract void onLoad(Potion potion);

	public abstract void onLoad(Portal portal);

	public abstract void onLoad(Enemy enemy);

	public abstract void onLoad(Bomb bomb);

	public abstract void onLoad(Spike spike);
}
