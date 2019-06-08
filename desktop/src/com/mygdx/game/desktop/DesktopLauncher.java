package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DodgeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new DodgeGame(), config);
		config.foregroundFPS = 30;
		config.title = DodgeGame.TITLE;
		config.width = DodgeGame.WIDTH;
		config.height = DodgeGame.HEIGHT;
		config.resizable = true;
	}
}
