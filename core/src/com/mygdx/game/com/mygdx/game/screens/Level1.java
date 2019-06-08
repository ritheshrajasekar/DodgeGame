package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.physicalEntities.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Level1 implements Screen {

    public static final int PLAYER_MOVE_DISTANCE = 63;//9*7, 7 is the scalar multiplier for all sprites
    public static final int PLAYER_SIZE = 56;//8*7
    public static final int COIN_SIZE = 56; // 8*7
    public static final int GRID_WIDTH = 665;
    public static final int GRID_HEIGHT = 665;
    public static final int GRID_OFFSET_X = 588;
    public static final int GRID_OFFSET_Y = 27;
    public static final int GRID_CORNER_SIZE = 84;//12*7
    public static final String[] DIRECTIONS = {"UP", "DOWN", "LEFT", "RIGHT"};
    public static final int MAX_BOULDERS = 6;
    public static final String WORLD = "GRASS";
    public static final String LEVEL = " LEVEL 1";

    private CopyOnWriteArrayList<Boulder> boulderList = new CopyOnWriteArrayList<Boulder>();
    private CopyOnWriteArrayList<Coin> coinList = new CopyOnWriteArrayList<Coin>();
    private Music music;
    private DodgeGame game;
    private Texture grid;
    private MyTimer timer;
    private Player player;

    private boolean bouldersSpawned;

    public Level1(DodgeGame game) {
        this.game = game;
        grid = new Texture("dodgeGrid.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("spinAndBurst.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();

        player = new Player();
    }

    public void show(){
        timer = new MyTimer(60);
    }

    public void render(float delta) {
        //creates background
        Gdx.gl.glClearColor(.135f, .206f, .235f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

        if (timer.getWorldTimer() % 5 == 0 && !bouldersSpawned){
            respawnBoulders();
            bouldersSpawned = true;
        }
        if (timer.getWorldTimer() % 5 != 0){
            bouldersSpawned = false;
        }

        // updates and renders each boulder in the list
        for (Boulder b : boulderList){
            if (b.isOnScreen){
                // updates the location of the boulder
                b.update(delta);
                // renders the boulder
                b.render(game.batch);
            }
        }

        game.font.setColor(Color.PURPLE);
        game.font.getData().setScale(4f);
        game.font.draw(game.batch, WORLD, (int)(DodgeGame.WIDTH * 0.07) , DodgeGame.HEIGHT/2 + 300);
        game.font.draw(game.batch, LEVEL, (int)(DodgeGame.WIDTH * 0.25) , DodgeGame.HEIGHT/2 + 300);
        game.batch.end();
    }

    public void respawnCoin(){
        coinList.clear();
        for (int i = 0; i < 2; i++){
            coinList.add(new Coin());
            if (i == 1 && coinList.get(0).x == coinList.get(1).x && coinList.get(0).y == coinList.get(1).y){
                i--;
                coinList.remove(1);
            }
        }
    }

    public void respawnBoulders(){
        boulderList.clear();
        for (int i = 0; i <= (int)(MAX_BOULDERS * Math.random()); i++){
            boulderList.add(new Boulder(DIRECTIONS[(int)(Math.random()*3)]));
            new BlinkingArrow(boulderList.get(i).direction,boulderList.get(i).x, boulderList.get(i).y).render(game.batch);
        }
    }

    // public void spawnCoins() - clear the arrayList of coins, and then adds 2 new coins
    // need to add collision detection later

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
