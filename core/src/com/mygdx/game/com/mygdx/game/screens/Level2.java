package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;


public class Level2 extends Level implements Screen{
    public Level2(DodgeGame g) {
        currentLevelNumber = 2;
        game = g;
        levelTexture = new Texture("sprites/dodgeGrassBG.png");
        coins = 0;
        world = "GRASS";
        level = " LEVEL 2";
        coinSpawnInterval = 10;
        minBoulders = 4;
        maxBoulders = 7;
        boulderSpawnInterval = 4;
        boulderSpawnDelay = 2;

        playMusic("music/05 - Grass World.mp3");
        createPlayer();
    }

    public void render(float delta) {
        displayBackground(levelTexture);

        //don't mess around with the order of the display methods unless you know what you're doing
        //it will cause a SpriteBatch.begin must be called before draw or SpriteBatch music be called before end error
        //for some reason displayTimer music be called first
        displayTimer(delta);
        displayWorldAndLevel();
        drawGrid();
        displayCoinCounter();

        detectCollision();
        detectCoin();

        spawnEntities();
        renderEntities(delta);

        game.batch.end();
    }

    public void spawnEntities() {
        spawnCoins();
        spawnBoulders();
    }

    public void renderEntities(float delta) {
        renderPlayer();
        renderCoins();
        renderBoulders(delta);
    }

    public void detectCollision() {
        detectBoulderCollision();
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

