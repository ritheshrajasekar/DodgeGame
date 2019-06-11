package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Projectile extends Entity {
    private int speed;
    private float elapsedTime;
    public boolean isOnScreen;
    Animation projectileAnimation;
    public boolean spawned;

    //x and y offset here are used to animate the movement of the projectile
    private int xOffset, yOffset;
    private float rotation;

    public Projectile(int dx, int dy, String d, int s, Animation a){
        isOnScreen = true;
        x = dx;
        y = dy;
        direction = d;
        speed = s;
        projectileAnimation = a;

        //rotates projectile
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
    }

    public static Animation createAnimation(String path, int width, int height, int rows, int cols) {
        int frames = rows * cols;

        Texture projectileTexture = new Texture(path);
        TextureRegion[] projectileAnimationFrames;
        TextureRegion[][] tmpFrames = TextureRegion.split(projectileTexture, width, height);
        projectileAnimationFrames = new TextureRegion[frames];

        int index = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++) {
                projectileAnimationFrames[index++] = tmpFrames[i][j];
            }
        }
        return new Animation(1f/frames, projectileAnimationFrames);
    }

    //projectile movement
    public void update(float deltaTime){
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (direction == "RIGHT"){
            xOffset += speed * deltaTime;
            if (xOffset > PLAYER_MOVE_DISTANCE / 2) {
                xOffset -= PLAYER_MOVE_DISTANCE;
                x++;
            }
            if (x > 8)
                isOnScreen = false;
        }

        else if(direction == "UP"){
            yOffset += speed * deltaTime;
            if (yOffset > PLAYER_MOVE_DISTANCE / 2) {
                yOffset -= PLAYER_MOVE_DISTANCE;
                y++;
            }
            if (y > 8)
                isOnScreen = false;
        }

        else if(direction == "LEFT"){
            xOffset -= speed * deltaTime;
            if (-xOffset > PLAYER_MOVE_DISTANCE / 2) {
                xOffset += PLAYER_MOVE_DISTANCE;
                x--;
            }
            if (x < -1)
                isOnScreen = false;
        }

        else if(direction == "DOWN"){
            yOffset -= speed * deltaTime;
            if (-yOffset > PLAYER_MOVE_DISTANCE / 2) {
                yOffset += PLAYER_MOVE_DISTANCE;
                y--;
            }
            if (y < -1)
                isOnScreen = false;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(projectileAnimation.getKeyFrame(elapsedTime,true), xCoordToPixel(x) + xOffset, yCoordToPixel(y) + yOffset, PLAYER_WIDTH / 2, PLAYER_WIDTH / 2, PLAYER_WIDTH, PLAYER_WIDTH, 1, 1, rotation);
    }
}