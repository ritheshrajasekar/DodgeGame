package com.mygdx.game.physicalEntities;

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

    private int xOffset, yOffset;

    public Boulder(String d){
        isOnScreen = true;
        direction = d;

        // randomly assigns a spawn position to the boulder based on what the direction of the boulder is
        if (direction == "UP"){
            x = (int)(Math.random() * 8);
            y = -1;
        }
        else if (direction == "DOWN"){
            x = (int)(Math.random() * 8);
            y = 8;
        }

        else if (direction == "LEFT"){
            x = 8;
            y = (int)(Math.random() * 8);
        }

        else if (direction == "RIGHT"){
            x = -1;
            y = (int)(Math.random() * 8);
        }

        boulderTexture = new Texture("dodgeBoulder.png");
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

    public void update(float deltaTime){
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (direction == "RIGHT"){
            xOffset += SPEED * deltaTime;
            if (xOffset > PLAYER_MOVE_DISTANCE) {
                xOffset %= PLAYER_MOVE_DISTANCE;
                x++;
            }
            if (x > 8)
                isOnScreen = false;
        }

        else if(direction == "UP"){
            yOffset += SPEED * deltaTime;
            if (yOffset > PLAYER_MOVE_DISTANCE) {
                yOffset %= PLAYER_MOVE_DISTANCE;
                y++;
            }
            if (y > 8)
                isOnScreen = false;
        }

        else if(direction == "LEFT"){
            xOffset -= SPEED * deltaTime;
            if (-xOffset > PLAYER_MOVE_DISTANCE) {
                xOffset %= PLAYER_MOVE_DISTANCE;
                x--;
            }
            if (x < -1)
                isOnScreen = false;
        }

        else if(direction == "DOWN"){
            yOffset -= SPEED * deltaTime;
            if (-yOffset > PLAYER_MOVE_DISTANCE) {
                yOffset %= PLAYER_MOVE_DISTANCE;
                y--;
            }
            if (y < -1)
                isOnScreen = false;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(boulderAnimation.getKeyFrame(elapsedTime,true), xCoordToPixel(x) + xOffset, yCoordToPixel(y) + yOffset, PLAYER_SIZE, PLAYER_SIZE);
    }
}

