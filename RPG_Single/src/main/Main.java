package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main {

	public static void initResources() throws SlickException {

	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.setBackground(Color.black);
	}

	public static void main(String[] args) throws Exception {
		AppGameContainer app = new AppGameContainer(new GameStateController());
		app.setDisplayMode(1280, 720, false);
		app.setTargetFrameRate(144);
		app.start();
	}
}
