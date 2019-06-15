package com.mygdx.game.entities;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Laser extends Projectile {
    public static final int SPEED = 0;
    public static int min, max;
    public static double spawnInterval;
    //this is a double array with only one value so it can be passed by reference to spawnProjectile()
    public static double[] spawnIntervalRandom = new double[1];
    public static double spawnDelay;
    public static boolean groupSpawned;
    public static CopyOnWriteArrayList<Projectile> list = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<BlinkingArrow> arrowList = new CopyOnWriteArrayList<>();
    public static ArrayList<Integer[]> oldPos = new ArrayList<>();

    //this constructor is never actually used
    public Laser(int dx, int dy, String d) {
        super("Laser", dx, dy, d, SPEED, createAnimation("sprites/dodgeLaser.png", 8, 9, 1, 3, 10), false);
    }
}