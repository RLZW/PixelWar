package com.itesm.pixelwars.Sprites.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TowerAnimation {

    private final Animation animacionDaño1;
    private final Animation animacionDaño2;
    private final Animation animacionDaño3;
    private Animation animacion;
    private Sprite sprite;
    private float timerAnimacion;
    private EStateTower estadoTorre;
    private TextureRegion[][] texturaTorre;
    private TextureRegion[][] texturaTorreDaño1;
    private TextureRegion[][] texturaTorreDaño2;
    private TextureRegion[][] texturaTorreDaño3;

    public TowerAnimation(float x, float y, Texture torre, Texture torreDaño1, Texture torreDaño2, Texture torreDaño3){
        // Cargar textura
        //Texture textura = new Texture("torreAzul");  // 400x66 = tamaño de la imagen

        // Crea una region
        TextureRegion region = new TextureRegion(torre);
        // Divide la región en frames de 32x64
        texturaTorre = region.split(69,124);
        animacion = new Animation(0.15f,texturaTorre[0][5],texturaTorre[0][4],texturaTorre[0][3],texturaTorre[0][2],texturaTorre[0][1],texturaTorre[0][0]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);


        //Primer Daño
        TextureRegion regionDaño1 = new TextureRegion(torreDaño1);
        // Divide la región en frames de 32x64
        texturaTorreDaño1 = region.split(69,124);
        animacionDaño1 = new Animation(0.15f,texturaTorreDaño1[0][5],texturaTorreDaño1[0][4],texturaTorreDaño1[0][3],texturaTorreDaño1[0][2],texturaTorreDaño1[0][1],texturaTorreDaño1[0][0]);
        animacionDaño1.setPlayMode(Animation.PlayMode.LOOP);

        //Segundo Daño
        TextureRegion regionDaño2 = new TextureRegion(torreDaño2);
        // Divide la región en frames de 32x64
        texturaTorreDaño2 = region.split(69,124);
        animacionDaño2 = new Animation(0.15f,texturaTorreDaño2[0][1],texturaTorreDaño2[0][0]);
        animacionDaño2.setPlayMode(Animation.PlayMode.LOOP);

        //Segundo Tercer Daño
        TextureRegion regionDaño3 = new TextureRegion(torreDaño3);
        // Divide la región en frames de 32x64
        texturaTorreDaño3 = region.split(69,124);
        animacionDaño3 = new Animation(0.15f,texturaTorreDaño3[0][1],texturaTorreDaño3[0][0]);
        animacionDaño3.setPlayMode(Animation.PlayMode.LOOP);



        estadoTorre = EStateTower.SINDAÑO;
        timerAnimacion = 0;
        // Quieto
        sprite = new Sprite(texturaTorre[0][0]);
        sprite.setPosition(x,y);
    }

    public void render(SpriteBatch batch){
        timerAnimacion += Gdx.graphics.getDeltaTime();
        switch (estadoTorre){
            case SINDAÑO:
                sprite.setRegion(texturaTorre[0][0]);
                TextureRegion region = (TextureRegion) animacion.getKeyFrame(timerAnimacion);
                batch.draw(region, sprite.getX(), sprite.getY());
                break;
            case DAÑO1:
                sprite.setRegion(texturaTorreDaño1[0][0]);
                TextureRegion regionDaño1 = (TextureRegion) animacionDaño1.getKeyFrame(timerAnimacion);
                batch.draw(regionDaño1, sprite.getX(), sprite.getY());
                break;
            case DAÑO2:
                sprite.setRegion(texturaTorreDaño2[0][0]);
                TextureRegion regionDaño2 = (TextureRegion) animacionDaño2.getKeyFrame(timerAnimacion);
                batch.draw(regionDaño2, sprite.getX(), sprite.getY());
                break;
            case DAÑO3:
                sprite.setRegion(texturaTorreDaño3[0][0]);
                TextureRegion regionDaño3 = (TextureRegion) animacionDaño3.getKeyFrame(timerAnimacion);
                batch.draw(regionDaño3, sprite.getX(), sprite.getY());
                break;
        }
    }


    public void setEstado(EStateTower estadoTorre) {
        this.estadoTorre = estadoTorre;
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

    public Sprite getSprite() {
        return sprite;
    }
}

