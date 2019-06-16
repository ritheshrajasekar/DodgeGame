package Benchmarks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DodgeGameScreen extends Game {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String TITLE = "Dodge";
    public SpriteBatch batch;
    public BitmapFont font;



    @Override
    public void create () {
        batch = new SpriteBatch();
        this.setScreen(new TestGameScreen(this));
        font = new BitmapFont();
    }

    @Override
    public void render () {
        super.render();
    }
}
