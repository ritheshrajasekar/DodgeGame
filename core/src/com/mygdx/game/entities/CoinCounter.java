package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.DodgeGame;

import static com.mygdx.game.com.mygdx.game.screens.Level.COIN_SIZE;


public class CoinCounter {
    private String coinCountString;
    private int coins;
    //private Texture counter =  new Texture("sprites/timer.png");
    Texture coin;
    TextureRegion animationFrames[];
    Animation animation;
    float elapsedTime;

    public CoinCounter() {
        coins = 0;
        coinCountString = "" + coins;
        coin = new Texture("sprites/dodgeBigCoin.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(coin,8,8);
        animationFrames = new TextureRegion[24];
        int index = 0;

        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 3; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }
        animation = new Animation(1f/24f,animationFrames);
    }

    public void update(int c) {
        coins = c;
        coinCountString = "" + coins;
        elapsedTime += Gdx.graphics.getDeltaTime();
    }

    public String getCoinCountString() {
        return coinCountString;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        batch.end();
        batch.begin();
       // batch.draw(counter,(int)(DodgeGame.WIDTH * 0.25) - 100, DodgeGame.HEIGHT/2 - 300,200 ,200 );
        batch.draw(animation.getKeyFrame(elapsedTime,true),230, DodgeGame.HEIGHT/2 - 243, COIN_SIZE, COIN_SIZE);

        font.setColor(Color.YELLOW);
        font.getData().setScale(4f);
        font.draw(batch, "x", (int)(DodgeGame.WIDTH * 0.20) + 40, DodgeGame.HEIGHT/2 + -190);
        font.draw(batch, getCoinCountString(), (int)(DodgeGame.WIDTH * 0.20) + 90, DodgeGame.HEIGHT/2 + -190);
    }
}