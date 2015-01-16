package entities.resources;

import java.util.Vector;

import world.GameWorld;
import entities.Resource;

public class WorldResources {

	private final float modifier = 64;
	
	private static GameWorld gameWorld;
	
	private static int numResources = 0;

	private static int maxResources = 10;
	
	private int numOre = 0;
	private int numTree = 0;
	private int numFish = 0;
	private int numHerb = 0;
	
	private int node [] [] = new int [20] [20];

	private static Vector<Resource> resources = new Vector<Resource>(0, 1);

	public void init(GameWorld gw){
		gameWorld = gw;
	}
	
	/** Adds a resource to the world */
	public static void addResource(Resource resource) {
		if (numResources < maxResources) {
			
			try{
				resources.addElement(resource);
			} catch (Exception e){}
			
			updateResources();
		}
	}

	/***/
	public static void removeResource(Resource resource) {
		try{
		resources.removeElement(resource);
		} catch (Exception e){}
		
		updateResources();
	}

	/***/
	public static void harvest(Resource e){
		
		boolean contains = false;
		
		if(resources.contains(e)){
			contains = true;
		}
		
		if(contains){
			e.harvest();
			removeResource(e);
			gameWorld.addToRemoved(e);
		}
	}
	
	/***/
	public static Vector<Resource> getResources() {
		return resources;
	}

	/****/
	public static void updateResources() {
		numResources = resources.size();
	}

	public static int getMaxNumResources() {
		return maxResources;
	}

	public static int getNumResources() {
		return numResources;
	}
}
