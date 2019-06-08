package com.mygdx.game.physicalEntities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DodgeGame;


public class MyTimer {

  /* public int secondsLeft = 10;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            System.out.println(secondsLeft);
            if(secondsLeft == 0)
                this.cancel();
            secondsLeft--;

        }
    };

    public void start(int delay, int period){
        timer.scheduleAtFixedRate(task, delay, period);
    }*/


    /*public static void main(String[] args){
        MyTimer timer1 = new MyTimer();
        timer1.start(0, 1000);
    }*/

    private int worldTimer;
    private String worldTimerString;
    private float timeCount;
    private Texture clock =  new Texture("timer.png");

    public MyTimer(int time) {
        worldTimer = time;
        worldTimerString = "" + worldTimer;
        timeCount = 0;
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            worldTimerString = "" + worldTimer;
            timeCount = 0;
        }
    }

    public String getWorldTimerString() {
        return worldTimerString;
    }

    public int getWorldTimer() {
        return worldTimer;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        batch.begin();
        batch.draw(clock,(int)(DodgeGame.WIDTH * 0.25) - 100, DodgeGame.HEIGHT/2 - 100,200 ,200 );

        font.setColor(Color.GREEN);
        font.getData().setScale(4f);
        font.draw(batch, getWorldTimerString(), (int)(DodgeGame.WIDTH * 0.20) + 33, DodgeGame.HEIGHT/2 + 10);





    }


}

