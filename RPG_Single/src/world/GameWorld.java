package world;

import java.util.Random;
import java.util.Vector;

import it.randomtower.engine.ME;
import it.randomtower.engine.World;
import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import entities.Resource;
import entities.players.Npc;
import entities.players.Player;
import entities.resources.WorldResources;
import factory.Factory;

/**
 * The main Game World which houses all Players, NPC's, map and resources
 * 
 * @author Collin Mabus
 */

public class GameWorld extends World {

	// UNIVERSAL TRUTHS //
	private final int MODIFIER = 64;

	// MAP VARIABLES //
	private static TiledMap map;

	private String mapPath = "Images/tileMAp/test1.tmx";

	private Camera cam;

	// ENTITIES //
	private static Player player;

	private Vector<Npc> npcs = new Vector<Npc>(0, 1);

	// RESOURCES //
	private WorldResources wResource;

	// RANDOM //
	private Random random = new Random();

	// CONTROLS //
	private Input input = container.getInput();

	/**
	 * GameWorld Constructor
	 * 
	 * @param id
	 *            - State ID
	 * @param container
	 *            - Window
	 * @throws SlickException
	 *             - Exception Handler
	 */
	public GameWorld(int id, GameContainer container) throws SlickException {
		super(id, container);
		ME.keyToggleDebug = Input.KEY_1;
	}

	/**
	 * Update Function GAMEWORLD
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);

		// MAP \\
		map = new TiledMap(mapPath);

		// CAMERA \\
		cam = new Camera(container, map);

		// ENTITIES \\
		player = new Player(3, 3, 1);
		player.setGameWorld(this);
		add(player);
	}

	/**
	 * Update Function GAMEWORLD
	 * 
	 * @param container
	 *            - Window
	 * @param game
	 *            - Game State
	 * @param delta
	 *            - Game Time
	 */

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);

		cam.centerOn(player.getX(), player.getY());
		keyShortcuts(game);
	}

	/**
	 * Render Function GAMEWORLD
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		super.render(container, game, g);

		g.setColor(Color.black); // Container Colors
		g.setBackground(Color.white); //

		cam.renderDetails(g); // Sends g off for Camera Rendering

		for (Entity e : getEntities()) { // Renders all Entities
			e.render(container, g);
		}
	}

	private void keyShortcuts(StateBasedGame game) throws SlickException {
		// Menu Transitions
		if (input.isKeyPressed(Input.KEY_I)) {
			game.enterState(1);
		}

		if (input.isKeyPressed(Input.KEY_3)) {
			System.out.println("Breakpoint");
		}

		if (input.isKeyPressed(Input.KEY_K)) {
		}

		if (input.isKeyPressed(Input.KEY_G)) {
			addResource();
		}
	}

	private void addResource() {
		
		int tempX = random.nextInt(map.getWidth());
		int tempY = random.nextInt(map.getHeight());

		Resource e = null;
		
		if (isTileEmpty(tempX, tempY)) {
			try {
				e = Factory.newOre(tempX, tempY,
						random.nextInt(5) + 1);
			} catch (SlickException e1) {
				e1.printStackTrace();
			}
			if (e != null && WorldResources.getNumResources() < WorldResources.getMaxNumResources()) {
				WorldResources.addResource(e);
				add(e);
			}
		} else {
			addResource();
		}
	}

	/** Gets The Player */
	public static Player getPlayer() {
		return player;
	}

	/** Gets Current Map */
	public static TiledMap getMap() {
		return map;
	}

	/** Finds an entity at a particular x & y */
	public Entity find(float x, float y) {
		for (Entity entity : getEntities()) {
			if (entity.x == x && entity.y == y)
				return entity;
		}
		return null;
	}

	/** Checks if a particular tile is empty */
	public boolean isTileEmpty(float x, float y) {
		for (Entity entity : getEntities()) {
			if (entity.x == x && entity.y == y)
				return false;
		}
		return true;
	}

	public WorldResources getWRes() {
		return wResource;
	}

	public Vector<Resource> getRes() {
		return wResource.getResources();
	}

	public void addToRemoved(Entity e) {
		remove(e);
	}
}