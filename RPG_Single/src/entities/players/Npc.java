package entities.players;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import entities.Mob;
import it.randomtower.engine.entity.Entity;

public class Npc extends Mob {

	public boolean hostile = false;
	private boolean mobile = true;

	// ANIMATION FOR LATER
	// private Image walk1 [];
	// private Animation walking;

	// Computer AI //
	public String targetName = null;

	// Mob SpriteSheet //

	Random rand = new Random(System.currentTimeMillis());

	public Npc(float x, float y, int ID, boolean immortal)
			throws SlickException {
		super(x, y, immortal);

		this.x = x * modifier;
		this.y = y * modifier;

		if (ID > 50) {
			hostile = true;
		} else if (ID < 50 && ID > 25) {
			hostile = false;
			mobile = true;
		} else if (ID < 25 && ID > 0) {
			hostile = false;
			mobile = false;
		} else if (ID < 0) {
			hostile = false;
			mobile = false;
		}
		/**
		 * setImages(); walking.setLooping(true);
		 */

		setGraphic(getClassGraphic(ID));
		depth = 0;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

		if (targetName == null) {
			findTarget();
		}
	}

	/**
	 * Getters for NPC Movement
	 */
	public boolean getHostile() {
		return hostile;
	}

	public boolean getMobile() {
		return mobile;
	}

	/**
	 * Getter for Hostile NPC Target
	 * 
	 * --Will eventually move to NPC AI
	 */
	public void findTarget() {
		for (Entity e : world.getEntities()) {
			if (e instanceof Player) {
				targetName = e.name;
			}
		}
	}

	public String getTarget() {
		return targetName;
	}
}