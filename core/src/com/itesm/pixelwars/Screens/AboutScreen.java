package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.pixelwars.PixelWars;
import com.itesm.pixelwars.Scenes.ParallaxTerrain;

public class AboutScreen implements Screen {
    private PixelWars game;
    private Viewport gamePort;

    private ParallaxTerrain terrainHud;
    private Texture texture;
    private Texture aboutinfo;
    private Stage stage;
    private Texture texturetback;
    private Texture texturebtnbackp;
    private TextureRegionDrawable trdaback;
    private TextureRegionDrawable trdaplayp;
    private ImageButton btnBack;



    public AboutScreen(PixelWars game){
        this.game = game;
        gamePort =  new FitViewport(game.ANCHO,game.ALTO,game.gamecam);
    }

    @Override
    public void show() {
        gamePort = new StretchViewport(PixelWars.ANCHO,PixelWars.ALTO,game.gamecam);
        terrainHud = new ParallaxTerrain(game);
        stage = new Stage(gamePort,game.batch);
        createButtons(stage);
        texture = new Texture("title1.png");
        aboutinfo = new Texture("aboutinfo.png");
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(false);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0,224f/255.0f,0,1f);

        terrainHud.stage.draw();
        game.batch.begin();
        game.batch.draw(aboutinfo,0,-PixelWars.ALTO*2);
        game.batch.end();
        stage.draw();
        stage.act();
        handleIsTouched(delta);


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

    public void createButtons(Stage stage){
        Texture texturetback = new Texture("btnBack.png");
        Texture texturebtnbackp = new Texture("btnBack_Pressed.png");
        TextureRegionDrawable trdaback = new TextureRegionDrawable(texturetback);
        TextureRegionDrawable trdaplayp = new TextureRegionDrawable(texturebtnbackp);

        ImageButton btnBack = new ImageButton(trdaback,trdaplayp);
        stage.addActor(btnBack);
        btnBack.setPosition(PixelWars.ANCHO-btnBack.getWidth()-1,PixelWars.ALTO-btnBack.getHeight()-1);

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

    }

    public void handleIsTouched(float deltaTime){
        float aument =200.0f;
        //Buttons Movement Behavior
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getY()>Gdx.graphics.getHeight()/2&& gamePort.getCamera().position.y > (aboutinfo.getHeight())*-1/2 ){
                aument *= deltaTime;
                gamePort.getCamera().position.y-=aument;
                Gdx.app.log("CameraPositon", ""+ gamePort.getCamera().position.y);

            }
            else if (Gdx.input.getY()<=Gdx.graphics.getHeight()/2&& gamePort.getCamera().position.y < PixelWars.ALTO/2-5 ){
                aument *= deltaTime;
                gamePort.getCamera().position.y+=aument;
                Gdx.app.log("CameraPositon", ""+ gamePort.getCamera().position.y);
                Gdx.app.log("AboutInfo Height", ""+ (aboutinfo.getHeight())*-1);

            }
        }

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        terrainHud.dispose();


    }
}
