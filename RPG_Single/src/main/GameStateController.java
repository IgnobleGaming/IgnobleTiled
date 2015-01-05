package main;

import java.io.IOException;

import it.randomtower.engine.ResourceManager;
import menus.InventoryMenu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import world.GameWorld;

public class GameStateController extends StateBasedGame {

	public static int ID = 0;
	public static int GO = 0;
	
	private static boolean resourcesInited;

	public GameStateController() {
		super("Delmor");

	}

	public void initStatesList(GameContainer container) throws SlickException {
		initResources();
		
		// addState(new Title(0,container));
		addState(new GameWorld(0, container));
		addState(new InventoryMenu(1, container));
	}

	public static void initResources() throws SlickException {
		if (resourcesInited){
			System.out.println("Done");
			return;
		}
		try {
			ResourceManager.loadResources("data/resources.xml");
		} catch (IOException e) {
			Log.error("failed to load ressource file 'data/resources.xml': "
					+ e.getMessage());
			throw new SlickException("Resource loading failed!");
		}

		resourcesInited = true;
	}
}