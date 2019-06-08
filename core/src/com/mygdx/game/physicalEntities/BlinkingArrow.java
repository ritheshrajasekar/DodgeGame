package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BlinkingArrow extends Entity{
    public static final int ARROW_HEIGHT = 8;
    public static final int ARROW_WIDTH = 10;

    private Texture arrow;
    private TextureRegion[] arrowAnimationFrames;
    private Animation arrowAnimation;

    public String direction;

    public BlinkingArrow(String d, int dx, int dy){
        direction = d;
        x = dx;
        y = dy;


        arrow = new Texture("dodgeBoulderArrow.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(arrow,8,10);
        arrowAnimationFrames = new TextureRegion[12];

        int index = 0;
        for (int i = 0; i < 1; i++){
            for(int j = 0; j < 2; j++) {
                arrowAnimationFrames[index++] = tmpFrames[i][j];
            }
        }

        arrowAnimation = new Animation(1f/2f, arrowAnimationFrames);

    }



    public void render(SpriteBatch batch){
        float elapsedTime = 0;
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(arrowAnimation.getKeyFrame(elapsedTime, true), xCoordToPixel(x), yCoordToPixel(y), ARROW_WIDTH, ARROW_HEIGHT); // must use the x and y to pixel coordinates here
    }
}
