package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.pixelwars.PixelWars;
import com.itesm.pixelwars.Sprites.Actors.CastleActor;
import com.itesm.pixelwars.Sprites.Animations.TowerAnimation;

public class GameScreen implements Screen {
    private Stage stage;
    private PixelWars game;
    private Viewport gamePort;
    private CastleActor myCastle;
    private CastleActor enemyCastle;
    private TowerAnimation animatedCastle;
    private TowerAnimation animatedEnemyCastle;

    //Clouds Animation
    private final int SPEED_DIFERENCCE = 60;
    private float srcX = 0;

    //ActionBar
    private float xBar = 0;
    private float xWarr = 15;
    private float xMiner = 40;
    private Texture actionbar;
    private Texture warrior_button,miner_button;
    private TextureRegionDrawable trd_warrior_button,trd_miner_button;
    private ImageButton btnWarrior;
    private ImageButton btnMiner;

    //Textures & TRDA


    private Texture textCastle, textEnemyCastle;
    private Texture skytext,bgrass,bmountains,bclouds;


    public GameScreen(PixelWars game){
        this.game = game;
        gamePort = new StretchViewport(game.ANCHO,game.ALTO,game.gamecam);
    }



    //Create a castle
    private void createCastle(float x, float y) {
        textCastle = new Texture("torreInvisible.png");
        myCastle = new CastleActor(textCastle);
        stage.addActor(myCastle);
        myCastle.setPosition(x, y );
        Texture texturaCastilloAnimado = new Texture("torreAzul.png");
        animatedCastle = new TowerAnimation(x,y,texturaCastilloAnimado);
    }

    private void createEnemyCastle(float x, float y) {
        textEnemyCastle = new Texture("torreInvisible.png");
        enemyCastle = new CastleActor(textEnemyCastle);
        stage.addActor(enemyCastle);
        enemyCastle.setPosition(x, y );
        Texture texturaCastilloAnimado = new Texture("torreRoja.png");
        animatedEnemyCastle = new TowerAnimation(x,y,texturaCastilloAnimado);

    }



    @Override
    public void show() {
        stage = new Stage(gamePort,game.batch);

        //Action Bar
        actionbar = new Texture("bar.png");
        warrior_button = new Texture("sword.png");
        miner_button = new Texture("money.png");
        trd_warrior_button = new TextureRegionDrawable(warrior_button);
        trd_miner_button = new TextureRegionDrawable(miner_button);

        btnWarrior = new ImageButton(trd_warrior_button);
        btnMiner = new ImageButton(trd_miner_button);
        stage.addActor(btnWarrior);
        stage.addActor(btnMiner);
        btnWarrior.setPosition(xWarr,PixelWars.ALTO-btnWarrior.getHeight()-1);
        btnMiner.setPosition(xMiner,PixelWars.ALTO-btnMiner.getHeight()-1);


        //Background
        bclouds = new Texture("bClouds.png");
        skytext = new Texture("bSky1.png");
        bgrass = new Texture("bGrass.png");
        bmountains = new Texture("bMtns.png");
        bclouds.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

        //Towers
        createCastle(10,bgrass.getHeight()/4);
        createEnemyCastle(PixelWars.ANCHO*1.66F,bgrass.getHeight()/4);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(false);


    }





    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(game.gamecam.combined);
        srcX += SPEED_DIFERENCCE*delta;
        stage.act();




        game.batch.begin();
        game.batch.draw(skytext,0,0);
        game.batch.draw(skytext,skytext.getWidth(),0);
        game.batch.draw(bclouds,0,bgrass.getHeight(),0, 0, bclouds.getWidth(), bclouds.getHeight(),1,1,0,(int)srcX,0,bclouds.getWidth(),bclouds.getHeight(),false,false);
        game.batch.draw(bmountains,0,bgrass.getHeight()-3);
        game.batch.draw(bgrass,0,0);
        animatedCastle.render(game.batch);
        animatedEnemyCastle.render(game.batch);
        game.batch.draw(actionbar,xBar,PixelWars.ALTO-actionbar.getHeight()+10);
        btnWarrior.setPosition(xWarr,PixelWars.ALTO-btnWarrior.getHeight()-1);
        btnMiner.setPosition(xMiner,PixelWars.ALTO-btnMiner.getHeight()-1);


        game.batch.end();
        update(delta);
        gamePort.getCamera().update();


    }

    public boolean isInside(int posx, int posy){
        if (posx > 0 && posx < bgrass.getWidth()*2 && posy >0 && posy < bgrass.getHeight()){
            return true;
        }
        else {return false;}
    }

    public void update(float deltaTime) {
        handleIsTouched(deltaTime);
    }

    public void handleIsTouched(float deltaTime){
        float aument =200;
        float pos = 0;
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX()>Gdx.graphics.getWidth()/2 && gamePort.getCamera().position.x < PixelWars.ANCHO*1.4){
                aument *= deltaTime;
                xBar += aument;
                xWarr += aument;
                xMiner += aument;
                gamePort.getCamera().position.x+=aument;
            }
            else if (Gdx.input.getX()<=Gdx.graphics.getWidth()/2 && gamePort.getCamera().position.x > 4 +PixelWars.ANCHO/2){
                aument *= deltaTime;
                xBar -= aument;
                xWarr += aument;
                xMiner += aument;
                gamePort.getCamera().position.x-=aument;
            }


        }

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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
