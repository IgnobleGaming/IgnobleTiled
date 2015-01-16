package entities.resources;

import java.util.Random;

import org.newdawn.slick.SlickException;

import entities.Item;
import entities.Resource;

public class Tree extends Resource{

	// Resource ID //
	private static final int id = 4;

	// Experience for harvesting // 
	private int experience;
	
	// Random used for Grade and Exp //
	private Random rand;

	public Tree(float x, float y, int subId) throws SlickException {
		super(x, y);

		this.x = x * modifier;
		this.y = y * modifier;

		Tree.ID = getID();
		initTree(subId);

		depth = 0;
	}

	private void initTree(int subID) throws SlickException {

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

	public int getID() {
		return id;
	}
}
