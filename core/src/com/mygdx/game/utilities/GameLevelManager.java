//This class is called whenever a screen needs to be changed to a new level
//Class made by Rithik Rajasekar

package com.mygdx.game.utilities;

import com.mygdx.game.DodgeGame;
import com.mygdx.game.com.mygdx.game.screens.*;


public class GameLevelManager {
    //plays a level based on the level number
    public static void playLevel(DodgeGame game, int level) {
        //using a switch case, playLevel sets the game's level
        switch (level) {
            case 1:
                game.setScreen(new Level1(game));
                break;
            case 2:
                game.setScreen(new Level2(game));
                break;
            case 3:
                game.setScreen(new Level3(game));
                break;
            case 4:
                game.setScreen(new Level4(game));
                break;
            case 5:
                game.setScreen(new Level5(game));
                break;
            case 6:
                game.setScreen(new Level6(game));
                break;
            case 7:
                game.setScreen( new Level7(game));
                break;
            case 8:
                game.setScreen( new Level8(game));
                break;
            case 9:
                game.setScreen( new Level9(game));
                break;
            case 10:
                game.setScreen( new Level10(game));
                break;
            case 11:
                game.setScreen( new Level11(game));
                break;
            case 12:
                game.setScreen( new Level12(game));
                break;
            case 13:
                game.setScreen(new FinishGame(game));
        }
    }
}
