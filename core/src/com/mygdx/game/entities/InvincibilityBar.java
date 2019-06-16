//displays the invincibility bar
//created by Matt Seng
package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InvincibilityBar {
    private final int X_COORD = 300;
    private final int Y_COORD = 80;

    private Texture bar;
//    private Texture bar0 = new Texture("sprites/dodgeInvincibilityBar0.png");
//    private Texture bar1 = new Texture("sprites/dodgeInvincibilityBar1.png");
//    private Texture bar2 = new Texture("sprites/dodgeInvincibilityBar2.png");
//    private Texture bar3 = new Texture("sprites/dodgeInvincibilityBar3.png");

    private Texture bar0 = new Texture("sprites/dodgeBoulder.png");
    private Texture bar1 = new Texture("sprites/dodgeCannonball.png");
    private Texture bar2 = new Texture("sprites/dodgeBoomerang.png");
    private Texture bar3 = new Texture("sprites/dodgeLaser.png");
    //renders the  bar based on the invincibilityState of the player
    public void render(SpriteBatch batch) {
        switch (Player.invincibilityState) {
            case 1:
                bar = bar1;
                break;
            case 2:
                bar = bar2;
                break;
            case 3:
                bar = bar3;
                break;
            default:
                bar = bar0;
                break;
        }

        batch.end();
        batch.begin();
        batch.draw(bar, X_COORD, Y_COORD, 32 * 7, 5 * 7);
    }
}
