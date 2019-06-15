//serves as the class which is the options menu
//created by Rithesh Rajasekar and edited by Rithik Rajasekar

package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.utilities.FileStreaming;

import static com.mygdx.game.com.mygdx.game.screens.Level.NUM_LEVELS;
import static com.mygdx.game.com.mygdx.game.screens.Level.isMuted;

public class Info implements Screen {
    private DodgeGame game;
    private Texture muteButton;
    private Texture unmuteButton;
    private Texture clearAllButton;
    private Texture homeButton;
    private Texture unlockButton;
    private Texture background;
    private Texture clearAllClicked;
    private Texture unlockAllClicked;
    private Texture helpButton;

    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 100;
    private static final int HOME_BUTTON_SIZE = 100;
    private static final int HOME_BUTTON_XVAL = 0;
    private static final int HOME_BUTTON_YVAL = DodgeGame.HEIGHT - HOME_BUTTON_SIZE;

    private static final int MUTE_BUTTON_Y_VALUE = (int) (DodgeGame.HEIGHT * 0.01);
    private static final int CLEAR_BUTTON_Y_VALUE = (int) (DodgeGame.HEIGHT * 0.16);
    private static final int UNLOCK_BUTTON_Y_VALUE = (int) (DodgeGame.HEIGHT * 0.31);
    private static final int OPTION_BUTTON_Y_VALUE = (int) (DodgeGame.HEIGHT * 0.46);
    private Music music;

    public static Sprite backgroundSprite;

    //private timer Timer;
    public Info(DodgeGame game) {
        this.game = game;
        muteButton = new Texture("sprites/muteButton.png");
        unmuteButton = new Texture("sprites/unmuteButton.png");
        clearAllButton = new Texture("sprites/clearDataButton.png");
        unlockButton = new Texture("sprites/unlockAllButton.png");
        homeButton = new Texture("sprites/homeButton.png");
        clearAllClicked = new Texture("sprites/clearDataButtonClicked.png");
        unlockAllClicked = new Texture("sprites/unlockAllButtonClicked.png");
        helpButton = new Texture("sprites/helpButton.png");

        background = new Texture("sprites/dodgeStartScreen.png");
        backgroundSprite = new Sprite(background);
        backgroundSprite.scale(6);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/01 - Menu.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        if (!isMuted) {
            music.play();
        }
    }

    public void show() {

    }

    public void render(float delta) {
        //sets background
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        backgroundSprite.setPosition(DodgeGame.WIDTH / 2 - backgroundSprite.getWidth() / 2, DodgeGame.HEIGHT / 2 - backgroundSprite.getHeight() / 2);
        backgroundSprite.draw(game.batch);

        //draws mute or unmute button and then checks if its clicked
        if (isMuted) {
            game.batch.draw(unmuteButton, DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2, MUTE_BUTTON_Y_VALUE, BUTTON_WIDTH, BUTTON_HEIGHT);
        } else {
            game.batch.draw(muteButton, DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2, MUTE_BUTTON_Y_VALUE, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        if (Gdx.input.getX() < DodgeGame.WIDTH / 2 + BUTTON_WIDTH / 2 &&
                Gdx.input.getX() > DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2 &&
                DodgeGame.HEIGHT - Gdx.input.getY() > MUTE_BUTTON_Y_VALUE &&
                DodgeGame.HEIGHT - Gdx.input.getY() < MUTE_BUTTON_Y_VALUE + BUTTON_HEIGHT && Gdx.input.justTouched()) {
            if (!isMuted) {
                isMuted = true;
                music.pause();
            } else if (isMuted) {
                isMuted = false;
                music.play();
            }
        }

        //displays clearAllButton and see if it's clicked
        game.batch.draw(clearAllButton, DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2, CLEAR_BUTTON_Y_VALUE, BUTTON_WIDTH, BUTTON_HEIGHT);
        if (Gdx.input.getX() < DodgeGame.WIDTH / 2 + BUTTON_WIDTH / 2 &&
                Gdx.input.getX() > DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2 &&
                DodgeGame.HEIGHT - Gdx.input.getY() > CLEAR_BUTTON_Y_VALUE &&
                DodgeGame.HEIGHT - Gdx.input.getY() < CLEAR_BUTTON_Y_VALUE + BUTTON_HEIGHT && Gdx.input.justTouched()) {
            //clear all save data
            FileStreaming.stars = new int[NUM_LEVELS];
            FileStreaming.unlockedLevel = 1;
            FileStreaming.write();
            game.batch.draw(clearAllClicked, DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2, CLEAR_BUTTON_Y_VALUE, BUTTON_WIDTH, BUTTON_HEIGHT);

        }

        //displays unlock all levels buttons and see if it's clicked
        game.batch.draw(unlockButton, DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2, UNLOCK_BUTTON_Y_VALUE, BUTTON_WIDTH, BUTTON_HEIGHT);
        if (Gdx.input.getX() < DodgeGame.WIDTH / 2 + BUTTON_WIDTH / 2 &&
                Gdx.input.getX() > DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2 &&
                DodgeGame.HEIGHT - Gdx.input.getY() > UNLOCK_BUTTON_Y_VALUE &&
                DodgeGame.HEIGHT - Gdx.input.getY() < UNLOCK_BUTTON_Y_VALUE + BUTTON_HEIGHT && Gdx.input.justTouched()) {
            //unlocks all levels
            game.batch.draw(unlockAllClicked, DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2, UNLOCK_BUTTON_Y_VALUE, BUTTON_WIDTH, BUTTON_HEIGHT);
            FileStreaming.unlockedLevel = 12;
            FileStreaming.write();
        }

        //displays help button and sees if it is clicked
        game.batch.draw(helpButton, DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2, OPTION_BUTTON_Y_VALUE, BUTTON_WIDTH, BUTTON_HEIGHT);
        if (Gdx.input.getX() < DodgeGame.WIDTH / 2 + BUTTON_WIDTH / 2 &&
                Gdx.input.getX() > DodgeGame.WIDTH / 2 - BUTTON_WIDTH / 2 &&
                DodgeGame.HEIGHT - Gdx.input.getY() > OPTION_BUTTON_Y_VALUE &&
                DodgeGame.HEIGHT - Gdx.input.getY() < OPTION_BUTTON_Y_VALUE + BUTTON_HEIGHT && Gdx.input.justTouched()){
            music.stop();
            this.dispose();
            game.setScreen(new Help(game));
        }

        //displays home button and see if it's clicked
        game.batch.draw(homeButton, HOME_BUTTON_XVAL, HOME_BUTTON_YVAL, HOME_BUTTON_SIZE, HOME_BUTTON_SIZE);
        if (Gdx.input.getX() > HOME_BUTTON_XVAL &&
                Gdx.input.getX() < HOME_BUTTON_XVAL + HOME_BUTTON_SIZE &&
                DodgeGame.HEIGHT - Gdx.input.getY() > HOME_BUTTON_YVAL &&
                DodgeGame.HEIGHT - Gdx.input.getY() < HOME_BUTTON_YVAL + HOME_BUTTON_SIZE &&
                Gdx.input.justTouched() ||
                Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            music.stop();
            this.dispose();
            game.setScreen(new Start(game));
        }

        game.batch.end();
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
        music.dispose();
        background.dispose();
        muteButton.dispose();
        unlockButton.dispose();
        unmuteButton.dispose();
        clearAllButton.dispose();
        homeButton.dispose();

    }
}
