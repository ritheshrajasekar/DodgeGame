package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.entities.*;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Level {
    public static final int NUM_LEVELS = 12;
    public static final int PLAYER_MOVE_DISTANCE = 63;//9*7, 7 is the scalar multiplier for all sprites
    public static final int PLAYER_WIDTH = 56;//8*7
    public static final int PLAYER_HEIGHT = 70;//10*7
    public static final int COIN_SIZE = 56; // 8*7
    public static final double COIN_SPAWN_INTERVAL = 10;
    public static final double COIN_DESPAWN_DELAY = 7.5;
    public static final int GRID_WIDTH = 665;
    public static final int GRID_HEIGHT = 665;
    public static final int GRID_OFFSET_X = 588;
    public static final int GRID_OFFSET_Y = 27;
    public static final int GRID_CORNER_SIZE = 84;//12*7
    public static final String[] DIRECTIONS = {"UP", "DOWN", "LEFT", "RIGHT"};
    public static final Texture GRID = new Texture("sprites/dodgeGrid.png");
    public static final Music COIN_SOUND = Gdx.audio.newMusic(Gdx.files.internal("music/04 - Coin.mp3"));

    public String world, level;
    public Music music;
    public DodgeGame game;
    public Timer timer;
    public CoinCounter coinCounter;
    public Player player;
    public Texture levelTexture;
    public static Sprite BackgroundSprite;
    public static int currentLevelNumber = 0;
    public ArrayList<Integer[]> projectileSpawnCoords = new ArrayList<>();

    public static int coins;
    public boolean coinsSpawned;

    public int minBoulders;
    public int maxBoulders;
    public double boulderSpawnInterval;
    //this is a double array with only one value so it can be passed by reference to spawnProjectile()
    public double[] boulderSpawnIntervalRandom = new double[1];
    public double boulderSpawnDelay;
    public boolean boulderSpawned;

    public int minCannons;
    public int maxCannons;
    public double cannonSpawnInterval;
    public double[] cannonSpawnIntervalRandom = new double[1];
    public double cannonSpawnDelay;
    public boolean cannonSpawned;

    public int minBoomerangs;
    public int maxBoomerangs;
    public double boomerangSpawnInterval;
    public double[] boomerangSpawnIntervalRandom = new double[1];
    public double boomerangSpawnDelay;
    public boolean boomerangSpawned;

    public CopyOnWriteArrayList<Coin> coinList = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<Projectile> boulderList = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<BlinkingArrow> boulderArrowList = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<Projectile> cannonList = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<BlinkingArrow> cannonArrowList = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<Projectile> boomerangList = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<BlinkingArrow> boomerangArrowList = new CopyOnWriteArrayList<>();

    public void show() {
        //do Timer(60.1) because sometimes starting the level will lag causing stuff that happens at exactly 60 seconds to not be registered
        timer = new Timer(60.1);
        coinCounter = new CoinCounter();
    }

    public void displayBackground(Texture background) {
        BackgroundSprite = new Sprite(background);
        BackgroundSprite.scale(6);
        game.batch.begin();
        BackgroundSprite.setPosition(DodgeGame.WIDTH / 2 - BackgroundSprite.getWidth() / 2, DodgeGame.HEIGHT / 2 - BackgroundSprite.getHeight() / 2);
        BackgroundSprite.draw(game.batch);
        game.batch.end();
    }

    public void playMusic(String path) {
        music = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }

    public void createPlayer() {
        player = new Player();
        player.x = 4;
        player.y = 4;
    }

    public void displayTimer(float delta) {
        timer.update(delta);
        timer.render(game.batch, game.font);

        //checks if time is up
        if (timer.getWorldTimer() <= 0) {
            music.stop();
             music.dispose();

            game.setScreen(new WonLevel(game));
        }
    }

    public void displayCoinCounter() {
        coinCounter.update(coins);
        coinCounter.render(game.batch, game.font);
    }

    public void displayWorldAndLevel() {
        game.font.setColor(Color.PURPLE);
        game.font.getData().setScale(4f);
        game.font.draw(game.batch, world, (int) (DodgeGame.WIDTH * 0.07), DodgeGame.HEIGHT / 2 + 300);
        game.font.draw(game.batch, level, (int) (DodgeGame.WIDTH * 0.25), DodgeGame.HEIGHT / 2 + 300);
    }

    public void drawGrid() {
        game.batch.draw(GRID, GRID_OFFSET_X, GRID_OFFSET_Y, GRID_WIDTH, GRID_HEIGHT);
    }

    public void renderPlayer() {
        player.update();
        player.render(game.batch);
    }

    public void renderCoins() {
        for (Coin c : coinList) {
            c.update();
            //causes the coin to blink when near despawn time
            double time = c.elapsedTime;
            double[][] blinkTimes = {
                    {5, 5.15},
                    {5.4, 5.55},
                    {5.8, 5.95},
                    {6.2, 6.35},
                    {6.6, 6.75},
                    {7, 7.1},
                    {7.2, 7.3},
                    {7.4, 7.5}
            };
            boolean blink = false;
            for (double[] t : blinkTimes) {
                if (time > t[0] && time < t[1])
                    blink = true;
            }
            if (!blink)
                c.render(game.batch);
        }
    }

    //i was going to make this method but the lists are of type object which is incompatible with Projectile and ProjectileArrow
    public void renderProjectile(float delta, double projectileSpawnInterval, double[] projectileSpawnIntervalRandom, CopyOnWriteArrayList<Projectile> projectileList, CopyOnWriteArrayList<BlinkingArrow> arrowList, double spawnDelay, boolean isBoomerang) {
        for (int i = 0; i < projectileList.size(); i++) {
            //only updates and renders once the projectile is spawned
            if (projectileList.get(i).spawned) {
                //updates and render if the projectile is on screen
                if (projectileList.get(i).isOnScreen) {
                    //checks if the projectile is a boomerang
                    if(isBoomerang){
                        projectileList.get(i).updateBoomerang(delta);
                    }
                    //else renders normally
                    else{
                        projectileList.get(i).update(delta);
                    }
                    projectileList.get(i).render(game.batch);
                    //deletes projectile and arrowTexture once the projectile leaves the screen
                } else {
                    projectileList.remove(i);
                    arrowList.remove(i);
                    i--;
                }
                //if the projectile hasn't spawned yet, the arrowTexture will blink
                //(it will stop blinking after the projectile is spawned because it exits this else statement)
            } else {
                arrowList.get(i).render(game.batch);
                //when it starts rendering the projectile and stops rendering the blinking arrow
                if (arrowList.get(i).elapsedTime > spawnDelay) {
                    projectileList.get(i).spawned = true;
                    //finds the coords of the projectile and deletes it from the list
                    for (Integer[] pos : projectileSpawnCoords) {
                        if (pos[0] == projectileList.get(i).x && pos[1] == projectileList.get(i).y) {
                            projectileSpawnCoords.remove(pos);
                            break;
                        }
                    }
                    //resets projectileSpawnIntervalRandom
                    projectileSpawnIntervalRandom[0] = projectileSpawnInterval;
                }
            }
        }
    }

    public void renderBoulders(float delta) {
        renderProjectile(delta, boulderSpawnInterval, boulderSpawnIntervalRandom, boulderList, boulderArrowList, boulderSpawnDelay, false);
    }

    public void renderCannons(float delta) {
        renderProjectile(delta, cannonSpawnInterval, cannonSpawnIntervalRandom, cannonList, cannonArrowList, cannonSpawnDelay, false);
    }

    public void renderBoomerang(float delta){
        renderProjectile(delta, boomerangSpawnInterval, boomerangSpawnIntervalRandom, boomerangList, boomerangArrowList, boomerangSpawnDelay, true);
    }


    public void spawnCoins() {
        if (timer.getWorldTimer() % COIN_SPAWN_INTERVAL == 0 && !coinsSpawned) {
            //x and y lists to test if it's trying spawn a coin where one already exists
            ArrayList<Integer> xList = new ArrayList<Integer>();
            ArrayList<Integer> yList = new ArrayList<Integer>();

            for (int i = 0; i < 5; i++) {
                int x = (int) (8 * Math.random());
                int y = (int) (8 * Math.random());

                boolean inList = false;
                for (int tempX : xList) {
                    for (int tempY : yList) {
                        if (x == tempX && y == tempY) {
                            inList = true;
                        }
                    }
                }

                if (inList == false) {
                    coinList.add(new Coin(x, y));
                    xList.add(x);
                    yList.add(y);
                } else {
                    i--;
                }
            }
            coinsSpawned = true;
        }
        if (timer.getWorldTimer() % COIN_SPAWN_INTERVAL != 0) {
            coinsSpawned = false;
        }
        //coins despawn after 7.5 seconds
        if (coinList.size() > 0 && coinList.get(0).elapsedTime > COIN_DESPAWN_DELAY) {
            coinList.clear();
        }
    }

    public boolean spawnProjectile(double projectileSpawnInterval, double[] projectileSpawnIntervalRandom, boolean projectileSpawned, int maxProjectiles, int minProjectiles, CopyOnWriteArrayList<Projectile> projectileList, CopyOnWriteArrayList<BlinkingArrow> arrowList, int s, Animation a, String path) {
        //chooses a random time to assign projectileSpawnIntervalRandom within a calculated interval of the projectileSpawnInterval
        double interval = projectileSpawnInterval / 4;
        while (projectileSpawnIntervalRandom[0] == projectileSpawnInterval || projectileSpawnIntervalRandom[0] <= 0.0)
            projectileSpawnIntervalRandom[0] = (Math.round((projectileSpawnInterval + Math.random() * interval * 2 - interval) * 10)) / 10.0;
        double spawnTime = Math.round(((60 - timer.getWorldTimer()) % projectileSpawnIntervalRandom[0]) * 10) / 10.0;
        //this == 0.1 because spawnTime never actually reaches 0.0 due to double rounding
        if (spawnTime == 0.1 && !projectileSpawned) {
            //x and y lists to test if it's trying spawn a projectile where one already exists
            ArrayList<Integer> xList = new ArrayList<>();
            ArrayList<Integer> yList = new ArrayList<>();

            for (int i = 0; i < (int) ((maxProjectiles - minProjectiles + 1) * Math.random() + minProjectiles); i++) {
                int x = 0;
                int y = 0;
                String direction = DIRECTIONS[(int) (Math.random() * 4)];

                // randomly assigns a spawn position to the projectile based on what the direction of the projectile is
                if (direction == "UP") {
                    x = (int) (Math.random() * 8);
                    y = -1;
                } else if (direction == "DOWN") {
                    x = (int) (Math.random() * 8);
                    y = 8;
                } else if (direction == "LEFT") {
                    x = 8;
                    y = (int) (Math.random() * 8);
                } else if (direction == "RIGHT") {
                    x = -1;
                    y = (int) (Math.random() * 8);
                }

                //checks to see if a projectile is already there
                boolean inList = false;
                for (int tempX : xList) {
                    for (int tempY : yList) {
                        if (x == tempX && y == tempY) {
                            inList = true;
                        }
                    }
                }
                //checks through all projectiles that are being spawned
                for (Integer[] pos : projectileSpawnCoords) {
                    if (x == pos[0] && y == pos[1]) {
                        inList = true;
                    }
                }
                if (!inList) {
                    projectileList.add(new Projectile(x, y, direction, s, a));
                    arrowList.add(new BlinkingArrow(x, y, direction, path));
                    xList.add(x);
                    yList.add(y);
                    projectileSpawnCoords.add(new Integer[] {x, y});
                } else {
                    i--;
                }
            }
            projectileSpawned = true;
        }
        if (spawnTime != 0.1) {
            projectileSpawned = false;
        }
        return projectileSpawned;
    }

    public void spawnBoulders() {
        boulderSpawned = spawnProjectile(boulderSpawnInterval, boulderSpawnIntervalRandom, boulderSpawned, maxBoulders, minBoulders, boulderList, boulderArrowList, Boulder.SPEED, Projectile.createAnimation("sprites/dodgeBoulder.png", 8, 8, 4, 3), "sprites/dodgeBoulderArrow.png");
        //System.out.println(boulderSpawnIntervalRandom[0]);
    }


    public void spawnCannon() {
        cannonSpawned = spawnProjectile(cannonSpawnInterval, cannonSpawnIntervalRandom, cannonSpawned, maxCannons, minCannons, cannonList, cannonArrowList, Cannon.SPEED, Projectile.createAnimation("sprites/dodgeCannonball.png", 8, 8, 1, 1), "sprites/dodgeCannonballArrow.png");
    }

    public void spawnBoomerang(){
        boomerangSpawned = spawnProjectile(boomerangSpawnInterval, boomerangSpawnIntervalRandom, boomerangSpawned, maxBoomerangs, minBoomerangs, boomerangList, boomerangArrowList, Boomerang.SPEED, Projectile.createAnimation("sprites/dodgeBoomerang.png", 8, 8, 3, 3), "sprites/dodgeBoomerangArrow.png");
    }

    public void detectCoin() {
        for (int i = 0; i < coinList.size(); i++) {
            if (coinList.get(i).x == player.x && coinList.get(i).y == player.y) {
                coins++;
                COIN_SOUND.stop();
                COIN_SOUND.play();
                coinList.remove(i);
            }
        }
    }

    public void detectProjectileCollision(CopyOnWriteArrayList<Projectile> projectileList) {
        for (int i = 0; i < projectileList.size(); i++) {
            if (projectileList.get(i).x == player.x && projectileList.get(i).y == player.y) {
                music.dispose();
                game.setScreen(new GameOver(game));
            }
        }
    }

    public void detectBoulderCollision() {
        detectProjectileCollision(boulderList);
    }

    public void detectCannonCollision() {
        detectProjectileCollision(cannonList);
    }

    public void detectBoomerangCollision(){
        detectProjectileCollision(boomerangList);
    }

    public void dispose() {
        GRID.dispose();
        COIN_SOUND.dispose();
        music.dispose();
        levelTexture.dispose();
        BackgroundSprite.getTexture().dispose();
    }

}
