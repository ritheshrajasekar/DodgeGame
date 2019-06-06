package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;

public class MainGameScreen implements Screen {
    Texture character;
    Texture grid;
    float x;
    float y;
    public static final float DISTANCE = 100 ;
    public static final int SMILEY_FACE_HEIGHT = 100;
    public static final int SMILEY_FACE_WIDTH = 100;
    public static final int GRID_WIDTH = 1000;
    public static final int GRID_HEIGHT = 1000;
    private Music music;

    DodgeGame game;

    public MainGameScreen(DodgeGame game) {
        this.game = game;
    }
    public void show(){
        character = new Texture("smileyface.jpg");
        grid = new Texture("grid.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("naruto.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }
    public void render(float delta){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            y += DISTANCE ;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
        {
            y -= DISTANCE ;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        {
            x -= DISTANCE ;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        {
            x += DISTANCE ;
        }

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(grid, DodgeGame.WIDTH/2  - GRID_WIDTH/2, DodgeGame.HEIGHT/2 - GRID_HEIGHT/2, GRID_WIDTH, GRID_HEIGHT);
        game.batch.draw(character,x,y, SMILEY_FACE_WIDTH, SMILEY_FACE_HEIGHT);
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
        character.dispose();
        grid.dispose();
    }
}
