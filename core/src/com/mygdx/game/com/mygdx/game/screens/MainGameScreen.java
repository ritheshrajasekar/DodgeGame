package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.physicalEntities.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainGameScreen implements Screen {


    public static final int PLAYER_MOVE_DISTANCE = 63;//9*7, 7 is the scalar multiplier for all sprites
    public static final int PLAYER_SIZE = 56;//8*7
    public static final int COIN_SIZE = 56; // 8*7
    public static final int GRID_WIDTH = 665;
    public static final int GRID_HEIGHT = 665;
    public static final int GRID_OFFSET_X = 588;
    public static final int GRID_OFFSET_Y = 27;
    public static final int GRID_CORNER_SIZE = 84;//12*7
    public static final String[] directions  = {"UP", "DOWN", "LEFT", "RIGHT"};
    public static final int maxBoulders = 6;

    private CopyOnWriteArrayList<Boulder> boulderList = new CopyOnWriteArrayList<Boulder>();
    private CopyOnWriteArrayList<Coin> coinList = new CopyOnWriteArrayList<Coin>();
    private Music music;
    private DodgeGame game;
    private Texture grid;
    private MyTimer timer;
    private Player player;


    public MainGameScreen(DodgeGame game) {
        this.game = game;
        grid = new Texture("dodgeGrid.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("spinAndBurst.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();

        //boulder = new Boulder(randDirection);

        for(int i = 0; i <= (int)(maxBoulders * Math.random()); i++){
            boulderList.add(new Boulder(directions[(int)(Math.random()*3)]));
        }
        for(int i = 0; i < 2; i++){
            coinList.add(new Coin());
            coinList.add(new Coin());
        }

        player = new Player();
    }

    public void show(){
        timer = new MyTimer(60);
    }

    public void render(float delta) {
        //timer code


        //creates background
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renders entities


        // print out the timer
        timer.update(delta);
        timer.render(game.batch, game.font);
        //checks if time is up
        if (timer.getWorldTimer() <= 0) {

            music.stop();
            this.dispose();
            game.setScreen(new MenuScreen(game));
        }

        //draws the grid
        game.batch.draw(grid, GRID_OFFSET_X, GRID_OFFSET_Y, GRID_WIDTH, GRID_HEIGHT);

        //update and renders the player
        player.update();

        player.render(game.batch);

        //update Coin
        if (timer.getWorldTimer() % 10 == 0) {
            respawnCoin();

        }
        else {
            for (Coin c : coinList) {
                c.update();
            }

            for (Coin c : coinList) {
            c.render(game.batch);
        }
    }




        // goes through each boulder in the list
        for(Boulder b : boulderList){
            if(b.isOnScreen){
                // updates the location of the boulder
                b.update(delta);
                // renders the boulder
                b.render(game.batch);
            }

            else if(timer.getWorldTimer() % 5 == 0){
                respawnBoulders();
            }
        }


        game.batch.end();
    }

    public void respawnCoin(){
        coinList.clear();
        for(int i = 0; i < 2; i++){
            coinList.add(new Coin());
            if(i == 1 && coinList.get(0).getxCoord() == coinList.get(1).getxCoord() && coinList.get(0).getyCoord() == coinList.get(1).getyCoord()){
                i--;
                coinList.remove(1);
            }
        }
    }
    public void respawnBoulders(){
        boulderList.clear();

        for(int i = 0; i <= (int)(maxBoulders * Math.random()); i++){
            boulderList.add(new Boulder(directions[(int)(Math.random()*3)]));
        }

    }

    // public void spawnCoins() - clear the arrayList of coins, and then adds 2 new coins
    // need to add colission detection later

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
        grid.dispose();
    }
}
