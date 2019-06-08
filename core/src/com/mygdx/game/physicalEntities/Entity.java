package com.mygdx.game.physicalEntities;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Entity {
    private int x, y;

    public void setX(int newX){
        x = newX;
    }
    public void setY(int newY){
        y = newY;
    }
    public int getX(){
        return x;
    }
    public int getY() { return y; }
    public int xCoordToPixel(int x) {
        return x * PLAYER_MOVE_DISTANCE + GRID_OFFSET_X;
    }
    public int yCoordToPixel(int y) {
        return y * PLAYER_MOVE_DISTANCE + GRID_OFFSET_Y;
    }
}