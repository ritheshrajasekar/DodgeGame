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
    //private timer Timer;
    public LevelSelect(DodgeGame game){
        JSONHelper helper = new JSONHelper();
        this.game = game;
        button1 = new Texture("button1.png");
        button2 = new Texture("button2.png");
        grass = new Texture("dodgeGrassBGS.png");
        grassBackground = new Sprite(grass);
        grassBackground.scale(7);
        sand = new Texture("dodgeGrassBGS.png");
        sandBackground = new Sprite(sand);
        sandBackground.scale(7);
        jungle = new Texture("dodgeGrassBGS.png");
        jungleBackground = new Sprite(jungle);
        jungleBackground.scale(7);
        hell = new Texture("dodgeGrassBGS.png");
        hellBackground = new Sprite(hell);
       hellBackground.scale(7);
        music = Gdx.audio.newMusic(Gdx.files.internal("myName.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }
    public void show(){

    }

    public void render(float delta){
        Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
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
        if(Gdx.input.getX() < X_VALUE_LEFTMOST + BUTTON_SIZE &&
                Gdx.input.getX() > X_VALUE_LEFTMOST &&
                DodgeGame.HEIGHT - Gdx.input.getY() > Y_VALUE_TOP  &&
                DodgeGame.HEIGHT - Gdx.input.getY() < Y_VALUE_TOP + BUTTON_SIZE) {

            if(Gdx.input.isTouched()){
              this.dispose();
            game.setScreen(new Level1(game));
            }
        }
        game.batch.draw(button2, X_VALUE_LEFTMOST, Y_VALUE_MIDDLE, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(button3, X_VALUE_LEFTMOST, Y_VALUE_BOTTOM, BUTTON_SIZE, BUTTON_SIZE);
        game.batch.draw(button4,X_VALUE_LEFT, Y_VALUE_TOP, BUTTON_SIZE, BUTTON_SIZE );
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
    }
}
