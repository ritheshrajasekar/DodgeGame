//class for countdown timer which is used various times throughout the game
//created by Rithesh Rajasekar and Matt Seng
package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.com.mygdx.game.screens.GameOver;

public class Timer {
    private final int X_COORD = 50;
    private final int Y_COORD = 50;
    private final int TIME_X_COORD = X_COORD + 74;
    private final int TIME_Y_COORD = Y_COORD + 125;
    private final int TIME_LESS_THAN_TEN_OFFSET = 15;

    private double worldTimer;
    private String worldTimerString;
    private float timeCount;
    private Texture clock = new Texture("sprites/timer.png");

    public Timer(double time) {
        worldTimer = time;
        worldTimerString = "" + (int) worldTimer;
        timeCount = 0;
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 0.1) {
            worldTimer -= 0.1;
            //rounds to the nearest tenth
            worldTimer = (Math.round(worldTimer * 10)) / 10.0;
            worldTimerString = "" + (int) worldTimer;
            timeCount = 0;
        }
    }

    public String getWorldTimerString() {
        return worldTimerString;
    }

    public double getWorldTimer() {
        return worldTimer;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        batch.begin();
        batch.draw(clock, X_COORD, Y_COORD, 200, 200);

        font.setColor(Color.GREEN);
        font.getData().setScale(4f);
        if (getWorldTimer() >= 10)
            font.draw(batch, getWorldTimerString(), TIME_X_COORD, TIME_Y_COORD);
        else
            font.draw(batch, getWorldTimerString(), TIME_X_COORD + TIME_LESS_THAN_TEN_OFFSET, TIME_Y_COORD);

        //sets the time for the gave over screen
        GameOver.time = getWorldTimerString();
    }
}