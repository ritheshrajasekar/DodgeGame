package com.mygdx.game.entities;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Entity {
    public int x, y;
    public String direction;

    public int xCoordToPixel(int x) {
        return x * PLAYER_MOVE_DISTANCE + GRID_OFFSET_X + GRID_CORNER_SIZE;
    }
    public int yCoordToPixel(int y) {
        return y * PLAYER_MOVE_DISTANCE + GRID_OFFSET_Y + GRID_CORNER_SIZE;
    }
}