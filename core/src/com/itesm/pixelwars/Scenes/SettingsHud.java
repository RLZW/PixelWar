package com.itesm.pixelwars.Scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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


    public SettingsHud(final PixelWars game){
        this.game = game;
        viewport = new StretchViewport(game.ANCHO,game.ALTO, game.gamecam);
        stage = new Stage(viewport,game.batch);
        table = new Table();

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
        btnVol.setPosition(PixelWars.ANCHO/2-20,PixelWars.ALTO/2);

        //Button Volume OFF
        Texture texturebtnVolOff = new Texture("btnSoundOff.png");
        Texture texturebtnVolpOff = new Texture("btnSoundOff_Pressed.png");

        TextureRegionDrawable trdVolOff = new TextureRegionDrawable(texturebtnVolOff);
        TextureRegionDrawable trdVolpOff = new TextureRegionDrawable(texturebtnVolpOff);

        ImageButton btnVolOff = new ImageButton(trdVolOff,trdVolpOff);
        stage.addActor(btnVolOff);
        btnVolOff.setPosition(PixelWars.ANCHO/2+20,PixelWars.ALTO/2);

        


        //Listeneres

        //List 1 Player
        btnBack.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    //Responder al evento del boton
                                    game.setScreen(new MenuScreen(game));
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
