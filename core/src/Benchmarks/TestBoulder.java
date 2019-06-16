//prototype boulder class
//created by Rithik Rajasekar

package Benchmarks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static Benchmarks.TestGameScreen.*;

public class TestBoulder {
    public static final int SPEED = 100;
    public static final int WIDTH = 56;
    public static final int LENGTH = 56;

    private static Texture boulderTexture;
    TextureRegion[] boulderAnimationFrames;
    Animation boulderAnimation;
    private float elapsedTime;
    int x, y;

    public boolean remove = false;

    public TestBoulder(int dx, int dy){
        this.x = xCoordToPixel(dx);
        this.y = yCoordToPixel(dy);

        boulderTexture = new Texture("sprites/dodgeBoulder.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(boulderTexture,8,8);
        boulderAnimationFrames = new TextureRegion[12];

        int index = 0;
        for (int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++) {
                boulderAnimationFrames[index++] = tmpFrames[i][j];
            }
        }

        boulderAnimation = new Animation(1f/12f,boulderAnimationFrames);


    }

    public int xCoordToPixel(int x) {
        return x * WIDTH + GRID_OFFSET_X + GRID_CORNER_SIZE;
    }

    public int yCoordToPixel(int y) {
        return y * LENGTH + GRID_OFFSET_Y +  GRID_CORNER_SIZE;
    }

    public void update(float deltaTime){
        elapsedTime += Gdx.graphics.getDeltaTime();
        x += SPEED * deltaTime;
        if(x > (TestGameScreen.GRID_WIDTH + TestGameScreen.GRID_OFFSET_X))
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(boulderAnimation.getKeyFrame(elapsedTime,true), x, y, WIDTH, LENGTH);
    }
}

