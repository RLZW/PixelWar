package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.pixelwars.PixelWars;
import com.itesm.pixelwars.Scenes.ParallaxTerrain;
import com.itesm.pixelwars.Scenes.SettingsHud;

public class SettingsScreen implements Screen {
    private PixelWars game;
    private Viewport gamePort;

    private ParallaxTerrain terrainHud;
    private SettingsHud settingsHud;
    private Texture texture;
    private Texture text_settings;

    public SettingsScreen(PixelWars game){
        this.game = game;
        gamePort =  new FitViewport(game.ANCHO,game.ALTO,game.gamecam);
    }

    @Override
    public void show() {

        gamePort = new StretchViewport(PixelWars.ANCHO,PixelWars.ALTO,game.gamecam);
        text_settings = new Texture("settings.png");
        terrainHud = new ParallaxTerrain(game);
        settingsHud= new SettingsHud(game);

        Gdx.input.setInputProcessor(settingsHud.stage);
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(settingsHud.stage.getCamera().combined);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new LoadingScreen(game,Screens.MenuScreen));
        }
        terrainHud.stage.draw();
        game.batch.begin();
        game.batch.draw(text_settings,0,0);
        game.batch.end();
        settingsHud.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        terrainHud.dispose();
        settingsHud.dispose();
        text_settings.dispose();

    }
}
