package com.itesm.pixelwars.Sprites.Animations;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Guerrero extends  AnimacionGuerrero{
    private int ataque;
    private static final float TIEMPO_BASE = 0.62f;
    private float tiempoAtaque = 0;
    private Sound sword_sound = Gdx.audio.newSound(Gdx.files.internal("Sword Attack.mp3"));

    public Guerrero(float x, float y, Texture guerreroCaminando, Texture guerreroParado, Texture guerreroAtacando, int caminandoWidth, int caminandoHeight, int paradoWidth, int paradoHeight, int atacandoWidth, int atacandoHeight, int hp, int ataque) {
        super(x, y, guerreroCaminando, guerreroParado, guerreroAtacando, caminandoWidth, caminandoHeight, paradoWidth, paradoHeight, atacandoWidth, atacandoHeight, hp, ataque);
        this.ataque = ataque;
    }

    public int Espadazo(int HP){
        tiempoAtaque+=Gdx.graphics.getDeltaTime();
        if (tiempoAtaque>=TIEMPO_BASE){
            sword_sound.play(0.20f);
            HP-= ataque;
            tiempoAtaque = 0;
        }
        return HP;
    }


}
