package com.itesm.pixelwars;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.nkzawa.emitter.Emitter;
import com.itesm.pixelwars.Screens.TransitionScreen;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


import org.json.JSONException;
import org.json.JSONObject;


public class PixelWars extends Game {
	public SpriteBatch batch;
	public OrthographicCamera gamecam;
	private Socket socket;

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
		connectSocket();
		configSocketEvents();
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

	// Para que las otras pantallas usen el assetManager
	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void connectSocket(){
		try{
			//Define a donde nos estamos conectando.
			socket = IO.socket("http://16286400.ngrok.io");
			socket.connect();
		} catch (Exception e){
			System.out.print(e);
		}
	}

	public void configSocketEvents(){
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
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
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.clear();
	}


}
