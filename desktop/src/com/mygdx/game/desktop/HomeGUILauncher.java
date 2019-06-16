// this class launches the game by creating a new object of DodgeGameBenchmarks for benchmarks
// this class was created by the Rithesh Rajasekar


package com.mygdx.game.desktop;

import Benchmarks.DodgeGameBenchmarks;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class HomeGUILauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new DodgeGameBenchmarks(), config);
        config.foregroundFPS = 30;
        config.title = DodgeGameBenchmarks.TITLE;
        config.width = DodgeGameBenchmarks.WIDTH;
        config.height = DodgeGameBenchmarks.HEIGHT;
        config.resizable = false;
    }
}