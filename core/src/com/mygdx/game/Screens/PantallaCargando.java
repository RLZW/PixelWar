package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PixelWars;

public class PantallaCargando implements Screen {
    private final PixelWars game;

    // ~ Camara del juego
    private OrthographicCamera camara;
    // ~ Escalar la camara (superclase)
    public Viewport vista;
    // ~ Optimizar los gráficos
    private SpriteBatch batch;

    //Tiempo
    private float contadorTiempo = 0;

    //Textura
    private Texture textCarga;

    public PantallaCargando(PixelWars gamePW) {
        // ~ Guardamos referencia del juego
        this.game = gamePW;
    }

    @Override
    public void show() {
        camara = new OrthographicCamera(game.ANCHO, game.ALTO);
        camara.position.set(game.ANCHO/2, game.ALTO/2, 0);
        camara.update();
        // ~ Vista  [StretchViewport ajusta la vista a toda la pantalla]
        vista = new StretchViewport(game.ANCHO, game.ALTO, camara);
        batch = new SpriteBatch();
        // ~ Imagen
        textCarga = new Texture("loading.png");

    }

    @Override
    public void render(float delta) {
        // ~ Dibujar (60 fps)
        Gdx.gl.glClearColor(1,0,0,1);
        // ~ Borra la pantalla completamente
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix((camara.combined));
        batch.begin();
        // ~ Dibujes
        batch.draw(textCarga, 0,0,game.ANCHO,game.ALTO);
        batch.end();


        contadorTiempo+=delta;
        if(contadorTiempo>=2){
            game.setScreen(new PantallaMenu(game));
        }

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
        textCarga.dispose();
        batch.dispose();


    }
}
