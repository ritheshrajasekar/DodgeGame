package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;

public class CannonArrow extends BlinkingArrow{
    public CannonArrow(int dx, int dy, String d) {
        super(dx, dy, d);
        arrowTexture = new Texture("dodgeCannonballArrow.png");
    }
}
