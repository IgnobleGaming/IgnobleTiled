package menus;

import it.randomtower.engine.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import world.GameWorld;
import entities.inventory.Inventory;
import entities.players.Player;

public class InventoryMenu extends World {

	private Image inventoryImage;

	private float currentX;
	private float currentY;

	private Inventory inventory;

	private Player player;

	public InventoryMenu(int id, GameContainer container) {
		super(id, container);
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {

		inventoryImage = new Image("Images/inventory.png");

		player = GameWorld.getPlayer();
		inventory = player.inventory();
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		inventoryImage.draw(0, 0);

		renderItems(arg0, g, currentX, currentY);
		renderGrid(arg0, g, currentX, currentY);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {

		Input inputInventory = container.getInput();

		if (inputInventory.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(0);
		}
	}

	public int getID() {
		return 1;
	}

	private void renderGrid(GameContainer container, Graphics g, float currentX,
			float currentY) {

		currentX = (container.getScreenWidth() / 5) + 10;
		currentY = ((container.getScreenHeight() / 5) * 2) + 10;

		for (int u = 110; u < container.getScreenWidth() - 300; u += 64) {
			g.drawLine(u, 190, u, container.getScreenHeight() - 264);
		}
		for (int y = 190; y < container.getScreenHeight() - 200; y += 64) {
			g.drawLine(110, y, container.getScreenWidth() - 306, y);
		}
	}

	private void renderItems(GameContainer container, Graphics g, float currentX,
			float currentY) throws SlickException {

		// currentX = startX;
		// currentY = startY;
		if (inventory.size() > 0) {
			for (int i = 0; i < inventory.size(); i++) {
				inventory.get(i).x = currentX;
				inventory.get(i).y = currentY;

				inventory.get(i).render(container, g);

				if ((i + 1) % 10 == 0) {
					currentX = (container.getScreenWidth() / 5) + 10;
					currentY += 64;
				} else {
					currentX += 64;
				}
			}
		}
	}
}