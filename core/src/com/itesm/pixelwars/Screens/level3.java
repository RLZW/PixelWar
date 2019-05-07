package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.pixelwars.PixelWars;
import com.itesm.pixelwars.Sprites.Actors.CastleActor;
import com.itesm.pixelwars.Sprites.Animations.AnimacionGuerrero;
import com.itesm.pixelwars.Sprites.Animations.Arquero;
import com.itesm.pixelwars.Sprites.Animations.Cura;
import com.itesm.pixelwars.Sprites.Animations.EstadoGuerrero;
import com.itesm.pixelwars.Sprites.Animations.Guerrero;
import com.itesm.pixelwars.Sprites.Animations.TowerAnimation;
import com.itesm.pixelwars.Sprites.Animations.minero;


public class level3 implements Screen {
    private Stage stage;
    private PixelWars game;
    private Viewport gamePort;
    private CastleActor myCastle;
    private CastleActor enemyCastle;
    private TowerAnimation animatedCastle;
    private TowerAnimation animatedEnemyCastle;
    private Queue<AnimacionGuerrero> myWarriorsQ = new Queue<AnimacionGuerrero>();
    private Queue<AnimacionGuerrero> enemyWarriorsQ = new Queue<AnimacionGuerrero>();
    private float row_height;
    private float timer = 0f;
    private float seconds = 5f;
    private boolean isFinish = false;



    //Clouds Animation
    private final int SPEED_DIFERENCCE = 60;
    private float srcX = 0;

    //ActionBar
    private float xBar = 0;
    private float xWarr = 15;
    private float xMiner = 40;
    private float xArcher = 65;
    private float xMonk = 90;
    private float xDragon = 115;
    private float xPause = 300;



    private BitmapFont bitmapFont,sbitmapFont;
    private Label.LabelStyle labelStyle,slabelStyle,mlabelStyle;
    private Label label1,label2,label3,label4,label5,label6,label7,label8,label9;



    private Texture actionbar;
    private Texture warrior_button,miner_button, archer_button, monk_button, dragon_button, pause_button, continue_button, exit_button, restart_button;
    private TextureRegionDrawable trd_warrior_button,trd_miner_button, trd_archer_button, trd_monk_button, trd_dragon_button, trd_pause_button,trd_continue_button, trd_exit_button, trd_restart_button;
    private Texture warrior_buttonp,miner_buttonp, archer_buttonp, monk_buttonp, dragon_buttonp, pause_buttonp,continue_buttonp,exit_buttonp,restart_buttonp;
    private TextureRegionDrawable trd_warrior_buttonp,trd_miner_buttonp, trd_archer_buttonp, trd_monk_buttonp, trd_dragon_buttonp, trd_pause_buttonp,trd_continue_buttonp,trd_exit_buttonp,trd_restart_buttonp;

    private ImageButton btnWarrior;
    private ImageButton btnMiner;
    private ImageButton btnArcher;
    private ImageButton btnMonk;
    private ImageButton btnDragon;

    private ImageButton btnPause;
    private ImageButton btnContinue;
    private ImageButton btnExit;
    private ImageButton btnRestart;

    private float timeToMove = 0;


    //Textures & TRDA

    private Texture textCastle, textEnemyCastle;
    private Texture skytext,bgrass,bmountains,bclouds;
    private Texture pause_menu;
    //Stadistics
    private int unidades;
    private float timerToMine = 0;
    private float timeToMine = 0.5F;
    private int gold = 600;


    //Pause
    private boolean isPaused = false;

    public level3(PixelWars game){
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


        //Background
        bclouds = new Texture("bClouds.png");
        skytext = new Texture("bSky1.png");
        bgrass = new Texture("bGrass.png");
        bmountains = new Texture("bMtns.png");
        bclouds.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

        //Pause
        pause_menu = new Texture("pause_bg.png");

        //Action Bar
        actionbar = new Texture("topBar.png");

        //Units Buttons
        warrior_button = new Texture("btnSword.png");
        warrior_buttonp = new Texture("btnSword_Pressed.png");

        miner_button = new Texture("btnDinero.png");
        miner_buttonp = new Texture("btnDinero_Pressed.png");

        archer_button = new Texture("btnBow.png");
        archer_buttonp = new Texture("BtnBow_Pressed.png");

        //Faltan
        monk_button = new Texture("btnMage.png");
        monk_buttonp = new Texture("btnMage_Pressed.png");

        dragon_button = new Texture("btnDragon.png");
        dragon_buttonp = new Texture("btnDragon_Pressed.png");

        //Elements Buttons
        pause_button = new Texture("btnPause.png");
        pause_buttonp = new Texture("btnPause_Pressed.png");
        restart_button = new Texture("pauseRestart.png");
        restart_buttonp = new Texture("pauseRestart_Pressed.png");
        exit_button = new Texture("pauseExit.png");
        exit_buttonp = new Texture("pauseExit_Pressed.png");
        continue_button = new Texture("pauseContinue.png");
        continue_buttonp = new Texture("pauseContinue_Pressed.png");




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

        //Elements Regions
        trd_pause_button = new TextureRegionDrawable(pause_button);
        trd_pause_buttonp = new TextureRegionDrawable(pause_buttonp);

        trd_restart_button = new TextureRegionDrawable(restart_button);
        trd_restart_buttonp = new TextureRegionDrawable(restart_buttonp);

        trd_exit_button = new TextureRegionDrawable(exit_button);
        trd_exit_buttonp = new TextureRegionDrawable(exit_buttonp);

        trd_continue_button = new TextureRegionDrawable(continue_button);
        trd_continue_buttonp = new TextureRegionDrawable(continue_buttonp);


        //ImageButtons Units
        btnWarrior = new ImageButton(trd_warrior_button,trd_warrior_buttonp);
        btnMiner = new ImageButton(trd_miner_button,trd_miner_buttonp);
        btnArcher = new ImageButton(trd_archer_button,trd_archer_buttonp);
        btnMonk = new ImageButton(trd_monk_button,trd_monk_buttonp);
        btnDragon = new ImageButton(trd_dragon_button, trd_dragon_buttonp);

        //ImageButtons Elements
        btnPause = new ImageButton(trd_pause_button,trd_pause_buttonp);
        btnContinue = new ImageButton(trd_continue_button, trd_continue_buttonp);
        btnRestart = new ImageButton(trd_restart_button, trd_restart_buttonp);
        btnExit = new ImageButton(trd_exit_button, trd_exit_buttonp);

        //Stage Add Units buttons
        stage.addActor(btnWarrior);
        stage.addActor(btnMiner);
        stage.addActor(btnArcher);
        //stage.addActor(btnMonk);
        //stage.addActor(btnDragon);

        //Stage Add Elements buttons
        stage.addActor(btnPause);


        //Units Buttons Position
        btnWarrior.setPosition(xWarr,PixelWars.ALTO-btnWarrior.getHeight()-1);
        btnMiner.setPosition(xMiner,PixelWars.ALTO-btnMiner.getHeight()-1);
        btnArcher.setPosition(xArcher, PixelWars.ALTO-btnArcher.getHeight()-1);
        btnMonk.setPosition(xMonk, PixelWars.ALTO-btnMonk.getHeight()-1);
        btnDragon.setPosition(xDragon, PixelWars.ALTO-btnDragon.getHeight()-1);

        //Units Buttons Position
        btnPause.setPosition(xPause,PixelWars.ALTO-btnPause.getHeight()+3);

        btnMiner.addListener(new ClickListener() {
                                 @Override
                                 public void clicked(InputEvent event, float x, float y) {
                                     if (gold>= 50 && unidades<20){
                                         minero miner = new minero(myCastle.getX()+myCastle.getWidth(), animatedCastle.getY(), new Texture("guerreroAzulCaminando.png"), new Texture("guerreroAzulParado.png"), new Texture("guerreroAzulAtacando.png"), 29, 44,29, 44, 59, 42, 25, 10, true, 'g');
                                         myWarriorsQ.addLast(miner);
                                         unidades+=1;
                                         gold-=50;
                                         label3.setText(gold);
                                         label2.setText(unidades+"/20");
                                     }
                                 }
                             }
        );

        btnWarrior.addListener(new ClickListener() {
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       if (gold>=100 && unidades < 20){
                                           Guerrero warrior = new Guerrero(myCastle.getX()+myCastle.getWidth(), animatedCastle.getY(), new Texture("guerreroAzulCaminando.png"), new Texture("guerreroAzulParado.png"), new Texture("guerreroAzulAtacando.png"), 29, 44,29, 44, 59, 42, 100, 20, true, 'g');
                                           myWarriorsQ.addLast(warrior);
                                           unidades +=1;
                                           gold-=100;
                                           label3.setText(gold);
                                           label2.setText(unidades+"/20");

                                       }

                                   }
                               }
        );

        btnArcher.addListener(new ClickListener() {
                                  @Override
                                  public void clicked(InputEvent event, float x, float y) {
                                      if (gold>=100 && unidades < 20){
                                          Arquero warrior = new Arquero(myCastle.getX()+myCastle.getWidth(), animatedCastle.getY(), new Texture("guerreroAzulCaminando.png"), new Texture("arqueroAzulParado.png"), new Texture("guerreroAzulAtacando.png"), 29, 44, 36,37,59, 42, 100, 10, true, 'a');
                                          myWarriorsQ.addLast(warrior);
                                          unidades +=1;
                                          gold-=100;
                                          label3.setText(gold);
                                          label2.setText(unidades+"/20");

                                      }

                                  }
                              }
        );

        btnMonk.addListener(new ClickListener(){
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    if (gold>=500 && unidades <20){
                                        Cura monk = new Cura(myCastle.getX()+myCastle.getWidth(), animatedCastle.getY(), new Texture("guerreroAzulCaminando.png"), new Texture("arqueroAzulParado.png"), new Texture("guerreroAzulAtacando.png"), 29, 44, 36,37,59, 42, 50, 20, true, 'm');
                                        myWarriorsQ.addLast(monk);
                                        unidades +=1;
                                        gold-=500;
                                        label3.setText(gold);
                                        label2.setText(unidades+"/20");
                                    }
                                }
                            }
        );

        btnDragon.addListener(new ClickListener() {
                                  @Override
                                  public void clicked(InputEvent event, float x, float y) {
                                      if (gold>=1500 && unidades < 20){
                                          Guerrero warrior = new Guerrero(myCastle.getX()+myCastle.getWidth(), animatedCastle.getY(), new Texture("guerreroAzulCaminando.png"), new Texture("guerreroAzulParado.png"), new Texture("guerreroAzulAtacando.png"), 29, 44,29, 44, 59, 42, 150, 30 ,true, 'd');
                                          myWarriorsQ.addLast(warrior);
                                          unidades +=1;
                                          gold-=1500;
                                          label3.setText(gold);
                                          label2.setText(unidades+"/20");

                                      }

                                  }
                              }
        );
        btnPause.addListener(new ClickListener() {
                                 @Override
                                 public void clicked(InputEvent event, float x, float y) {
                                     Gdx.gl.glClearColor(1, 1, 1, 1);
                                     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                                     if(!isPaused){
                                         isPaused = true;
                                         btnPause.remove();
                                         btnWarrior.remove();
                                         btnArcher.remove();
                                         btnDragon.remove();
                                         btnMiner.remove();
                                         btnMonk.remove();
                                         label1.remove();
                                         label2.remove();
                                         label3.remove();

                                     }

                                 }
                             }
        );

        slabelStyle = new Label.LabelStyle();
        mlabelStyle = new Label.LabelStyle();
        sbitmapFont = new BitmapFont(Gdx.files.internal("spixel.fnt"));
        slabelStyle.font = sbitmapFont;
        mlabelStyle.font = sbitmapFont;
        slabelStyle.fontColor = Color.WHITE;
        mlabelStyle.fontColor = Color.YELLOW;

        row_height = PixelWars.ALTO/16;


        label1 = new Label("Level3",slabelStyle);
        label1.setSize(PixelWars.ANCHO/2-label1.getWidth(),row_height);
        label1.setPosition(bgrass.getWidth()/2-label1.getWidth()/2-20,PixelWars.ALTO-row_height*3);
        label1.setAlignment(Align.center);
        stage.addActor(label1);


        label2 = new Label(String.valueOf(unidades)+"/20",slabelStyle);
        label2.setSize(PixelWars.ANCHO/2-label2.getWidth(),row_height);
        label2.setPosition(bgrass.getWidth()/2-label2.getWidth()/2-40,PixelWars.ALTO-row_height*1-4);
        label2.setAlignment(Align.center);
        stage.addActor(label2);

        label3 = new Label(String.valueOf(gold),mlabelStyle);
        label3.setSize(PixelWars.ANCHO/2-label3.getWidth(),row_height);
        label3.setPosition(bgrass.getWidth()/2-label3.getWidth()-10,PixelWars.ALTO-row_height*1-4);
        label3.setAlignment(Align.center);
        stage.addActor(label3);


        //Towers
        createCastle(10,bgrass.getHeight()/4);
        createEnemyCastle(PixelWars.ANCHO*1.66F,bgrass.getHeight()/4);




        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);


    }





    @Override
    public void render(float delta) {

        if(!isPaused) {

            timer += delta;
            if (timer >= seconds) {
                timer = 0;
                Guerrero warrior = new Guerrero(PixelWars.ANCHO * 1.66F, enemyCastle.getY(), new Texture("guerreroRojoCaminando.png"), new Texture("guerreroRojoParado.png"), new Texture("guerreroRojoAtacando.png"), 29, 44, 29, 44, 59, 42, 100, 10, false, 'g');

                enemyWarriorsQ.addLast(warrior);
            }


            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.batch.setProjectionMatrix(game.gamecam.combined);
            srcX += SPEED_DIFERENCCE * delta;

            game.batch.begin();
            game.batch.draw(skytext, 0, 0);
            game.batch.draw(skytext, skytext.getWidth(), 0);
            game.batch.draw(bclouds, 0, bgrass.getHeight(), 0, 0, bclouds.getWidth(), bclouds.getHeight(), 1, 1, 0, (int) srcX, 0, bclouds.getWidth(), bclouds.getHeight(), false, false);
            game.batch.draw(bmountains, 0, bgrass.getHeight() - 3);
            game.batch.draw(bgrass, 0, 0);
            if (myCastle.isAlive()) {
                animatedCastle.render(game.batch);
                myCastle.remove();

            }
            if (enemyCastle.isAlive()) {
                animatedEnemyCastle.render(game.batch);
                enemyCastle.remove();
            }
            game.batch.draw(actionbar, xBar, PixelWars.ALTO - actionbar.getHeight());

            if (!myWarriorsQ.isEmpty()) {
                for (AnimacionGuerrero warrior : myWarriorsQ) {
                    warrior.render(game.batch);
                }
                ColisionConEnemigo(myWarriorsQ.first());
                formarUnidades();

            }
            if (!enemyWarriorsQ.isEmpty()) {
                for (AnimacionGuerrero enemy : enemyWarriorsQ) {
                    enemy.render(game.batch);
                }
                ColisionAliado(enemyWarriorsQ.first());
                formarEnemigos();
            }

            if (!myCastle.isAlive()) {
                labelStyle = new Label.LabelStyle();
                bitmapFont = new BitmapFont(Gdx.files.internal("pixel.fnt"));
                Texture youlose = new Texture("youLOSE.png");
                game.batch.draw(youlose,(gamePort.getCamera().position.x)-youlose.getWidth()/2,(PixelWars.ALTO / 2)-youlose.getHeight()/2);
                isFinish = true;
                enemyWarriorsQ.clear();
                myWarriorsQ.clear();
                stage.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          super.clicked(event, x, y);
                                          game.setScreen(new MenuScreen(game));
                                      }
                                  }
                );
            }

            if (!enemyCastle.isAlive()) {
                labelStyle = new Label.LabelStyle();
                bitmapFont = new BitmapFont(Gdx.files.internal("pixel.fnt"));
                labelStyle.font = bitmapFont;
                labelStyle.fontColor = Color.GREEN;
                isFinish = true;
                Texture youwin = new Texture("youWIN.png");
                game.batch.draw(youwin,(gamePort.getCamera().position.x)-youwin.getWidth()/2,(PixelWars.ALTO / 2)-youwin.getHeight()/2);
                enemyWarriorsQ.clear();
                myWarriorsQ.clear();
                stage.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          super.clicked(event, x, y);
                                          game.setScreen(new level4(game));
                                      }
                                  }
                );
            }


            game.batch.end();
            stage.draw();
            stage.act();

            update(delta);
            gamePort.getCamera().update();

        }
        else{

            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            game.batch.begin();
            game.batch.draw(pause_menu,gamePort.getCamera().position.x-pause_menu.getWidth()/2,0);
            stage.addActor(btnContinue);
            stage.addActor(btnRestart);
            stage.addActor(btnExit);

            btnContinue.setPosition(gamePort.getCamera().position.x-btnContinue.getWidth()/2,PixelWars.ALTO/2+btnContinue.getHeight()/2);
            btnRestart.setPosition(gamePort.getCamera().position.x-btnRestart.getWidth()/2,PixelWars.ALTO/2-btnContinue.getHeight()/2);
            btnExit.setPosition(gamePort.getCamera().position.x-btnExit.getWidth()/2,PixelWars.ALTO/2-btnContinue.getHeight()*1.5f);
            btnContinue.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            Gdx.gl.glClearColor(1, 1, 1, 1);
                                            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                                            if(isPaused){
                                                btnContinue.remove();
                                                btnExit.remove();
                                                btnRestart.remove();
                                                stage.addActor(btnPause);
                                                stage.addActor(btnWarrior);
                                                stage.addActor(btnArcher);
                                                //stage.addActor(btnDragon);
                                                stage.addActor(btnMiner);
                                                //stage.addActor(btnMonk);
                                                stage.addActor(label1);
                                                stage.addActor(label2);
                                                stage.addActor(label3);

                                                isPaused = false;
                                                System.out.println(isPaused);
                                            }

                                        }
                                    }
            );
            btnRestart.addListener(new ClickListener() {
                                       @Override
                                       public void clicked(InputEvent event, float x, float y) {
                                           Gdx.gl.glClearColor(1, 1, 1, 1);
                                           Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                                           game.setScreen(new GameScreen(game));
                                       }

                                   }

            );

            btnExit.addListener(new ClickListener() {
                                    @Override
                                    public void clicked(InputEvent event, float x, float y) {
                                        Gdx.gl.glClearColor(1, 1, 1, 1);
                                        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                                        game.setScreen(new MenuScreen(game));
                                    }
                                }
            );
            game.batch.end();
            stage.draw();
            stage.act();

        }
    }

    private void ArquerosDisparando(AnimacionGuerrero warrior){
        if (myWarriorsQ.indexOf(warrior, false)-myWarriorsQ.indexOf(myWarriorsQ.first(),false)<=3 && myWarriorsQ.first().getEstado() == EstadoGuerrero.ATACANDO && !enemyWarriorsQ.isEmpty()){
            warrior.setEstado(EstadoGuerrero.ATACANDO);
            Arquero archer = (Arquero) warrior;
            enemyWarriorsQ.first().setHp(archer.Flechazo(enemyWarriorsQ.first().getHp()));
        }else if (myWarriorsQ.indexOf(warrior, false)-myWarriorsQ.indexOf(myWarriorsQ.first(),false)<=3 && myWarriorsQ.first().getEstado() == EstadoGuerrero.ATACANDO && enemyWarriorsQ.isEmpty()){
            warrior.setEstado(EstadoGuerrero.ATACANDO);
            Arquero archer = (Arquero) warrior;
            enemyCastle.setHp(archer.Flechazo(enemyCastle.getHp()));
        }else{
            warrior.setEstado(EstadoGuerrero.QUIETO);
        }
    }

    private void ColisionCastilloAliado(TowerAnimation castle) {
        if (enemyWarriorsQ.first().getSprite().getBoundingRectangle().overlaps(castle.getSprite().getBoundingRectangle())){
            enemyWarriorsQ.first().setEstado(EstadoGuerrero.ATACANDO);
            Guerrero warrior = (Guerrero) enemyWarriorsQ.first();
            myCastle.setHp(warrior.Espadazo(myCastle.getHp()));
            isCastleAlive(myCastle);
        }else{
            enemyWarriorsQ.first().setEstado(EstadoGuerrero.CAMINANDO);
            enemyWarriorsQ.first().moverX(-1);
        }
    }
    private void ColisionConEnemigo(AnimacionGuerrero first) {
        if (!enemyWarriorsQ.isEmpty()){
            if (first.getSprite().getBoundingRectangle().overlaps(enemyWarriorsQ.first().getSprite().getBoundingRectangle())){
                if (first.getClass() == Guerrero.class) {
                    Guerrero warrior = (Guerrero) first;
                    first.setEstado(EstadoGuerrero.ATACANDO);
                    enemyWarriorsQ.first().setHp(warrior.Espadazo(enemyWarriorsQ.first().getHp()));
                    comprobarVivoEnemigo();
                }else if (first.getClass() == Arquero.class){
                    Arquero warrior = (Arquero) first;
                    first.setEstado(EstadoGuerrero.ATACANDO);
                    enemyWarriorsQ.first().setHp(warrior.Flechazo(enemyWarriorsQ.first().getHp()));
                    comprobarVivoEnemigo();
                }else if (first.getClass() == minero.class){
                    minero miner = (minero) first;
                    first.setEstado(EstadoGuerrero.ATACANDO);
                    timerToMine += Gdx.graphics.getDeltaTime();
                    if (timerToMine>= timeToMine){
                        gold += 10;
                        label3.setText(gold);
                        timerToMine = 0;
                    }
                    enemyWarriorsQ.first().setHp(miner.picar(enemyWarriorsQ.first().getHp()));
                }
            }else {
                first.setEstado(EstadoGuerrero.CAMINANDO);
                first.moverX(1);
            }


        }else{
            ColisionCatilloEnemigo(animatedEnemyCastle);

        }
    }

    private void comprobarVivoEnemigo() {
        if(enemyWarriorsQ.first().getHp()<=0){
            enemyWarriorsQ.removeFirst();
            gold+= 200;
            label3.setText(gold);

        }
    }

    private void ColisionAliado(AnimacionGuerrero first) {
        if (!myWarriorsQ.isEmpty()){
            if (first.getSprite().getBoundingRectangle().overlaps(myWarriorsQ.first().getSprite().getBoundingRectangle())){
                first.setEstado(EstadoGuerrero.ATACANDO);
                Guerrero warrior = (Guerrero) first;
                myWarriorsQ.first().setHp(warrior.Espadazo(myWarriorsQ.first().getHp()));
                comprobarVivoAliado();
            }else {
                first.setEstado(EstadoGuerrero.CAMINANDO);
                first.moverX(-1);
            }
        }else{
            ColisionCastilloAliado(animatedCastle);
        }

    }

    private void comprobarVivoAliado() {
        if (myWarriorsQ.first().getHp()<= 0){
            unidades -= 1;
            label2.setText(unidades + "/20");
            myWarriorsQ.removeFirst();
        }


    }

    private void formarEnemigos() {
        for (int i = 0; i < enemyWarriorsQ.size; i++){
            AnimacionGuerrero enemy = enemyWarriorsQ.get(i);
            if (!enemy.equals(enemyWarriorsQ.first())){
                if (enemy.getSprite().getBoundingRectangle().overlaps(enemyWarriorsQ.get(i-1).getSprite().getBoundingRectangle())){
                    enemy.setEstado(EstadoGuerrero.QUIETO);
                }else{
                    enemy.setEstado(EstadoGuerrero.CAMINANDO);
                    enemy.moverX(-1);
                }
            }
        }
    }

    private void ColisionCatilloEnemigo(TowerAnimation castle) {
        if (myWarriorsQ.first().getSprite().getBoundingRectangle().overlaps(castle.getSprite().getBoundingRectangle())){
            myWarriorsQ.first().setEstado(EstadoGuerrero.ATACANDO);
            Guerrero warrior = (Guerrero) myWarriorsQ.first();
            enemyCastle.setHp(warrior.Espadazo(enemyCastle.getHp()));
            isCastleAlive(enemyCastle);
        }else{
            myWarriorsQ.first().setEstado(EstadoGuerrero.CAMINANDO);
            myWarriorsQ.first().moverX(1);
        }
    }



    private void isCastleAlive(CastleActor Castle) {
        if (Castle.getHp()<= 0) {
            Castle.setAlive(false);
        }
    }

    private void formarUnidades() {
        for (int i = 0; i < myWarriorsQ.size; i++){
            AnimacionGuerrero warrior = myWarriorsQ.get(i);
            if (!warrior.equals(myWarriorsQ.first())){
                if (warrior.getSprite().getBoundingRectangle().overlaps(myWarriorsQ.get(i-1).getSprite().getBoundingRectangle())){
                    if (warrior.getClass()==Arquero.class)
                        ArquerosDisparando(warrior);
                    else if(warrior.getClass() == Cura.class)
                        Curacion(warrior);
                    else {
                        warrior.setEstado(EstadoGuerrero.QUIETO);
                    }
                }else{
                    warrior.setEstado(EstadoGuerrero.CAMINANDO);
                    warrior.moverX(1);
                }
            }
        }
    }

    private void Curacion(AnimacionGuerrero warrior) {
        if (myWarriorsQ.indexOf(warrior, false)-myWarriorsQ.indexOf(myWarriorsQ.first(),false)<=1 && myWarriorsQ.first().getEstado() == EstadoGuerrero.ATACANDO && !enemyWarriorsQ.isEmpty()){
            warrior.setEstado(EstadoGuerrero.ATACANDO);
            Cura Monje = (Cura) warrior;
            enemyWarriorsQ.first().setHp(Monje.Curacion(myWarriorsQ.first().getHp()));
        }else{
            warrior.setEstado(EstadoGuerrero.QUIETO);
        }
    }


    public boolean isInside(int posx, int posy){
        if (posx > 0 && posx < bgrass.getWidth()*2 && posy >0 && posy < bgrass.getHeight()){
            return true;
        }
        else {return false;}
    }

    public void update(float deltaTime) {
        if(!isPaused){
            handleIsTouched(deltaTime);
        }
    }


    //Screen Movement
    public void handleIsTouched(float deltaTime){
        float aument =200;
        //Buttons Movement Behavior
        if (Gdx.input.isTouched() && !isFinish) {
            if (Gdx.input.getX()>Gdx.graphics.getWidth()/2 && gamePort.getCamera().position.x < PixelWars.ANCHO*1.4&&Gdx.input.getY()>50){
                aument *= deltaTime;
                xBar += aument;
                label2.setPosition(label2.getX()+aument,PixelWars.ALTO-row_height*1-4);
                label3.setPosition(label3.getX()+aument,PixelWars.ALTO-row_height*1-4);
                label3.setText(gold);
                label2.setText(unidades+"/20");
                btnWarrior.setPosition(xWarr+=aument,PixelWars.ALTO-btnWarrior.getHeight()-1);
                btnMiner.setPosition(xMiner+=aument,PixelWars.ALTO-btnMiner.getHeight()-1);
                btnArcher.setPosition(xArcher+=aument, PixelWars.ALTO-btnArcher.getHeight()-1);
                btnMonk.setPosition(xMonk+=aument, PixelWars.ALTO-btnMonk.getHeight()-1);
                btnDragon.setPosition(xDragon+=aument, PixelWars.ALTO-btnDragon.getHeight()-1);
                btnPause.setPosition(xPause+=aument,PixelWars.ALTO-btnPause.getHeight()+3);
                gamePort.getCamera().position.x+=aument;
            }
            else if (Gdx.input.getX()<=Gdx.graphics.getWidth()/2 && gamePort.getCamera().position.x > 4 +PixelWars.ANCHO/2&&Gdx.input.getY()>50){
                aument *= deltaTime;
                xBar -= aument;
                label2.setPosition(label2.getX()-aument,PixelWars.ALTO-row_height*1-4);
                label3.setPosition(label3.getX()-aument,PixelWars.ALTO-row_height*1-4);
                label3.setText(gold);
                label2.setText(unidades+"/20");
                btnWarrior.setPosition(xWarr-=aument,PixelWars.ALTO-btnWarrior.getHeight()-1);
                btnMiner.setPosition(xMiner-=aument,PixelWars.ALTO-btnMiner.getHeight()-1);
                btnArcher.setPosition(xArcher-=aument, PixelWars.ALTO-btnArcher.getHeight()-1);
                btnMonk.setPosition(xMonk-=aument, PixelWars.ALTO-btnMonk.getHeight()-1);
                btnDragon.setPosition(xDragon-=aument, PixelWars.ALTO-btnDragon.getHeight()-1);
                btnPause.setPosition(xPause-=aument,PixelWars.ALTO-btnPause.getHeight()+3);
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
        stage.dispose();
        textEnemyCastle.dispose();
        textCastle.dispose();
        skytext.dispose();
        bgrass.dispose();
        bmountains.dispose();
        bclouds.dispose();

    }
    /**

     private class ProcesadorEntrada implements InputProcessor{
     int unidades = 0;

     @Override
     public boolean keyDown(int keycode) {
     if (keycode == Input.Keys.SPACE){
     AnimacionGuerrero warrior = new AnimacionGuerrero(myCastle.getX()+myCastle.getWidth(), animatedCastle.getY(), "aliado");
     arrWarriors.add(warrior);
     unidades += 1;
     }
     else if (keycode == Input.Keys.E){
     AnimacionGuerrero warrior = new AnimacionGuerrero(PixelWars.ANCHO*1.66F, enemyCastle.getY(), "enemigo");
     arrEnemyWarriors.add(warrior);
     }
     return true;
     }

     @Override
     public boolean keyUp(int keycode) {
     return false;
     }

     @Override
     public boolean keyTyped(char character) {
     return false;
     }

     @Override
     public boolean touchDown(int screenX, int screenY, int pointer, int button) {
     return false;
     }

     @Override
     public boolean touchUp(int screenX, int screenY, int pointer, int button) {
     return false;
     }

     @Override
     public boolean touchDragged(int screenX, int screenY, int pointer) {
     return false;
     }

     @Override
     public boolean mouseMoved(int screenX, int screenY) {
     return false;
     }

     @Override
     public boolean scrolled(int amount) {
     return false;
     }
     }
     **/

}