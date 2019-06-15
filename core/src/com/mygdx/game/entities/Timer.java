//class for countdown timer which is used various timses throughout the game
//created by Rithesh Rajasekar
package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.com.mygdx.game.screens.GameOver;


public class Timer {
    private double worldTimer;
    private String worldTimerString;
    private float timeCount;
    private Texture clock = new Texture("sprites/timer.png");
    private final int Y_OFFSET = -350;

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
        batch.draw(clock, (int) (DodgeGame.WIDTH * 0.25) - 100, DodgeGame.HEIGHT / 2 + Y_OFFSET, 200, 200);

        font.setColor(Color.GREEN);
        font.getData().setScale(4f);
        if (getWorldTimer() >= 10)
            font.draw(batch, getWorldTimerString(), (int) (DodgeGame.WIDTH * 0.20) + 38, DodgeGame.HEIGHT / 2 + Y_OFFSET + 125);
        else
            font.draw(batch, getWorldTimerString(), (int) (DodgeGame.WIDTH * 0.20) + 53, DodgeGame.HEIGHT / 2 + Y_OFFSET + 125);

        //sets the time for the gave over screen
        GameOver.time = getWorldTimerString();
    }
}