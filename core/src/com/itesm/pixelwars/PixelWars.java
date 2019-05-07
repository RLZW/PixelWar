package com.itesm.pixelwars;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itesm.pixelwars.Screens.GameScreen;
import com.itesm.pixelwars.Screens.MapScreen;
import com.itesm.pixelwars.Screens.TransitionScreen;
import com.itesm.pixelwars.Screens.level3;
import com.itesm.pixelwars.Screens.level4;
import com.itesm.pixelwars.Screens.level5;

public class PixelWars extends Game {
	public SpriteBatch batch;
	public OrthographicCamera gamecam;


	//Tamaño Pantalla
	public static final int ANCHO = 320;
	public static final int ALTO = 180;

	//Sound
	public static final boolean SOUND = true;

	// Music
	private Music musicBack;

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
		loadMusic();
		startMusic();
	}

	// MUSICA
	public void loadMusic() {
		AssetManager manager = new AssetManager();
		manager.load("medieval_back.mp3", Music.class);
		manager.finishLoading();    // síncrono
		musicBack = manager.get("medieval_back.mp3");

	}

	public void startMusic(){
		musicBack.play();
	}

	public void pauseMusic(){
		musicBack.stop();
	}

	@Override
	public void render () {
		super.render();
	}
	

}
