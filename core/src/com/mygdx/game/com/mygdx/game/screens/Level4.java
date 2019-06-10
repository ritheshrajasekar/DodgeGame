package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.utilities.FileStreaming;


public class Level4 extends Level implements Screen{
    public Level4(DodgeGame g) {
        currentLevelNumber = 4;
        game = g;
        levelTexture = new Texture("dodgeGrassBG.png");
        coins = 0;
        world = "SAND";
        level = " LEVEL 1";
        coinSpawnInterval = 10;

/*      minBoulders = 3;
        maxBoulders = 6;
        boulderSpawnInterval = 5;
        boulderSpawnDelay = 2;*/

        minCannon = 3;
        maxCannon = 6;
        cannonSpawnInterval = 5;
        cannonSpawnDelay = 2;

        playMusic();
        createPlayer();
    }

    public void render(float delta) {
        displayBackground(levelTexture);

        FileStreaming.write();
        FileStreaming.read();

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
        //spawnBoulders();
        spawnCannon();
    }

    public void renderEntities(float delta) {
        renderPlayer();
        renderCoins();
        //renderBoulders(delta);
        renderCannon(delta);
    }

    public void detectCollision() {
        //detectBoulderCollision();
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
