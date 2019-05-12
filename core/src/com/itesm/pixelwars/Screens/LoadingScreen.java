package com.itesm.pixelwars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.pixelwars.PixelWars;



public class LoadingScreen implements Screen {
    private PixelWars game;
    Texture loadingTexture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //Tiempo
    private float timeCounter = 0;

    // AssetManager
    private AssetManager manager;


    private Screens nextScreen;
    private int avance; // % de Carga

    public LoadingScreen(PixelWars game, Screens nextScreen) {
        this.game = game;
        this.nextScreen = nextScreen;
    }

    @Override
    public void show() {
        gamecam = new OrthographicCamera(PixelWars.ANCHO, PixelWars.ALTO);
        gamecam.position.set(PixelWars.ANCHO / 2, PixelWars.ALTO / 2, 0);
        gamePort = new StretchViewport(PixelWars.ANCHO, PixelWars.ALTO, gamecam);

        //Texutre
        loadingTexture = new Texture("loading.png");

        ///////////////
        loadResourcesNextScreen();
    }

    // Carga los recursos de la siguiente pantalla
    private void loadResourcesNextScreen() {
        manager = game.getAssetManager();
        avance = 0;
        switch (nextScreen) {
            case MenuScreen:
                loadMenuResources();
                break;
            case AboutScreen:
                loadAboutResources();
                break;
            case SettingsScreen:
                loadSettingsResources();
                break;
            case GameScreen:
                loadGameResources();
                break;
            case MapScreen:
                loadMapResources();
                break;
            case level2:
                loadLevel2Resources();
                break;
            case level3:
                loadLevel3Resources();
                break;
            case level4:
                loadLevel4Resources();
                break;
            case level5:
                loadLevel5Resources();
                break;
        }
    }

    private void loadLevel5Resources() {
        manager.load("torreInvisible.png", Texture.class);
        manager.load("torreAzul.png", Texture.class);
        manager.load("torreAzulDaño1.png", Texture.class);
        manager.load("torreAzulDaño2.png", Texture.class);
        manager.load("torreAzulDaño3.png", Texture.class);

        manager.load("torreRoja.png", Texture.class);
        manager.load("torreRojaDaño1.png", Texture.class);
        manager.load("torreRojaDaño2.png", Texture.class);
        manager.load("torreRojaDaño3.png", Texture.class);

        manager.load("bClouds.png", Texture.class);
        manager.load("bSky1.png", Texture.class);
        manager.load("bGrass.png", Texture.class);
        manager.load("bMtns.png", Texture.class);

        manager.load("topBar.png", Texture.class);

        manager.load("btnSword.png", Texture.class);
        manager.load("btnSword_Pressed.png", Texture.class);

        manager.load("btnDinero.png", Texture.class);
        manager.load("btnDinero_Pressed.png", Texture.class);

        manager.load("btnBow.png", Texture.class);
        manager.load("BtnBow_Pressed.png", Texture.class);

        manager.load("btnMage.png", Texture.class);
        manager.load("btnMage_Pressed.png", Texture.class);

        manager.load("btnDragon.png", Texture.class);
        manager.load("btnDragon_Pressed.png", Texture.class);

        manager.load("btnPause.png", Texture.class);
        manager.load("btnPause_Pressed.png", Texture.class);
        manager.load("pauseRestart.png", Texture.class);
        manager.load("pauseRestart_Pressed.png", Texture.class);
        manager.load("pauseExit.png", Texture.class);
        manager.load("pauseExit_Pressed.png", Texture.class);
        manager.load("pauseContinue.png", Texture.class);
        manager.load("pauseContinue_Pressed.png", Texture.class);

    }

    private void loadLevel4Resources() {
        manager.load("torreInvisible.png", Texture.class);
        manager.load("torreAzul.png", Texture.class);
        manager.load("torreAzulDaño1.png", Texture.class);
        manager.load("torreAzulDaño2.png", Texture.class);
        manager.load("torreAzulDaño3.png", Texture.class);

        manager.load("torreRoja.png", Texture.class);
        manager.load("torreRojaDaño1.png", Texture.class);
        manager.load("torreRojaDaño2.png", Texture.class);
        manager.load("torreRojaDaño3.png", Texture.class);

        manager.load("bClouds.png", Texture.class);
        manager.load("bSky1.png", Texture.class);
        manager.load("bGrass.png", Texture.class);
        manager.load("bMtns.png", Texture.class);

        manager.load("topBar.png", Texture.class);

        manager.load("btnSword.png", Texture.class);
        manager.load("btnSword_Pressed.png", Texture.class);

        manager.load("btnDinero.png", Texture.class);
        manager.load("btnDinero_Pressed.png", Texture.class);

        manager.load("btnBow.png", Texture.class);
        manager.load("BtnBow_Pressed.png", Texture.class);

        manager.load("btnMage.png", Texture.class);
        manager.load("btnMage_Pressed.png", Texture.class);

        manager.load("btnDragon.png", Texture.class);
        manager.load("btnDragon_Pressed.png", Texture.class);

        manager.load("btnPause.png", Texture.class);
        manager.load("btnPause_Pressed.png", Texture.class);
        manager.load("pauseRestart.png", Texture.class);
        manager.load("pauseRestart_Pressed.png", Texture.class);
        manager.load("pauseExit.png", Texture.class);
        manager.load("pauseExit_Pressed.png", Texture.class);
        manager.load("pauseContinue.png", Texture.class);
        manager.load("pauseContinue_Pressed.png", Texture.class);

    }

    private void loadLevel3Resources() {
        manager.load("torreInvisible.png", Texture.class);
        manager.load("torreAzul.png", Texture.class);
        manager.load("torreAzulDaño1.png", Texture.class);
        manager.load("torreAzulDaño2.png", Texture.class);
        manager.load("torreAzulDaño3.png", Texture.class);

        manager.load("torreRoja.png", Texture.class);
        manager.load("torreRojaDaño1.png", Texture.class);
        manager.load("torreRojaDaño2.png", Texture.class);
        manager.load("torreRojaDaño3.png", Texture.class);

        manager.load("bClouds.png", Texture.class);
        manager.load("bSky1.png", Texture.class);
        manager.load("bGrass.png", Texture.class);
        manager.load("bMtns.png", Texture.class);

        manager.load("topBar.png", Texture.class);

        manager.load("btnSword.png", Texture.class);
        manager.load("btnSword_Pressed.png", Texture.class);

        manager.load("btnDinero.png", Texture.class);
        manager.load("btnDinero_Pressed.png", Texture.class);

        manager.load("btnBow.png", Texture.class);
        manager.load("BtnBow_Pressed.png", Texture.class);

        manager.load("btnMage.png", Texture.class);
        manager.load("btnMage_Pressed.png", Texture.class);

        manager.load("btnDragon.png", Texture.class);
        manager.load("btnDragon_Pressed.png", Texture.class);

        manager.load("btnPause.png", Texture.class);
        manager.load("btnPause_Pressed.png", Texture.class);
        manager.load("pauseRestart.png", Texture.class);
        manager.load("pauseRestart_Pressed.png", Texture.class);
        manager.load("pauseExit.png", Texture.class);
        manager.load("pauseExit_Pressed.png", Texture.class);
        manager.load("pauseContinue.png", Texture.class);
        manager.load("pauseContinue_Pressed.png", Texture.class);

    }

    private void loadLevel2Resources() {
        manager.load("torreInvisible.png", Texture.class);
        manager.load("torreAzul.png", Texture.class);
        manager.load("torreAzulDaño1.png", Texture.class);
        manager.load("torreAzulDaño2.png", Texture.class);
        manager.load("torreAzulDaño3.png", Texture.class);

        manager.load("torreRoja.png", Texture.class);
        manager.load("torreRojaDaño1.png", Texture.class);
        manager.load("torreRojaDaño2.png", Texture.class);
        manager.load("torreRojaDaño3.png", Texture.class);

        manager.load("bClouds.png", Texture.class);
        manager.load("bSky1.png", Texture.class);
        manager.load("bGrass.png", Texture.class);
        manager.load("bMtns.png", Texture.class);

        manager.load("topBar.png", Texture.class);

        manager.load("btnSword.png", Texture.class);
        manager.load("btnSword_Pressed.png", Texture.class);

        manager.load("btnDinero.png", Texture.class);
        manager.load("btnDinero_Pressed.png", Texture.class);

        manager.load("btnBow.png", Texture.class);
        manager.load("BtnBow_Pressed.png", Texture.class);

        manager.load("btnMage.png", Texture.class);
        manager.load("btnMage_Pressed.png", Texture.class);

        manager.load("btnDragon.png", Texture.class);
        manager.load("btnDragon_Pressed.png", Texture.class);

        manager.load("btnPause.png", Texture.class);
        manager.load("btnPause_Pressed.png", Texture.class);
        manager.load("pauseRestart.png", Texture.class);
        manager.load("pauseRestart_Pressed.png", Texture.class);
        manager.load("pauseExit.png", Texture.class);
        manager.load("pauseExit_Pressed.png", Texture.class);
        manager.load("pauseContinue.png", Texture.class);
        manager.load("pauseContinue_Pressed.png", Texture.class);

    }

    private void loadMapResources() {
        manager.load("heroic.mp3", Sound.class);
        manager.load("map.png", Texture.class);
    }

    private void loadGameResources() {
        manager.load("torreInvisible.png", Texture.class);
        manager.load("torreAzul.png", Texture.class);
        manager.load("torreAzulDaño1.png", Texture.class);
        manager.load("torreAzulDaño2.png", Texture.class);
        manager.load("torreAzulDaño3.png", Texture.class);

        manager.load("torreRoja.png", Texture.class);
        manager.load("torreRojaDaño1.png", Texture.class);
        manager.load("torreRojaDaño2.png", Texture.class);
        manager.load("torreRojaDaño3.png", Texture.class);

        manager.load("bClouds.png", Texture.class);
        manager.load("bSky1.png", Texture.class);
        manager.load("bGrass.png", Texture.class);
        manager.load("bMtns.png", Texture.class);

        manager.load("topBar.png", Texture.class);

        manager.load("btnSword.png", Texture.class);
        manager.load("btnSword_Pressed.png", Texture.class);

        manager.load("btnDinero.png", Texture.class);
        manager.load("btnDinero_Pressed.png", Texture.class);

        manager.load("btnBow.png", Texture.class);
        manager.load("BtnBow_Pressed.png", Texture.class);

        manager.load("btnMage.png", Texture.class);
        manager.load("btnMage_Pressed.png", Texture.class);

        manager.load("btnDragon.png", Texture.class);
        manager.load("btnDragon_Pressed.png", Texture.class);

        manager.load("btnPause.png", Texture.class);
        manager.load("btnPause_Pressed.png", Texture.class);
        manager.load("pauseRestart.png", Texture.class);
        manager.load("pauseRestart_Pressed.png", Texture.class);
        manager.load("pauseExit.png", Texture.class);
        manager.load("pauseExit_Pressed.png", Texture.class);
        manager.load("pauseContinue.png", Texture.class);
        manager.load("pauseContinue_Pressed.png", Texture.class);


    }

    private void loadSettingsResources() {
        manager.load("btnSoundOff.png", Texture.class);
        manager.load("btnSoundOff_Pressed.png", Texture.class);
        manager.load("back.png", Texture.class);

    }

    private void loadAboutResources() {
        manager.load("back.png", Texture.class);

    }

    private void loadMenuResources() {
        manager.load("medieval_back.mp3", Sound.class);
        manager.load("btn1P.png", Texture.class);
        manager.load("btn1P_Pressed.png", Texture.class);
        manager.load("btn2P.png", Texture.class);
        manager.load("btn2P_Pressed.png", Texture.class);
        manager.load("btnAbout.png", Texture.class);
        manager.load("btnAbout_Pressed.png", Texture.class);
        manager.load("btnOptions.png", Texture.class);
        manager.load("btnOptions_Pressed.png", Texture.class);


    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        game.batch.draw(loadingTexture, 0, 0);
        game.batch.end();


        timeCounter += delta;
        if (timeCounter >= 5) {
            game.setScreen(new MenuScreen(game));
        }

        // ACTUALIZAR CARGA
        updateResourcesLoad();


    }

    private void updateResourcesLoad() {
        if (manager.update()) { // TERMINÓ ????
            switch (nextScreen) {
                case MenuScreen:
                    game.setScreen(new MenuScreen(game)); // 100% DE CARGA
                    break;
                case AboutScreen:
                    game.setScreen(new AboutScreen(game)); // 100% DE CARGA
                    break;
                case SettingsScreen:
                    game.setScreen(new SettingsScreen(game));
                    break;
                case GameScreen:
                    game.setScreen(new GameScreen(game));
                    break;
                case MapScreen:
                    game.setScreen(new MapScreen(game));
                    break;
                case level2:
                    game.setScreen(new level2(game));
                    break;
                case level3:
                    game.setScreen(new level3(game));
                    break;
                case level4:
                    game.setScreen(new level4(game));
                    break;
                case level5:
                    game.setScreen(new level5(game));
                    break;
            }
        }
        avance = (int) (manager.getProgress() * 100);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

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

        loadingTexture.dispose();
    }

}

