package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;

public class LaserArrow extends BlinkingArrow {
    public LaserArrow(int dx, int dy, String d) {
        super(dx, dy, d);
        arrowTexture = new Texture("sprites/dodgeCannonArrow.png");
    }
}
