package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;

public class BoulderArrow extends BlinkingArrow {
    public BoulderArrow(int dx, int dy, String d) {
        super(dx, dy, d);
        arrowTexture = new Texture("sprites/dodgeBoulderArrow.png");
    }
}
