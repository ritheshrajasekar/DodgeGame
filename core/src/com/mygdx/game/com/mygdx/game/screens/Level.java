//Authors were Zak Asis, Matt Seng, Rithesh Rajsaekar, and Rithik Rajasekar
// the purpose of this class is to be the super class for all the specific levels so that all the game logic is consolidated into one class

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
    public static final double COIN_DESPAWN_DELAY = 8.5;
    public static final int GRID_WIDTH = 665;
    public static final int GRID_HEIGHT = 665;
    public static final int GRID_OFFSET_X = 588;
    public static final int GRID_OFFSET_Y = 27;
    public static final int GRID_CORNER_SIZE = 84;//12*7
    public static final String[] DIRECTIONS = {"UP", "DOWN", "LEFT", "RIGHT"};
    public static final Texture GRID = new Texture("sprites/dodgeGrid.png");
    public static final Music COIN_SOUND = Gdx.audio.newMusic(Gdx.files.internal("music/04 - Coin.mp3"));
    public static final Music BOULDER_SOUND = Gdx.audio.newMusic(Gdx.files.internal("music/Boulder.mp3"));
    public static final Music CANNON_SOUND = Gdx.audio.newMusic(Gdx.files.internal("music/Cannonball.mp3"));
    public static final Music BOOMERANG_SOUND = Gdx.audio.newMusic(Gdx.files.internal("music/Boomerang.mp3"));
    public static final Music LASER_SOUND = Gdx.audio.newMusic(Gdx.files.internal("music/Laser.mp3"));

    public static String world;
    public String level;
    public Music music;
    public DodgeGame game;
    public Timer timer;
    public CoinCounter coinCounter;
    public InvincibilityBar invincibilityBar;
    public Player player;
    public Texture levelTexture;
    public static Sprite BackgroundSprite;
    public static int currentLevelNumber = 0;
    public static boolean isMuted = false;
    public static int coins;
    public boolean coinsSpawned;
    public CopyOnWriteArrayList<Coin> coinList = new CopyOnWriteArrayList<>();
    public ArrayList<Integer[]> projectileSpawnCoords = new ArrayList<>();

    public void show() {
        //do Timer(60.1) because sometimes starting the level will lag causing stuff that happens at exactly 60 seconds to not be registered
        timer = new Timer(60.1);
        coinCounter = new CoinCounter();
        invincibilityBar = new InvincibilityBar();
        resetProjectiles();
    }

    public void resetProjectiles() {
        Boulder.list = new CopyOnWriteArrayList<>();
        Boulder.arrowList = new CopyOnWriteArrayList<>();
        Boulder.oldPos = new ArrayList<>();
        Cannon.list = new CopyOnWriteArrayList<>();
        Cannon.arrowList = new CopyOnWriteArrayList<>();
        Cannon.oldPos = new ArrayList<>();
        Boomerang.list = new CopyOnWriteArrayList<>();
        Boomerang.arrowList = new CopyOnWriteArrayList<>();
        Boomerang.oldPos = new ArrayList<>();
        Laser.list = new CopyOnWriteArrayList<>();
        Laser.arrowList = new CopyOnWriteArrayList<>();
        Laser.oldPos = new ArrayList<>();
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
        if (!isMuted) {
            music = Gdx.audio.newMusic(Gdx.files.internal(path));
            music.setLooping(true);
            music.setVolume(1f);
            music.play();
        }
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
        if (timer.getWorldTimer() < 1) {
            if (!isMuted) {
                music.stop();
                music.dispose();
            }

            game.setScreen(new WonLevel(game));
        }
    }

    public void displayCoinCounter() {
        coinCounter.update(coins);
        coinCounter.render(game.batch, game.font);
    }

    public void displayInvincibilityBar() {
        invincibilityBar.render(game.batch);
    }

    public void displayWorldAndLevel() {
        game.font.setColor(Color.WHITE);
        game.font.getData().setScale(4f);
        double width = 0;
        int height = 300;
        switch (world) {
            case "GRASS":
                width = 0.23;
                break;
            case "SAND":
                width = 0.2;
                break;
            case "JUNGLE":
                width = 0.25;
                break;
            case "HELL":
                width = 0.2;
                height = 225;
                break;
        }
        game.font.draw(game.batch, world, (int) (DodgeGame.WIDTH * 0.07), DodgeGame.HEIGHT / 2 + height);
        game.font.draw(game.batch, level, (int) (DodgeGame.WIDTH * width), DodgeGame.HEIGHT / 2 + height);
    }

    public void drawGrid() {
        game.batch.draw(GRID, GRID_OFFSET_X, GRID_OFFSET_Y, GRID_WIDTH, GRID_HEIGHT);
    }

    public void renderPlayer(float delta) {
        player.update();
        player.updateInvincibility(delta);
        player.render(game.batch);
    }

    public void renderCoins() {
        for (Coin c : coinList) {
            c.update();
            //causes the coin to blink when near despawn time
            double time = c.elapsedTime;
            double[][] blinkTimes = {
                    {6, 6.15},
                    {6.4, 6.55},
                    {6.8, 6.95},
                    {7.2, 7.35},
                    {7.6, 7.75},
                    {8, 8.1},
                    {8.2, 8.3},
                    {8.4, 8.5}
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

    public void renderProjectile(String type, float delta, double projectileSpawnInterval, double[] projectileSpawnIntervalRandom, CopyOnWriteArrayList<Projectile> projectileList, CopyOnWriteArrayList<BlinkingArrow> arrowList, double spawnDelay) {
        for (int i = 0; i < projectileList.size(); i++) {
            //only updates and renders once the projectile is spawned
            if (projectileList.get(i).spawned) {
                //deletes laser after 2 seconds
                if (projectileList.get(i).type == "Laser" && projectileList.get(i).elapsedTime > 2)
                    projectileList.get(i).isOnScreen = false;
                //updates and render if the projectile is on screen
                if (projectileList.get(i).isOnScreen) {
                    projectileList.get(i).update(delta);
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

                    //sound effects for each projectile
                    if (!isMuted) {
                        switch (type) {
                            case "Boulder":
                                BOULDER_SOUND.play();
                                break;
                            case "Cannon":
                                CANNON_SOUND.play();
                                break;
                            case "Boomerang":
                                BOOMERANG_SOUND.play();
                                break;
                            case "Laser":
                                LASER_SOUND.play();
                                break;
                        }
                    }
                }
            }
        }
    }

    public void renderBoulders(float delta) {
        renderProjectile("Boulder", delta, Boulder.spawnInterval, Boulder.spawnIntervalRandom, Boulder.list, Boulder.arrowList, Boulder.spawnDelay);
    }

    public void renderCannons(float delta) {
        renderProjectile("Cannon", delta, Cannon.spawnInterval, Cannon.spawnIntervalRandom, Cannon.list, Cannon.arrowList, Cannon.spawnDelay);
    }

    public void renderBoomerang(float delta) {
        renderProjectile("Boomerang", delta, Boomerang.spawnInterval, Boomerang.spawnIntervalRandom, Boomerang.list, Boomerang.arrowList, Boomerang.spawnDelay);
    }

    public void renderLaser(float delta) {
        renderProjectile("Laser", delta, Laser.spawnInterval, Laser.spawnIntervalRandom, Laser.list, Laser.arrowList, Laser.spawnDelay);
    }


    public void spawnCoins() {
        if (timer.getWorldTimer() % COIN_SPAWN_INTERVAL == 0 && !coinsSpawned) {
            //x and y lists to test if it's trying spawn a coin where one already exists
            ArrayList<Integer> xList = new ArrayList<>();
            ArrayList<Integer> yList = new ArrayList<>();

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
        //coins despawn after a set number of seconds
        if (coinList.size() > 0 && coinList.get(0).elapsedTime > COIN_DESPAWN_DELAY) {
            coinList.clear();
        }
    }

    public boolean spawnProjectile(String type, double projectileSpawnInterval, double[] projectileSpawnIntervalRandom, boolean projectileSpawned, int maxProjectiles, int minProjectiles, CopyOnWriteArrayList<Projectile> projectileList, CopyOnWriteArrayList<BlinkingArrow> arrowList, int s, Animation a, String path) {
        //chooses a random time to assign projectileSpawnIntervalRandom within a calculated interval of the projectileSpawnInterval
        double interval = projectileSpawnInterval / 4;
        while (projectileSpawnIntervalRandom[0] == projectileSpawnInterval || projectileSpawnIntervalRandom[0] <= 0.0)
            projectileSpawnIntervalRandom[0] = (Math.round((projectileSpawnInterval + Math.random() * interval * 2 - interval) * 10)) / 10.0;

        //calculates the amount of seconds left until a projectile spawns
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
                }//if not in list, spawns projectile
                if (!inList) {
                    if (type == "Laser") {
                        //creates 8 lasers for each tile
                        boolean cutOffLastPixel = false;
                        if (direction == "DOWN" || direction == "LEFT")
                            cutOffLastPixel = true;
                        for (int j = 0; j < 8; j++) {
                            if ((direction == "UP" || direction == "RIGHT") && j == 7)
                                cutOffLastPixel = true;
                            if (direction == "UP") {
                                projectileList.add(new Projectile(type, x, j, direction, s, a, cutOffLastPixel));
                                arrowList.add(new BlinkingArrow(x, y, direction, path));
                                projectileSpawnCoords.add(new Integer[]{x, j});
                            } else if (direction == "DOWN") {
                                projectileList.add(new Projectile(type, x, j, direction, s, a, cutOffLastPixel));
                                arrowList.add(new BlinkingArrow(x, y, direction, path));
                                projectileSpawnCoords.add(new Integer[]{x, j});
                            } else if (direction == "LEFT") {
                                projectileList.add(new Projectile(type, j, y, direction, s, a, cutOffLastPixel));
                                arrowList.add(new BlinkingArrow(x, y, direction, path));
                                projectileSpawnCoords.add(new Integer[]{j, y});
                            } else if (direction == "RIGHT") {
                                projectileList.add(new Projectile(type, j, y, direction, s, a, cutOffLastPixel));
                                arrowList.add(new BlinkingArrow(x, y, direction, path));
                                projectileSpawnCoords.add(new Integer[]{j, y});
                            }
                            if (direction == "DOWN" || direction == "LEFT")
                                cutOffLastPixel = false;
                        }
                    } else {
                        projectileList.add(new Projectile(type, x, y, direction, s, a, false));
                        arrowList.add(new BlinkingArrow(x, y, direction, path));
                        projectileSpawnCoords.add(new Integer[]{x, y});
                    }
                    xList.add(x);
                    yList.add(y);
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
        Boulder.groupSpawned = spawnProjectile("Boulder", Boulder.spawnInterval, Boulder.spawnIntervalRandom, Boulder.groupSpawned, Boulder.max, Boulder.min, Boulder.list, Boulder.arrowList, Boulder.SPEED, Projectile.createAnimation("sprites/dodgeBoulder.png", 8, 8, 4, 3, 12), "sprites/dodgeBoulderArrow.png");
    }

    public void spawnCannon() {
        Cannon.groupSpawned = spawnProjectile("Cannon", Cannon.spawnInterval, Cannon.spawnIntervalRandom, Cannon.groupSpawned, Cannon.max, Cannon.min, Cannon.list, Cannon.arrowList, Cannon.SPEED, Projectile.createAnimation("sprites/dodgeCannonball.png", 8, 8, 1, 1, 1), "sprites/dodgeCannonballArrow.png");
    }

    public void spawnBoomerang() {
        Boomerang.groupSpawned = spawnProjectile("Boomerang", Boomerang.spawnInterval, Boomerang.spawnIntervalRandom, Boomerang.groupSpawned, Boomerang.max, Boomerang.min, Boomerang.list, Boomerang.arrowList, Boomerang.SPEED, Projectile.createAnimation("sprites/dodgeBoomerang.png", 8, 8, 2, 4, 8), "sprites/dodgeBoomerangArrow.png");
    }

    public void spawnLaser() {
        Laser.groupSpawned = spawnProjectile("Laser", Laser.spawnInterval, Laser.spawnIntervalRandom, Laser.groupSpawned, Laser.max, Laser.min, Laser.list, Laser.arrowList, Laser.SPEED, Projectile.createAnimation("sprites/dodgeLaser.png", 8, 9, 1, 3, 10), "sprites/dodgeLaserArrow.png");
    }

    public void detectCoin() {
        //detects coin collision
        for (int i = 0; i < coinList.size(); i++) {
            if (coinList.get(i).x == player.x && coinList.get(i).y == player.y) {
                coins++;
                if (!isMuted) {
                    COIN_SOUND.stop();
                    COIN_SOUND.play();
                }
                coinList.remove(i);
            }
        }
    }

    public void detectProjectileCollision(CopyOnWriteArrayList<Projectile> projectileList, ArrayList<Integer[]> projectileOldPos) {
        //only detects collision if the player is not invincible
        if (player.invincibilityTime == 0) {
            //detect for current position
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i).x == player.x && projectileList.get(i).y == player.y && projectileList.get(i).spawned) {
                    if (!isMuted) {
                        music.dispose();
                    }
                    game.setScreen(new GameOver(game));
                }
            }
            //detect for position one frame ago (to prevent phasing through the projectile if you go towards it on the exact frame)
            for (int i = 0; i < projectileOldPos.size(); i++) {
                if (projectileOldPos.get(i)[0] == player.x && projectileOldPos.get(i)[1] == player.y) {
                    if (!isMuted) {
                        music.dispose();
                    }
                    game.setScreen(new GameOver(game));
                }
            }
            //updates the old positions
            projectileOldPos.clear();
            for (Projectile p : projectileList) {
                if (p.spawned)
                    projectileOldPos.add(new Integer[]{p.x, p.y});
            }
        }
    }

    public void detectBoulderCollision() {
        detectProjectileCollision(Boulder.list, Boulder.oldPos);
    }

    public void detectCannonCollision() {
        detectProjectileCollision(Cannon.list, Cannon.oldPos);
    }

    public void detectBoomerangCollision() {
        detectProjectileCollision(Boomerang.list, Boomerang.oldPos);
    }

    public void detectLaserCollision() {
        detectProjectileCollision(Laser.list, Laser.oldPos);
    }

    public void dispose() {
        //disposes of all textures and sounds
        GRID.dispose();
        COIN_SOUND.dispose();
        music.dispose();
        levelTexture.dispose();
        BackgroundSprite.getTexture().dispose();
    }
}