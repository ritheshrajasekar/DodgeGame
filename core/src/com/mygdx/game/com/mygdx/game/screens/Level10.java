package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;


public class Level10 extends Level implements Screen {
    public Level10(DodgeGame g) {
        currentLevelNumber = 10;
        game = g;
        levelTexture = new Texture("sprites/dodgeHellBG.png");
        coins = 0;
        world = "HELL";
        level = " LEVEL 10";

        minBoulders = 1;
        maxBoulders = 2;
        boulderSpawnInterval = 4;
        boulderSpawnDelay = 2;

        minCannons = 1;
        maxCannons = 2;
        cannonSpawnInterval = 4;
        cannonSpawnDelay = 3;

        minBoomerangs = 4;
        maxBoomerangs = 5;
        boomerangSpawnInterval = 5;
        boomerangSpawnDelay = 3;

        minLasers = 1;
        maxLasers = 2;
        laserSpawnInterval = 4;
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
