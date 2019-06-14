package com.mygdx.game.entities;

public class Laser extends Projectile{
    public static final int SPEED = 1500;

    public Laser(int dx, int dy, String d) {
        super("Laser", dx, dy, d, SPEED, createAnimation("sprites/dodgeLaser.png", 8, 8, 1, 3, 10), 0);
    }
}