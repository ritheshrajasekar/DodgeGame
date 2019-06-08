package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class boulder {
    public static final int BOULDER_MOVE_DISTANCE = 7;
    public static final int BOUDLER_PIXEL_WIDTH = 56;
    public static final int BOULDER_PIXEL_LENGTH = 56;

    public static final int[] POSSIBLE_BOULDER_X_SPAWN_POSITIONS = {600}; // need to figure out the different x pixel coordinate for the boulder
    public static final int[] POSSIBLE_BOULDER_Y_SPAWN_POSITIONS = {700}; // need to figure out the different y pixel coordinate for the boulder

    private int boulderX;
    private int boulderY;

    private int direction;

    private Texture boulder;
    private TextureRegion[] boulderAnimationFrames;
    private Animation boulderAnimation;

    public boulder(){
        boulderX = POSSIBLE_BOULDER_X_SPAWN_POSITIONS[0]; //randomly assigns a x spawn position;
        boulderY = POSSIBLE_BOULDER_Y_SPAWN_POSITIONS[0]; // randomly assigns a y spawn position;
        boulder = new Texture("dodgeStillBoulder.png");
        /*boulderAnimationFrames = new TextureRegion[12];

        TextureRegion[][] tmpFrames = TextureRegion.split(boulder,8,10);

        boulderAnimationFrames = new TextureRegion[9];
        int index = 0;

        for (int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++) {
                boulderAnimationFrames[index++] = tmpFrames[i][j];
            }
        }

        boulderAnimation = new Animation(1f/9f,boulderAnimationFrames);*/
    }

    public int getBoulderX() {

        return boulderX;
    }

    public int getBoulderY() {
        setBoulderY(); // this gets the new pixel coordinates of the boulder
        return boulderY; }

    public void setBoulderY() {
        this.boulderY =- 1;
    }

    /*public Animation getBoulderAnimation(){ return boulderAnimation;}*/

    public int getBoulderWidth(){
        return BOUDLER_PIXEL_WIDTH;
    }

    public int getBoulderLength(){
        return BOULDER_PIXEL_LENGTH;
    }

    public Texture getBoulder(){
        return boulder;
    }
}
