//displays the coin counter
//created by Rithesh Rajasekar and Matt Seng
package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level.COIN_SIZE;

public class CoinCounter {
    private final int X_COORD = 335;
    private final int Y_COORD = 150;
    private final int SYMBOL_X_OFFSET = 66;
    private final int NUMBER_X_OFFSET = 116;
    private final int NUMBER_Y_OFFSET = 53;

    private String coinCountString;
    private int coins;
    Texture coin;
    TextureRegion animationFrames[];
    Animation animation;
    float elapsedTime;

    public CoinCounter() {
        coins = 0;
        coinCountString = "" + coins;
        coin = new Texture("sprites/dodgeBigCoin.png");

        //creates the animation
        TextureRegion[][] tmpFrames = TextureRegion.split(coin, 8, 8);
        animationFrames = new TextureRegion[24];
        int index = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }
        animation = new Animation(1f / 24f, animationFrames);
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

        batch.draw(animation.getKeyFrame(elapsedTime, true), X_COORD, Y_COORD, COIN_SIZE, COIN_SIZE);
        //draws coins and number of coins
        font.setColor(Color.YELLOW);
        font.getData().setScale(4f);
        font.draw(batch, "x", X_COORD + SYMBOL_X_OFFSET, Y_COORD + NUMBER_Y_OFFSET);
        font.draw(batch, getCoinCountString(), X_COORD + NUMBER_X_OFFSET, Y_COORD + NUMBER_Y_OFFSET);
    }
}