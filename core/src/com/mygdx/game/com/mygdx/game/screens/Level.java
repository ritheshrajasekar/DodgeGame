package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.entities.CoinCounter;
import com.mygdx.game.entities.Timer;
import com.mygdx.game.entities.Player;

public class Level {
    public static final int PLAYER_MOVE_DISTANCE = 63;//9*7, 7 is the scalar multiplier for all sprites
    public static final int PLAYER_SIZE = 56;//8*7
    public static final int COIN_SIZE = 56; // 8*7
    public static final int GRID_WIDTH = 665;
    public static final int GRID_HEIGHT = 665;
    public static final int GRID_OFFSET_X = 588;
    public static final int GRID_OFFSET_Y = 27;
    public static final int GRID_CORNER_SIZE = 84;//12*7
    public static final String[] DIRECTIONS = {"UP", "DOWN", "LEFT", "RIGHT"};
    //public static final Music coinSound = Gdx.audio.newMusic(Gdx.files.internal("coin.mp3"));
    //public static final Music loseSound = Gdx.audio.newMusic(Gdx.files.internal("lose.mp3"));

    public String world, level;
    public Music music;
    public DodgeGame game;
    public Texture grid;
    public Timer timer;
    public CoinCounter coinCounter;
    public Player player;
    public int coins;
}
