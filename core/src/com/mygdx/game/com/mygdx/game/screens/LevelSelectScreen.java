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

    private TextureRegion grassBackground;
    private Texture grass;
    //private timer Timer;
    public LevelSelectScreen(DodgeGame game){
        this.game = game;
        grass = new Texture("dodgeGrassBG.png");
        grassBackground = new TextureRegion(grass, 0 , 0, (int)(grass.getWidth() * 0.6), grass.getHeight());
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
        game.batch.draw(grassBackground, 0, 0, 330, 720);
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
