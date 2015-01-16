package entities.resources;

import java.util.Random;

import org.newdawn.slick.SlickException;
import entities.Item;
import entities.Resource;

public class Ore extends Resource {

	/** Resource ID */
	private static final int id = 1;
	
	/** Random used for Grade and Exp */
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
			skillReq = 0;
			experience = 10;
			name = "Stone";
			break;
		case 2:
			skillReq = 15;
			experience += 15;
			name = "Bronze";
			break;
		case 3:
			skillReq = 30;
			experience += 25;
			name = "Iron";
			break;
		case 4:
			skillReq = 50;
			experience += 50;
			name = "Coal";
			break;
		case 5:
			skillReq = 70;
			experience += 190;
			name = "Mithril";
		default:
			break;
		}

		init(subID);
	}
	
	public int getID() {
		return id;
	}
}
