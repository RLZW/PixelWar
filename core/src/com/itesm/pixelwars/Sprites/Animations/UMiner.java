package com.itesm.pixelwars.Sprites.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.itesm.pixelwars.PixelWars;

public class UMiner extends AnimationWarrior {

    private int ataque;
    private static final float TIEMPO_BASE = 0.5f;
    private float tiempoAtaque = 0;
    private Sound mine_sound = Gdx.audio.newSound(Gdx.files.internal("mine.mp3"));

    public UMiner(float x, float y, Texture guerreroCaminando, Texture guerreroParado, Texture guerreroAtacando, int caminandoWidth, int caminandoHeight, int paradoWidth, int paradoHeight, int atacandoWidth, int atacandoHeight, int hp, int ataque, boolean equipoAzul, char unidad) {
        super(x, y, guerreroCaminando, guerreroParado, guerreroAtacando, caminandoWidth, caminandoHeight, paradoWidth, paradoHeight, atacandoWidth, atacandoHeight, hp, ataque, equipoAzul, unidad);
        this.ataque=ataque;
    }

    public int picar(int hp){
        tiempoAtaque+=Gdx.graphics.getDeltaTime();
        if (tiempoAtaque>=TIEMPO_BASE){
            if (PixelWars.effects) {
                mine_sound.play(0.20f);
            }
            hp-= ataque;
            tiempoAtaque = 0;
        }
        return hp;
    }


}
