package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DodgeGame;


public class CoinCounter {
    private String coinCountString;
    private int coins;
    //private Texture counter =  new Texture("coinCounter.png");
    private Texture counter =  new Texture("timer.png");

    public CoinCounter() {
        coins = 0;
        coinCountString = "" + coins;
    }

    public void update(int c) {
        coins = c;
        coinCountString = "" + coins;
    }

    public String getCoinCountString() {
        return coinCountString;
    }

    public int getCoinCount() {
        return coins;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        batch.end();
        batch.begin();
        batch.draw(counter,(int)(DodgeGame.WIDTH * 0.25) - 100, DodgeGame.HEIGHT/2 - 300,200 ,200 );

        font.setColor(Color.YELLOW);
        font.getData().setScale(4f);
        font.draw(batch, getCoinCountString(), (int)(DodgeGame.WIDTH * 0.20) + 35, DodgeGame.HEIGHT/2 + -190);
    }
}