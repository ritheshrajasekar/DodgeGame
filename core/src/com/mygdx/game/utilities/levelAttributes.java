package com.mygdx.game.utilities;

public class levelAttributes{
    public boolean[] unlocked= new boolean[12];
    public int[] coins = new int [12];

    public levelAttributes(){

    }

    public void setUnlccked(int level, boolean isUnlocked){
        unlocked[level] = isUnlocked;
    }

    public void setCoins(int level, int numStars){
        coins[level] = numStars;
    }
}






