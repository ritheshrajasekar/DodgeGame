package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;


public class Level11 extends Level implements Screen {
    public Level11(DodgeGame g) {
        currentLevelNumber = 10;
        game = g;
        levelTexture = new Texture("sprites/dodgeHellBG.png");
        coins = 0;
        world = "HELL";
        level = " LEVEL 11";

        minBoulders = 2;
        maxBoulders = 3;
        boulderSpawnInterval = 5;
        boulderSpawnDelay = 2;

        minCannons = 2;
        maxCannons = 3;
        cannonSpawnInterval = 5;
        cannonSpawnDelay = 3;

        minBoomerangs = 2;
        maxBoomerangs = 2;
        boomerangSpawnInterval = 6;
        boomerangSpawnDelay = 3;

        minLasers = 2;
        maxLasers = 3;
        laserSpawnInterval = 5;
        laserSpawnDelay = 3;

        playMusic("music/08 - Hell World.mp3");
        createPlayer();
    }

    public void render(float delta) {
        displayBackground(levelTexture);

        //don't mess around with the order of the display methods unless you know what you're doing
        //it will cause a "SpriteBatch.begin must be called before draw" or "SpriteBatch music be called before end" error
        //for some reason displayTimer must be called first
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
        spawnBoomerang();
        spawnLaser();
    }

    public void renderEntities(float delta) {
        renderPlayer();
        renderCoins();
        renderBoulders(delta);
        renderCannons(delta);
        renderBoomerang(delta);
        renderLaser(delta);
    }

    public void detectCollision() {
        detectBoulderCollision();
        detectCannonCollision();
        detectBoomerangCollision();
        detectLaserCollision();
    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {
        super.dispose();
    }
}
