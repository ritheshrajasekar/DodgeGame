package com.mygdx.game.entities;

public class Boomerang extends Projectile {
    public static final int SPEED = 600;

    public Boomerang(int dx, int dy, String d) {
        super("Boomerang", dx, dy, d, SPEED, createAnimation("sprites/dodgeBoomerang.png", 8, 8, 3, 3), 0);
    }
}