package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.pixelwars.PixelWars;
import com.itesm.pixelwars.Sprites.Actors.TitleActor;

public class MapScreen implements Screen {
    private final LevelInfo levelInfor;
    private PixelWars game;
    private Stage stage;
    private Texture map;
    private TitleActor mapActor;
    private Viewport gamePort;
    private Texture marker, marker_blocked;
    private TextureRegionDrawable marker_d, marker_blocked_d;
    private ImageButton level1_button,level2_button,level3_button,level4_button,level5_button;
    private Sound warsong;
    private int saved_levels;


    public MapScreen(PixelWars game, LevelInfo levelInfo){
        this.game = game;
        gamePort = new StretchViewport(PixelWars.ANCHO,PixelWars.ALTO,game.gamecam);
        this.levelInfor=levelInfo;
    }

    @Override
    public void show() {
        saved_levels = levelInfor.getPassedLevel();
        map = new Texture("map.png");
        mapActor = new TitleActor(map);
        stage = new Stage(gamePort,game.batch);
        stage.addActor(mapActor);
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        createMarkers(saved_levels);




        if(game.SOUND){
            warsong = Gdx.audio.newSound(Gdx.files.internal("heroic.mp3"));
            warsong.play();
            game.pauseMusic();
        }

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(stage.getCamera().combined);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new LoadingScreen(game,Screens.MenuScreen));
        }
        stage.draw();
        stage.act();
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
        map.dispose();
    }
    public void createMarkers(int saved_levels){
        marker = new Texture("marker1.png");
        marker_blocked = new Texture("marker2.png");

        marker_d = new TextureRegionDrawable(marker);
        marker_blocked_d = new TextureRegionDrawable(marker_blocked);

        if (saved_levels == 0){
            level1_button = new ImageButton(marker_d);
            level2_button = new ImageButton(marker_blocked_d);
            level3_button = new ImageButton(marker_blocked_d);
            level4_button = new ImageButton(marker_blocked_d);
            level5_button = new ImageButton(marker_blocked_d);

            stage.addActor(level1_button);
            stage.addActor(level2_button);
            stage.addActor(level3_button);
            stage.addActor(level4_button);
            stage.addActor(level5_button);

            level1_button.setPosition(PixelWars.ANCHO/4+10,PixelWars.ALTO/2+20);
            level2_button.setPosition(PixelWars.ANCHO/3+10,PixelWars.ALTO/2+30);
            level3_button.setPosition(PixelWars.ANCHO/2+10,PixelWars.ALTO/2-30);
            level4_button.setPosition(PixelWars.ANCHO/3*2,PixelWars.ALTO/2+20);
            level5_button.setPosition(PixelWars.ANCHO/2,PixelWars.ALTO/2+40);

            level1_button.addListener(new ClickListener() {
                                  @Override
                                  public void clicked(InputEvent event, float x, float y) {
                                      super.clicked(event, x, y);
                                      //game.setScreen(new level3(game));
                                      game.setScreen(new LoadingScreen(game,Screens.GameScreen));
                                  }
                              }
            );
        }
        else if(saved_levels == 1){
            level1_button = new ImageButton(marker_d);
            level2_button = new ImageButton(marker_d);
            level3_button = new ImageButton(marker_blocked_d);
            level4_button = new ImageButton(marker_blocked_d);
            level5_button = new ImageButton(marker_blocked_d);

            stage.addActor(level1_button);
            stage.addActor(level2_button);
            stage.addActor(level3_button);
            stage.addActor(level4_button);
            stage.addActor(level5_button);

            level1_button.setPosition(PixelWars.ANCHO/4+10,PixelWars.ALTO/2+20);
            level2_button.setPosition(PixelWars.ANCHO/3+10,PixelWars.ALTO/2+30);
            level3_button.setPosition(PixelWars.ANCHO/2+10,PixelWars.ALTO/2-30);
            level4_button.setPosition(PixelWars.ANCHO/3*2,PixelWars.ALTO/2+20);
            level5_button.setPosition(PixelWars.ANCHO/2,PixelWars.ALTO/2+40);
            level1_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new History(game));
                                          }
                                      }
            );
            level2_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level2));
                                          }
                                      }
            );
        }
        else if(saved_levels == 2){
            level1_button = new ImageButton(marker_d);
            level2_button = new ImageButton(marker_d);
            level3_button = new ImageButton(marker_d);
            level4_button = new ImageButton(marker_blocked_d);
            level5_button = new ImageButton(marker_blocked_d);

            stage.addActor(level1_button);
            stage.addActor(level2_button);
            stage.addActor(level3_button);
            stage.addActor(level4_button);
            stage.addActor(level5_button);

            level1_button.setPosition(PixelWars.ANCHO/4+10,PixelWars.ALTO/2+20);
            level2_button.setPosition(PixelWars.ANCHO/3+10,PixelWars.ALTO/2+30);
            level3_button.setPosition(PixelWars.ANCHO/2+10,PixelWars.ALTO/2-30);
            level4_button.setPosition(PixelWars.ANCHO/3*2,PixelWars.ALTO/2+20);
            level5_button.setPosition(PixelWars.ANCHO/2,PixelWars.ALTO/2+40);
            level1_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.GameScreen));
                                          }
                                      }
            );
            level2_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level2));
                                          }
                                      }
            );
            level3_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level3));
                                          }
                                      }
            );

        }
        else if(saved_levels == 3){
            level1_button = new ImageButton(marker_d);
            level2_button = new ImageButton(marker_d);
            level3_button = new ImageButton(marker_d);
            level4_button = new ImageButton(marker_d);
            level5_button = new ImageButton(marker_blocked_d);

            stage.addActor(level1_button);
            stage.addActor(level2_button);
            stage.addActor(level3_button);
            stage.addActor(level4_button);
            stage.addActor(level5_button);

            level1_button.setPosition(PixelWars.ANCHO/4+10,PixelWars.ALTO/2+20);
            level2_button.setPosition(PixelWars.ANCHO/3+10,PixelWars.ALTO/2+30);
            level3_button.setPosition(PixelWars.ANCHO/2+10,PixelWars.ALTO/2-30);
            level4_button.setPosition(PixelWars.ANCHO/3*2,PixelWars.ALTO/2+20);
            level5_button.setPosition(PixelWars.ANCHO/2,PixelWars.ALTO/2+40);
            level1_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.GameScreen));
                                          }
                                      }
            );
            level2_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level2));
                                          }
                                      }
            );
            level3_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level3));
                                          }
                                      }
            );
            level4_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level4));
                                          }
                                      }
            );

        }
        else if(saved_levels >= 4){
            level1_button = new ImageButton(marker_d);
            level2_button = new ImageButton(marker_d);
            level3_button = new ImageButton(marker_d);
            level4_button = new ImageButton(marker_d);
            level5_button = new ImageButton(marker_d);

            stage.addActor(level1_button);
            stage.addActor(level2_button);
            stage.addActor(level3_button);
            stage.addActor(level4_button);
            stage.addActor(level5_button);

            level1_button.setPosition(PixelWars.ANCHO/4+10,PixelWars.ALTO/2+20);
            level2_button.setPosition(PixelWars.ANCHO/3+10,PixelWars.ALTO/2+30);
            level3_button.setPosition(PixelWars.ANCHO/2+10,PixelWars.ALTO/2-30);
            level4_button.setPosition(PixelWars.ANCHO/3*2,PixelWars.ALTO/2+20);
            level5_button.setPosition(PixelWars.ANCHO/2,PixelWars.ALTO/2+40);

            level1_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.GameScreen));
                                          }
                                      }
            );
            level2_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level2));
                                          }
                                      }
            );
            level3_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level3));
                                          }
                                      }
            );
            level4_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level4));
                                          }
                                      }
            );
            level5_button.addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {
                                              super.clicked(event, x, y);
                                              //game.setScreen(new level3(game));
                                              game.setScreen(new LoadingScreen(game,Screens.level5));
                                          }
                                      }
            );

        }



    }
}
