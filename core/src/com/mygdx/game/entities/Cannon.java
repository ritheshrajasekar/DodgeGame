package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Cannon extends Entity {
    public static final int SPEED = 500;

    private static Texture cannonTexture;
    private TextureRegion[] cannonAnimationFrames;
    private Animation cannonAnimation;
    private float elapsedTime;
    public boolean isOnScreen;
    public boolean spawned;

    //x and y offset here are used to animate the movement of the cannon
    private int xOffset, yOffset;
    private float rotation;

    public Cannon(int dx, int dy, String d){
        isOnScreen = true;
        x = dx;
        y = dy;
        direction = d;

        //rotates cannonball
        if (direction == "UP") {
            rotation = 0f;
        }
        if (direction == "LEFT") {
            rotation = 90f;
        }
        if (direction == "DOWN") {
            rotation = 180f;
        }
        if (direction == "RIGHT") {
            rotation = 270f;
        }

        cannonTexture = new Texture("sprites/dodgeCannonball.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(cannonTexture,8,8);
        cannonAnimationFrames = new TextureRegion[1];

        int index = 0;
        for (int i = 0; i < 1; i++){
            for (int j = 0; j < 1; j++) {
                cannonAnimationFrames[index++] = tmpFrames[i][j];
            }
        }

        cannonAnimation = new Animation(1f/1f,cannonAnimationFrames);
    }

    //cannon movement
    public void update(float deltaTime){
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (direction == "RIGHT"){
            xOffset += SPEED * deltaTime;
            if (xOffset > PLAYER_MOVE_DISTANCE / 2) {
                xOffset -= PLAYER_MOVE_DISTANCE;
                x++;
            }
            if (x > 8)
                isOnScreen = false;
        }

        else if(direction == "UP"){
            yOffset += SPEED * deltaTime;
            if (yOffset > PLAYER_MOVE_DISTANCE / 2) {
                yOffset -= PLAYER_MOVE_DISTANCE;
                y++;
            }
            if (y > 8)
                isOnScreen = false;
        }

        else if(direction == "LEFT"){
            xOffset -= SPEED * deltaTime;
            if (-xOffset > PLAYER_MOVE_DISTANCE / 2) {
                xOffset += PLAYER_MOVE_DISTANCE;
                x--;
            }
            if (x < -1)
                isOnScreen = false;
        }

        else if(direction == "DOWN"){
            yOffset -= SPEED * deltaTime;
            if (-yOffset > PLAYER_MOVE_DISTANCE / 2) {
                yOffset += PLAYER_MOVE_DISTANCE;
                y--;
            }
            if (y < -1)
                isOnScreen = false;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(cannonAnimation.getKeyFrame(elapsedTime,true), xCoordToPixel(x) + xOffset, yCoordToPixel(y) + yOffset, PLAYER_WIDTH / 2, PLAYER_WIDTH / 2, PLAYER_WIDTH, PLAYER_WIDTH, 1, 1, rotation);
    }
}

