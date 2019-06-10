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
import com.mygdx.game.utilities.JSONHelper;

import static com.mygdx.game.com.mygdx.game.screens.Level.coins;
import static com.mygdx.game.com.mygdx.game.screens.Level.currentLevelNumber;

public class WonLevel implements Screen {
    private DodgeGame game;

    private Music music;

    private Texture levelSelectButton;
    private Texture nextButton;
    private Texture wonLevelBackground;
    private static final int BUTTON_SIZE = 300;
    private static final int XVALUE_NEXT_LEVEL = (int)(DodgeGame.WIDTH * 0.25) - BUTTON_SIZE / 2 ;
    private static final int XVALUE_SELECT_LEVEL = (int)(DodgeGame.WIDTH * 0.75) - BUTTON_SIZE / 2;
    private static final int YVALUE_NEXT_LEVEL= 100;
    private static final int YVALUE_SELECT_LEVEL = 100;

    public static Sprite wonLevelBackgroundSprite;

    //private timer Timer;
    public WonLevel(DodgeGame game){
        this.game = game;
        levelSelectButton = new Texture("levelSelectButton.png");
        nextButton = new Texture("nextLevel.png");
        wonLevelBackground = new Texture("completeLevelBackground.png");
        wonLevelBackgroundSprite = new Sprite(wonLevelBackground);
        wonLevelBackgroundSprite.scale(7);
        music = Gdx.audio.newMusic(Gdx.files.internal("spinAndBurst.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }
    public void show(){
        JSONHelper jLevel = new JSONHelper();
        JSONHelper jCoins= new JSONHelper();
        String savedCoins = jLevel.read("COINS", "COINS" + currentLevelNumber, false);
        // enables the next level to be true;
        jLevel.write("LEVEL", "LEVEL" + (currentLevelNumber + 1), "TRUE", false);
        // writes the new coin value into the String if currentValue is greater than previously earned value for that level;

        if(savedCoins == null || Integer.parseInt(savedCoins) < coins)
            jCoins.write("COIN", "COIN" + currentLevelNumber, "" + coins, false);

        if (coins > FileStreaming.stars[currentLevelNumber - 1]) {

        }
        FileStreaming.stars[currentLevelNumber] = 0;
        FileStreaming.unlockedLevel = currentLevelNumber + 1;
        FileStreaming.write();
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        wonLevelBackgroundSprite.setPosition(DodgeGame.WIDTH/2 - wonLevelBackgroundSprite.getWidth()/2,DodgeGame.HEIGHT/2 - wonLevelBackgroundSprite.getHeight()/2);
        wonLevelBackgroundSprite.draw(game.batch);
        game.batch.draw(nextButton, XVALUE_NEXT_LEVEL, YVALUE_NEXT_LEVEL, BUTTON_SIZE, BUTTON_SIZE);
        //creates the next level button
        if(Gdx.input.getX() < XVALUE_NEXT_LEVEL + BUTTON_SIZE &&
                Gdx.input.getX() >XVALUE_NEXT_LEVEL&&
                DodgeGame.HEIGHT - Gdx.input.getY() > YVALUE_NEXT_LEVEL  &&
                DodgeGame.HEIGHT - Gdx.input.getY() < YVALUE_NEXT_LEVEL + BUTTON_SIZE ){

            if(Gdx.input.isTouched()){
                this.dispose();
                //game.setScreen(new Level1(game));
                GameLevelManager gm = new GameLevelManager();
                gm.nextLevel(game, currentLevelNumber);

            }

        }
        game.batch.draw(levelSelectButton, XVALUE_SELECT_LEVEL, YVALUE_SELECT_LEVEL, BUTTON_SIZE, BUTTON_SIZE);
        //creates the next level select button
        if(Gdx.input.getX() < XVALUE_SELECT_LEVEL + BUTTON_SIZE &&
                Gdx.input.getX() > XVALUE_SELECT_LEVEL &&
                DodgeGame.HEIGHT - Gdx.input.getY() > YVALUE_SELECT_LEVEL  &&
                DodgeGame.HEIGHT - Gdx.input.getY() < YVALUE_SELECT_LEVEL + BUTTON_SIZE ){

            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new LevelSelect(game));
            }
        }

        //  game.batch.draw(levels, 220, 600, 250, 50);
      /*  if(Gdx.input.justTouched()){
            this.dispose();
            game.setScreen(new LevelSelect(game));
        }
        game.batch.draw(retryButton, 220, 400, 100, 100);
        if(Gdx.input.justTouched()){
            this.dispose();
            game.setScreen(new Level1(game));
        }*/
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

