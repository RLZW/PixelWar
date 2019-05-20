package com.itesm.pixelwars.Sprites.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.itesm.pixelwars.PixelWars;

public class Archer extends AnimationWarrior {
    private int ataque;
    private static final float TIEMPO_BASE = 0.5f;
    private float tiempoAtaque = 0;
    private Sound archer_sound = Gdx.audio.newSound(Gdx.files.internal("ArcherAttack.mp3"));


    public Archer(float x, float y, Texture guerreroCaminando, Texture guerreroParado, Texture guerreroAtacando, int caminandoWidth, int caminandoHeight, int paradoWidth, int paradoHeight, int atacandoWidth, int atacandoHeight, int hp, int ataque, boolean equipoAzul, char unidad) {
        super(x, y, guerreroCaminando, guerreroParado, guerreroAtacando, caminandoWidth, caminandoHeight, paradoWidth, paradoHeight, atacandoWidth, atacandoHeight, hp, ataque, equipoAzul, unidad);
        this.ataque = ataque;
    }

    public int Flechazo(int Hp){
        tiempoAtaque+=Gdx.graphics.getDeltaTime();
        if (tiempoAtaque>=TIEMPO_BASE){
            if (PixelWars.effects) {
                archer_sound.play(0.20f);
            }
            Hp-= ataque;
            tiempoAtaque = 0;
        }
        return Hp;
    }


}
