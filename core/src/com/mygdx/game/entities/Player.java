package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Player extends Entity {
    private Texture player;
    private TextureRegion[] animationFrames;
    private Animation animation;
    private float elapsedTime;
    private int shiftCount;


    //creates the animation for the player
    public Player() {
        player = new Texture("sprites/dodgeGuy.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(player, 8, 10);

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

    // renders the player animation
    public void render(SpriteBatch batch) {

        batch.draw(animation.getKeyFrame(elapsedTime, true), xCoordToPixel(x), yCoordToPixel(y), PLAYER_WIDTH, PLAYER_HEIGHT);
    }


}
