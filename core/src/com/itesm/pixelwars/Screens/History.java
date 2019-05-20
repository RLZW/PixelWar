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
    private Texture history1,history2,history3,history4;
    private Viewport gamePort;


    public History(PixelWars game){
        this.game = game;
    }

    @Override
    public void show() {
        gamePort = new StretchViewport(PixelWars.ANCHO,PixelWars.ALTO,game.gamecam);
        stage = new Stage(gamePort,game.batch);
        Gdx.input.setInputProcessor(stage);

        logoTexture = new Texture(Gdx.files.internal("fobutec.png"));
        history1 = new Texture("history1.png");
        history2 = new Texture("history2.png");
        history3 = new Texture("history3.png");
        history4 = new Texture("history4.png");
        Image his1 = new Image(history1);
        Image his2 = new Image(history2);
        Image his3 = new Image(history3);
        Image his4 = new Image(history4);

        stage.addActor(his1);
        stage.addActor(his2);
        stage.addActor(his3);
        stage.addActor(his4);

        his1.addAction(sequence(delay(0.2f), fadeIn(2),delay(2), fadeOut(1), run(new Runnable() {
            @Override
            public void run() {

            }
        })));

        his2.addAction(sequence(delay(0.2f), fadeIn(2),delay(2), fadeOut(1), run(new Runnable() {
            @Override
            public void run() {

            }
        })));

        his3.addAction(sequence(delay(0.2f), fadeIn(2),delay(2), fadeOut(1), run(new Runnable() {
            @Override
            public void run() {

            }
        })));

        his4.addAction(sequence(delay(0.2f), fadeIn(2),delay(2), fadeOut(1), run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(new LoadingScreen(game,Screens.GameScreen));
            }
        })));


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, Color.WHITE.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(stage.getCamera().combined);

        stage.act(delta);
        stage.draw();
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
        history2.dispose();
        history3.dispose();
        history4.dispose();
        stage.dispose();

    }
}
