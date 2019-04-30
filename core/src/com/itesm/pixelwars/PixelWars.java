package com.itesm.pixelwars;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itesm.pixelwars.Screens.GameScreen;
import com.itesm.pixelwars.Screens.TransitionScreen;

public class PixelWars extends Game {
	public SpriteBatch batch;
	public OrthographicCamera gamecam;


	//Tamaño Pantalla
	public static final int ANCHO = 320;
	public static final int ALTO = 180;

	// Music
	private Music musicaFondo;

	// Hay un SOLO assetManager para el juego
	private final AssetManager assetManager;

	public PixelWars(){
		assetManager = new AssetManager();
	}



	@Override
	public void create () {
		batch = new SpriteBatch();
		gamecam = new OrthographicCamera(ANCHO,ALTO);
		gamecam.position.set(ANCHO/2,ALTO/2,0);
		setScreen(new TransitionScreen(this));
		cargarMusica();
        iniciarMusica();
	}



	// MUSICA
	public void cargarMusica() {
		AssetManager manager = new AssetManager();
		manager.load("MenuMusic.mp3", Music.class);
		manager.finishLoading();    // síncrono
		musicaFondo = manager.get("MenuMusic.mp3");

	}

	public void iniciarMusica(){
        musicaFondo.play();
    }

    public void pausarMusica(){
	    musicaFondo.pause();
    }

	// Para que las otras pantallas usen el assetManager
	public AssetManager getAssetManager() {
		return assetManager;
	}


	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.clear();
	}

}
