//this class serves as the player object
// created by Matt Seng, and Zak Asis

package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Player extends Entity {
    private Texture playerTexture;
    private TextureRegion[] animationFrames;
    private Animation animation;
    private float elapsedTime;
    private int shiftCount;

    public static int invincibilityState;
    public float invincibilityTime;
    private int colorFrame = 1;

    //creates the animation for the player
    public Player() {
        playerTexture = new Texture("sprites/dodgePlayer.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(playerTexture, 8, 10);
        animationFrames = new TextureRegion[9];

        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }

        animation = new Animation(1f / 9f, animationFrames);
    }

    // updates the position of the player, and time elapsed for the animation
    public void update() {
        elapsedTime += Gdx.graphics.getDeltaTime();
        //increments shiftCount if shift is pressed because without shiftCount, the player moves too fast
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            shiftCount++;
            if (shiftCount == 4)
                shiftCount = 0;
        } else {
            shiftCount = -1;
        }
        if (shiftCount == 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
                if (x < 7)
                    x++;
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
                if (x > 0)
                    x--;
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
                if (y < 7)
                    y++;
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
                if (y > 0)
                    y--;
        } else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.D))
                if (x < 7)
                    x++;
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.A))
                if (x > 0)
                    x--;
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W))
                if (y < 7)
                    y++;
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S))
                if (y > 0)
                    y--;
        }
    }

    public void updateInvincibility(float delta) {
        if (invincibilityState == 0 && coins >= 5)
            invincibilityState = 1;
        if (invincibilityState == 1 && coins >= 10)
            invincibilityState = 2;
        if (invincibilityState == 2 && coins >= 15)
            invincibilityState = 3;
        if (invincibilityState == 3 && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            invincibilityState = -1;
            //sets how long the player has invincibility for
            invincibilityTime = 3;
        }
        if (invincibilityTime > 0) {
            invincibilityTime -= delta;
        }
        if (invincibilityTime < 0) {
            invincibilityTime = 0;
        }
    }

    // renders the player animation
    public void render(SpriteBatch batch) {
        if (invincibilityTime == 0)
            batch.draw(animation.getKeyFrame(elapsedTime, true), xCoordToPixel(x), yCoordToPixel(y), PLAYER_WIDTH, PLAYER_HEIGHT);
        else {
            //makes the colored player
            Texture playerColorTexture = new Texture("sprites/playerColors/dodgePlayer" + colorFrame + ".png");
            TextureRegion[][] tmpFrames = TextureRegion.split(playerColorTexture, 8, 10);
            TextureRegion[] animationColorFrames = new TextureRegion[9];

            int index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    animationColorFrames[index++] = tmpFrames[i][j];
                }
            }

            Animation animationColor = new Animation(1f / 9f, animationColorFrames);

            batch.draw(animationColor.getKeyFrame(elapsedTime, true), xCoordToPixel(x), yCoordToPixel(y), PLAYER_WIDTH, PLAYER_HEIGHT);

            colorFrame++;
            if (colorFrame > 36)
                colorFrame = 1;
        }

    }
}
