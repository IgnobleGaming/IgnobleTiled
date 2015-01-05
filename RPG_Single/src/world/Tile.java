package world;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *   This class is the framework for the individual
 * tiles that populate the World.
 * 
 * @author Collin Mabus
 *  
 */
public class Tile {
	
	/** UNIVERSAL TRUTHS */
	private static final float modifier = 32;
	private static final float scale = 2;
	
	/** X & Y position */
	private float x;
	private float y;
	
	/** Tile Image */
	private Image tileImage;
	
	/** SpriteSheet */
	private Image sheetImage;
	private SpriteSheet sheet;
	
	/** BOOLEANS */
	private boolean occupied;	  // Is tile occupied?
	private boolean traversable;  // Is tile traversable?
	private boolean isWater;      // Is it a Water Tile?
	private boolean isRock;       // Is it a Rock Tile?
	
	/** Random */
	Random rand = new Random(System.currentTimeMillis());
	
	/**
	 * Tile Constructor
	 * @param x - X position in world
	 * @param y - Y position in world
	 * @param fID - file ID used to navigate to proper image
	 * @param bID - Y value on spritesheet
	 * @param position - X value on spritesheet
	 * @throws SlickException
	 */
	public Tile(float x, float y, int fID, int bID, int position) throws SlickException{
		
		this.x = x * modifier;
		this.y = y * modifier;
		
		setBooleans(bID); // call to set movement id's
		
		if(fID == bID){
			setImages((int) (fID * modifier), (int) (fID * modifier), (int) (rand.nextInt(8) * modifier));
		} else {
			setImages((int) (fID * modifier), (int) (fID * modifier), (int) (position * modifier));
		}
		
		tileImage.draw(this.x, this.y);
	}
	
	/** Defines each type of block a set of booleans which will determine movement */
	private void setBooleans(int backID){
		switch(backID){
		case 1: // Grass
			traversable = true;
			isWater = false;
			isRock = false;
			break;
		case 2: // Water
			traversable = false;
			isWater = true;
			isRock = false;
			break;
		case 3: // Dirt
			traversable = true;
			isWater = false;
			isRock = false;
			break;
		case 4: // Sand
			traversable = true;
			isWater = false;
			isRock = false;
			break;
		case 5: // Stone
			traversable = false;
			isWater = false;
			isRock = true;
			break;
		case 6: // Cobblestone
			traversable = true;
			isWater = false;
			isRock = false;
			break;
		case 7: // Brick
			traversable = true;
			isWater = false;
			isRock = false;
			break;
		default:
			break;
		}
	}
	
	/** Gets the Image for this Tile */
	private void setImages(int foreID, int backID, int pos) throws SlickException{
		sheetImage = new Image("Images/Tiles/set" + foreID + ".png");
		sheet = new SpriteSheet(sheetImage, (int) modifier, (int) modifier);
		tileImage = sheet.getSubImage((int) (pos * modifier), (int) (backID * modifier)).getScaledCopy(scale);
	}
	
	/** Returns true if an object is on this Tile */
	public boolean isOccupied(){
		return occupied;
	}
	
	/** Returns true if Mob can move across this Tile */
	public boolean isTraversable(){
		return traversable;
	}
	
	/** Is this Tile a rock? */
	public boolean isRock(){
		return isRock;
	}
	
	/** Is this Tile water? */
	public boolean isWater(){
		return isWater;
	}
	
	/** Returns True if tile has a resource */
	public boolean hasResource(){
		return true;
	}
	
	/** Returns the Tiles X position in World */
	public float getX(){
		return x;
	}
	
	/** Returns the Tiles Y position in World */
	public float getY(){
		return y;
	}
}
