package com.mygdx.game.entities;

public class Boulder extends Projectile {
    public static final int SPEED = 200;

    public Boulder(int dx, int dy, String d) {
        super(dx, dy, d, SPEED, createAnimation("sprites/dodgeBoulder.png", 8, 8, 4, 3));
    }
}