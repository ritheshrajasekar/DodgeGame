package com.mygdx.game.com.mygdx.game.screens;

import java.util.Timer;
import java.util.TimerTask;

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
       private  String worldTimerString;
        private float timeCount;

        public MyTimer(int time){
            worldTimer = time;
            worldTimerString = "" + worldTimer;
            timeCount = 0;
        }
        public void update(float dt){
            timeCount+= dt;
            if(timeCount>=1){
                worldTimer--;
                worldTimerString = " " + worldTimer;
                timeCount = 0;
            }
        }
        public String getWorldTimerString(){
            return worldTimerString;
        }
        public int getWorldTimer(){
            return worldTimer;
        }



    }

