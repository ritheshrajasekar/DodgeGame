package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Laser extends Entity {
    public static final int SPEED = 350;

    private static Texture laserTexture;
    private TextureRegion[] laserAnimationFrames;
    private Animation laserAnimation;
    private float elapsedTime;
    public boolean isOnScreen;
    public boolean spawned;

    //x and y offset here are used to animate the movement of the laser
    private int xOffset, yOffset;
    private float rotation;

    public Laser(int dx, int dy, String d){
        isOnScreen = true;
        x = dx;
        y = dy;
        direction = d;

        //rotates laser
        if (direction.equals("UP")) {
            rotation = 0f;
        }
        if (direction.equals("LEFT")) {
            rotation = 90f;
        }
        if (direction.equals("DOWN")) {
            rotation = 180f;
        }
        if (direction.equals("RIGHT")) {
            rotation = 270f;
        }

        laserTexture = new Texture("sprites/dodgeLaser.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(laserTexture,8,8);
        laserAnimationFrames = new TextureRegion[1];

        int index = 0;
        for (int i = 0; i < 1; i++){
            for (int j = 0; j < 1; j++) {
                laserAnimationFrames[index++] = tmpFrames[i][j];
            }
        }

        laserAnimation = new Animation(1f/1f,laserAnimationFrames);
    }

    //laser movement
    public void update(float deltaTime){
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (direction.equals("RIGHT")){
            xOffset += SPEED * deltaTime;
            if (xOffset > PLAYER_MOVE_DISTANCE / 2) {
                xOffset -= PLAYER_MOVE_DISTANCE;
                x++;
            }
            if (x > 8)
                isOnScreen = false;
        }

        else if(direction.equals("UP")){
            yOffset += SPEED * deltaTime;
            if (yOffset > PLAYER_MOVE_DISTANCE / 2) {
                yOffset -= PLAYER_MOVE_DISTANCE;
                y++;
            }
            if (y > 8)
                isOnScreen = false;
        }

        else if(direction.equals("LEFT")){
            xOffset -= SPEED * deltaTime;
            if (-xOffset > PLAYER_MOVE_DISTANCE / 2) {
                xOffset += PLAYER_MOVE_DISTANCE;
                x--;
            }
            if (x < -1)
                isOnScreen = false;
        }

        else if(direction.equals("DOWN")){
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
        batch.draw(laserAnimation.getKeyFrame(elapsedTime,true), xCoordToPixel(x) + xOffset, yCoordToPixel(y) + yOffset, PLAYER_WIDTH / 2, PLAYER_WIDTH / 2, PLAYER_WIDTH, PLAYER_WIDTH, 1, 1, rotation);
    }
}
