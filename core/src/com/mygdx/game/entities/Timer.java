package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DodgeGame;


public class Timer {
    private double worldTimer;
    private String worldTimerString;
    private float timeCount;
    private Texture clock =  new Texture("sprites/timer2.png");

    public Timer(double time) {
        worldTimer = time;
        worldTimerString = "" + (int) worldTimer;
        timeCount = 0;
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 0.1) {
            worldTimer -= 0.1;
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
        batch.draw(clock,(int)(DodgeGame.WIDTH * 0.25) - 100, DodgeGame.HEIGHT/2 - 100,200 ,200 );

        font.setColor(Color.GREEN);
        font.getData().setScale(4f);
        font.draw(batch, getWorldTimerString(), (int)(DodgeGame.WIDTH * 0.20) + 45, DodgeGame.HEIGHT/2 + 25);
    }
}