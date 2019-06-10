package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.utilities.JSONHelper;


import java.awt.*;

public class LevelSelect implements Screen {
    private DodgeGame game;

    private Music music;

    private Texture grass;
    private Texture sand;
    private Texture jungle;
    private Texture hell;
    private Texture button1;
    private Texture button2;
    JSONHelper helper = new JSONHelper();
    private Texture button3;
    private Texture button4;
    private Texture button5;
    private Texture button6;
    private Texture button7;
    private Texture button8;
    private Texture button9;
    private Texture button10;
    private Texture button11;
    private Texture button12;
    private Texture homeButton;
    private Texture oneStar;
    private Texture zeroStar;
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
    public static final int X_VALUE_HOME_BUTTOM = 1180;
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
        button1 = new Texture("buttonNum1.png");
        button2 = new Texture("buttonNum2.png");
        button3 = new Texture("buttonNum3.png");
        button4 = new Texture("buttonNum4.png");
        button5 = new Texture("buttonNum5.png");
        button6 = new Texture("buttonNum6.png");
        button7 = new Texture("buttonNum7.png");
        button8 = new Texture("buttonNum8.png");
        button9 = new Texture("buttonNum9.png");
        button10 = new Texture("buttonNum10.png");
        button11= new Texture("buttonNum11.png");
        button12= new Texture("buttonNum12.png");
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

        game.batch.draw(button1, X_VALUE_LEFTMOST, Y_VALUE_TOP, BUTTON_SIZE, BUTTON_SIZE);
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
        game.batch.draw(button2, X_VALUE_LEFTMOST, Y_VALUE_MIDDLE, BUTTON_SIZE, BUTTON_SIZE);
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
        game.batch.draw(button3, X_VALUE_LEFTMOST, Y_VALUE_BOTTOM, BUTTON_SIZE, BUTTON_SIZE);
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

        game.batch.draw(button4,X_VALUE_LEFT, Y_VALUE_TOP, BUTTON_SIZE, BUTTON_SIZE );

        if(Gdx.input.getX() < X_VALUE_LEFT + BUTTON_SIZE &&
                Gdx.input.getX() > X_VALUE_LEFT &&
                DodgeGame.HEIGHT - Gdx.input.getY() > Y_VALUE_TOP  &&
                DodgeGame.HEIGHT - Gdx.input.getY() < Y_VALUE_TOP + BUTTON_SIZE && helper.read("LEVEL", "LEVEL4", false)!= null && helper.read("LEVEL", "LEVEL4", false).equals("TRUE")){

            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new Level4(game));
            }
        }
        game.batch.draw(button5, X_VALUE_LEFT, Y_VALUE_MIDDLE, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(button6, X_VALUE_LEFT, Y_VALUE_BOTTOM, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(button7, X_VALUE_RIGHT, Y_VALUE_TOP, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(button8, X_VALUE_RIGHT,Y_VALUE_MIDDLE, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(button9, X_VALUE_RIGHT, Y_VALUE_BOTTOM, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(button10, X_VALUE_RIGHTMOST, Y_VALUE_TOP, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(button11, X_VALUE_RIGHTMOST, Y_VALUE_MIDDLE, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(button12, X_VALUE_RIGHTMOST,Y_VALUE_BOTTOM, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(homeButton, X_VALUE_HOME_BUTTOM, Y_VALUE_HOME_BUTTON, BUTTON_SIZE, BUTTON_SIZE  );
        if(Gdx.input.getX() < X_VALUE_HOME_BUTTOM + BUTTON_SIZE &&
                Gdx.input.getX() > X_VALUE_HOME_BUTTOM &&
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
            if(Integer.parseInt(level1Coins) >= 1 && Integer.parseInt(level1Coins) <=5 ){game.batch.draw(oneStar, X_VALUE_LEFTMOST - 56, Y_VALUE_TOP - 100, 224, 70);}
            else if(Integer.parseInt(level1Coins) >= 6 &&  Integer.parseInt(level1Coins) <= 9) {game.batch.draw(twoStar, X_VALUE_LEFTMOST - 56, Y_VALUE_TOP -100, 224, 70);}
            else if((Integer.parseInt(level1Coins) > 9)){game.batch.draw(threeStar, X_VALUE_LEFTMOST - 56, Y_VALUE_TOP -100, 224, 70);}
            }

        if(new JSONHelper().read("LEVEL", "LEVEL2", false) != null && new JSONHelper().read("LEVEL", "LEVEL2", false).length()>0){
            if(Integer.parseInt(level2Coins) >= 1 && Integer.parseInt(level2Coins) <=5 ){game.batch.draw(oneStar, X_VALUE_LEFTMOST - 56, Y_VALUE_MIDDLE -100, 224, 70);}
            else if(Integer.parseInt(level2Coins) >= 6 &&  Integer.parseInt(level2Coins) <= 9) {game.batch.draw(twoStar, X_VALUE_LEFTMOST - 56, Y_VALUE_MIDDLE -100, 224, 70);}
            else if((Integer.parseInt(level2Coins) > 9)){game.batch.draw(threeStar, X_VALUE_LEFTMOST - 56, Y_VALUE_MIDDLE -100, 224, 70);}
        }

        if(new JSONHelper().read("LEVEL", "LEVEL3", false) != null && new JSONHelper().read("LEVEL", "LEVEL3", false).length()>0){
            if(Integer.parseInt(level3Coins) >= 1 && Integer.parseInt(level3Coins) <=5 ){game.batch.draw(oneStar, X_VALUE_LEFTMOST - 56, Y_VALUE_BOTTOM -100, 224, 70);}
            else if(Integer.parseInt(level3Coins) >= 6 &&  Integer.parseInt(level3Coins) <= 9) {game.batch.draw(twoStar, X_VALUE_LEFTMOST - 56, Y_VALUE_BOTTOM -100, 224, 70);}
            else if((Integer.parseInt(level3Coins) > 9)){game.batch.draw(threeStar, X_VALUE_LEFTMOST - 56, Y_VALUE_BOTTOM -100, 224, 70);}
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
        button1.dispose();
        button2.dispose();
        music.dispose();
    }
}
