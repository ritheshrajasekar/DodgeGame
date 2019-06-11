package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Coin extends Entity{
    Texture coin;
    TextureRegion animationFrames[];
    Animation animation;
    float elapsedTime;

    public Coin(int xVal, int yVal){
        coin = new Texture("sprites/dodgeBigCoin.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(coin,8,8);
        x = xVal;
        y = yVal;
        animationFrames = new TextureRegion[24];
        int index = 0;

        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 3; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }

        animation = new Animation(1f/24f,animationFrames);
    }

    public void update() {
        elapsedTime += Gdx.graphics.getDeltaTime();
    }

    public void render(SpriteBatch batch){
        batch.draw(animation.getKeyFrame(elapsedTime,true), xCoordToPixel(x), yCoordToPixel(y), COIN_SIZE, COIN_SIZE);
    }
}