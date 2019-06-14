package com.mygdx.game.entities;

public class Laser extends Projectile{
    public static final int SPEED = 0;

    //these constructors are never actually used
    public Laser(int dx, int dy, String d) {
        super("Laser", dx, dy, d, SPEED, createAnimation("sprites/dodgeLaser.png", 8, 9, 1, 3, 10), false);
    }
}