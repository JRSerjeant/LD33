package com.LD33.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jack on 22/08/2015.
 */
public class Boat {

    static Texture textureBoat;
    private Sprite spriteBoat;
    static  Texture textureFlash;
    private Sprite spriteFlash;


    float scale = 4f;
    float floatStartPositionX;
    float floatStartPositionY;
    float floatPositionX;
    float floatPositionY;
    float floatRotation;
    Vector2 boatPosition;
    public Vector2 nessiePosition;

    public static int intMasterStartSide = 0;
    int intStartSide;


    private float floatSpeed = 100f;

    private  boolean boolIsDestroyed = false;

    List<Boat> listBoat = new ArrayList<>();

    public Boat(List<Boat> listBoat, SpriteBatch b)
    {
        textureBoat = new Texture("boat.png");
        spriteBoat = new Sprite(textureBoat);
        textureFlash = new Texture("flash.png");
        spriteFlash = new Sprite(textureFlash);
        spriteBoat.setSize(spriteBoat.getWidth()/scale,spriteBoat.getHeight()/scale);
        intStartSide = intMasterStartSide;
        intMasterStartSide ++;

        if (intMasterStartSide == 4)
            intMasterStartSide = 0;




        switch (intStartSide) {
            case 0:
                floatStartPositionX = MathUtils.random(0f+spriteBoat.getWidth(),Gdx.graphics.getWidth()-spriteBoat.getWidth());
                floatStartPositionY = MathUtils.random(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() + 250f);
                floatRotation = 180f;
                break;
            case 1:
                floatStartPositionX = MathUtils.random(Gdx.graphics.getWidth(), Gdx.graphics.getWidth() + 250f);
                floatStartPositionY = MathUtils.random(0f +spriteBoat.getWidth(), Gdx.graphics.getHeight()-spriteBoat.getWidth());
                floatRotation = 90f;
                break;
            case 2:
                floatStartPositionX = MathUtils.random(0f+spriteBoat.getWidth(),Gdx.graphics.getWidth()-spriteBoat.getWidth());
                floatStartPositionY = MathUtils.random(0f, -500f);
                floatRotation = 0f;
                break;
            case 3:
                floatStartPositionX = MathUtils.random(0f, -500f);
                floatStartPositionY = MathUtils.random(0f + spriteBoat.getHeight(), Gdx.graphics.getHeight() - spriteBoat.getHeight());
                floatRotation = 270f;
                break;
        }

        this.listBoat = listBoat;
        floatPositionY = floatStartPositionY;
        floatPositionX = floatStartPositionX;
        spriteBoat.setRotation(floatRotation);
        spriteBoat.setPosition(floatPositionX, floatPositionY);
        spriteBoat.setOriginCenter();

    }

    public void update()
    {
        boatPosition = new Vector2(spriteBoat.getX(),spriteBoat.getY());
        nessiePosition = Nessie.position;
        spriteFlash.setPosition(spriteBoat.getX(), spriteBoat.getY());
        switch(intStartSide)
        {
            case 0:
                spriteBoat.translateY(-floatSpeed * Gdx.graphics.getDeltaTime());
                break;
            case 1:
                spriteBoat.translateX(-floatSpeed * Gdx.graphics.getDeltaTime());
                break;
            case 2:
                spriteBoat.translateY(floatSpeed * Gdx.graphics.getDeltaTime());
                break;
            case 3:
                spriteBoat.translateX(floatSpeed * Gdx.graphics.getDeltaTime());
                break;
        }

    }

    public void draw(Batch batch)
    {

        spriteBoat.draw(batch);

        if(checkPosition()) {
            spriteFlash.draw(batch);
        }
    }

    public boolean checkBounds()
    {
        switch(intStartSide)
        {
            case 0:
                if (spriteBoat.getY() < 0 - Gdx.graphics.getHeight()/2 - spriteBoat.getHeight())
                    boolIsDestroyed = true;
                break;
            case 1:
                if (spriteBoat.getX() < 0 - Gdx.graphics.getWidth()/2 - spriteBoat.getWidth())
                    boolIsDestroyed = true;
                break;
            case 2:
                if(spriteBoat.getY() > Gdx.graphics.getHeight() + ((spriteBoat.getHeight()/2)/scale))
                    boolIsDestroyed = true;
                break;
            case 3:
                if(spriteBoat.getX() > Gdx.graphics.getWidth() + ((spriteBoat.getWidth()/2)/scale))
                    boolIsDestroyed = true;
                break;
        }
        return  boolIsDestroyed;
    }
    public boolean checkPosition()
    {
        boolean b = false;
        if (nessiePosition.dst(this.boatPosition) < 50)
            b = true;
        return b;
    }

}
