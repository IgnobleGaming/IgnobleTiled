package entities;

import org.newdawn.slick.SlickException;

import it.randomtower.engine.entity.Entity;

public class Item extends Entity {

	// UNIVERSAL TRUTHS //
	private static int modifier = 64;

	// ITEM NAMES //
	public String name;
	public static final String ITEM = "item";
	public static final String SOLID = "SOLID";
	public static final String POTION_RED = "red Potion";

	public Item(float x, float y, int ID, boolean collidable)
			throws SlickException {
		super(x, y);

		if (collidable) {
			addType(ITEM);
			setHitBox(0, 0, modifier, modifier);
		} else {
			collidable = false;
		}

		depth = 0;
	}
}