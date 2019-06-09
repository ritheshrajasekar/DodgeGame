package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.DodgeGame;

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

        game.batch.draw(button1, 110, 600, 100, 100);
        game.batch.draw(button2, 110, 350, 100, 100);
        game.batch.draw(button3, 110, 100, 100, 100);
        game.batch.draw(button4,430, 600, 100, 100 );
        game.batch.draw(button5, 430, 350, 100, 100);
        game.batch.draw(button6, 430, 100, 100, 100);
        game.batch.draw(button7, 750, 600, 100, 100);
        game.batch.draw(button8, 750,350, 100, 100);
        game.batch.draw(button9, 750, 100, 100, 100);
        game.batch.draw(button10, 1070, 600, 100, 100);
        game.batch.draw(button11, 1070, 350, 100, 100);
        game.batch.draw(button12, 1070,100, 100, 100);
        game.batch.draw(homeButton, 1180, 0, 100, 100  );
        if(Gdx.input.justTouched()){
            this.dispose();
            game.setScreen(new Level1(game));
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
