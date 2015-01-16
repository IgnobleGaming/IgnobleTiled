package entities.resources;

import java.util.Random;

import org.newdawn.slick.SlickException;

import entities.Item;
import entities.Resource;

public class Fish extends Resource{


	// Resource ID //
	private static final int id = 2;

	// Experience for harvesting // 
	private int experience;
	
	// Random used for Grade and Exp //
	private Random rand;

	public Fish(float x, float y, int subId) throws SlickException {
		super(x, y);

		this.x = x * modifier;
		this.y = y * modifier;

		Tree.ID = getID();
		initFish(subId);

		depth = 0;
	}

	private void initFish(int subID) throws SlickException {

		switch (subID) {
		case 1:
			name = "Bait";
			break;
		case 2:
			experience = rand.nextInt(10) + 15;
			name = "Salmon";
			break;
		case 3:
			experience = rand.nextInt(10) + 25;
			name = "Tuna";
			break;
		case 4:
			experience = rand.nextInt(10) + 50;
			name = "Lobster";
			break;
		case 5:
			experience = rand.nextInt(10) + 75;
			name = "SwordFish";
		default:
			break;
		}

		init(subID);
	}
	
	 public int getID(){
		 return id;
	 }
}
