package entities.resources;

import java.util.Random;

import org.newdawn.slick.SlickException;

import entities.Item;
import entities.Resource;

public class Herb extends Resource{

	/** Resource ID */
	private static final int id = 3;
	
	/** Rand Gen for Exp and Grade */
	private Random rand;

	public Herb(float x, float y, int subId) throws SlickException {
		super(x, y);

		this.x = x * modifier;
		this.y = y * modifier;

		Tree.ID = getID();
		initHerb(subId);

		depth = 0;
	}

	private void initHerb(int subID) throws SlickException {

		switch (subID) {
		case 1:
			name = "Tree";
			break;
		case 2:
			experience = rand.nextInt(10) + 15;
			name = "Oak";
			break;
		case 3:
			experience = rand.nextInt(10) + 25;
			name = "Willow";
			break;
		case 4:
			experience = rand.nextInt(10) + 50;
			name = "Maple";
			break;
		case 5:
			experience = rand.nextInt(10) + 75;
			name = "Larch";
		default:
			break;
		}

		init(subID);
	}

	private void addHerb(Item item, int amt) {
		for (int i = 0; i < amt; i++) {
			inventory.add(item, false);
		}
	}

	public static int getID() {
		return id;
	}
}
