package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;


public class Level8 extends Level implements Screen {
    public Level8(DodgeGame g) {
        currentLevelNumber = 8;
        game = g;
        levelTexture = new Texture("sprites/dodgeJungleBG.png");
        coins = 0;
        world = "JUNGLE";
        level = " LEVEL 8";

        minBoulders = 2;
        maxBoulders = 4;
        boulderSpawnInterval = 4;
        boulderSpawnDelay = 2;

        minCannons = 2;
        maxCannons = 4;
        cannonSpawnInterval = 5;
        cannonSpawnDelay = 3;

        minBoomerangs = 2;
        maxBoomerangs = 4;
        boomerangSpawnInterval = 4.5;
        boomerangSpawnDelay = 3;

        playMusic("music/07 - Jungle World.mp3");
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
    }

    public void renderEntities(float delta) {
        renderPlayer();
        renderCoins();
        renderBoulders(delta);
        renderCannons(delta);
        renderBoomerang(delta);
    }

    public void detectCollision() {
        detectBoulderCollision();
        detectCannonCollision();
        detectBoomerangCollision();
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
