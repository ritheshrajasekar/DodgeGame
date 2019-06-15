// this class serves as the screen which is level11
// created by Matt Seng and Zak Asis

package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.entities.Boomerang;
import com.mygdx.game.entities.Boulder;
import com.mygdx.game.entities.Cannon;
import com.mygdx.game.entities.Laser;


public class Level11 extends Level implements Screen {
    public Level11(DodgeGame g) {
        currentLevelNumber = 11;
        game = g;
        levelTexture = new Texture("sprites/dodgeHellBG.png");
        coins = 0;
        world = "HELL";
        level = " LEVEL 11";

        Boulder.min = 1;
        Boulder.max = 3;
        Boulder.spawnInterval = 5;
        Boulder.spawnDelay = 2;

        Cannon.min = 1;
        Cannon.max = 3;
        Cannon.spawnInterval = 5;
        Cannon.spawnDelay = 3;

        Boomerang.min = 1;
        Boomerang.max = 2;
        Boomerang.spawnInterval = 5;
        Boomerang.spawnDelay = 3;

        Laser.min = 2;
        Laser.max = 3;
        Laser.spawnInterval = 4.5;
        Laser.spawnDelay = 3;

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
        displayInvincibilityBar();

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
        renderPlayer(delta);
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
