package factory;

import java.util.Random;

import org.newdawn.slick.SlickException;

import world.GameWorld;
import world.Tile;
import entities.Item;
import entities.Mob;
import entities.Resource;
import entities.players.Player;
import entities.resources.Fish;
import entities.resources.Herb;
import entities.resources.Ore;
import entities.resources.Tree;

public class Factory {

	private GameWorld world;
	
	private int tiles [] [] = new int [30] [30];
	
	private static Random rand = new Random();
	
	public static Player loadPlayer() {
		return null;
	}

	public static Mob newMob() {
		return null;
	}
	
	/**
	 * 
	 * @param ID
	 * @return
	 * @throws SlickException 
	 */
	public static Resource newRandResource(int x, int y, int ID) throws SlickException{
		
		int temp = rand.nextInt(4) + 1;
		
		Resource res = null;
		
		switch (temp){
		case 1:
			res = new Ore(x, y, rand.nextInt());
			return res;
		case 2:
			res = new Fish(x, y, rand.nextInt());
			return res;
		case 3:
			res = new Herb(x, y, rand.nextInt());
			return res;
		case 4:
			res = new Tree(x, y, rand.nextInt());
			return res;
		}
		return res;
	}
	
	public static Ore newOre(int x, int y, int subID) throws SlickException{
		Ore ore = new Ore(x, y, subID);
		return ore;
	}
	
	public static Tree newTree(int x, int y, int subID) throws SlickException{
		Tree tree = new Tree(x, y, subID);
		return tree;
		
	}
	
	public static Fish newFish(int x, int y, int subID) throws SlickException{
		Fish fish = new Fish(x, y, subID);
		return fish;
	}
	
	public static Herb newHerb(int x, int y, int subID) throws SlickException{
		Herb herb = new Herb(x, y, subID);
		return herb;
	}

	public static Item newItem(float x, float y, int mID, int ID, int subID) {
		return null;
	}

	public static Tile newTile(float x, float y, int ID, int subID) {
		return null;
	}
}
