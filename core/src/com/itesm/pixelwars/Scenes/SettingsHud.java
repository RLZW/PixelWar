package com.itesm.pixelwars.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.pixelwars.PixelWars;
import com.itesm.pixelwars.Screens.MenuScreen;

public class SettingsHud implements Disposable {
    public Stage stage;
    private Table table;
    private PixelWars game;
    private Viewport viewport;
    private Label.LabelStyle labelStyle,slabelStyle;
    private BitmapFont bitmapFont,sbitmapFont;
    private Label label1;



    public SettingsHud(final PixelWars game){
        this.game = game;
        viewport = new StretchViewport(game.ANCHO,game.ALTO, game.gamecam);
        stage = new Stage(viewport,game.batch);
        table = new Table();

        //Text
        labelStyle = new Label.LabelStyle();
        bitmapFont = new BitmapFont(Gdx.files.internal("pixel.fnt"));
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = Color.WHITE;

        slabelStyle = new Label.LabelStyle();
        sbitmapFont = new BitmapFont(Gdx.files.internal("spixel.fnt"));
        slabelStyle.font = sbitmapFont;
        slabelStyle.fontColor = Color.WHITE;

        float row_height = PixelWars.ALTO/16;
        label1 = new Label("Music",labelStyle);
        label1.setSize(30,30);
        label1.setPosition(PixelWars.ANCHO/2-80,PixelWars.ALTO/2+25);
        label1.setAlignment(Align.center);
        stage.addActor(label1);

        //Buttons & Textures
        //Button Back
        Texture texturetback = new Texture("back.png");
        //Texture texturebtnbackp = new Texture("btn1P_Pressed.png");

        TextureRegionDrawable trdaback = new TextureRegionDrawable(texturetback);
        //TextureRegionDrawable trdaplayp = new TextureRegionDrawable(texturebtnplayp);

        ImageButton btnBack = new ImageButton(trdaback);
        stage.addActor(btnBack);
        btnBack.setPosition(PixelWars.ANCHO-btnBack.getWidth()-1,PixelWars.ALTO-btnBack.getHeight()-1);

        //Button Volume
        Texture texturebtnVol = new Texture("btnVol.png");
        Texture texturebtnVolp = new Texture("btnVol_Pressed.png");

        TextureRegionDrawable trdVol = new TextureRegionDrawable(texturebtnVol);
        final TextureRegionDrawable trdVolp = new TextureRegionDrawable(texturebtnVolp);

        ImageButton btnVol = new ImageButton(trdVol);
        stage.addActor(btnVol);
        btnVol.setPosition(PixelWars.ANCHO/2-100,PixelWars.ALTO/2);

        ImageButton btnVolp = new ImageButton(trdVolp);
        stage.addActor(btnVolp);
        btnVolp.setPosition(PixelWars.ANCHO/2-75,PixelWars.ALTO/2);


        //Listeneres

        //List 1 Back
        btnBack.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    //Responder al evento del boton
                                    game.setScreen(new MenuScreen(game));

                                }
                            }
        );

        //List 2 Vol
        btnVol.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    //Responder al evento del boton
                                    game.iniciarMusica();

                                }
                            }
        );

        //List 3 Vol off
        btnVolp.addListener(new ClickListener() {
                               @Override
                               public void clicked(InputEvent event, float x, float y) {
                                   super.clicked(event, x, y);
                                   //Responder al evento del boton
                                   game.pausarMusica();

                               }
                           }
        );







        //Layout


        //prueba = new Label("PRUEBA",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
