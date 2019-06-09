package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DodgeGame;


public class Timer {
    private int worldTimer;
    private String worldTimerString;
    private float timeCount;
    private Texture clock =  new Texture("timer.png");

    public Timer(int time) {
        worldTimer = time;
        worldTimerString = "" + worldTimer;
        timeCount = 0;
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            worldTimerString = "" + worldTimer;
            timeCount = 0;
        }
    }

    public String getWorldTimerString() {
        return worldTimerString;
    }

    public int getWorldTimer() {
        return worldTimer;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        batch.begin();
        batch.draw(clock,(int)(DodgeGame.WIDTH * 0.25) - 100, DodgeGame.HEIGHT/2 - 100,200 ,200 );

        font.setColor(Color.GREEN);
        font.getData().setScale(4f);
        font.draw(batch, getWorldTimerString(), (int)(DodgeGame.WIDTH * 0.20) + 35, DodgeGame.HEIGHT/2 + 10);
    }
}