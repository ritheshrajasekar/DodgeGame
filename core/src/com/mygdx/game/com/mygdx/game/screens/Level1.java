package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.physicalEntities.*;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Level1 extends Level implements Screen{
    private CopyOnWriteArrayList<Coin> coinList = new CopyOnWriteArrayList<Coin>();
    private CopyOnWriteArrayList<Boulder> boulderList = new CopyOnWriteArrayList<Boulder>();;
    private CopyOnWriteArrayList<BlinkingArrow> arrowList = new CopyOnWriteArrayList<BlinkingArrow>();

    public static final int MIN_BOULDERS = 3;
    public static final int MAX_BOULDERS = 6;
    public static final int BOULDER_SPAWN_INTERVAL = 5;
    public static final int BOULDER_SPAWN_DELAY = 2;
    public static final int COIN_SPAWN_TIME = 10;
    private boolean bouldersSpawned;
    private boolean coinsSpawned;

    public Level1(DodgeGame g) {
        game = g;
        grid = new Texture("dodgeGrid.png");
        world = "GRASS";
        level = " LEVEL 1";

        music = Gdx.audio.newMusic(Gdx.files.internal("spinAndBurst.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();

        player = new Player();
        player.x = 4;
        player.y = 4;
    }

    public void show(){
        timer = new MyTimer(60);
    }

    public void render(float delta) {
        //creates background
        Gdx.gl.glClearColor(.135f, .206f, .235f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //print out the timer
        timer.update(delta);
        timer.render(game.batch, game.font);

        //checks if time is up
        if (timer.getWorldTimer() <= 0) {
            music.stop();
            this.dispose();
            game.setScreen(new StartScreen(game));
        }

        //draws the grid
        game.batch.draw(grid, GRID_OFFSET_X, GRID_OFFSET_Y, GRID_WIDTH, GRID_HEIGHT);

        spawnEntities();
        
        renderEntities(delta);

        detectCollision();

        detectCoin();

        game.font.setColor(Color.PURPLE);
        game.font.getData().setScale(4f);
        game.font.draw(game.batch, world, (int)(DodgeGame.WIDTH * 0.07) , DodgeGame.HEIGHT/2 + 300);
        game.font.draw(game.batch, level, (int)(DodgeGame.WIDTH * 0.25) , DodgeGame.HEIGHT/2 + 300);
        game.batch.end();
    }

    public void spawnCoins(){
        coinList.clear();
        for (int i = 0; i < 2; i++){
            coinList.add(new Coin());
            if (i == 1 && coinList.get(0).x == coinList.get(1).x && coinList.get(0).y == coinList.get(1).y){
                i--;
                coinList.remove(1);
            }
        }
    }

    //spawns boulders and blinking arrows
    public void spawnBoulders(){
        //x and y lists to test if it's trying spawn a boulder where one already exists
        ArrayList<Integer> xList = new ArrayList<Integer>();
        ArrayList<Integer> yList = new ArrayList<Integer>();

        for (int i = 0; i < (int)((MAX_BOULDERS - MIN_BOULDERS + 1) * Math.random() + MIN_BOULDERS); i++){
            int x = 0;
            int y = 0;
            String direction = DIRECTIONS[(int)(Math.random() * 4)];

            // randomly assigns a spawn position to the boulder based on what the direction of the boulder is
            if (direction == "UP"){
                x = (int)(Math.random() * 8);
                y = -1;
            }
            else if (direction == "DOWN"){
                x = (int)(Math.random() * 8);
                y = 8;
            }
            else if (direction == "LEFT"){
                x = 8;
                y = (int)(Math.random() * 8);
            }
            else if (direction == "RIGHT"){
                x = -1;
                y = (int)(Math.random() * 8);
            }

            //does not create a boulder if one is already there
            boolean inList = false;
            for (int tempX : xList) {
                for (int tempY : yList) {
                    if (x == tempX && y == tempY) {
                        inList = true;
                    }
                }
            }
            if (!inList) {
                boulderList.add(new Boulder(x, y, direction));
                arrowList.add(new BlinkingArrow(x, y, direction));
                xList.add(x);
                yList.add(y);
            } else {
                i--;
            }
        }
    }

    public void spawnEntities() {
        //spawns coins
        if (timer.getWorldTimer() % COIN_SPAWN_TIME == 0 && !coinsSpawned){
            spawnCoins();
            coinsSpawned = true;
        }
        if (timer.getWorldTimer() % COIN_SPAWN_TIME != 0){
            coinsSpawned = false;
        }

        //spawns boulders
        if (timer.getWorldTimer() % BOULDER_SPAWN_INTERVAL == 0 && !bouldersSpawned){
            spawnBoulders();
            bouldersSpawned = true;
        }
        if (timer.getWorldTimer() % BOULDER_SPAWN_INTERVAL != 0){
            bouldersSpawned = false;
        }
    }

    public void renderEntities(float delta) {
        //update and renders the player
        player.update();
        player.render(game.batch);

        //updates and renders each coin in the list
        for (Coin c : coinList) {
            c.update();
            c.render(game.batch);
        }

        //updates and renders each boulder and arrow in the list
        for (int i = 0; i < boulderList.size(); i++) {
            //only updates and renders once the boulder is spawned
            if (boulderList.get(i).spawned) {
                //updates and render if the boulder is on screen
                if (boulderList.get(i).isOnScreen) {
                    boulderList.get(i).update(delta);
                    boulderList.get(i).render(game.batch);
                //deletes boulder and arrow once the boulder leaves the screen
                } else {
                    boulderList.remove(i);
                    arrowList.remove(i);
                    i--;
                }
            //if the boulder hasn't spawned yet, the arrow will blink
            //(it will stop blinking after the boulder is spawned because it exits this else statement)
            } else {
                arrowList.get(i).render(game.batch);
                if (arrowList.get(i).elapsedTime > BOULDER_SPAWN_DELAY)
                    boulderList.get(i).spawned = true;
            }
        }
    }

    public void detectCollision() {
        for (int i = 0; i < boulderList.size(); i++) {
            if (boulderList.get(i).x == player.x && boulderList.get(i).y == player.y) {
                //loseSound.play()
                this.dispose();
                game.setScreen(new StartScreen(game));
            }
        }
    }

    public void detectCoin() {
        for (int i = 0; i < coinList.size(); i++) {
            if (coinList.get(i).x == player.x && coinList.get(i).y == player.y) {
                coins++;
                //coinSound.play();
                coinList.remove(i);
            }
        }
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
        grid.dispose();
    }
}
