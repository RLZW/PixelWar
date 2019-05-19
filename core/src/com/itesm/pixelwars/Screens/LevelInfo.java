package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class LevelInfo {

    private Preferences level;

    public LevelInfo(){
        level = Gdx.app.getPreferences("Levels");
    }

    public void setLevel(int passedLevel){
        level.putInteger("levels", passedLevel);
        level.flush();
    }

    public int getPassedLevel(){
        int passedLevel = level.getInteger("levels",0);
        return passedLevel;
    }


}
