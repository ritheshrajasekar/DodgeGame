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

public class Help implements Screen {
    private DodgeGame game;
    private Texture background;
    private Music music;
    private Texture optionsButtonInactive;

    public static Sprite backgroundSprite;

    private static final int OPTIONS_BUTTON_WIDTH = 150;
    private static final int OPTION_BUTTON_HEIGHT = 75;
    private static final int OPTIONS_BUTTON_XVAL = 0;
    private static final int OPTIONS_BUTTON_YVAL = DodgeGame.HEIGHT - OPTION_BUTTON_HEIGHT;

    public Help(DodgeGame game){
        this.game = game;
        optionsButtonInactive = new Texture("sprites/optionsButton.jpg");
        background = new Texture("sprites/instructions.png");
        backgroundSprite = new Sprite(background);
        backgroundSprite.scale(6);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/01 - Menu.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        if (!isMuted) {
            music.play();
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //sets the background to the instructions
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        backgroundSprite.setPosition(DodgeGame.WIDTH / 2 - backgroundSprite.getWidth() / 2, DodgeGame.HEIGHT / 2 - backgroundSprite.getHeight() / 2);
        backgroundSprite.draw(game.batch);

        //displays home button and see if it's clicked
        game.batch.draw(optionsButtonInactive, OPTIONS_BUTTON_XVAL, OPTIONS_BUTTON_YVAL, OPTIONS_BUTTON_WIDTH, OPTION_BUTTON_HEIGHT);
        if (Gdx.input.getX() > OPTIONS_BUTTON_XVAL &&
                Gdx.input.getX() < OPTIONS_BUTTON_XVAL + OPTIONS_BUTTON_WIDTH &&
                DodgeGame.HEIGHT - Gdx.input.getY() > OPTIONS_BUTTON_YVAL &&
                DodgeGame.HEIGHT - Gdx.input.getY() < OPTIONS_BUTTON_YVAL + OPTION_BUTTON_HEIGHT &&
                Gdx.input.justTouched() ||
                Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            music.stop();
            this.dispose();
            game.setScreen(new Options(game));
        }

/*
        if( Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            music.stop();
            this.dispose();
            game.setScreen(new Options(game));
        }
*/

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
