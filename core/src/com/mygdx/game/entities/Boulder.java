package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Boulder extends Entity {
    public static final int SPEED = 200;

    private static Texture boulderTexture;
    private TextureRegion[] boulderAnimationFrames;
    private Animation boulderAnimation;
    private float elapsedTime;
    public boolean isOnScreen;
    public boolean spawned;

    //x and y offset here are used to animate the movement of the boulder
    private int xOffset, yOffset;
    private float rotation;

    public Boulder(int dx, int dy, String d){
        isOnScreen = true;
        x = dx;
        y = dy;
        direction = d;

        //rotates boulder
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

        boulderTexture = new Texture("sprites/dodgeBoulder.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(boulderTexture,8,8);
        boulderAnimationFrames = new TextureRegion[12];

        int index = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 3; j++) {
                boulderAnimationFrames[index++] = tmpFrames[i][j];
            }
        }

        boulderAnimation = new Animation(1f/12f,boulderAnimationFrames);
    }

    //boulder movement
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
        batch.draw(boulderAnimation.getKeyFrame(elapsedTime,true), xCoordToPixel(x) + xOffset, yCoordToPixel(y) + yOffset, PLAYER_WIDTH / 2, PLAYER_WIDTH / 2, PLAYER_WIDTH, PLAYER_WIDTH, 1, 1, rotation);
    }
}

