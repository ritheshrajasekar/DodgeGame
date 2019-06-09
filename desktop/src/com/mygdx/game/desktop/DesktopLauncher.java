package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.utilities.JSONHelper;

public class DesktopLauncher {

	public static void main (String[] arg) {
		JSONHelper helper = new JSONHelper();
		helper.write("LEVEL", "LEVEL1", "TRUE", true);

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new DodgeGame(), config);
		config.foregroundFPS = 30;
		config.title = DodgeGame.TITLE;
		config.width = DodgeGame.WIDTH;
		config.height = DodgeGame.HEIGHT;
		config.resizable = false;
	}
}
