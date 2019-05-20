package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.pixelwars.PixelWars;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class History implements Screen {
    private PixelWars game;
    private Stage stage;
    private Texture backgroundTexture;
    private Texture logoTexture;
    private Texture history1;
    private Texture history2;
    private Texture history3;
    private Texture history4;
    private Viewport gamePort;


    public History(PixelWars game){
        this.game = game;
    }

    @Override
    public void show() {
        gamePort = new StretchViewport(PixelWars.ANCHO,PixelWars.ALTO,game.gamecam);
        stage = new Stage(gamePort,game.batch);

        history1 = new Texture(Gdx.files.internal("final_history.png"));


        Image his1 = new Image(history1);

        his1.getColor().a = 0f;
        stage.addActor(his1);
        his1.addAction(sequence(delay(1), fadeIn(1),delay(10), fadeOut(2), run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(new LoadingScreen(game,Screens.GameScreen));
                dispose();
            }
        })));


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, Color.WHITE.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
        stage.getViewport().update(width,height,true);

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
        history1.dispose();
        stage.dispose();

    }
}
