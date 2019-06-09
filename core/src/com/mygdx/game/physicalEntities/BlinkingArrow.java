package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level.PLAYER_SIZE;

public class BlinkingArrow extends Entity{
    public static final int ARROW_HEIGHT = 56;//8*7
    public static final int ARROW_WIDTH = 70;//10*7

    private Texture arrow;
    private TextureRegion[] arrowAnimationFrames;
    private Animation arrowAnimation;

    public float elapsedTime = 0;

    public BlinkingArrow(String d, int dx, int dy){
        direction = d;
        x = dx;
        y = dy;

        arrow = new Texture("dodgeBoulderArrow.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(arrow,10,8);
        arrowAnimationFrames = new TextureRegion[2];

        int index = 0;
        for (int i = 0; i < 1; i++){
            for(int j = 0; j < 2; j++) {
                arrowAnimationFrames[index++] = tmpFrames[i][j];
            }
        }

        arrowAnimation = new Animation(0.3f, arrowAnimationFrames);
    }

    public void render(SpriteBatch batch){
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(arrowAnimation.getKeyFrame(elapsedTime, true), xCoordToPixel(x), yCoordToPixel(y), ARROW_WIDTH, ARROW_HEIGHT); // must use the x and y to pixel coordinates here
    }
}
