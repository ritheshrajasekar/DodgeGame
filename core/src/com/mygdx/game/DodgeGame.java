package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.com.mygdx.game.screens.MainGameScreen;
import com.mygdx.game.com.mygdx.game.screens.MenuScreen;

/**
 * Main game class.
 */
public class DodgeGame extends Game {

	public static final int WIDTH = 1500;
	public static final int HEIGHT = 1000;
	public static final String TITLE = "Dodge";
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
