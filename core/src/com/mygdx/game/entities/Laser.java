package com.mygdx.game.entities;
// have fun writing the code for this zak
public class Laser extends Projectile{
    public static final int SPEED = 0;
    public Laser(int dx, int dy, String d) {
        super(dx, dy, d, SPEED, createAnimation("sprites/dodgelaser.png", 73, 8, 1, 1));
    }
}
