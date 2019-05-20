package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.itesm.pixelwars.PixelWars;
import com.itesm.pixelwars.Sprites.Animations.AnimationWarrior;
import com.itesm.pixelwars.Sprites.Animations.AnimationTower;

import org.json.JSONException;
import org.json.JSONObject;

public class MultiplayerScreen implements Screen {
    private Stage stage;
    private PixelWars game;
    private Viewport gamePort;

    //In game textures and animations
    private AnimationTower myAnimatedCastle;
    private AnimationTower enemyAnimatedCastle;
    private Texture skytext,bgrass,bmountains,bclouds;
    private Queue<AnimationWarrior> myWarriorsQ = new Queue<AnimationWarrior>();
    private Queue<AnimationWarrior> enemyWarriorsQ = new Queue<AnimationWarrior>();

    //Action Bar
    private Texture actionbar;
    private Texture warrior_button,miner_button, archer_button, monk_button, dragon_button, pause_button, continue_button, exit_button, restart_button;
    private TextureRegionDrawable trd_warrior_button,trd_miner_button, trd_archer_button, trd_monk_button, trd_dragon_button, trd_pause_button,trd_continue_button, trd_exit_button, trd_restart_button;
    private Texture warrior_buttonp,miner_buttonp, archer_buttonp, monk_buttonp, dragon_buttonp, pause_buttonp,continue_buttonp,exit_buttonp,restart_buttonp;
    private TextureRegionDrawable trd_warrior_buttonp,trd_miner_buttonp, trd_archer_buttonp, trd_monk_buttonp, trd_dragon_buttonp, trd_pause_buttonp,trd_continue_buttonp,trd_exit_buttonp,trd_restart_buttonp;
    private ImageButton btnWarrior,btnArcher,btnMiner,btnMonk,btnDragon;

    //ActionBar
    private float xBar = 0;
    private float xWarr = 15;
    private float xMiner = 40;
    private float xArcher = 65;
    private float xMonk = 90;
    private float xDragon = 115;
    private boolean isFinish = false;


    //Clouds Animation
    private final int SPEED_DIFERENCCE = 60;
    private float srcX = 0;


    //Multiplayer
    private Socket socket;
    private boolean ready = false;



    public MultiplayerScreen(PixelWars game){
        this.game = game;
        gamePort = new StretchViewport(game.ANCHO,game.ALTO,game.gamecam);
    }

    public void connectSocket(){
        try{
            //Define a donde nos estamos conectando.
            socket = IO.socket("http://04647f2f.ngrok.io");
            socket.connect();
        } catch (Exception e){
            System.out.print(e);
        }
    }

    public void configSocketEvents(){
        //This is what it does when something is recived
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                createCastle(10, bgrass.getHeight() / 4);
                Gdx.app.log("SocketIO", "Connected");
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "My ID: " + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("pair", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("pair");
                    Gdx.app.log("SocketIO", "Your pair is: " + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting pair");
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player Connected: " + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New PlayerID");
                }
            }
        });
    }


    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(game.gamecam.combined);
        srcX += SPEED_DIFERENCCE * delta;

        game.batch.begin();
        game.batch.draw(skytext, 0, 0);
        game.batch.draw(skytext, skytext.getWidth(), 0);
        game.batch.draw(bclouds, 0, bgrass.getHeight(), 0, 0, bclouds.getWidth(), bclouds.getHeight(), 1, 1, 0, (int) srcX, 0, bclouds.getWidth(), bclouds.getHeight(), false, false);
        game.batch.draw(bmountains, 0, bgrass.getHeight() - 3);
        game.batch.draw(bgrass, 0, 0);
        game.batch.draw(actionbar, xBar, PixelWars.ALTO - actionbar.getHeight());
        if (myAnimatedCastle != null){
            myAnimatedCastle.render(game.batch);

        }

        game.batch.end();

        stage.draw();
        stage.act();
        handleIsTouched(delta);
        gamePort.getCamera().update();

    }


    //Screen Movement
    public void handleIsTouched(float deltaTime){
        float aument =200.0f;
        //Buttons Movement Behavior
        if (Gdx.input.isTouched() && !isFinish) {
            if (Gdx.input.getX()>Gdx.graphics.getWidth()/2 && gamePort.getCamera().position.x < PixelWars.ANCHO*1.4 && Gdx.input.getY() > actionbar.getHeight()){
                aument *= deltaTime;
                xBar += aument;
                //label2.setPosition(label2.getX()+aument,PixelWars.ALTO-row_height*1-4);
                //label3.setPosition(label3.getX()+aument,PixelWars.ALTO-row_height*1-4);
                //label3.setText(gold);
                //label2.setText(unidades+"/20");
                btnWarrior.setPosition(xWarr+=aument,PixelWars.ALTO-btnWarrior.getHeight()-1);
                btnMiner.setPosition(xMiner+=aument,PixelWars.ALTO-btnMiner.getHeight()-1);
                btnArcher.setPosition(xArcher+=aument, PixelWars.ALTO-btnArcher.getHeight()-1);
                btnMonk.setPosition(xMonk+=aument, PixelWars.ALTO-btnMonk.getHeight()-1);
                btnDragon.setPosition(xDragon+=aument, PixelWars.ALTO-btnDragon.getHeight()-1);
                //btnPause.setPosition(xPause+=aument,PixelWars.ALTO-btnPause.getHeight()+3);
                gamePort.getCamera().position.x+=aument;
            }
            else if (Gdx.input.getX()<=Gdx.graphics.getWidth()/2 && gamePort.getCamera().position.x > 4 +PixelWars.ANCHO/2 && Gdx.input.getY() > actionbar.getHeight()){
                aument *= deltaTime;
                xBar -= aument;
                //label2.setPosition(label2.getX()-aument,PixelWars.ALTO-row_height*1-4);
                //label3.setPosition(label3.getX()-aument,PixelWars.ALTO-row_height*1-4);
                //label2.setText(unidades+"/20");
                btnWarrior.setPosition(xWarr-=aument,PixelWars.ALTO-btnWarrior.getHeight()-1);
                btnMiner.setPosition(xMiner-=aument,PixelWars.ALTO-btnMiner.getHeight()-1);
                btnArcher.setPosition(xArcher-=aument, PixelWars.ALTO-btnArcher.getHeight()-1);
                btnMonk.setPosition(xMonk-=aument, PixelWars.ALTO-btnMonk.getHeight()-1);
                btnDragon.setPosition(xDragon-=aument, PixelWars.ALTO-btnDragon.getHeight()-1);
                gamePort.getCamera().position.x-=aument;
            }
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
    public void show() {
        connectSocket();
        configSocketEvents();

        stage = new Stage(gamePort,game.batch);
        createBackground();
        createButtons(stage);


        //Towers
        createCastle(10, bgrass.getHeight() / 4);
        createEnemyCastle(PixelWars.ANCHO*1.66F,bgrass.getHeight()/4);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);


    }

    public void createBackground(){
        //Background
        bclouds = new Texture("bClouds.png");
        skytext = new Texture("bSky1.png");
        bgrass = new Texture("bGrass.png");
        bmountains = new Texture("bMtns.png");
        bclouds.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        actionbar = new Texture("topBar.png");
    }

    public void createButtons(Stage stage){
        //Units Buttons
        warrior_button = new Texture("btnSword.png");
        warrior_buttonp = new Texture("btnSword_Pressed.png");

        miner_button = new Texture("btnDinero.png");
        miner_buttonp = new Texture("btnDinero_Pressed.png");

        archer_button = new Texture("btnBow.png");
        archer_buttonp = new Texture("BtnBow_Pressed.png");

        monk_button = new Texture("btnMage.png");
        monk_buttonp = new Texture("btnMage_Pressed.png");

        dragon_button = new Texture("btnDragon.png");
        dragon_buttonp = new Texture("btnDragon_Pressed.png");

        //Units Regions
        trd_warrior_button = new TextureRegionDrawable(warrior_button);
        trd_warrior_buttonp = new TextureRegionDrawable(warrior_buttonp);

        trd_miner_button = new TextureRegionDrawable(miner_button);
        trd_miner_buttonp = new TextureRegionDrawable(miner_buttonp);

        trd_archer_button = new TextureRegionDrawable(archer_button);
        trd_archer_buttonp = new TextureRegionDrawable(archer_buttonp);

        trd_monk_button = new TextureRegionDrawable(monk_button);
        trd_monk_buttonp = new TextureRegionDrawable(monk_buttonp);

        trd_dragon_button = new TextureRegionDrawable(dragon_button);
        trd_dragon_buttonp = new TextureRegionDrawable(dragon_buttonp);

        //ImageButtons Units
        btnWarrior = new ImageButton(trd_warrior_button,trd_warrior_buttonp);
        btnMiner = new ImageButton(trd_miner_button,trd_miner_buttonp);
        btnArcher = new ImageButton(trd_archer_button,trd_archer_buttonp);
        btnMonk = new ImageButton(trd_monk_button,trd_monk_buttonp);
        btnDragon = new ImageButton(trd_dragon_button, trd_dragon_buttonp);


        //Stage Add Units buttons
        stage.addActor(btnWarrior);
        stage.addActor(btnMiner);
        stage.addActor(btnArcher);
        stage.addActor(btnMonk);
        stage.addActor(btnDragon);

        btnWarrior.setPosition(xWarr,PixelWars.ALTO-btnWarrior.getHeight()-1);
        btnMiner.setPosition(xMiner,PixelWars.ALTO-btnMiner.getHeight()-1);
        btnArcher.setPosition(xArcher, PixelWars.ALTO-btnArcher.getHeight()-1);
        btnMonk.setPosition(xMonk, PixelWars.ALTO-btnMonk.getHeight()-1);
        btnDragon.setPosition(xDragon, PixelWars.ALTO-btnDragon.getHeight()-1);


    }

    private void createCastle(float x, float y) {
        Texture texturaCastilloAnimado = new Texture("torreAzul.png");
        Texture texturaCastilloAnimadoDaño1 = new Texture("torreAzulDaño1.png");
        Texture texturaCastilloAnimadoDaño2 = new Texture("torreAzulDaño2.png");
        Texture texturaCastilloAnimadoDaño3 = new Texture("torreAzulDaño3.png");
        myAnimatedCastle = new AnimationTower(x,y,texturaCastilloAnimado, texturaCastilloAnimadoDaño1, texturaCastilloAnimadoDaño2, texturaCastilloAnimadoDaño3);
    }

    private void createEnemyCastle(float x, float y) {
        Texture texturaCastilloAnimado = new Texture("torreRoja.png");
        Texture texturaCastilloAnimadoDaño1 = new Texture("torreRojaDaño1.png");
        Texture texturaCastilloAnimadoDaño2 = new Texture("torreRojaDaño2.png");
        Texture texturaCastilloAnimadoDaño3 = new Texture("torreRojaDaño3.png");
        enemyAnimatedCastle = new AnimationTower(x,y,texturaCastilloAnimado, texturaCastilloAnimadoDaño1, texturaCastilloAnimadoDaño2, texturaCastilloAnimadoDaño3);

    }

    @Override
    public void dispose() {
        stage.dispose();
        skytext.dispose();
        bgrass.dispose();
        bmountains.dispose();
        bclouds.dispose();
    }
}
