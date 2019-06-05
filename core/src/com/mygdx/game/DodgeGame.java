package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.com.mygdx.game.screens.MainGameScreen;

/**
 * Main game class.
 */
public class DodgeGame extends Game {

	public static final int WIDTH = 2000;
	public static final int HEIGHT = 1500;
	public static final String TITLE = "Dodge";
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
