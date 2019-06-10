package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.utilities.FileStreaming;
import com.mygdx.game.utilities.GameLevelManager;

import static com.mygdx.game.com.mygdx.game.screens.Level.coins;
import static com.mygdx.game.com.mygdx.game.screens.Level.currentLevelNumber;

public class WonLevel implements Screen {
    private DodgeGame game;

    private Music music;

    private static final Texture levelSelectButton = new Texture("sprites/levelSelectButton.png");
    private static final Texture nextButton = new Texture("sprites/nextLevel.png");
    private static final Texture wonLevelBackground = new Texture("sprites/completeLevelBackground.png");
    private static final Sprite wonLevelBackgroundSprite = new Sprite(wonLevelBackground);

    private static final int BUTTON_SIZE = 300;
    private static final int X_VALUE_NEXT_LEVEL = (int)(DodgeGame.WIDTH * 0.25) - BUTTON_SIZE / 2 ;
    private static final int Y_VALUE_NEXT_LEVEL = 100;
    private static final int X_VALUE_SELECT_LEVEL = (int)(DodgeGame.WIDTH * 0.75) - BUTTON_SIZE / 2;
    private static final int Y_VALUE_SELECT_LEVEL = 100;

    public WonLevel(DodgeGame game){
        this.game = game;

        wonLevelBackgroundSprite.scale(7);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/smashLifelight.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }
    public void show(){
        //coin to stars converter
        int stars = 0;
        if (coins > 0 && coins <= 8)
            stars = 1;
        if (coins > 8 && coins <= 11)
            stars = 2;
        if (coins >= 12)
            stars = 3;

        //assigns stars and unlockedLevel to their appropriate values
        if (stars > FileStreaming.stars[currentLevelNumber - 1])
            FileStreaming.stars[currentLevelNumber - 1] = stars;
        if (currentLevelNumber + 1 > FileStreaming.unlockedLevel)
            FileStreaming.unlockedLevel = currentLevelNumber + 1;

        FileStreaming.write();
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        wonLevelBackgroundSprite.setPosition(DodgeGame.WIDTH/2 - wonLevelBackgroundSprite.getWidth()/2,DodgeGame.HEIGHT/2 - wonLevelBackgroundSprite.getHeight()/2);
        wonLevelBackgroundSprite.draw(game.batch);
        game.batch.draw(nextButton, X_VALUE_NEXT_LEVEL, Y_VALUE_NEXT_LEVEL, BUTTON_SIZE, BUTTON_SIZE);

        //creates the next level button
        if(Gdx.input.getX() < X_VALUE_NEXT_LEVEL + BUTTON_SIZE &&
                Gdx.input.getX() > X_VALUE_NEXT_LEVEL &&
                DodgeGame.HEIGHT - Gdx.input.getY() > Y_VALUE_NEXT_LEVEL &&
                DodgeGame.HEIGHT - Gdx.input.getY() < Y_VALUE_NEXT_LEVEL + BUTTON_SIZE ){
            if(Gdx.input.isTouched()){
                this.dispose();
                GameLevelManager.playLevel(game, currentLevelNumber + 1);
            }
        }
        game.batch.draw(levelSelectButton, X_VALUE_SELECT_LEVEL, Y_VALUE_SELECT_LEVEL, BUTTON_SIZE, BUTTON_SIZE);
        //creates the next level select button
        if(Gdx.input.getX() < X_VALUE_SELECT_LEVEL + BUTTON_SIZE &&
                Gdx.input.getX() > X_VALUE_SELECT_LEVEL &&
                DodgeGame.HEIGHT - Gdx.input.getY() > Y_VALUE_SELECT_LEVEL &&
                DodgeGame.HEIGHT - Gdx.input.getY() < Y_VALUE_SELECT_LEVEL + BUTTON_SIZE ){

            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new LevelSelect(game));
            }
        }

        game.batch.end();
    }
    public void resize(int width, int height){

    }
    public void pause(){

    }
    public void resume(){

    }
    public void hide(){

    }
    public void dispose(){
        music.dispose();
    }
}

