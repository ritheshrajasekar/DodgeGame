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

    public static final int PLAYER_MOVE_DISTANCE = 63;
    public static final int SMILEY_FACE_HEIGHT = 63;
    public static final int SMILEY_FACE_WIDTH = 63;
    public static final int GRID_WIDTH = 665;
    public static final int GRID_HEIGHT = 665;
    public static final int GRID_OFFSET_X = 600;
    public static final int GRID_OFFSET_Y = 100;
    int[] playerCoord = {0, 0};

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
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            if (playerCoord[0] < 7)
                playerCoord[0]++;
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            if (playerCoord[0] > 0)
                playerCoord[0]--;
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            if (playerCoord[1] < 7)
                playerCoord[1]++;
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            if (playerCoord[1] > 0)
                playerCoord[1]--;

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(grid, DodgeGame.WIDTH/2  - GRID_WIDTH/2, DodgeGame.HEIGHT/2 - GRID_HEIGHT/2, GRID_WIDTH, GRID_HEIGHT);
        game.batch.draw(character, xCoordToPixel(playerCoord[0]), yCoordToPixel(playerCoord[1]), SMILEY_FACE_WIDTH, SMILEY_FACE_HEIGHT);
        game.batch.end();
    }

    public int xCoordToPixel(int x) {
        return x * PLAYER_MOVE_DISTANCE + GRID_OFFSET_X;
    }

    public int yCoordToPixel(int y) {
        return y * PLAYER_MOVE_DISTANCE + GRID_OFFSET_Y;
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
