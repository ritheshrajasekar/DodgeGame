package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Boulder extends Entity {
    public static final int SPEED = 125;
    public static final int WIDTH = 56;
    public static final int LENGTH = 56;

    private static Texture boulderTexture;
    private TextureRegion[] boulderAnimationFrames;
    private Animation boulderAnimation;
    private float elapsedTime;
    public boolean isOnScreen;
    public String direction;

    public Boulder(String d){
        isOnScreen = true;
        direction = d;

        // randomly assigns a spawn position to the boulder based on what the direction of the boulder is
        if(direction == "UP"){
            setX(xCoordToPixel((int)(Math.random() * 8)));
            setY(yCoordToPixel(0));
        }
        else if(direction == "DOWN"){
            setX(xCoordToPixel((int)(Math.random() * 8)));
            setY(yCoordToPixel(7));
        }

        else if(direction == "LEFT"){
            setX(xCoordToPixel(7));
            setY(yCoordToPixel((int)(Math.random() * 8)));
        }

        else if(direction == "RIGHT"){
            setX(xCoordToPixel(0));
            setY(yCoordToPixel((int)(Math.random() * 8)));
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

    public void update(float deltaTime){
        elapsedTime += Gdx.graphics.getDeltaTime();
        if(direction == "RIGHT"){
            setX((int) (getX() + SPEED * deltaTime));
            if(getX() > (GRID_WIDTH + GRID_OFFSET_X ))
                isOnScreen= false;
        }

        else if(direction == "UP"){
            setY((int) (getY() + SPEED * deltaTime));
            if(getY() > (GRID_HEIGHT + GRID_OFFSET_Y ))
                isOnScreen = false;
        }

        else if(direction == "LEFT"){
            setX((int) (getX() - SPEED * deltaTime));
            if(getX() < (GRID_OFFSET_X ))
                isOnScreen = false;
        }

        else if(direction == "DOWN"){
            setY((int) (getY() - SPEED * deltaTime));
            if(getY() < (GRID_OFFSET_Y ))
                isOnScreen = false;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(boulderAnimation.getKeyFrame(elapsedTime,true), getX(), getY(), WIDTH, LENGTH);
    }
}

