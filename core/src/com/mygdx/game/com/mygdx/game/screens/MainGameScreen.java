package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;

public class MainGameScreen implements Screen {
    Texture img;
    float x;
    float y;
    public static final float SPEED = 120;

    DodgeGame game;

    public MainGameScreen(DodgeGame game) {
        this.game = game;
    }
    public void show(){
        img = new Texture("smileyface.jpg");
    }
    public void render(float delta){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            y += SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
        {
            y -= SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        {
            x -= SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        {
            x += SPEED * Gdx.graphics.getDeltaTime();
        }

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(img,x,y);
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

    }
}
