package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BlinkingArrow extends Entity{
    public static final int ARROW_WIDTH = 70;//10*7
    public static final int ARROW_HEIGHT = 56;//8*7

    public Texture arrowTexture;
    private TextureRegion[] arrowAnimationFrames;
    private Animation arrowAnimation;

    private float rotation;
    //x and y offset here are used to reposition the arrowTexture after rotation
    private int xOffset, yOffset;

    public float elapsedTime;

    public BlinkingArrow(int dx, int dy, String d){
        x = dx;
        y = dy;
        direction = d;

        //rotates arrowTexture and shifts it in the appropriate direction
        if (direction == "RIGHT") {
            rotation = 0f;
            xOffset = -14;
        }
        if (direction == "UP") {
            rotation = 90f;
            xOffset = -7;
            yOffset = -7;
        }
        if (direction == "LEFT") {
            rotation = 180f;
        }
        if (direction == "DOWN") {
            rotation = 270f;
            xOffset = -7;
            yOffset = 7;
        }

        //default texture
        arrowTexture = new Texture("sprites/dodgeBoulderArrow.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(arrowTexture,10,8);
        arrowAnimationFrames = new TextureRegion[2];

        int index = 0;
        for (int i = 0; i < 1; i++){
            for(int j = 0; j < 2; j++) {
                arrowAnimationFrames[index++] = tmpFrames[i][j];
            }
        }

        arrowAnimation = new Animation(0.3f, arrowAnimationFrames);
    }

    public void render(SpriteBatch batch){
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(arrowAnimation.getKeyFrame(elapsedTime, true), xCoordToPixel(x) + xOffset, yCoordToPixel(y) + yOffset, ARROW_WIDTH / 2, ARROW_HEIGHT / 2, ARROW_WIDTH, ARROW_HEIGHT, 1, 1, rotation); // must use the x and y to pixel coordinates here
    }
}
