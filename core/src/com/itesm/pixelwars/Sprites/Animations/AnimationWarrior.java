package com.itesm.pixelwars.Sprites.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.itesm.pixelwars.Sprites.Animations.StateWarrior.CAMINANDO;


public class AnimationWarrior {


    private Sprite sprite;
    private Animation animacionCaminando;
    private Animation animacionParado;
    private Animation animacionAtacando;
    private float timerAnimacion;
    private Texture texturaCaminando;
    private Texture texturaParado;
    private Texture texturaAtacando;

    private StateWarrior estado;
    private TextureRegion[][] texturaGuerreroCaminando;
    private TextureRegion[][] texturaGuerreroParado;
    private TextureRegion[][] texturaGuerreroAtacando;
    private boolean equipoAzul;


    private char unidad;


    private int hp;
    private int ataque;


    public AnimationWarrior(float x, float y, Texture guerreroCaminando, Texture guerreroParado, Texture guerreroAtacando, int caminandoWidth, int caminandoHeight, int paradoWidth, int paradoHeight, int atacandoWidth, int atacandoHeight, int hp, int ataque, boolean equipoAzul, char unidad){
        this.equipoAzul=equipoAzul;
        this.unidad = unidad;
        this.texturaCaminando = guerreroCaminando;
        this.texturaAtacando = guerreroAtacando;
        this.texturaParado = guerreroParado;
        this.hp= hp;
        this.ataque = ataque;


        // Crea una region Caminando
        TextureRegion regionCaminando = new TextureRegion(texturaCaminando);
        // Divide la región en frames de 29x44
        texturaGuerreroCaminando = regionCaminando.split(caminandoWidth,caminandoHeight);


        // Crea una region Parado
        TextureRegion regionParado = new TextureRegion(texturaParado);
        // Divide la región en frames de 29x44
        texturaGuerreroParado = regionParado.split(paradoWidth,paradoHeight);


        // Crea una region Atacando
        TextureRegion regionAtacando = new TextureRegion(texturaAtacando);
        // Divide la región en frames de 59x42
        texturaGuerreroAtacando = regionAtacando.split(atacandoWidth,atacandoHeight);

/// Falta modificar lo de abajo para decir que pedo con cada uno (el número del array)
        switch (unidad){
            case 'g': //guerrero
                animacionCaminando = new Animation(0.15f,texturaGuerreroCaminando[0][5],texturaGuerreroCaminando[0][4],texturaGuerreroCaminando[0][3],texturaGuerreroCaminando[0][2],texturaGuerreroCaminando[0][1],texturaGuerreroCaminando[0][0]);
                animacionParado = new Animation(0.15f,texturaGuerreroParado[0][3],texturaGuerreroParado[0][2],texturaGuerreroParado[0][1],texturaGuerreroParado[0][0]);
                animacionAtacando = new Animation(0.15f,texturaGuerreroAtacando[0][3],texturaGuerreroAtacando[0][2],texturaGuerreroAtacando[0][1],texturaGuerreroAtacando[0][0]);
                break;
            case 'a': //arquero
                animacionCaminando = new Animation(0.15f,texturaGuerreroCaminando[0][5],texturaGuerreroCaminando[0][4],texturaGuerreroCaminando[0][3],texturaGuerreroCaminando[0][2],texturaGuerreroCaminando[0][1],texturaGuerreroCaminando[0][0]);
                animacionParado = new Animation(0.15f,texturaGuerreroParado[0][3],texturaGuerreroParado[0][2],texturaGuerreroParado[0][1],texturaGuerreroParado[0][0]);
                animacionAtacando = new Animation(0.15f,texturaGuerreroAtacando[0][8],texturaGuerreroAtacando[0][7],texturaGuerreroAtacando[0][6],texturaGuerreroAtacando[0][5],texturaGuerreroAtacando[0][4],texturaGuerreroAtacando[0][3],texturaGuerreroAtacando[0][2],texturaGuerreroAtacando[0][1],texturaGuerreroAtacando[0][0]);
                break;
            case 'm': //monje
                animacionCaminando = new Animation(0.15f,texturaGuerreroParado[0][3],texturaGuerreroParado[0][2],texturaGuerreroParado[0][1],texturaGuerreroParado[0][0]);
                animacionParado = new Animation(0.15f,texturaGuerreroCaminando[0][3],texturaGuerreroCaminando[0][2],texturaGuerreroCaminando[0][1],texturaGuerreroCaminando[0][0]);
                animacionAtacando = new Animation(0.15f,texturaGuerreroAtacando[0][3],texturaGuerreroAtacando[0][2],texturaGuerreroAtacando[0][1],texturaGuerreroAtacando[0][0]);
                break;
            case 'd': //dragon
                animacionCaminando = new Animation(0.15f,texturaGuerreroCaminando[0][3],texturaGuerreroCaminando[0][2],texturaGuerreroCaminando[0][1],texturaGuerreroCaminando[0][0]);
                //no hay textura parado para dragon
                animacionParado = new Animation(0.15f,texturaGuerreroParado[0][3],texturaGuerreroParado[0][2],texturaGuerreroParado[0][1],texturaGuerreroParado[0][0]);
                animacionAtacando = new Animation(0.15f,texturaGuerreroAtacando[0][5],texturaGuerreroAtacando[0][4],texturaGuerreroAtacando[0][3],texturaGuerreroAtacando[0][2],texturaGuerreroAtacando[0][1],texturaGuerreroAtacando[0][0]);
                break;
            case 'p': //minero/picador
                animacionCaminando = new Animation(0.15f,texturaGuerreroCaminando[0][5],texturaGuerreroCaminando[0][4],texturaGuerreroCaminando[0][3],texturaGuerreroCaminando[0][2],texturaGuerreroCaminando[0][1],texturaGuerreroCaminando[0][0]);
                //no hay textura parado para dragon
                animacionParado = new Animation(0.15f,texturaGuerreroCaminando[0][4],texturaGuerreroParado[0][3],texturaGuerreroParado[0][2],texturaGuerreroParado[0][1],texturaGuerreroParado[0][0]);
                animacionAtacando = new Animation(0.15f,texturaGuerreroAtacando[0][4],texturaGuerreroAtacando[0][3],texturaGuerreroAtacando[0][2],texturaGuerreroAtacando[0][1],texturaGuerreroAtacando[0][0]);
                break;


        }

        animacionCaminando.setPlayMode(Animation.PlayMode.LOOP);
        animacionParado.setPlayMode(Animation.PlayMode.LOOP);
        animacionAtacando.setPlayMode(Animation.PlayMode.LOOP);

        // Quieto
        sprite = new Sprite(texturaGuerreroCaminando[0][0]);


        sprite.setPosition(x,y);
        timerAnimacion = 0;

        estado = CAMINANDO;

    }


    public void render(SpriteBatch batch){
        timerAnimacion += Gdx.graphics.getDeltaTime();
        switch (estado){
            case QUIETO:
                sprite.setRegion(texturaGuerreroParado[0][0]);
                TextureRegion regionParado = (TextureRegion) animacionParado.getKeyFrame(timerAnimacion);
                batch.draw(regionParado, sprite.getX(), sprite.getY());
                break;
            case CAMINANDO:
                sprite.setRegion(texturaGuerreroCaminando[0][0]);
                TextureRegion regionCaminando = (TextureRegion) animacionCaminando.getKeyFrame(timerAnimacion);
                batch.draw(regionCaminando, sprite.getX(), sprite.getY());
                break;
            case ATACANDO:

                sprite.setRegion(texturaGuerreroAtacando[0][0]);
                //spriteAtacando.setPosition(sprite.getX(), sprite.getY());
                TextureRegion regionAtacando = (TextureRegion) animacionAtacando.getKeyFrame(timerAnimacion);
                if (equipoAzul==true){
                    batch.draw(regionAtacando, sprite.getX()-(regionAtacando.getRegionWidth()/3), sprite.getY());
                }else{
                    batch.draw(regionAtacando, sprite.getX()-(regionAtacando.getRegionWidth()/3), sprite.getY());
                }
                break;
            case MUERTO:
                sprite.setColor(1,0,0,1);
                sprite.draw(batch);
        }

    }


    public StateWarrior getEstado() {
        return estado;
    }

    public void setEstado(StateWarrior estado) {
        this.estado = estado;
    }


    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public void setX(float x){
        sprite.setX(x);
    }

    public void setY(float y){
        sprite.setY(y);
    }

    public void moverX(float dx){
        sprite.setX(sprite.getX() + dx);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public char getUnidad(){
        return unidad;
    }




}

