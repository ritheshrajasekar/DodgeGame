package com.mygdx.game.entities;

public class Boomerang extends Projectile {
    public static final int SPEED = 600;

    //these constructors are never actually used
    public Boomerang(int dx, int dy, String d) {
        super("Boomerang", dx, dy, d, SPEED, createAnimation("sprites/dodgeBoomerang.png", 8, 8, 3, 3, 9), false);
    }
}