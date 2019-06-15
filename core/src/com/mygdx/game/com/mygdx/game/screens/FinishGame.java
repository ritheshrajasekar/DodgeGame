package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.DodgeGame;

import static com.mygdx.game.com.mygdx.game.screens.Level.isMuted;

public class FinishGame implements Screen {
    private DodgeGame game;
    private Texture homeButton = new Texture("sprites/homeButton.png");
    private Texture endGmeBackground = new Texture("sprites/gregory.png");
    private Texture trophy = new Texture("sprites/trophy.png");
    private Texture congratulations = new Texture("sprites/congratulations.png");

    private static final int X_VALUE_HOME_BUTTON = 1180;
    private static final int Y_VALUE_HOME_BUTTON = 620;
    private static final int HOME_BUTTON_SIZE = 100;
    private static final int X_VALUE_TROPHY = 100;
    private static final int Y_VALUE_TROPHY = 100;
    private static final int TROPHY_WIDTH = 160;
    private static final int TROPHY_HEIGHT= 93;
    private static final int CONGRATULATIONS_WIDTH = 1280;
    private static final int CONGRATULATIONS_HEIGHT= 350;
    private static final int X_VALUE_CONGRATULATIONS = DodgeGame.WIDTH / 2 - CONGRATULATIONS_WIDTH / 2;
    private static final int Y_VALUE_CONGRATULATIONS = (int)(DodgeGame.HEIGHT * 0.45);


    public static Sprite endGameBackgroundSprite;





    private Music music;

    public static Sprite backgroundSprite;

    //private timer Timer;
    public FinishGame(DodgeGame game) {
        this.game = game;

        endGameBackgroundSprite = new Sprite(endGmeBackground);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/10 - Ending.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        if(!isMuted){
            music.play();
        }
    }

    public void show() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        endGameBackgroundSprite.setPosition(DodgeGame.WIDTH / 2 - endGameBackgroundSprite.getWidth() / 2, DodgeGame.HEIGHT / 2 - endGameBackgroundSprite.getHeight() / 2);
        endGameBackgroundSprite.draw(game.batch);


        //draws home button
        game.batch.draw(homeButton, X_VALUE_HOME_BUTTON, Y_VALUE_HOME_BUTTON, HOME_BUTTON_SIZE, HOME_BUTTON_SIZE);

        //checks home button click
        if (Gdx.input.getX() > X_VALUE_HOME_BUTTON &&
                Gdx.input.getX() < X_VALUE_HOME_BUTTON + HOME_BUTTON_SIZE &&
                DodgeGame.HEIGHT - Gdx.input.getY() > Y_VALUE_HOME_BUTTON &&
                DodgeGame.HEIGHT - Gdx.input.getY() < Y_VALUE_HOME_BUTTON + HOME_BUTTON_SIZE &&
                Gdx.input.justTouched() ||
                Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            music.stop();
            this.dispose();
            game.setScreen(new Start(game));
        }

        game.batch.draw(trophy, X_VALUE_TROPHY, Y_VALUE_TROPHY, TROPHY_WIDTH, TROPHY_HEIGHT);

        game.batch.draw(congratulations, X_VALUE_CONGRATULATIONS, Y_VALUE_CONGRATULATIONS, CONGRATULATIONS_WIDTH, CONGRATULATIONS_HEIGHT);

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
        endGmeBackground.dispose();
        trophy.dispose();
        homeButton.dispose();
    }
}
