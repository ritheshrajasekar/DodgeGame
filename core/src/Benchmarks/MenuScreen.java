//this class serves as the intital menuScreen
//created by Rithesh Rajasekar
package Benchmarks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen implements Screen {
    private DodgeGameBenchmarks game;
    private Texture exitButtonInactive;
    private Texture exitButtonActive;
    private Texture playButtonInactive;
    private Texture playButtonActive;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_YVALUE = (int)(DodgeGameBenchmarks.HEIGHT * 0.2);
    private static final int PLAY_BUTTON_YVALUE = (int)(DodgeGameBenchmarks.HEIGHT * 0.6);
    private Music music;

    public MenuScreen(DodgeGameBenchmarks game){
        this.game = game;
        exitButtonInactive = new Texture("sprites/exitButton.jpg");
        exitButtonActive = new Texture("sprites/exitButtonActive.jpg");
        playButtonInactive = new Texture("sprites/playButton.jpg");
        playButtonActive = new Texture("sprites/playButtonActive.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("music/01 - Menu.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }
    public void show(){

    }
    public void render(float delta){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        // checks if the exit button is touched
        if(Gdx.input.getX() < DodgeGameBenchmarks.WIDTH/2  + EXIT_BUTTON_WIDTH /2 &&
                Gdx.input.getX() > DodgeGameBenchmarks.WIDTH/2 - EXIT_BUTTON_WIDTH/2 &&
                DodgeGameBenchmarks.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_YVALUE  &&
                DodgeGameBenchmarks.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_YVALUE + EXIT_BUTTON_HEIGHT) {
            game.batch.draw(exitButtonActive, DodgeGameBenchmarks.WIDTH/2 - EXIT_BUTTON_WIDTH/2,EXIT_BUTTON_YVALUE, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if(Gdx.input.isTouched() ==true){
                Gdx.app.exit();
            }
        }

        else {
            game.batch.draw(exitButtonInactive, DodgeGameBenchmarks.WIDTH/2 - EXIT_BUTTON_WIDTH/2,EXIT_BUTTON_YVALUE, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        //checks if the playButton is touched
        if(Gdx.input.getX() < DodgeGameBenchmarks.WIDTH/2  + PLAY_BUTTON_WIDTH /2 &&
                Gdx.input.getX() > DodgeGameBenchmarks.WIDTH/2 - PLAY_BUTTON_WIDTH/2 &&
                DodgeGameBenchmarks.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_YVALUE  &&
                DodgeGameBenchmarks.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_YVALUE + PLAY_BUTTON_HEIGHT) {
            game.batch.draw(playButtonActive, DodgeGameBenchmarks.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YVALUE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
        else {
            game.batch.draw(playButtonInactive, DodgeGameBenchmarks.WIDTH/2 - PLAY_BUTTON_WIDTH/2,PLAY_BUTTON_YVALUE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

        }

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