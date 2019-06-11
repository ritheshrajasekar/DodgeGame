package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;


public class Level6 extends Level implements Screen{
    public Level6(DodgeGame g) {
        currentLevelNumber = 6;
        game = g;
        levelTexture = new Texture("sprites/dodgeDesertBG.png");
        coins = 0;
        world = "SAND";
        level = " LEVEL 6";
        coinSpawnInterval = 10;

        minBoulders = 3;
        maxBoulders = 6;
        boulderSpawnInterval = 4;
        boulderSpawnDelay = 2;

        minCannons = 3;
        maxCannons = 6;
        cannonSpawnInterval = 5;
        cannonSpawnDelay = 3;

        playMusic("music/06 - Sand World.mp3");
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
        spawnCannon();
    }

    public void renderEntities(float delta) {
        renderPlayer();
        renderCoins();
        renderBoulders(delta);
        renderCannons(delta);
    }

    public void detectCollision() {
        detectBoulderCollision();
        detectCannonCollision();
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
