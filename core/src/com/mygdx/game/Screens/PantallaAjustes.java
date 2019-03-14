package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PixelWars;


public class PantallaAjustes implements Screen {
    private final PixelWars game;

    // ~ Camara del juego
    private OrthographicCamera camara;
    // ~ Escalar la camara (superclase)
    public Viewport vista;
    // ~ Optimizar/Administrar los gráficos
    private SpriteBatch batch;

    private Texture textNubes;
    private Texture textPasto;
    private Texture textMontañas;
    private Texture textCielo;
    private BitmapFont font = new BitmapFont();
    public PantallaAjustes(PixelWars game) {
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


        textNubes = new Texture("bClouds.png");
        textPasto = new Texture("sGrass1.png");
        textMontañas = new Texture("sMtns1.png");
        textCielo = new Texture("bSky1.png");



    }

    public void handleInput(float dt){

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new PantallaMenu(game));
        }

    }

    public void update(float dt){
        handleInput(dt);
        camara.update();
    }

    @Override
    public void render(float delta) {

        update(delta);
        // ~ Dibujar (60 fps)
        Gdx.gl.glClearColor(0,0,1,1);
        // ~ Borra la pantalla completamente
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix((camara.combined));

        batch.begin();
        // ~ Dibujes

        batch.draw(textCielo, 0,0);
        batch.draw(textCielo, textCielo.getWidth(),0);
        batch.draw(textNubes,0,50);
        batch.draw(textMontañas, 0,50);
        batch.draw(textPasto, 0,0);
        batch.draw(textPasto, textPasto.getWidth(),0);
        font.draw(batch,"AJUSTE EFECTOS DE SONIDO",50,120);
        font.draw(batch,"AJUSTE MÚSICA",50,90);


        batch.end();

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