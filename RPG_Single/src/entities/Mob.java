package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.inventory.Inventory;
import world.GameWorld;
import it.randomtower.engine.entity.Entity;

public class Mob extends Entity {

	/** Universal Truths */
	protected static final float modifier = 64;
	protected static final double modDouble = 64.0;

	/** Is this Mob Immortal */
	@SuppressWarnings("unused")
	private boolean immortal;

	/** Mob's Current amount of Health */
	private int health;
	
	/** Mob's Maximum amount of Health */
	private int maxHealth;

	/** Is the Mob moving */
	private boolean moving;

	/** Mob's inventory */
	protected Inventory inventory;

	//temp
	protected Image dragoonImage;
	// temp
	protected Image eDragoonImage;

	public Mob(float x, float y, boolean immortal) throws SlickException {
		super(x, y);

		this.immortal = immortal;

		defineImages();

		addType("SOLID");
		setHitBox(0, 0, (int) modifier, (int) modifier);

		depth = 0;
	}

	/** UPDATE THIS BITCH */
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);
	}

	/** Takes in a direction to move the player */
	public void move(float xa, float ya) {
		float xc = x + (xa * modifier);
		float yc = y + (ya * modifier);

		if (collide("SOLID", xc, yc) == null && collide("RESOURCE", xc, yc) == null && xc >= 0 && yc >= 0
				&& yc < GameWorld.getMap().getHeight() * modifier
				&& xc < GameWorld.getMap().getWidth() * modifier) {
			x = xc;
			y = yc;
		}
	}

	/**
	 * All character info is set here
	 * 
	 * @param id
	 * @param name
	 * @param health
	 */
	public void setCharacterInfo(int id, String name, int experience, int health) {

		changeClass(id);
		changeClassGraphic(id);
		this.name = name;
		this.maxHealth = health;
	}

	/** Changes the value of the Mobs class */
	public void changeClass(int id) {
		if (id > 0 && id < 10) {
			changeClassGraphic(id);
		}
	}

	/** To be deprecated or heavily modified */
	private Image changeClassGraphic(int id) {
		switch (id) {
		case 1:
			// setGraphic to warrior
			return dragoonImage;
		case 2:
			return eDragoonImage;
		default:
			return changeClassGraphic(1);
		}
	}

	public Image getClassGraphic(int id) {
		return changeClassGraphic(id);
	}

	/**
	 * Sets all the images
	 * 
	 * @throws SlickException
	 */

	public void defineImages() throws SlickException {
		dragoonImage = new Image("Images/Entities/Player Classes/Player3.png");
		eDragoonImage = new Image(
				"Images/Entities/Enemy Humanoid/Enemy_Dragoon.png");
	}

	public void setSpriteSheet() {

	}

	/**
	 * Item Pickup
	 * 
	 * @param item
	 *            - Item to pick up
	 */

	public void pickUp(Item item) {
		if (this.inventory.isFull() || item == null) {
		} else {
			world.remove(item);
			this.inventory.add(item, false);
		}
	}

	/** Returns this Mob's inventory */
	public Inventory inventory() {
		return inventory;
	}

	/** Function to Get Players X Position for Camera */
	public float getX() {
		return x;
	}

	/** Function to Get Players X Position for Camera */
	public float getY() {
		return y;
	}

	/**
	 * Moves the character to a new Location
	 * 
	 * @param xa
	 *            - new player x Position
	 * @param ya
	 *            - new player y Position
	 */
	public void reposition(int xa, int ya) {
		x = xa;
		y = ya;
	}
}
