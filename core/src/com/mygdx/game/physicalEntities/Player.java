package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Player extends Entity{
    Texture player;
    TextureRegion[] animationFrames;
    Animation animation;
    float elapsedTime;

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

    // updates the position of the player, and time elapsed for the animation
    public void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            if (x < 7)
                x++;
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            if (x > 0)
                x--;
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            if (y < 7)
                y++;
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            if (y > 0)
                y--;
        elapsedTime += Gdx.graphics.getDeltaTime();
    }
    // renders the player animation
    public void render(SpriteBatch batch){

        batch.draw(animation.getKeyFrame(elapsedTime,true), xCoordToPixel(x), yCoordToPixel(y), PLAYER_SIZE, PLAYER_SIZE);
    }




}
