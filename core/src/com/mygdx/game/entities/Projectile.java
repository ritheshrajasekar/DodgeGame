package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Projectile extends Entity {
    //speed can not go above 1500 because that would be moving more than one tile per frame; it'll still work but is just not ideal
    private int speed;
    private float elapsedTime;
    public boolean isOnScreen;
    Animation projectileAnimation;
    public boolean spawned, acrossScreen;

    //x and y offset here are used to animate the movement of the projectile
    private int xOffset, yOffset;
    private float rotation;

    public Projectile(int dx, int dy, String d, int s, Animation a) {
        isOnScreen = true;
        acrossScreen = false;

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
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                projectileAnimationFrames[index++] = tmpFrames[i][j];
            }
        }
        return new Animation(1f / frames, projectileAnimationFrames);
    }


    // because a boomerang has to come across the screen and then back, this update method does exactly just that by using the across screen variable.
    public void updateBoomerang(float deltaTime) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        if (direction == "RIGHT") {
            if (acrossScreen) { // checks if the projectile has gone across the screen, and if it has, then it emulates the left direction behavior
                leftBehavior(deltaTime);
            } else {
                xOffset += speed * deltaTime;
                if (xOffset > PLAYER_MOVE_DISTANCE / 2) {
                    xOffset -= PLAYER_MOVE_DISTANCE;
                    x++;
                }
                if (x > 7) // checks if the projectile has moved across the screen, and if it has, sets acrossScreen to false
                    acrossScreen = true;

            }
        } else if (direction == "UP") {
            if (acrossScreen) { // checks if the projectile has gone across the screen, and if it has, then it emulates the up direction behavior
                downBehavior(deltaTime);
            } else {
                yOffset += speed * deltaTime;
                if (yOffset > PLAYER_MOVE_DISTANCE / 2) {
                    yOffset -= PLAYER_MOVE_DISTANCE;
                    y++;
                }
                if (y > 7) // checks if the projectile has moved across the screen, and if it has, sets acrossScreen to falsev
                    acrossScreen = true;
            }
        } else if (direction == "LEFT") {
            if (acrossScreen) { // checks if the projectile has gone across the screen, and if it has, then it emulates the right direction behavior
                rightBehavior(deltaTime);
            } else {
                xOffset -= speed * deltaTime;
                if (-xOffset > PLAYER_MOVE_DISTANCE / 2) {
                    xOffset += PLAYER_MOVE_DISTANCE;
                    x--;
                }
                if (x < 0) // checks if the projectile has moved across the screen, and if it has, sets acrossScreen to false
                    acrossScreen = true;
            }

        } else if (direction == "DOWN") {
            if (acrossScreen) { // checks if the projectile has gone across the screen, and if it has, then it emulates the up direction behavior
                upBehavior(deltaTime);
            } else {
                yOffset -= speed * deltaTime;
                if (-yOffset > PLAYER_MOVE_DISTANCE / 2) {
                    yOffset += PLAYER_MOVE_DISTANCE;
                    y--;
                }
                if (y < 0) // checks if the projectile has moved across the screen, and if it has, sets acrossScreen to false
                    acrossScreen = true;
            }
        }
    }
    //changes the entity x coordinates on its direction which is right
    public void rightBehavior(float deltaTime) {
        xOffset += speed * deltaTime;
        if (xOffset > PLAYER_MOVE_DISTANCE / 2) {
            xOffset -= PLAYER_MOVE_DISTANCE;
            x++;
        }
        if (x > 8) // checks if the projectile has moved across the screen, and if it has, sets acrossScreen to false
            isOnScreen = false;
    }
    //
    public void updateLaser(float deltaTime){

        elapsedTime += Gdx.graphics.getDeltaTime();
    }
    //changes the entity x coordinates on its direction which is left
    public void leftBehavior(float deltaTime) {
        xOffset -= speed * deltaTime;
        if (-xOffset > PLAYER_MOVE_DISTANCE / 2) {
            xOffset += PLAYER_MOVE_DISTANCE;
            x--;
        }
        if (x < -1) // checks if the projectile has moved across the screen, and if it has, sets acrossScreen to false
            isOnScreen = false;
    }
    // changes the entity y coordinates based on its direction which is up
    public void upBehavior(float deltaTime) {
        yOffset += speed * deltaTime;
        if (yOffset > PLAYER_MOVE_DISTANCE / 2) {
            yOffset -= PLAYER_MOVE_DISTANCE;
            y++;
        }
        if (y > 8) // checks if the projectile has moved across the screen, and if it has, sets acrossScreen to falsev
            isOnScreen = false;
    }
    // changes the entity y coordinates based on its direction which is down
    public void downBehavior(float deltaTime) {
        yOffset -= speed * deltaTime;
        if (-yOffset > PLAYER_MOVE_DISTANCE / 2) {
            yOffset += PLAYER_MOVE_DISTANCE;
            y--;
        }
        if (y < -1) // checks if the projectile has moved across the screen, and if it has, sets acrossScreen to false
            isOnScreen = false;
    }

    // updates pos of entity based on direction which is then configured to the different behavior methods
    public void update(float deltaTime) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (direction == "RIGHT") {
            rightBehavior(deltaTime);
        } else if (direction == "UP") {
            upBehavior(deltaTime);
        } else if (direction == "LEFT") {
            leftBehavior(deltaTime);
        } else if (direction == "DOWN") {
            downBehavior(deltaTime);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(projectileAnimation.getKeyFrame(elapsedTime, true), xCoordToPixel(x) + xOffset, yCoordToPixel(y) + yOffset, PLAYER_WIDTH / 2, PLAYER_WIDTH / 2, PLAYER_WIDTH, PLAYER_WIDTH, 1, 1, rotation);
    }
}