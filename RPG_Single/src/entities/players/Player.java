package entities.players;

import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import skill.Skill;
import skill.skills.Alchemy;
import skill.skills.Blacksmith;
import skill.skills.Botony;
import skill.skills.Cooking;
import skill.skills.Fishing;
import skill.skills.Fletching;
import skill.skills.LeatherWorking;
import skill.skills.Mining;
import skill.skills.Skinning;
import skill.skills.Tailoring;
import skill.skills.WoodCutting;
import world.GameWorld;
import entities.Item;
import entities.Mob;
import entities.Resource;
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
	private Skill skills[] = new Skill[12];
	private int skillExp[] = new int[12];
	private int skillLevels[] = new int[12];
	private final int maxEL = 100;

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

		map = GameWorld.getMap();

		setGraphic(getClassGraphic(clothing));

		depth = 0;

		init();
	}

	/** Player Update Loop */
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

		nonCombatControls();
		updateSkillExp();
	}

	/** Definitions */
	public void init() {

		defineControls();
		defineSkills();
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
	 * Defines User Skills
	 */
	private void defineSkills() {
		skills[0] = null;
		skills[1] = new Mining();
		skills[2] = new Fishing();
		skills[3] = new Botony();
		skills[4] = new WoodCutting();
		skills[5] = new Skinning();
		skills[6] = new Blacksmith();
		skills[7] = new Cooking();
		skills[8] = new Alchemy();
		skills[9] = new Fletching();
		skills[10] = new LeatherWorking();
		skills[11] = new Tailoring();
	}

	private void updateSkillExp() {
		for (int i = 0; i < skillExp.length; i++) {

			if (skillLevels[i] >= maxEL) {
				skillLevels[i] = maxEL;
				continue;
			} else {
				if (skillExp[i] >= maxEL) {
					skillLevels[i] = (int) Math.floor(skillExp[i] / maxEL);
					skillExp[i] = skillExp[i] % maxEL;
				}
			}
		}
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
					if (skillLevels[((Resource) e).getId()] >= ((Resource) e)
							.getReq()) {
						if (harvest((Resource) e)) {

							int tempId = ((Resource) e).getId();
							int exp = ((Resource) e).getExp();
							skills[tempId].addExp(exp);
							WorldResources.removeResource((Resource) e);
						}
					} else {
						System.out.println("Ain't enough skill, nuigguh");
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

		if (resource.harvest()) {

			int skillId = resource.getId();
			int exp = resource.getExp();

			skills[skillId].addExp(exp);
			WorldResources.removeResource(resource);
			return true;
		} else {
			return false;
		}
	}

	public void talk(Npc npc) {
		// TODO Auto-generated method stub

	}

	/** Item Collision detection */
	@Override
	public void collisionResponse(Entity itemPickup) {
		if (itemPickup instanceof Item) {
			Item item = (Item) itemPickup;
			this.pickUp(item);
		}
	}

	/** Sets the GameWorld for the player */
	public void setGameWorld(GameWorld gameWorld) {
		this.gameworld = gameWorld;
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
}