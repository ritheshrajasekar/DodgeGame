package com.mygdx.game.entities;
// have fun writing the code for this zak
public class Laser extends Projectile{
    public static final int SPEED = 1500;

    public Laser(int dx, int dy, String d) {
        super("Laser", dx, dy, d, SPEED, createAnimation("sprites/dodgeLaser.png", 73, 8, 1, 1), 0);
    }
}