package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.utilities.FileStreaming;
import com.mygdx.game.utilities.JSONHelper;


import java.awt.*;

import static com.mygdx.game.com.mygdx.game.screens.Level.NUM_LEVELS;

public class LevelSelect implements Screen {
    private DodgeGame game;

    private Music music;

    JSONHelper helper = new JSONHelper();
    private Texture grass;
    private Texture sand;
    private Texture jungle;
    private Texture hell;
    private Texture[] buttons = new Texture[NUM_LEVELS];
    private Texture homeButton;
    private Texture zeroStar;
    private Texture oneStar;
    private Texture twoStar;
    private Texture threeStar;

    public static Sprite grassBackground;
    public static Sprite sandBackground;
    public static Sprite jungleBackground;
    public static Sprite hellBackground;

    public static final int X_VALUE_LEFTMOST = 100;
    public static final int X_VALUE_LEFT = 430;
    public static final int X_VALUE_RIGHT = 750;
    public static final int X_VALUE_RIGHTMOST = 1070;
    public static final int Y_VALUE_TOP =  600;
    public static final int Y_VALUE_MIDDLE = 350;
    public static final int Y_VALUE_BOTTOM = 100;
    public static final int X_VALUE_HOME_BUTTON = 1180;
    public static final int Y_VALUE_HOME_BUTTON = 0;
    public static final int BUTTON_SIZE = 100;

    private String level1Coins;
    private String level2Coins;
    private String level3Coins;
    private String level4Coins;
    private String level5Coins;
    private String level6Coins;
    private String level7Coins;
    private String level8Coins;
    private String level9Coins;
    private String level12Coins;
    private String level10Coins;
    private String level11Coins;
    //private timer Timer;
    public LevelSelect(DodgeGame game){
        this.game = game;
        for (int i = 0; i < NUM_LEVELS; i++) {
            buttons[i] = new Texture("buttonNum" + (i + 1) + ".png");
        }
        homeButton = new Texture("homeButton.png");
        zeroStar = new Texture("dodge0Star.png");
        oneStar = new Texture("dodge1Star.png");
        twoStar = new Texture("dodge2Star.png");
        threeStar = new Texture("dodge3Star.png");

        grass = new Texture("dodgeGrassBGS.png");
        grassBackground = new Sprite(grass);
        grassBackground.scale(7);
        sand = new Texture("dodgeDesertBGS.png");
        sandBackground = new Sprite(sand);
        sandBackground.scale(7);
        jungle = new Texture("dodgeJungleBGS.png");
        jungleBackground = new Sprite(jungle);
        jungleBackground.scale(7);
        hell = new Texture("dodgeHellBGS.png");
        hellBackground = new Sprite(hell);
        hellBackground.scale(7);
        music = Gdx.audio.newMusic(Gdx.files.internal("myName.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();

        level1Coins = helper.read("COIN", "COIN1", false);
        level2Coins = helper.read("COIN", "COIN2", false);
        level3Coins = helper.read("COIN", "COIN3", false);
        level4Coins = helper.read("COIN", "COIN4", false);
        level5Coins = helper.read("COIN", "COIN5", false);
        level6Coins = helper.read("COIN", "COIN6", false);
        level7Coins = helper.read("COIN", "COIN7", false);
        level8Coins = helper.read("COIN", "COIN8", false);
        level9Coins = helper.read("COIN", "COIN9", false);
        level10Coins = helper.read("COIN", "COIN10", false);
        level11Coins = helper.read("COIN", "COIN11", false);
        level12Coins = helper.read("COIN", "COIN12", false);
    }
    public void show(){

    }

    public void render(float delta){
        Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        //




       //grassBackground.setPosition(DodgeGame.WIDTH / 2 - grassBackground.getWidth()/2, DodgeGame.HEIGHT/2 - grassBackground.getHeight()/2);
        grassBackground.setPosition(DodgeGame.WIDTH/8, DodgeGame.HEIGHT/2 - grassBackground.getHeight()/2);
        grassBackground.draw(game.batch);

        sandBackground.setPosition(DodgeGame.WIDTH/8 + 320, DodgeGame.HEIGHT/2 - grassBackground.getHeight()/2);
        sandBackground.draw(game.batch);


        jungleBackground.setPosition(DodgeGame.WIDTH/8 + 320 + 320, DodgeGame.HEIGHT/2 - grassBackground.getHeight()/2);
        jungleBackground.draw(game.batch);


        hellBackground.setPosition(DodgeGame.WIDTH/8 + 320 + 320 + 320, DodgeGame.HEIGHT/2 - grassBackground.getHeight()/2);
        hellBackground.draw(game.batch);

        game.batch.draw(buttons[0], X_VALUE_LEFTMOST, Y_VALUE_TOP, BUTTON_SIZE, BUTTON_SIZE);
        // checks if the mouse is clicking the level 1 button, and if level 1 is even unlocked(which it is always)
        if(Gdx.input.getX() < X_VALUE_LEFTMOST + BUTTON_SIZE &&
                Gdx.input.getX() > X_VALUE_LEFTMOST &&
                DodgeGame.HEIGHT - Gdx.input.getY() > Y_VALUE_TOP  &&
                DodgeGame.HEIGHT - Gdx.input.getY() < Y_VALUE_TOP + BUTTON_SIZE && helper.read("LEVEL", "LEVEL1", false).equals("TRUE")){

            if(Gdx.input.isTouched()){
              this.dispose();
            game.setScreen(new Level1(game));
            }
        }
        game.batch.draw(buttons[1], X_VALUE_LEFTMOST, Y_VALUE_MIDDLE, BUTTON_SIZE, BUTTON_SIZE);
        // checks the level 2 condition: if mouse is clicking, and if the level is unlocked through file streaming
        if(Gdx.input.getX() < X_VALUE_LEFTMOST + BUTTON_SIZE &&
                Gdx.input.getX() > X_VALUE_LEFTMOST &&
                DodgeGame.HEIGHT - Gdx.input.getY() > Y_VALUE_MIDDLE  &&
                DodgeGame.HEIGHT - Gdx.input.getY() < Y_VALUE_MIDDLE + BUTTON_SIZE && helper.read("LEVEL", "LEVEL2", false)!= null && helper.read("LEVEL", "LEVEL2", false).equals("TRUE")){

            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new Level2(game));
            }
        }
        game.batch.draw(buttons[2], X_VALUE_LEFTMOST, Y_VALUE_BOTTOM, BUTTON_SIZE, BUTTON_SIZE);
        // checks the level 3 condition: if mouse is clicking, and if the level is unlocked through file streaming
        if(Gdx.input.getX() < X_VALUE_LEFTMOST + BUTTON_SIZE &&
                Gdx.input.getX() > X_VALUE_LEFTMOST &&
                DodgeGame.HEIGHT - Gdx.input.getY() > Y_VALUE_BOTTOM  &&
                DodgeGame.HEIGHT - Gdx.input.getY() < Y_VALUE_BOTTOM + BUTTON_SIZE && helper.read("LEVEL", "LEVEL3", false)!= null && helper.read("LEVEL", "LEVEL3", false).equals("TRUE")){

            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new Level3(game));
            }
        }

        game.batch.draw(buttons[3], X_VALUE_LEFT, Y_VALUE_TOP, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(buttons[4], X_VALUE_LEFT, Y_VALUE_MIDDLE, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(buttons[5], X_VALUE_LEFT, Y_VALUE_BOTTOM, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(buttons[6], X_VALUE_RIGHT, Y_VALUE_TOP, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(buttons[7], X_VALUE_RIGHT,Y_VALUE_MIDDLE, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(buttons[8], X_VALUE_RIGHT, Y_VALUE_BOTTOM, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(buttons[9], X_VALUE_RIGHTMOST, Y_VALUE_TOP, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(buttons[10], X_VALUE_RIGHTMOST, Y_VALUE_MIDDLE, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(buttons[11], X_VALUE_RIGHTMOST,Y_VALUE_BOTTOM, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(homeButton, X_VALUE_HOME_BUTTON, Y_VALUE_HOME_BUTTON, BUTTON_SIZE, BUTTON_SIZE  );
        if(Gdx.input.getX() < X_VALUE_HOME_BUTTON + BUTTON_SIZE &&
                Gdx.input.getX() > X_VALUE_HOME_BUTTON &&
                DodgeGame.HEIGHT - Gdx.input.getY() > Y_VALUE_HOME_BUTTON &&
                DodgeGame.HEIGHT - Gdx.input.getY() < Y_VALUE_HOME_BUTTON + BUTTON_SIZE) {

            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new Start(game));
            }
        }
        game.batch.end();
        displayStars();// calls displayStars which displays the stars!
    }

    public void displayStars(){
        game.batch.begin();
        if(new JSONHelper().read("LEVEL", "LEVEL1", false) != null && new JSONHelper().read("LEVEL", "LEVEL1", false).length()>0){
            if(Integer.parseInt(level1Coins) >= 1 && Integer.parseInt(level1Coins) <=5 ) {
                game.batch.draw(oneStar, X_VALUE_LEFTMOST - 56, Y_VALUE_TOP - 100, 224, 70);
            } else if(Integer.parseInt(level1Coins) >= 6 &&  Integer.parseInt(level1Coins) <= 9) {
                game.batch.draw(twoStar, X_VALUE_LEFTMOST - 56, Y_VALUE_TOP -100, 224, 70);
            } else if((Integer.parseInt(level1Coins) > 9)){
                game.batch.draw(threeStar, X_VALUE_LEFTMOST - 56, Y_VALUE_TOP -100, 224, 70);
            }
        }

        if(new JSONHelper().read("LEVEL", "LEVEL2", false) != null && new JSONHelper().read("LEVEL", "LEVEL2", false).length()>0){
            if (Integer.parseInt(level2Coins) >= 1 && Integer.parseInt(level2Coins) <=5) {
                game.batch.draw(oneStar, X_VALUE_LEFTMOST - 56, Y_VALUE_MIDDLE -100, 224, 70);
            } else if(Integer.parseInt(level2Coins) >= 6 &&  Integer.parseInt(level2Coins) <= 9) {
                game.batch.draw(twoStar, X_VALUE_LEFTMOST - 56, Y_VALUE_MIDDLE -100, 224, 70);
            } else if((Integer.parseInt(level2Coins) > 9)) {
                game.batch.draw(threeStar, X_VALUE_LEFTMOST - 56, Y_VALUE_MIDDLE -100, 224, 70);
            }
        }

        if(new JSONHelper().read("LEVEL", "LEVEL3", false) != null && new JSONHelper().read("LEVEL", "LEVEL3", false).length()>0){
            if(Integer.parseInt(level3Coins) >= 1 && Integer.parseInt(level3Coins) <=5 ){game.batch.draw(oneStar, X_VALUE_LEFTMOST - 56, Y_VALUE_BOTTOM -100, 224, 70);}
            else if(Integer.parseInt(level3Coins) >= 6 &&  Integer.parseInt(level3Coins) <= 9) {game.batch.draw(twoStar, X_VALUE_LEFTMOST - 56, Y_VALUE_BOTTOM -100, 224, 70);}
            else if((Integer.parseInt(level3Coins) > 9)){game.batch.draw(threeStar, X_VALUE_LEFTMOST - 56, Y_VALUE_BOTTOM -100, 224, 70);}
        }

        for (int i = 0; i < NUM_LEVELS; i++) {
            Texture starTexture = zeroStar;
            switch (FileStreaming.stars[i]) {
                case 1:
                    starTexture = oneStar;
                    break;
                case 2:
                    starTexture = twoStar;
                    break;
                case 3:
                    starTexture = threeStar;
                    break;
            }
            game.batch.draw(starTexture, X_VALUE_LEFTMOST - 56, Y_VALUE_TOP - 100, 224, 70);
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
        grass.dispose();
        sand.dispose();
        jungle.dispose();
        hell.dispose();
        for (int i = 0; i < NUM_LEVELS; i++) {
            buttons[i].dispose();
        }
        music.dispose();
    }
}
