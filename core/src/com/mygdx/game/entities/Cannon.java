package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Cannon extends Entity {
    public static final int SPEED = 300;

    private static Texture boulderTexture;
    private TextureRegion[] boulderAnimationFrames;
    private Animation boulderAnimation;
    private float elapsedTime;
    public boolean isOnScreen;
    public boolean spawned;
}
