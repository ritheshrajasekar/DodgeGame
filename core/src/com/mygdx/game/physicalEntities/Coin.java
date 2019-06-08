package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Coin {
    Texture coin;
    TextureRegion animationFrames[];
    Animation animation;
    float elapsedTime;
    int xCoord;
    int yCoord;

    public Coin(){
        coin = new Texture("dodgeBigCoin.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(coin,8,8);
        xCoord = (int)(8 * Math.random());
        yCoord = (int)(8 * Math.random());
        animationFrames = new TextureRegion[24];
        int index = 0;

        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 3; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }

        animation = new Animation(1f/24f,animationFrames);
    }
    public int getX(){
        return xCoord;
    }
    public int getY() { return yCoord; }
    public int xCoordToPixel(int x) {
        return x * PLAYER_MOVE_DISTANCE + GRID_OFFSET_X;
    }

    public int yCoordToPixel(int y) {
        return y * PLAYER_MOVE_DISTANCE + GRID_OFFSET_Y;
    }

    public void update(){
        elapsedTime += Gdx.graphics.getDeltaTime();
    }
    public void render(SpriteBatch batch){

        batch.draw(animation.getKeyFrame(elapsedTime,true), xCoordToPixel(xCoord) + GRID_CORNER_SIZE, yCoordToPixel(yCoord) + GRID_CORNER_SIZE, COIN_SIZE, COIN_SIZE);
    }


}
