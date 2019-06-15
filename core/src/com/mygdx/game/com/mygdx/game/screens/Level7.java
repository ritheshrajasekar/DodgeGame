package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.entities.*;


public class Level7 extends Level implements Screen {
    public Level7(DodgeGame g) {
        currentLevelNumber = 7;
        game = g;
        levelTexture = new Texture("sprites/dodgeJungleBG.png");
        coins = 0;
        world = "JUNGLE";
        level = " LEVEL 7";

        Boulder.min = 1;
        Boulder.max = 3;
        Boulder.spawnInterval = 4;
        Boulder.spawnDelay = 2;

        Cannon.min = 1;
        Cannon.max = 3;
        Cannon.spawnInterval = 5;
        Cannon.spawnDelay = 3;

        Boomerang.min = 1;
        Boomerang.max = 3;
        Boomerang.spawnInterval = 5;
        Boomerang.spawnDelay = 3;

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
