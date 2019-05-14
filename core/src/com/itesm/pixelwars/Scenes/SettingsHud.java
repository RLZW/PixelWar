package com.itesm.pixelwars.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
import com.itesm.pixelwars.Screens.LoadingScreen;
import com.itesm.pixelwars.Screens.MenuScreen;
import com.itesm.pixelwars.Screens.Screens;

public class SettingsHud implements Disposable {
    public Stage stage;
    private Table table;
    private PixelWars game;
    private Viewport viewport;
    private Label.LabelStyle labelStyle,slabelStyle;
    private BitmapFont bitmapFont,sbitmapFont;
    private Label label1,label2,label3,label4;



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
        label1 = new Label("Settings",labelStyle);
        label1.setSize(PixelWars.ANCHO/2-label1.getWidth(),row_height);
        label1.setPosition(PixelWars.ANCHO/2,PixelWars.ALTO/2+50);
        label1.setAlignment(Align.center);
        stage.addActor(label1);


        label2 = new Label("music",labelStyle);
        label2.setSize(PixelWars.ANCHO/2,row_height);
        label2.setPosition(PixelWars.ANCHO/2-80,PixelWars.ALTO/2+20);
        label2.setAlignment(Align.center);
        stage.addActor(label2);

        label3 = new Label("ON",labelStyle);
        label3.setSize(PixelWars.ANCHO/2-label1.getWidth(),30);
        label3.setPosition(PixelWars.ANCHO/2-140,PixelWars.ALTO/2-30);
        label3.setAlignment(Align.center);
        stage.addActor(label3);

        label4 = new Label("OFF",labelStyle);
        label4.setSize(PixelWars.ANCHO/2-label1.getWidth(),30);
        label4.setPosition(PixelWars.ANCHO/2-150,PixelWars.ALTO/2-70);
        label4.setAlignment(Align.center);
        stage.addActor(label4);





        //Buttons & Textures
        //Button 1 Player
        Texture texturetback = new Texture("back.png");
        //Texture texturebtnbackp = new Texture("btn1P_Pressed.png");

        TextureRegionDrawable trdaback = new TextureRegionDrawable(texturetback);
        //TextureRegionDrawable trdaplayp = new TextureRegionDrawable(texturebtnplayp);

        ImageButton btnBack = new ImageButton(trdaback);
        stage.addActor(btnBack);
        btnBack.setPosition(PixelWars.ANCHO-btnBack.getWidth()-1,PixelWars.ALTO-btnBack.getHeight()-1);


        //Button Volume ON
        Texture texturebtnVol = new Texture("btnSoundOn.png");
        Texture texturebtnVolp = new Texture("btnSoundOn_Pressed.png");

        TextureRegionDrawable trdVol = new TextureRegionDrawable(texturebtnVol);
        TextureRegionDrawable trdVolp = new TextureRegionDrawable(texturebtnVolp);

        ImageButton btnVol = new ImageButton(trdVol,trdVolp);
        stage.addActor(btnVol);
        btnVol.setPosition(PixelWars.ANCHO/2,PixelWars.ALTO/2-35);

        //Button Volume OFF
        Texture texturebtnVolOff = new Texture("btnSoundOff.png");
        Texture texturebtnVolpOff = new Texture("btnSoundOff_Pressed.png");

        TextureRegionDrawable trdVolOff = new TextureRegionDrawable(texturebtnVolOff);
        TextureRegionDrawable trdVolpOff = new TextureRegionDrawable(texturebtnVolpOff);

        ImageButton btnVolOff = new ImageButton(trdVolOff,trdVolpOff);
        stage.addActor(btnVolOff);
        btnVolOff.setPosition(PixelWars.ANCHO/2,PixelWars.ALTO/2-70);

        


        //Listeneres

        //List 1 Player
        btnBack.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    //Responder al evento del boton
                                    //game.setScreen(new MenuScreen(game));
                                    game.setScreen(new LoadingScreen(game,Screens.MenuScreen));
                                }
                            }
        );


        //List 2 Back Music Vol ON
        btnVol.addListener(new ClickListener() {
                               @Override
                               public void clicked(InputEvent event, float x, float y) {
                                   super.clicked(event, x, y);
                                   //Responder al evento del boton
                                    game.startMusic();


                               }
                           }
        );

        //List 3 Back Music OFF
        btnVolOff.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    //Responder al evento del boton
                                    game.pauseMusic();


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
