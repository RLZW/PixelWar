package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.pixelwars.PixelWars;
import com.itesm.pixelwars.Sprites.Actors.TitleActor;

import java.util.logging.SocketHandler;

public class MapScreen implements Screen {
    private final LevelInfo levelInfor;
    private PixelWars game;
    private Stage stage;
    private Texture map;
    private TitleActor mapActor;
    private Viewport gamePort;
    private Sound warsong;
    public MapScreen(PixelWars game, LevelInfo levelInfo){
        this.game = game;
        gamePort = new StretchViewport(PixelWars.ANCHO,PixelWars.ALTO,game.gamecam);
        this.levelInfor=levelInfo;
    };




    @Override
    public void show() {
        map = new Texture("map2.png");
        mapActor = new TitleActor(map);
        stage = new Stage(gamePort,game.batch);
        stage.addActor(mapActor);
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(false);
        if(game.SOUND){
            warsong = Gdx.audio.newSound(Gdx.files.internal("heroic.mp3"));
            warsong.play();

        game.pauseMusic();
        }

        stage.addListener(new ClickListener() {
                              @Override
                              public void clicked(InputEvent event, float x, float y) {
                                  super.clicked(event, x, y);
                                  //game.setScreen(new GameScreen(game));
                                  game.setScreen(new LoadingScreen(game,Screens.GameScreen));
                              }
                          }
        );

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();

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
        stage.dispose();
        map.dispose();

    }
}
