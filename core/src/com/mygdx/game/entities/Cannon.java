package com.mygdx.game.entities;

public class Cannon extends Projectile {
    public static final int SPEED = 500;

    //these constructors are never actually used
    public Cannon(int dx, int dy, String d) {
        super("Cannon", dx, dy, d, SPEED, createAnimation("sprites/dodgeCannonball.png", 8, 8, 1, 1, 1), false);
    }
}