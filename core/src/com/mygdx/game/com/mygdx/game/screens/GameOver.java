package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;

public class GameOver implements Screen {
    private DodgeGame game;

    private Music music;

    private Texture levels;
    private Texture retryButton;


    //private timer Timer;
    public GameOver(DodgeGame game){
        this.game = game;
        levels = new Texture("levels.jpg");
        retryButton = new Texture("retryButton.jpg");
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


        game.batch.draw(levels, 220, 600, 250, 50);
        if(Gdx.input.justTouched()){
            this.dispose();
            game.setScreen(new LevelSelect(game));
        }
        game.batch.draw(retryButton, 220, 400, 100, 100);
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
    }
}
