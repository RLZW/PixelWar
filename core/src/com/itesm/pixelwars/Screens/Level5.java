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
import com.itesm.pixelwars.Sprites.Animations.AnimationWarrior;
import com.itesm.pixelwars.Sprites.Animations.AnimationTower;
import com.itesm.pixelwars.Sprites.Animations.Archer;
import com.itesm.pixelwars.Sprites.Animations.Priest;
import com.itesm.pixelwars.Sprites.Animations.StateWarrior;
import com.itesm.pixelwars.Sprites.Animations.EStateTower;
import com.itesm.pixelwars.Sprites.Animations.UWarrior;
import com.itesm.pixelwars.Sprites.Animations.UMiner;


public class Level5 implements Screen {
    private final LevelInfo levelInfo;
    private final highscore highscore;
    private Stage stage;
    private PixelWars game;
    private Viewport gamePort;
    private AnimationTower myAnimatedCastle;
    private AnimationTower enemyAnimatedCastle;
    private Queue<AnimationWarrior> myWarriorsQ = new Queue<AnimationWarrior>();
    private Queue<AnimationWarrior> enemyWarriorsQ = new Queue<AnimationWarrior>();
    private float row_height;
    private float timer = 0f;
    private float seconds = 3f;
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

    private int warriors = 0;
    private int archers = 0;
    private int miners = 0;
    private int monks = 0;
    private int dragons = 0;

    private float dragonTime = 0;
    private float dragonSeconds = 25f;
    private int number_of_dragons = 1;


    //Textures & TRDA

    private Texture skytext,bgrass,bmountains,bclouds;
    private Texture pause_menu;
    //Stadistics
    private int unidades;
    private float timerToMine = 0;
    private float timeToMine = 0.5F;
    private int gold = 200;


    //Pause
    private boolean isPaused = false;
    private Texture youlose;
    private Texture youwin;

    public Level5(PixelWars game, LevelInfo levelInfo, highscore highscore){
        this.game = game;
        gamePort = new StretchViewport(game.ANCHO,game.ALTO,game.gamecam);
        this.levelInfo=levelInfo;
        this.highscore=highscore;
    }



    //Create a castle
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
    public void show() {
        stage = new Stage(gamePort,game.batch);


        //Background
        bclouds = new Texture("bClouds.png");
        skytext = new Texture("bSky4.png");
        bgrass = new Texture("sGrass4.png");
        bmountains = new Texture("sMtns4.png");
        bclouds.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

        //Pause
        pause_menu = new Texture("pause_bg.png");

        //Action Bar
        actionbar = new Texture("topBar.png");

        //Units Buttons
        warrior_button = new Texture("btnSword.png");
        warrior_buttonp = new Texture("btnSword_Pressed.png");

        miner_button = new Texture("btnPick.png");
        miner_buttonp = new Texture("btnPick_Pressed.png");

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


        //Win & Lose
        youwin = new Texture("youWIN.png");
        youlose = new Texture("youLOSE.png");


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
        stage.addActor(btnMonk);
        stage.addActor(btnDragon);

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
                                         UMiner miner = new UMiner(myAnimatedCastle.getX()+myAnimatedCastle.getWidth(), myAnimatedCastle.getY(), new Texture("mineroAzulCaminando.png"), new Texture("mineroAzulParado.png"), new Texture("mineroAzulAtacando.png"), 29, 44,29, 44, 34, 38, 50, 20, true, 'p');
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
                                           UWarrior warrior = new UWarrior(myAnimatedCastle.getX()+myAnimatedCastle.getWidth(), myAnimatedCastle.getY(), new Texture("guerreroAzulCaminando.png"), new Texture("guerreroAzulParado.png"), new Texture("guerreroAzulAtacando.png"), 29, 44,29, 44, 59, 42, 100, 20, true, 'g');
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
                                          Archer warrior = new Archer(myAnimatedCastle.getX()+myAnimatedCastle.getWidth(), myAnimatedCastle.getY(), new Texture("arqueroAzulCaminando.png"), new Texture("arqueroAzulParado.png"), new Texture("arqueroAzulAtacando.png"), 29, 44, 37,43,43, 42, 40, 10, true, 'a');
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
                                        Priest monk = new Priest(myAnimatedCastle.getX()+myAnimatedCastle.getWidth(), myAnimatedCastle.getY(), new Texture("monjeAzulCaminando.png"), new Texture("monjeAzulParado.png"), new Texture("monjeAzulAtacando.png"), 32, 44, 29,44,29, 44, 50, 20, true, 'm');
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
                                          UWarrior warrior = new UWarrior(myAnimatedCastle.getX()+myAnimatedCastle.getWidth(), myAnimatedCastle.getY(), new Texture("dragonAzulParado.png"), new Texture("dragonAzulParado.png"), new Texture("dragonAzulAtacando.png"), 47, 43,47, 43, 47, 43, 150, 50 ,true, 'd');
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


        label1 = new Label("Level5",slabelStyle);
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
            dragonTime += delta;
            if (timer >= seconds) {
                timer = 0;
                if (miners < 3){
                    UMiner miner = new UMiner(PixelWars.ANCHO * 1.66F, enemyAnimatedCastle.getY(), new Texture("mineroRojoCaminando.png"), new Texture("mineroRojoParado.png"), new Texture("mineroRojoAtacando.png"), 29, 44,29, 44, 34, 38, 50, 15, false , 'p');
                    enemyWarriorsQ.addLast(miner);
                    miners +=1;
                }else if (warriors < 1){
                    UWarrior warrior = new UWarrior(PixelWars.ANCHO * 1.66F, enemyAnimatedCastle.getY(), new Texture("guerreroRojoCaminando.png"), new Texture("guerreroRojoParado.png"), new Texture("guerreroRojoAtacando.png"), 29, 44, 29, 44, 59, 42, 100, 20, false, 'g');
                    enemyWarriorsQ.addLast(warrior);
                    warriors+=1;
                }else if(monks <1) {
                    Priest monk = new Priest(PixelWars.ANCHO * 1.66F, enemyAnimatedCastle.getY(), new Texture("monjeRojoCaminando.png"), new Texture("monjeRojoParado.png"), new Texture("monjeRojoAtacando.png"), 32, 44, 29,44,29, 44, 50, 10, true, 'm');
                    enemyWarriorsQ.addLast(monk);
                    monks += 1;
                }else if (archers < 3){
                    Archer archer = new Archer(PixelWars.ANCHO * 1.66F, enemyAnimatedCastle.getY(), new Texture("arqueroRojoCaminando.png"), new Texture("arqueroRojoParado.png"), new Texture("arqueroRojoAtacando.png"), 29, 44, 37,43,43, 42, 40, 10, false, 'a');
                    enemyWarriorsQ.addLast(archer);
                    archers += 1;
                    if (archers == 2) {
                        miners = 0;
                        warriors = 0;
                        archers = 0;
                        monks = 0;
                    }
                }
                if (dragonTime >= dragonSeconds){
                    timer = 0;
                    while (dragons < number_of_dragons) {
                        timer+= delta;
                        if (timer >= seconds) {
                            UWarrior warrior = new UWarrior(PixelWars.ANCHO * 1.66F, enemyAnimatedCastle.getY(), new Texture("dragonRojoParado.png"), new Texture("dragonRojoParado.png"), new Texture("dragonRojoAtacando.png"), 47, 43, 47, 43, 47, 43, 150, 50, true, 'd');
                            enemyWarriorsQ.addLast(warrior);
                            dragons += 1;
                        }

                    }
                    if (number_of_dragons < 6) {
                        number_of_dragons += 1;
                    }
                    dragons = 0;
                    dragonTime = 0;
                    dragonTime = 0;
                }

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
            if (myAnimatedCastle.isAlive()) {
                myAnimatedCastle.render(game.batch);

            }
            if (enemyAnimatedCastle.isAlive()) {
                enemyAnimatedCastle.render(game.batch);
            }
            game.batch.draw(actionbar, xBar, PixelWars.ALTO - actionbar.getHeight());

            if (!myWarriorsQ.isEmpty()) {
                for (AnimationWarrior warrior : myWarriorsQ) {
                    warrior.render(game.batch);
                }
                ColisionConEnemigo(myWarriorsQ.first());
                formarUnidades();

            }
            if (!enemyWarriorsQ.isEmpty()) {
                for (AnimationWarrior enemy : enemyWarriorsQ) {
                    enemy.render(game.batch);
                }
                ColisionAliado(enemyWarriorsQ.first());
                formarEnemigos();
            }

            if (!myAnimatedCastle.isAlive()) {
                game.batch.draw(youlose,(gamePort.getCamera().position.x)-youlose.getWidth()/2,(PixelWars.ALTO / 2)-youlose.getHeight()/2);
                isFinish = true;
                enemyWarriorsQ.clear();
                myWarriorsQ.clear();
                stage.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          super.clicked(event, x, y);
                                          //game.setScreen(new MenuScreen(game));
                                          game.setScreen(new LoadingScreen(game,Screens.MenuScreen));
                                      }
                                  }
                );
            }

            if (!enemyAnimatedCastle.isAlive()) {
                isFinish = true;
                game.batch.draw(youwin,(gamePort.getCamera().position.x)-youwin.getWidth()/2,(PixelWars.ALTO / 2)-youwin.getHeight()/2);
                if(levelInfo.getPassedLevel()<5){
                    levelInfo.setLevel(levelInfo.getPassedLevel()+1);
                }
                enemyWarriorsQ.clear();
                myWarriorsQ.clear();
                stage.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          super.clicked(event, x, y);
                                          //game.setScreen(new level3(game));
                                          game.setScreen(new LoadingScreen(game,Screens.MenuScreen));
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
                                                stage.addActor(btnDragon);
                                                stage.addActor(btnMiner);
                                                stage.addActor(btnMonk);
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
                                           //game.setScreen(new GameScreen(game));
                                           game.setScreen(new LoadingScreen(game,Screens.level5));
                                       }

                                   }

            );

            btnExit.addListener(new ClickListener() {
                                    @Override
                                    public void clicked(InputEvent event, float x, float y) {
                                        Gdx.gl.glClearColor(1, 1, 1, 1);
                                        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                                        //game.setScreen(new LoadingScreen(game));
                                        game.setScreen(new LoadingScreen(game,Screens.MenuScreen));
                                    }
                                }
            );
            game.batch.end();
            stage.draw();
            stage.act();

        }
    }

    private void ArquerosDisparando(AnimationWarrior warrior){
        if (myWarriorsQ.indexOf(warrior, false)-myWarriorsQ.indexOf(myWarriorsQ.first(),false)<=3 && myWarriorsQ.first().getEstado() == StateWarrior.ATACANDO && !enemyWarriorsQ.isEmpty()){
            warrior.setEstado(StateWarrior.ATACANDO);
            Archer archer = (Archer) warrior;
            enemyWarriorsQ.first().setHp(archer.Flechazo(enemyWarriorsQ.first().getHp()));
        }else if (myWarriorsQ.indexOf(warrior, false)-myWarriorsQ.indexOf(myWarriorsQ.first(),false)<=3 && myWarriorsQ.first().getEstado() == StateWarrior.ATACANDO && enemyWarriorsQ.isEmpty()){
            warrior.setEstado(StateWarrior.ATACANDO);
            Archer archer = (Archer) warrior;
            enemyAnimatedCastle.setHp(archer.Flechazo(enemyAnimatedCastle.getHp()));
        }else{
            warrior.setEstado(StateWarrior.QUIETO);
        }
    }

    private void EnemigosArquerosDisparando(AnimationWarrior warrior){
        if (enemyWarriorsQ.indexOf(warrior, false)-enemyWarriorsQ.indexOf(enemyWarriorsQ.first(),false)<=3 && enemyWarriorsQ.first().getEstado() == StateWarrior.ATACANDO && !myWarriorsQ.isEmpty()){
            warrior.setEstado(StateWarrior.ATACANDO);
            Archer archer = (Archer) warrior;
            myWarriorsQ.first().setHp(archer.Flechazo(myWarriorsQ.first().getHp()));
        }else if (enemyWarriorsQ.indexOf(warrior, false)-enemyWarriorsQ.indexOf(enemyWarriorsQ.first(),false)<=3 && enemyWarriorsQ.first().getEstado() == StateWarrior.ATACANDO && myWarriorsQ.isEmpty()){
            warrior.setEstado(StateWarrior.ATACANDO);
            Archer archer = (Archer) warrior;
            myAnimatedCastle.setHp(archer.Flechazo(myAnimatedCastle.getHp()));
        }else{
            warrior.setEstado(StateWarrior.QUIETO);
        }
    }

    private void ColisionCastilloAliado(AnimationTower castle) {
        if (enemyWarriorsQ.first().getSprite().getBoundingRectangle().overlaps(castle.getSprite().getBoundingRectangle())){
            if (enemyWarriorsQ.first().getClass() == UWarrior.class) {
                UWarrior warrior = (UWarrior) enemyWarriorsQ.first();
                warrior.setEstado(StateWarrior.ATACANDO);
                myAnimatedCastle.setHp(warrior.Espadazo(myAnimatedCastle.getHp()));
                isCastleAlive(myAnimatedCastle);
            }else if (enemyWarriorsQ.first().getClass() == Archer.class){
                Archer warrior = (Archer) enemyWarriorsQ.first();
                warrior.setEstado(StateWarrior.ATACANDO);
                myAnimatedCastle.setHp(warrior.Flechazo(myAnimatedCastle.getHp()));
                isCastleAlive(myAnimatedCastle);
            }else if (enemyWarriorsQ.first().getClass() == UMiner.class){
                UMiner warrior = (UMiner) enemyWarriorsQ.first();
                warrior.setEstado(StateWarrior.ATACANDO);
                myAnimatedCastle.setHp(warrior.picar(myAnimatedCastle.getHp()));
                timerToMine += Gdx.graphics.getDeltaTime();
                if (timerToMine>= timeToMine){
                    gold -= 100;
                    label3.setText(gold);
                    timerToMine = 0;
                }
                isCastleAlive(myAnimatedCastle);
            }
        }else{
            enemyWarriorsQ.first().setEstado(StateWarrior.CAMINANDO);
            enemyWarriorsQ.first().moverX(-1);
        }
        if(myAnimatedCastle.getHp()>350){
            myAnimatedCastle.setEstado(EStateTower.SINDAÑO);
        }else {
            if(myAnimatedCastle.getHp()<=350 && myAnimatedCastle.getHp()>225){
                myAnimatedCastle.setEstado(EStateTower.DAÑO1);
            }else{
                if(myAnimatedCastle.getHp()<=225 && myAnimatedCastle.getHp()>100){
                    myAnimatedCastle.setEstado(EStateTower.DAÑO2);
                }else {
                    myAnimatedCastle.setEstado(EStateTower.DAÑO3);
                }
            }
        }
    }
    private void ColisionConEnemigo(AnimationWarrior first) {
        if (!enemyWarriorsQ.isEmpty()){
            if (first.getSprite().getBoundingRectangle().overlaps(enemyWarriorsQ.first().getSprite().getBoundingRectangle())){
                if (first.getClass() == UWarrior.class) {
                    UWarrior warrior = (UWarrior) first;
                    first.setEstado(StateWarrior.ATACANDO);
                    enemyWarriorsQ.first().setHp(warrior.Espadazo(enemyWarriorsQ.first().getHp()));
                    comprobarVivoEnemigo();
                }else if (first.getClass() == Archer.class){
                    Archer warrior = (Archer) first;
                    first.setEstado(StateWarrior.ATACANDO);
                    enemyWarriorsQ.first().setHp(warrior.Flechazo(enemyWarriorsQ.first().getHp()));
                    comprobarVivoEnemigo();
                }else if (first.getClass() == UMiner.class){
                    UMiner miner = (UMiner) first;
                    first.setEstado(StateWarrior.ATACANDO);
                    timerToMine += Gdx.graphics.getDeltaTime();
                    if (timerToMine>= timeToMine){
                        gold += 50;
                        label3.setText(gold);
                        timerToMine = 0;
                    }
                    enemyWarriorsQ.first().setHp(miner.picar(enemyWarriorsQ.first().getHp()));
                    comprobarVivoEnemigo();
                }
            }else {
                first.setEstado(StateWarrior.CAMINANDO);
                first.moverX(1);
            }


        }else{
            ColisionCatilloEnemigo(enemyAnimatedCastle);

        }
    }

    private void comprobarVivoEnemigo() {
        if(enemyWarriorsQ.first().getHp()<=0){

            if (enemyWarriorsQ.first().getClass() == UWarrior.class && enemyWarriorsQ.first().getUnidad() == 'g') {
                gold += 125;
                label3.setText(gold);
            }else if (enemyWarriorsQ.first().getClass() == Archer.class){
                gold += 225;
                label3.setText(gold);
            }else if (enemyWarriorsQ.first().getClass() == Priest.class){
                gold += 250;
                label3.setText(gold);
            }else if (enemyWarriorsQ.first().getClass() == UMiner.class){
                gold += 55;
                label3.setText(gold);
            }else{
                gold += 1250;
                label3.setText(gold);
            }
            enemyWarriorsQ.removeFirst();

        }
    }

    private void ColisionAliado(AnimationWarrior first) {
        if (!myWarriorsQ.isEmpty()){
            if (first.getSprite().getBoundingRectangle().overlaps(myWarriorsQ.first().getSprite().getBoundingRectangle())){
                if (first.getClass() == UWarrior.class) {
                    UWarrior warrior = (UWarrior) first;
                    first.setEstado(StateWarrior.ATACANDO);
                    myWarriorsQ.first().setHp(warrior.Espadazo(myWarriorsQ.first().getHp()));
                    comprobarVivoAliado();
                }else if (first.getClass() == Archer.class){
                    Archer warrior = (Archer) first;
                    first.setEstado(StateWarrior.ATACANDO);
                    myWarriorsQ.first().setHp(warrior.Flechazo(myWarriorsQ.first().getHp()));
                    comprobarVivoAliado();
                }else if (first.getClass() == UMiner.class){
                    UMiner miner = (UMiner) first;
                    first.setEstado(StateWarrior.ATACANDO);
                    timerToMine += Gdx.graphics.getDeltaTime();
                    if (timerToMine>= timeToMine){
                        gold -= 50;
                        label3.setText(gold);
                        timerToMine = 0;
                    }
                    myWarriorsQ.first().setHp(miner.picar(myWarriorsQ.first().getHp()));
                    comprobarVivoAliado();
                }
            }else {
                first.setEstado(StateWarrior.CAMINANDO);
                first.moverX(-1);
            }


        }else{
            ColisionCastilloAliado(myAnimatedCastle);

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
            AnimationWarrior warrior = enemyWarriorsQ.get(i);
            if (!warrior.equals(enemyWarriorsQ.first())){
                if (warrior.getSprite().getBoundingRectangle().overlaps(enemyWarriorsQ.get(i-1).getSprite().getBoundingRectangle())){
                    if (warrior.getClass()==Archer.class)
                        EnemigosArquerosDisparando(warrior);
                    else if(warrior.getClass() == Priest.class)
                        enemigoCuracion(warrior);
                    else {
                        warrior.setEstado(StateWarrior.QUIETO);
                    }
                }else{
                    warrior.setEstado(StateWarrior.CAMINANDO);
                    warrior.moverX(-1);
                }
            }
        }

    }

    private void ColisionCatilloEnemigo(AnimationTower castle) {
        if (myWarriorsQ.first().getSprite().getBoundingRectangle().overlaps(castle.getSprite().getBoundingRectangle())){
            if (myWarriorsQ.first().getClass() == UWarrior.class) {
                UWarrior warrior = (UWarrior) myWarriorsQ.first();
                warrior.setEstado(StateWarrior.ATACANDO);
                enemyAnimatedCastle.setHp(warrior.Espadazo(enemyAnimatedCastle.getHp()));
                isCastleAlive(enemyAnimatedCastle);
            }else if (myWarriorsQ.first().getClass() == Archer.class){
                Archer warrior = (Archer) myWarriorsQ.first();
                warrior.setEstado(StateWarrior.ATACANDO);
                enemyAnimatedCastle.setHp(warrior.Flechazo(enemyAnimatedCastle.getHp()));
                isCastleAlive(enemyAnimatedCastle);
            }else if (myWarriorsQ.first().getClass() == UMiner.class){
                UMiner warrior = (UMiner) myWarriorsQ.first();
                warrior.setEstado(StateWarrior.ATACANDO);
                enemyAnimatedCastle.setHp(warrior.picar(enemyAnimatedCastle.getHp()));
                timerToMine += Gdx.graphics.getDeltaTime();
                if (timerToMine>= timeToMine){
                    gold += 100;
                    label3.setText(gold);
                    timerToMine = 0;
                }
                isCastleAlive(enemyAnimatedCastle);
            }
        }else{
            myWarriorsQ.first().setEstado(StateWarrior.CAMINANDO);
            myWarriorsQ.first().moverX(1);
        }
        if(enemyAnimatedCastle.getHp()>350){
            enemyAnimatedCastle.setEstado(EStateTower.SINDAÑO);
        }else {
            if(enemyAnimatedCastle.getHp()<=350 && enemyAnimatedCastle.getHp()>225){
                enemyAnimatedCastle.setEstado(EStateTower.DAÑO1);
            }else{
                if(enemyAnimatedCastle.getHp()<=225 && enemyAnimatedCastle.getHp()>100){
                    enemyAnimatedCastle.setEstado(EStateTower.DAÑO2);
                }else {
                    enemyAnimatedCastle.setEstado(EStateTower.DAÑO3);
                }
            }
        }
    }



    private void isCastleAlive(AnimationTower Castle) {
        if (Castle.getHp()<= 0) {
            Castle.setAlive(false);
        }
    }

    private void formarUnidades() {
        for (int i = 0; i < myWarriorsQ.size; i++){
            AnimationWarrior warrior = myWarriorsQ.get(i);
            if (!warrior.equals(myWarriorsQ.first())){
                if (warrior.getSprite().getBoundingRectangle().overlaps(myWarriorsQ.get(i-1).getSprite().getBoundingRectangle())){
                    if (warrior.getClass()==Archer.class)
                        ArquerosDisparando(warrior);
                    else if(warrior.getClass() == Priest.class)
                        Curacion(warrior);
                    else {
                        warrior.setEstado(StateWarrior.QUIETO);
                    }
                }else{
                    warrior.setEstado(StateWarrior.CAMINANDO);
                    warrior.moverX(1);
                }
            }
        }
    }

    private void Curacion(AnimationWarrior warrior) {
        if (myWarriorsQ.indexOf(warrior, false)-myWarriorsQ.indexOf(myWarriorsQ.first(),false)<4 && myWarriorsQ.first().getEstado() == StateWarrior.ATACANDO && !enemyWarriorsQ.isEmpty()){
            warrior.setEstado(StateWarrior.ATACANDO);
            Priest Monje = (Priest) warrior;
            myWarriorsQ.first().setHp(Monje.Curacion(myWarriorsQ.first().getHp()));
        }else{
            warrior.setEstado(StateWarrior.QUIETO);
        }
    }

    public void enemigoCuracion(AnimationWarrior warrior){
        if (enemyWarriorsQ.indexOf(warrior, false)-enemyWarriorsQ.indexOf(enemyWarriorsQ.first(),false)<4 && enemyWarriorsQ.first().getEstado() == StateWarrior.ATACANDO && !myWarriorsQ.isEmpty()){

            warrior.setEstado(StateWarrior.ATACANDO);
            Priest Monje = (Priest) warrior;
            enemyWarriorsQ.first().setHp(Monje.Curacion(enemyWarriorsQ.first().getHp()));
        }else{
            warrior.setEstado(StateWarrior.QUIETO);
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
            if (Gdx.input.getX()>Gdx.graphics.getWidth()/2 && gamePort.getCamera().position.x < PixelWars.ANCHO*1.4 && Gdx.input.getY() > actionbar.getHeight()){
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
            else if (Gdx.input.getX()<=Gdx.graphics.getWidth()/2 && gamePort.getCamera().position.x > 4 +PixelWars.ANCHO/2 && Gdx.input.getY() > actionbar.getHeight()){
                aument *= deltaTime;
                xBar -= aument;
                label2.setPosition(label2.getX()-aument,PixelWars.ALTO-row_height*1-4);
                label3.setPosition(label3.getX()-aument,PixelWars.ALTO-row_height*1-4);
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
        dispose();

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