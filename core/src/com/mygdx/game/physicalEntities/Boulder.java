package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.com.mygdx.game.screens.MainGameScreen;

import static com.mygdx.game.com.mygdx.game.screens.MainGameScreen.*;

public class Boulder {
    public static final int SPEED = 100;
    public static final int WIDTH = 56;
    public static final int LENGTH = 56;


    private static Texture boulderTexture;
    TextureRegion[] boulderAnimationFrames;
    Animation boulderAnimation;
    private float elapsedTime;
    private int x, y;
    public boolean isOnScreen;


    public Boulder( String direction){
        isOnScreen = true;
        // randomly assigns a spawn position to the boulder based on what the direction of the boulder is
        if(direction == "UP"){
            x = xCoordToPixel((int)(Math.random() * 8));
            y = yCoordToPixel(0);
        }
        else if(direction == "DOWN"){
            x = xCoordToPixel((int)(Math.random() * 8));
            y = yCoordToPixel(7);
        }

        else if(direction == "LEFT"){
            x = xCoordToPixel(7);
            y = yCoordToPixel((int)(Math.random() * 8));
        }

        else if(direction == "RIGHT"){
            x = xCoordToPixel(0);
            y = yCoordToPixel((int)(Math.random() * 8));
        }

        boulderTexture = new Texture("dodgeBoulder.png");
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
        return x * PLAYER_MOVE_DISTANCE+ GRID_OFFSET_X + GRID_CORNER_SIZE;
    }

    public int yCoordToPixel(int y) {
        return y * PLAYER_MOVE_DISTANCE + GRID_OFFSET_Y +  GRID_CORNER_SIZE;
    }

    public void update(float deltaTime, String direction){
        elapsedTime += Gdx.graphics.getDeltaTime();
        if(direction == "RIGHT"){
            x += SPEED * deltaTime;
            if(x > (GRID_WIDTH + GRID_OFFSET_X ))
                isOnScreen= false;
        }

        else if(direction == "UP"){
            y += SPEED * deltaTime;
            if(y > (GRID_HEIGHT + GRID_OFFSET_Y ))
                isOnScreen = false;
        }

        else if(direction == "LEFT"){
            x -= SPEED * deltaTime;
            if(x < (GRID_OFFSET_X ))
                isOnScreen = false;
        }

        else if(direction == "DOWN"){
            y -= SPEED * deltaTime;
            if(y < (GRID_OFFSET_Y ))
                isOnScreen = false;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(boulderAnimation.getKeyFrame(elapsedTime,true), x, y, WIDTH, LENGTH);
    }


}

