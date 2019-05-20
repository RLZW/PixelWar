package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class highscore {

    private Preferences level;

    public highscore(){
        level = Gdx.app.getPreferences("highscore");
    }

    public void setLevel(int passedLevel){
        level.putInteger("highscore", passedLevel);
        level.flush();
    }

    public int getPassedLevel(){
        int passedLevel = level.getInteger("highscore",0);
        return passedLevel;
    }


}
