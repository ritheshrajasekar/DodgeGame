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

public class LevelSelectScreen implements Screen {
    private DodgeGame game;

    private Music music;

    private Texture grass;
    public static Sprite grassBackground;
    //private timer Timer;
    public LevelSelectScreen(DodgeGame game){
        this.game = game;
        grass = new Texture("dodgeGrassBGS.png");
        grassBackground = new Sprite(grass);
        grassBackground.scale(7);
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
