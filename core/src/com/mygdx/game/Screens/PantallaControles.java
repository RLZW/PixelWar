package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PixelWars;



class PantallaControles implements Screen {
    private final PixelWars game;

    // ~ Camara del juego
    private OrthographicCamera camara;
    // ~ Escalar la camara (superclase)
    public Viewport vista;
    // ~ Optimizar/Administrar los gráficos
    private SpriteBatch batch;

    public PantallaControles(PixelWars game) {
        this.game = game;
    }

    @Override
    public void show() {
        //~ Constructor
        camara = new OrthographicCamera(game.ANCHO, game.ALTO);
        camara.position.set(game.ANCHO/2, game.ALTO/2, 0);
        camara.update();
        // ~ Vista  [StretchViewport ajusta la vista a toda la pantalla]
        vista = new StretchViewport(game.ANCHO, game.ALTO, camara);
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        // ~ Dibujar (60 fps)
        Gdx.gl.glClearColor(33,44,0,0);
        // ~ Borra la pantalla completamente
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix((camara.combined));

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
