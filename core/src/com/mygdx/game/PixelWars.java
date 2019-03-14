package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PantallaCargando;


public class PixelWars extends Game {
	public SpriteBatch batch;

	//Tamaño Pantalla
	public static final int ANCHO = 320;
	public static final int ALTO = 180;


	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PantallaCargando(this));
	}

	@Override
	public void render () {
		super.render();
	}

}
