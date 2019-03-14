package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PixelWars;


public class PantallaMenu implements Screen {

    private PixelWars game;

    // ~ Camara del juego
    private OrthographicCamera camara;
    // ~ Escalar la camara (superclase)
    public Viewport vista;
    // ~ Optimizar los gráficos
    private SpriteBatch batch;

    //Textura
    private Texture textFondo;

    // MENU, Escenas, Independecia dela cámara(movimeintos)
    private Stage escenaMenu; // Botones
    private Texture textNubes;
    private Texture textPasto;
    private Texture textMontañas;
    private Texture textCielo;
    private Texture textTitulo;

    public PantallaMenu(PixelWars game) {
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
        // ~ Imagen
        textFondo = new Texture("background.jpg");
        textNubes = new Texture("bClouds.png");
        textPasto = new Texture("sGrass1.png");
        textMontañas = new Texture("sMtns1.png");
        textCielo = new Texture("bSky1.png");
        textTitulo = new Texture("pwTitle.png");

        // Menú
        crearMenu();
        // Pasamos el control de INPUT a la escena
        Gdx.input.setInputProcessor(escenaMenu);

    }

    private void crearMenu() {
        escenaMenu = new Stage(vista);
        // Boton PLAY
        Texture textBtnPlay = new Texture("btn1P.png");
        TextureRegionDrawable trdBtnPlay = new TextureRegionDrawable(new TextureRegion(textBtnPlay));
        ImageButton btnPlay = new ImageButton(trdBtnPlay);
        btnPlay.setPosition(game.ANCHO/2-62.5f,game.ALTO/2 -90);

        //Agregar el LISTENER
        btnPlay.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    //Responder al evento del boton
                                    game.setScreen(new PantallaNivel1(game));

                                }
                            }
        );

        // Boton AJUSTES
        Texture textBtnAjustes = new Texture("btnOptions.png");
        TextureRegionDrawable trdBtnAjustes = new TextureRegionDrawable(new TextureRegion(textBtnAjustes));
        ImageButton btnAjustes = new ImageButton(trdBtnAjustes);
        btnAjustes.setPosition(1f,game.ALTO-22.5f);

        //Agregar el LISTENER
        btnAjustes.addListener(new ClickListener() {
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       super.clicked(event, x, y);
                                       //Responder al evento del boton
                                       game.setScreen(new PantallaAjustes(game));

                                   }
                               }
        );

        // Boton Controles
        Texture textBtnControles = new Texture("btn2P.png");
        TextureRegionDrawable trdBtnControles = new TextureRegionDrawable(new TextureRegion(textBtnControles));
        ImageButton btnControles = new ImageButton(trdBtnControles);
        btnControles.setPosition(game.ANCHO/2+20,game.ALTO/2 -90);

        //Agregar el LISTENER
        btnControles.addListener(new ClickListener() {
                                     @Override
                                     public void clicked(InputEvent event, float x, float y) {
                                         super.clicked(event, x, y);
                                         //Responder al evento del boton
                                         game.setScreen(new PantallaNivel1(game));

                                     }
                                 }
        );

        // Boton Ayuda
        Texture textBtnAyuda = new Texture("btnAbout.png");
        TextureRegionDrawable trdBtnAyuda = new TextureRegionDrawable(new TextureRegion(textBtnAyuda));
        ImageButton btnAyuda = new ImageButton(trdBtnAyuda);
        btnAyuda.setPosition(game.ANCHO-20,game.ALTO-20);

        //Agregar el LISTENER
        btnAyuda.addListener(new ClickListener() {
                                 @Override
                                 public void clicked(InputEvent event, float x, float y) {
                                     super.clicked(event, x, y);
                                     //Responder al evento del boton
                                     game.setScreen(new PantallaAyuda(game));

                                 }
                             }
        );

        escenaMenu.addActor(btnPlay);
        escenaMenu.addActor(btnAjustes);
        escenaMenu.addActor(btnControles);
        escenaMenu.addActor(btnAyuda);

    }

    @Override
    public void render(float delta) {
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
        batch.draw(textTitulo,game.ANCHO/2-70,game.ALTO/2-30);

        batch.end();

        escenaMenu.draw();
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
        textFondo.dispose();
        batch.dispose();


    }
}
