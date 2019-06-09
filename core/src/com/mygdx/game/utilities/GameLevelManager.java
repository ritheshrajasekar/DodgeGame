package com.mygdx.game.utilities;
import com.mygdx.game.DodgeGame;
import com.mygdx.game.com.mygdx.game.screens.*;

public class GameLevelManager {
    public GameLevelManager(){

    }
    // using the current level that is passed in , this level determines and sets the next screen to the subsequent level
    public void nextLevel(DodgeGame game, int level){
        switch (level){
            case 1:
                game.setScreen( new Level2(game));
                break;
            case 2:
                game.setScreen( new Level3(game));
                break;
//            case 3:
//                game.setScreen( new Level4(game));
//                break;
//            case 4:
//                game.setScreen( new Level5(game));
//                break;
//            case 5:
//                game.setScreen( new Level6(game));
//                break;
//            case 6:
//                game.setScreen( new Level7(game));
//                break;
//            case 7:
//                game.setScreen( new Level8(game));
//                break;
//            case 8:
//                game.setScreen( new Level9(game));
//                break;
//            case 9:
//                game.setScreen( new Level10(game));
//                break;
//            case 10:
//                game.setScreen( new Level11(game));
//                break;
//            case 11:
//                game.setScreen( new Level12(game));
//                break;
//            case 12:
//                game.setScreen( new screenCred(game));
//                break;

        }

    }
    // based on the current level, retry sets the current game to same the level again.
    public void retry(DodgeGame game, int level){
        switch (level){
            case 1:
                game.setScreen( new Level1(game));
                break;
            case 2:
                game.setScreen( new Level2(game));
                break;
//            case 3:
//                game.setScreen( new Level3(game));
//                break;
//            case 4:
//                game.setScreen( new Level4(game));
//                break;
//            case 5:
//                game.setScreen( new Level5(game));
//                break;
//            case 6:
//                game.setScreen( new Level6(game));
//                break;
//            case 7:
//                game.setScreen( new Level7(game));
//                break;
//            case 8:
//                game.setScreen( new Level8(game));
//                break;
//            case 9:
//                game.setScreen( new Level9(game));
//                break;
//            case 10:
//                game.setScreen( new Level10(game));
//                break;
//            case 11:
//                game.setScreen( new Level11(game));
//                break;
//            case 12:
//                game.setScreen( new Level12(game));
//                break;

        }

    }


}