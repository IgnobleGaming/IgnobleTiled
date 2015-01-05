package entities.resources;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import entities.Item;
import entities.Resource;

public class Ore extends Resource {

	// Resource ID //
	private static final int id = 1;
	
	// Random used for Grade and Exp //
	private Random rand;

	public Ore(float x, float y, int subId) throws SlickException {
		super(x, y);

		this.x = x * modifier;
		this.y = y * modifier;

		Ore.ID = getID();
		initOre(subId);

		depth = 0;
	}

	private void initOre(int subID) throws SlickException {

		switch (subID) {
		case 1:
			experience = 10;
			name = "Stone";
			break;
		case 2:
			experience = rand.nextInt(10) + 15;
			name = "Bronze";
			break;
		case 3:
			experience = rand.nextInt(10) + 25;
			name = "Iron";
			break;
		case 4:
			experience = rand.nextInt(10) + 50;
			name = "Coal";
			break;
		case 5:
			experience = rand.nextInt(10) + 75;
			name = "Mithril";
		default:
			break;
		}

		init(subID);
	}

	private void addOre(Item item, int amt) {
		for (int i = 0; i < amt; i++) {
			inventory.add(item, false);
		}
	}
	
	public static int getID() {
		return id;
	}
}
