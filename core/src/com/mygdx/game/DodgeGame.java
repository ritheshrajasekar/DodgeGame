//Author : Rithesh

package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.com.mygdx.game.screens.Start;
import com.mygdx.game.utilities.FileStreaming;


//Creating a DodgeGame which is called in your other screens where you create an object of type DodgeGame and also access it's public static fields.
public class DodgeGame extends Game {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String TITLE = "Dodge";

    public SpriteBatch batch;
    public BitmapFont font;

    @Override

    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new Start(this));
        font = new BitmapFont();

        FileStreaming.read();
    }

    @Override
    public void render() {
        super.render();
    }
}
