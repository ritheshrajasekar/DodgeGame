package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BlinkingArrow {
    public static final int ARROW_HEIGHT = 8;
    public static final int ARROW_WIDTH = 10;

    private Texture arrow;
    private TextureRegion blinkingArrow;
    private TextureRegion[] boulderAnimationFrames;
    private Animation boulderAnimation;


    private int x, y;
    public String direction;

    public BlinkingArrow(String d, int dx, int dy){
        direction = d;
        x = dx;
        y = dy;
        
        arrow = new Texture("dodgeBoulderArrow.png");


    }


}
