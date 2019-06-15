// this class serves as the screen which is level1
// created by Rithik Rajasekar and Matt Seng

package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.entities.Boulder;


public class Level1 extends Level implements Screen {
    public Level1(DodgeGame g) {
        currentLevelNumber = 1;
        game = g;
        levelTexture = new Texture("sprites/dodgeGrassBG.png");
        coins = 0;
        world = "GRASS";
        level = " LEVEL 1";

        Boulder.min = 3;
        Boulder.max = 6;
        Boulder.spawnInterval = 5;
        Boulder.spawnDelay = 2;

        playMusic("music/05 - Grass World.mp3");
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
    }

    public void renderEntities(float delta) {
        renderPlayer(delta);
        renderCoins();
        renderBoulders(delta);
    }

    public void detectCollision() {
        detectBoulderCollision();
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
