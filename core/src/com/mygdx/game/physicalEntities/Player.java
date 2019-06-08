package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Player {
    Texture player;
    TextureRegion[] animationFrames;
    Animation animation;
    float elapsedTime;
    int[] playerCoord = {0, 0};

    //creates the animation for the player
    public Player(){
        player = new Texture("dodgeGuy.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(player,8,10);

        animationFrames = new TextureRegion[9];
        int index = 0;

        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }

        animation = new Animation(1f/9f,animationFrames);
    }

    public int xCoordToPixel(int x) {
        return x * PLAYER_MOVE_DISTANCE + GRID_OFFSET_X;
    }

    public int yCoordToPixel(int y) {
        return y * PLAYER_MOVE_DISTANCE + GRID_OFFSET_Y;
    }
    // updates the position of the player, and time elapsed for the animation
    public void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            if (playerCoord[0] < 7)
                playerCoord[0]++;
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            if (playerCoord[0] > 0)
                playerCoord[0]--;
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            if (playerCoord[1] < 7)
                playerCoord[1]++;
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            if (playerCoord[1] > 0)
                playerCoord[1]--;
        elapsedTime += Gdx.graphics.getDeltaTime();
    }
    // renders the player animation
    public void render(SpriteBatch batch){

        batch.draw(animation.getKeyFrame(elapsedTime,true), xCoordToPixel(playerCoord[0]) + GRID_CORNER_SIZE, yCoordToPixel(playerCoord[1]) + GRID_CORNER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
    }




}
