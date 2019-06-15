//this class serves as the start screen for the game
//created by Rithesh Rajasekar

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

import static com.mygdx.game.com.mygdx.game.screens.Level.currentLevelNumber;
import static com.mygdx.game.com.mygdx.game.screens.Level.isMuted;

public class Start implements Screen {
    private DodgeGame game;
    private Texture exitButtonInactive;
    private Texture exitButtonActive;
    private Texture playButtonInactive;
    private Texture playButtonActive;
    private Texture startScreenBackground;
    private Texture optionsButtonInactive;
    private Texture optionsButtonActive;
    private Texture trophy = new Texture("sprites/trophy.png");
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int OPTIONS_BUTTON_WIDTH = 300;
    private static final int OPTION_BUTTON_HEIGHT = 150;
    private static final int PLAY_BUTTON_Y_VALUE = (int) (DodgeGame.HEIGHT * 0.45);
    private static final int OPTIONS_BUTTON_Y_VALUE = (int) (DodgeGame.HEIGHT * 0.23);
    private static final int EXIT_BUTTON_Y_VALUE = (int) (DodgeGame.HEIGHT * 0.01);
    private Music music;

    public static Sprite backgroundSprite;

    //private timer Timer;
    public Start(DodgeGame game) {
        this.game = game;
        exitButtonInactive = new Texture("sprites/exitButton.jpg");
        exitButtonActive = new Texture("sprites/exitButtonActive.jpg");
        playButtonInactive = new Texture("sprites/playButton.jpg");
        playButtonActive = new Texture("sprites/playButtonActive.jpg");
        startScreenBackground = new Texture("sprites/dodgeStartScreen.png");

        optionsButtonInactive = new Texture("sprites/optionsButton.jpg");
        optionsButtonActive = new Texture("sprites/optionsButtonActive.jpg");
        backgroundSprite = new Sprite(startScreenBackground);
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
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        backgroundSprite.setPosition(DodgeGame.WIDTH / 2 - backgroundSprite.getWidth() / 2, DodgeGame.HEIGHT / 2 - backgroundSprite.getHeight() / 2);
        backgroundSprite.draw(game.batch);
        //draws two trophies if the user beat all the levels
        if (FileStreaming.unlockedLevel == 13) {
            game.batch.draw(trophy, 797, 193, 163, 93);
            game.batch.draw(trophy, 330, 193, 163, 93);
        }
        //checks exit button click
        if (Gdx.input.getX() < DodgeGame.WIDTH / 2 + EXIT_BUTTON_WIDTH / 2 &&
                Gdx.input.getX() > DodgeGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2 &&
                DodgeGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y_VALUE &&
                DodgeGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y_VALUE + EXIT_BUTTON_HEIGHT) {
            game.batch.draw(exitButtonActive, DodgeGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_Y_VALUE, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.justTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, DodgeGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_Y_VALUE, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        //checks level select button click
        if (Gdx.input.getX() < DodgeGame.WIDTH / 2 + PLAY_BUTTON_WIDTH / 2 &&
                Gdx.input.getX() > DodgeGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2 &&
                DodgeGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y_VALUE &&
                DodgeGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y_VALUE + PLAY_BUTTON_HEIGHT) {
            game.batch.draw(playButtonActive, DodgeGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_Y_VALUE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.justTouched()) {
                music.stop();
                this.dispose();
                game.setScreen(new LevelSelect(game));
            }
        } else {
            game.batch.draw(playButtonInactive, DodgeGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_Y_VALUE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
        //check options select button click
        if (Gdx.input.getX() < DodgeGame.WIDTH / 2 + OPTIONS_BUTTON_WIDTH / 2 &&
                Gdx.input.getX() > DodgeGame.WIDTH / 2 - OPTIONS_BUTTON_WIDTH / 2 &&
                DodgeGame.HEIGHT - Gdx.input.getY() > OPTIONS_BUTTON_Y_VALUE &&
                DodgeGame.HEIGHT - Gdx.input.getY() < OPTIONS_BUTTON_Y_VALUE + OPTION_BUTTON_HEIGHT) {
            game.batch.draw(optionsButtonActive, DodgeGame.WIDTH / 2 - OPTIONS_BUTTON_WIDTH / 2, OPTIONS_BUTTON_Y_VALUE, OPTIONS_BUTTON_WIDTH, OPTION_BUTTON_HEIGHT);
            if (Gdx.input.justTouched()) {
                music.stop();
                this.dispose();
                game.setScreen(new Options(game));
            }
        } else {
            game.batch.draw(optionsButtonInactive, DodgeGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, OPTIONS_BUTTON_Y_VALUE, OPTIONS_BUTTON_WIDTH, OPTION_BUTTON_HEIGHT);
        }
        //can also use escape to exit or space to go to level select
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            music.stop();
            this.dispose();
            game.setScreen(new LevelSelect(game));
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
        startScreenBackground.dispose();
        exitButtonActive.dispose();
        exitButtonInactive.dispose();
        playButtonActive.dispose();
        playButtonInactive.dispose();

    }
}