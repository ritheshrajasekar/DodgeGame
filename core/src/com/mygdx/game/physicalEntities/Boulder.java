package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.com.mygdx.game.screens.MainGameScreen;
import sun.applet.Main;

public class Boulder {
    public static final int SPEED = 200;
    public static final int DEFAULT_FOR_TEST_Y = 500;
    public static final int WIDTH = 56;
    public static final int LENGTH = 56;
    private static Texture boulderTexture;

    float x, y;

    public boolean remove = false;

    public Boulder(float x){
        this.x = x;
        this.y = DEFAULT_FOR_TEST_Y;
        boulderTexture = new Texture("dodgeStillBoulder.png");
    }

    public void update(float deltaTime){
        x += SPEED * deltaTime;
        if(x > (MainGameScreen.GRID_WIDTH + MainGameScreen.GRID_OFFSET_X))
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(boulderTexture, x, y, WIDTH, LENGTH);
    }



}
