package entities;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import entities.inventory.Inventory;
import it.randomtower.engine.entity.Entity;

public class Resource extends Entity {

	/** UNIVERSAL TRUTH */
	protected static final float modifier = 64;

	/** Resource ID (i.e. Tree, ore, etc) */
	protected static int ID;

	/** Individual ID (i.e Iron, Steel, Mithril for Ore) */
	protected int subID;

	/** Skill required to Harvest */
	protected int skillReq;

	/** Quality of Resource */
	private float grade;
	
	/** Experience from Harvesting */
	protected static int experience;

	/** Sprite Sheet Image */
	protected Image sSImage;

	/** Loaded Sprite Sheet */
	protected SpriteSheet spriteSheet;

	/** Resource Inventory */
	protected Inventory inventory;

	/** Harvested */
	protected boolean isHarvested;

	/** Random` for ID Generation */
	protected Random newRand = new Random(System.currentTimeMillis());

	/** CONSTRUCTOR **/
	public Resource(float x, float y) {
		super(x, y);

		addType("RESOURCE");
		setHitBox(0f, 0f, (int) modifier, (int) modifier); // Hit box
		setGrade();
	}

	/** Begins Defining the Resource */
	public void init(int subId) throws SlickException {
		setSubID(subId);
		setInfo();
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);
		
		if(isHarvested)
			this.destroyRes();
	}
	
	/**
	 * Sets all Ore info at Instantiation
	 * 
	 * @throws SlickException
	 */
	private void setInfo() throws SlickException {

		isHarvested = false;

		inventory = new Inventory(5); //Inventory for Resource Items

		switch (ID) {
		case 1: // Ore
			sSImage = new Image("Images/Resources/Ore.png");
			spriteSheet = new SpriteSheet(sSImage, 64, 64);
			break;
		case 2: // TIER 2
			sSImage = new Image("Images/Resources/Ore.png");
			spriteSheet = new SpriteSheet(sSImage, 64, 64);
			break;
		case 3: // TIER 3
			sSImage = new Image("Images/Resources/Ore.png");
			spriteSheet = new SpriteSheet(sSImage, 64, 64);
			break;
		case 4: // TIER 4
			sSImage = new Image("Images/Resources/Ore.png");
			spriteSheet = new SpriteSheet(sSImage, 64, 64);
			break;
		default:
			isHarvested = true;
			break;
		}
		if (!isHarvested) {
			setGraphic(spriteSheet.getSubImage(
					(int) (subID * modifier - modifier), 0));
		}
	}

	/** Sets the SubID */
	private void setSubID(int subId) {
		subID = subId;
	}
	
	@Override
	public void removedFromWorld(){
		this.collidable = false;
		this.destroy();
	}

	/** Randomly Generated Resource Quality */
	private void setGrade() {
		grade = newRand.nextInt(100);
		if (grade < 50) {
			grade += newRand.nextInt(20);
		}
	}

	public boolean harvest() {
		isHarvested = true;
		
		return isHarvested;
	}
	
	public void destroyRes(){
		this.destroy();
	}

	/** Returns Quality of resource */
	public float getGrade() {
		return grade;
	}
	
	public static int getExp(){
		return experience;
	}
	
	public int getReq(){
		return skillReq;
	}

	/** Gets Resource name */
	public String getName() {
		return this.name;
	}

	/** Gets the number of resources in the node */
	public int getInvSize() {
		return inventory.size();
	}

	/** Gets the Node's inventory */
	public Inventory getInv() {
		return inventory;
	}
	
	public boolean isHarvested(){
		return isHarvested;
	}
	
	public int getId(){
		return ID;
	}
}
