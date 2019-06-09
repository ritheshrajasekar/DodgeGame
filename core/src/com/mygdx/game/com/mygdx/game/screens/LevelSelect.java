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
    public static Sprite grassBackground;
    public static Sprite sandBackground;
    public static Sprite jungleBackground;
    public static Sprite hellBackground;
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

        game.batch.draw(button1, 220, 600, 100, 100);
        if(Gdx.input.justTouched() && helper.read("LEVEL", "LEVEL1", false).equals("TRUE")){
            this.dispose();
            game.setScreen(new Level1(game));
        }
        game.batch.draw(button2, 220, 400, 100, 100);
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
