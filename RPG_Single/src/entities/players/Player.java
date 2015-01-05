package entities.players;

import it.randomtower.engine.World;
import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import skill.Skill;
import world.GameWorld;
import entities.Item;
import entities.Mob;
import entities.Resource;
import entities.players.skills.SkillSet;
import entities.resources.WorldResources;

public class Player extends Mob {

	/** TiledMap necessary for boundary determination */
	private TiledMap map;

	/***/
	private GameWorld gameworld;

	/** Sprite sheet for all the different Movements */
	private SpriteSheet movement;

	/** Sprite Sheet for all the different Harvests */

	/** Current Animation */
	private Animation anim;

	/** X & Y of object to be interacted with */
	private float intX;
	private float intY;

	/** Direction the player is facing */
	private int direction = -1;
	
	/** Player's Skills */
	private SkillSet skills;

	@SuppressWarnings("unused")
	private int height;
	@SuppressWarnings("unused")
	private int width;

	// Movement Controls //
	private static final String UP = "up";
	private static final String DOWN = "down";
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	private static final String INTERACT = "interact";

	public Player(float x, float y, int clothing) throws SlickException {
		super(x, y, false);

		name = "Cocoa";

		// anim = new Animation(butts, 2);

		this.x = x * modifier;
		this.y = y * modifier;

		setGraphic(getClassGraphic(clothing));

		depth = 0;

		defineControls();
	}

	/**
	 * Defines User Controls
	 */
	private void defineControls() {
		define(UP, Input.KEY_W, Input.KEY_UP);
		define(DOWN, Input.KEY_S, Input.KEY_DOWN);
		define(LEFT, Input.KEY_A, Input.KEY_LEFT);
		define(RIGHT, Input.KEY_D, Input.KEY_RIGHT);
		define(INTERACT, Input.KEY_E);
	}

	/**
	 * Non Combat Movement
	 * 
	 * @throws InterruptedException
	 */
	private void nonCombatControls() {
		if (pressed(LEFT)) {
			direction = 0;
			doMove(direction);
			this.intX = (getX() - modifier);
			this.intY = getY();
		}
		if (pressed(RIGHT)) {
			direction = 1;
			doMove(direction);
			this.intX = (getX() + modifier);
			this.intY = getY();
		}
		if (pressed(UP)) {
			direction = 2;
			doMove(direction);
			this.intX = getX();
			this.intY = (getY() - modifier);
		}
		if (pressed(DOWN)) {
			direction = 3;
			doMove(direction);
			this.intX = getX();
			this.intY = (getY() + modifier);
		}
		if (pressed(INTERACT)) {
			Entity e = this.gameworld.find(getDirX(), getDirY());

			if (e != null) {

				if (e instanceof Npc)
					talk((Npc) e);

				if (e instanceof Resource) {
					if(harvest((Resource) e)){
						
						int tempId = ((Resource) e).getId();
						int exp = Resource.getExp();
						
						switch(tempId){
						case 1:
							
							SkillSet.getSmithing().addExp(exp);
						}
					}
				}
			}
		}
	}

	private void doMove(int direction) {
		switch (direction) {
		case 0:
			if (collide("item", x - modifier, y) != null
					|| collide("item", x - modifier, y) == null)
				move(-1, 0);
			break;
		case 1:
			if (collide("item", x + modifier, y) != null)
				move(1, 0);
			else
				move(1, 0);
			break;
		case 2:
			if (collide("item", x, y - modifier) != null)
				move(0, -1);
			else
				move(0, -1);
			break;
		case 3:
			if (collide("item", x, y + modifier) != null)
				move(0, 1);
			else
				move(0, 1);
			break;
		}
	}

	public boolean harvest(Resource resource) {
		/**
		 * change animation depending on instanceof object for(int i = 0; i <
		 * resource.getInvSize(); i++){ if(!inventory.isFull()){
		 * 
		 * } else {
		 * 
		 * } }
		 */

		if (resource.harvest()) {
			return true;
		} else {
			return false;
		}
	}

	public void talk(Npc npc) {
		// TODO Auto-generated method stub

	}

	/** Player Update Loop */
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

		nonCombatControls();
	}

	/** Item Collision detection */
	@Override
	public void collisionResponse(Entity itemPickup) {
		if (itemPickup instanceof Item) {
			Item item = (Item) itemPickup;
			this.pickUp(item);
		}
	}

	/** Name Getter */
	public String getName() {
		return name;
	}

	/** Returns the X coordinate the player is facing */
	public float getDirX() {
		return intX;
	}

	/** Returns the Y coordinate the player is facing */
	public float getDirY() {
		return intY;
	}

	public void setGameWorld(GameWorld gameWorld) {
		this.gameworld = gameWorld;
	}
}