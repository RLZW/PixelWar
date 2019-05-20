package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class highscore {

    private Preferences Score;

    public highscore(){
        Score = Gdx.app.getPreferences("highscore");
    }

    public void setHighScore(int actualHighScore){
        Score.putInteger("highscore", actualHighScore);
        Score.flush();
    }

    public int getHighScore(){
        int actualHighScore = Score.getInteger("highscore",0);
        return actualHighScore;
    }


}
