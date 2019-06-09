package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;


public class Level3 extends Level implements Screen{
    Texture level1;

    public Level3(DodgeGame g) {
        currentLevelNumber = 3;
        levelNum = 3;
        game = g;
        level1 = new Texture("dodgeGrassBG.png");

        world = "GRASS";
        level = " LEVEL 3";
        coinSpawnInterval = 10;
        minBoulders = 5;
        maxBoulders = 8;
        boulderSpawnInterval = 2;
        boulderSpawnDelay = 3;

        playMusic();
        createPlayer();
    }

    public void render(float delta) {
        //rithesh i know youre doing something with the background here so imma leave this here
        Gdx.gl.glClearColor(.135f, .206f, .235f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //setting the background through passing in Texture into another method in Level which Level1 extends
        displayBackground(level1);

        //creates background



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

    }
}

