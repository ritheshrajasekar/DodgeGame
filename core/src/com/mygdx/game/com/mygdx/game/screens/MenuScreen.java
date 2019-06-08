package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;

public class MenuScreen implements Screen {
    private DodgeGame game;
    private Texture exitButtonInactive;
    private Texture exitButtonActive;
    private Texture playButtonInactive;
    private Texture playButtonActive;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_YVALUE = (int)(DodgeGame.HEIGHT * 0.1);
    private static final int PLAY_BUTTON_YVALUE = (int)(DodgeGame.HEIGHT * 0.4);
    private Music music;

    //private timer Timer;
    public MenuScreen(DodgeGame game){
        this.game = game;
        exitButtonInactive = new Texture("exitButton.jpg");
        exitButtonActive = new Texture("exitButtonActive.jpg");
        playButtonInactive = new Texture("playButton2.jpg");
        playButtonActive = new Texture("playButton2Active.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("myName.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }
    public void show(){

    }
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        if(Gdx.input.getX() < DodgeGame.WIDTH/2  + EXIT_BUTTON_WIDTH /2 &&
            Gdx.input.getX() > DodgeGame.WIDTH/2 - EXIT_BUTTON_WIDTH/2 &&
                 DodgeGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_YVALUE  &&
             DodgeGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_YVALUE + EXIT_BUTTON_HEIGHT) {
            game.batch.draw(exitButtonActive, DodgeGame.WIDTH/2 - EXIT_BUTTON_WIDTH/2,EXIT_BUTTON_YVALUE, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if(Gdx.input.isTouched() ==true){
                Gdx.app.exit();
            }
        }

        else {
            game.batch.draw(exitButtonInactive, DodgeGame.WIDTH/2 - EXIT_BUTTON_WIDTH/2,EXIT_BUTTON_YVALUE, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        if(Gdx.input.getX() < DodgeGame.WIDTH/2  + PLAY_BUTTON_WIDTH /2 &&
                Gdx.input.getX() > DodgeGame.WIDTH/2 - PLAY_BUTTON_WIDTH/2 &&
                DodgeGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_YVALUE  &&
                DodgeGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_YVALUE + PLAY_BUTTON_HEIGHT) {
            game.batch.draw(playButtonActive, DodgeGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YVALUE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isTouched() == true){
                music.stop();
                this.dispose();
                game.setScreen(new MainGameScreen(game));
            }
        }
        else {
            game.batch.draw(playButtonInactive, DodgeGame.WIDTH/2 - PLAY_BUTTON_WIDTH/2,PLAY_BUTTON_YVALUE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

        }
        //System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());

        //game.batch.draw(exitButtonInactive, DodgeGame.WIDTH/2 - EXIT_BUTTON_WIDTH/2,EXIT_BUTTON_YVALUE, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        //game.batch.draw(playButtonInactive, 0, 0, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

        //game.batch.draw(playButtonInactive, DodgeGame.WIDTH/2 - PLAY_BUTTON_WIDTH/2, PLAY_BUTTON_YVALUE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

        game.batch.end();
    }
    public void resize(int width, int height){

    }
    public void pause(){

    }
    public void resume(){

    }
    public void hide(){

    }
    public void dispose(){
        music.dispose();
    }
}
