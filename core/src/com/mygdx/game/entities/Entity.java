//everything that moves on the screen is an Entity, and the class Entity is the super class
//created by Matt Seng

package com.mygdx.game.entities;

import static com.mygdx.game.com.mygdx.game.screens.Level1.*;

public class Entity {
    public int x, y;
    public String direction;
    //all entities store their x and y values as the grid coordinates, so these two methods convert them to pixel coordinates
    public int xCoordToPixel(int x) {
        return x * PLAYER_MOVE_DISTANCE + GRID_OFFSET_X + GRID_CORNER_SIZE;
    }

    public int yCoordToPixel(int y) {
        return y * PLAYER_MOVE_DISTANCE + GRID_OFFSET_Y + GRID_CORNER_SIZE;
    }
}