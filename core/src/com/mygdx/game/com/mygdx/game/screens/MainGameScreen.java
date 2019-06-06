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

    public static final float DISTANCE = 92 ;
    public static final int SMILEY_FACE_HEIGHT = 85;
    public static final int SMILEY_FACE_WIDTH = 85;
    public static final int GRID_WIDTH = 700;
    public static final int GRID_HEIGHT = 700;
    float x = DodgeGame.WIDTH /2 - SMILEY_FACE_WIDTH / 2;
    float y = DodgeGame.HEIGHT / 2 - SMILEY_FACE_HEIGHT / 2;

    private Music music;

    DodgeGame game;

    public MainGameScreen(DodgeGame game) {
        this.game = game;
        character = new Texture("smileyface.jpg");
        grid = new Texture("grid.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("spinAndBurst.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }
    public void show(){

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
