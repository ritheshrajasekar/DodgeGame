package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.physicalEntities.*;

public class MainGameScreen implements Screen {
    Texture grid;
    MyTimer timer;
    Player player;

    public static final int PLAYER_MOVE_DISTANCE = 63;//9*7, 7 is the scalar multiplier for all sprites
    public static final int PLAYER_SIZE = 56;//8*7
    public static final int GRID_WIDTH = 665;
    public static final int GRID_HEIGHT = 665;
    public static final int GRID_OFFSET_X = 588;
    public static final int GRID_OFFSET_Y = 27;
    public static final int GRID_CORNER_SIZE = 84;//12*7

    private Music music;
    Boulder boulder;
    DodgeGame game;

    public MainGameScreen(DodgeGame game) {
        this.game = game;

        grid = new Texture("dodgeGrid.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("spinAndBurst.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();

        boulder = new Boulder(0, 8);
        player = new Player();
    }

    public void show(){
        timer = new MyTimer(60);
    }

    public void render(float delta){
        //timer code
        timer.update(delta);

        //creates background
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renders entities
        game.batch.begin();
        game.font.setColor(Color.GREEN);

        // print out the timer
        timer.render(game.batch, game.font);

        //checks if time is up
        if(timer.getWorldTimer() <= 0){

            music.stop();
            this.dispose();
            game.setScreen(new MenuScreen(game));
        }

        //draws the grid
        game.batch.draw(grid, GRID_OFFSET_X, GRID_OFFSET_Y, GRID_WIDTH, GRID_HEIGHT);

        //update and renders the player
        player.update();
        player.render(game.batch);


        // updates the location of the boulder
        if(!boulder.remove){
            boulder.update(delta);
            boulder.render(game.batch);
        } // renders the boulder

        //ends SpriteBatch
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
        /*player.dispose();*/
        grid.dispose();

    }
}
