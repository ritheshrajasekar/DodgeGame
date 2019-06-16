// this class launches the game by creating a new object of DodgeGameScreen for benchmarks
// this class was created by the Rithesh Rajasekar


package com.mygdx.game.desktop;

import Benchmarks.DodgeGameScreen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class GameLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new DodgeGameScreen(), config);
        config.foregroundFPS = 30;
        config.title = DodgeGameScreen.TITLE;
        config.width = DodgeGameScreen.WIDTH;
        config.height = DodgeGameScreen.HEIGHT;
        config.resizable = false;
    }
}
