//prototype game screen
//created by Rithik Rajasekar
package Benchmarks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.Gdx.app;

public class TestGameScreen implements Screen {
    Texture player;
    Texture grid;
    Texture clock;
    TextureRegion[] animationFrames;
    Animation animation;
    float elapsedTime;
    TestTimer timer;

    public static final int PLAYER_MOVE_DISTANCE = 63;//9*7, 7 is the scalar multiplier for all sprites
    public static final int PLAYER_SIZE = 56;//8*7
    public static final int GRID_WIDTH = 665;
    public static final int GRID_HEIGHT = 665;
    public static final int GRID_OFFSET_X = 588;
    public static final int GRID_OFFSET_Y = 27;
    public static final int GRID_CORNER_SIZE = 84;//12*7
    int[] playerCoord = {0, 0};

    private Music music;
    TestBoulder boulder;

    DodgeGameScreen game;

    public TestGameScreen(DodgeGameScreen game) {
        this.game = game;

        player = new Texture("sprites/dodgePlayer.png");
        grid = new Texture("sprites/dodgeGrid.png");
        clock = new Texture("sprites/timer.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("music/05 - Grass World.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();

        boulder = new TestBoulder(0, 8);

        //player animation
        TextureRegion[][] tmpFrames = TextureRegion.split(player,8,10);

        animationFrames = new TextureRegion[9];
        int index = 0;

        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                animationFrames[index++] = tmpFrames[j][i];
            }
        }

        animation = new Animation(1f/9f,animationFrames);
    }

    public void show(){
        timer = new TestTimer(60);
    }

    public void render(float delta){
        //timer code
        timer.update(delta);

        //get key presses for player movement
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            if (playerCoord[0] < 7)
                playerCoord[0]++;
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            if (playerCoord[0] > 0)
                playerCoord[0]--;
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            if (playerCoord[1] < 7)
                playerCoord[1]++;
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            if (playerCoord[1] > 0)
                playerCoord[1]--;

        //creates background
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //updates elapsed time for player animation
        elapsedTime += Gdx.graphics.getDeltaTime();

        //renders entities
        game.batch.begin();
        game.font.setColor(Color.GREEN);

        // print out the timer
        game.batch.draw(clock,(int)(DodgeGameScreen.WIDTH * 0.25) - 100, DodgeGameScreen.HEIGHT/2 - 100,200 ,200 );
        game.font.getData().setScale(4f);
        game.font.draw(game.batch, timer.getWorldTimerString(), (int)(DodgeGameScreen.WIDTH * 0.20) + 22, DodgeGameScreen.HEIGHT/2 + 10);

        //checks if time is up
        if(timer.getWorldTimer() <= 0){

            music.stop();
            this.dispose();
            app.exit();
        }

        //draws the gird
        game.batch.draw(grid, GRID_OFFSET_X, GRID_OFFSET_Y, GRID_WIDTH, GRID_HEIGHT);

        //draws tbe player animation
        game.batch.draw(animation.getKeyFrame(elapsedTime,true), xCoordToPixel(playerCoord[0]) + GRID_CORNER_SIZE, yCoordToPixel(playerCoord[1]) + GRID_CORNER_SIZE, PLAYER_SIZE, PLAYER_SIZE);

        // updates the location of the boulder
        if((!boulder.remove) /*&& timer.getWorldTimer() <  55 */){
            boulder.update(delta);
            boulder.render(game.batch);
        } // renders the boulder

        //ends SpriteBatch
        game.batch.end();
    }

    public int xCoordToPixel(int x) {
        return x * PLAYER_MOVE_DISTANCE + GRID_OFFSET_X;
    }

    public int yCoordToPixel(int y) {
        return y * PLAYER_MOVE_DISTANCE + GRID_OFFSET_Y;
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
        player.dispose();
        grid.dispose();

    }
}