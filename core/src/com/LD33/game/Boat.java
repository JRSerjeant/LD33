package com.LD33.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;


/**
 * Created by Jack on 22/08/2015.
 */
public class Boat {

    private static Texture textureBoat;
    private Sprite spriteBoat;

    private float floatStartPositionX;
    private float floatStartPositionY;

    private float floatSpeed = 1f;

    private  boolean boolIsDestroyed = false;

    public Boat()
    {
        textureBoat = new Texture("boat.png");
        spriteBoat = new Sprite(textureBoat);
        floatStartPositionX = MathUtils.random(0f,Gdx.graphics.getWidth());
        floatStartPositionY = Gdx.graphics.getHeight() - spriteBoat.getHeight();
        spriteBoat.setPosition(floatStartPositionX,floatStartPositionY);
        System.out.println(floatStartPositionX);
    }

    public void update()
    {
        System.out.flush();
        spriteBoat.translateY(-floatSpeed);
    }

    public void draw(Batch batch)
    {
        spriteBoat.draw(batch);
    }

    public boolean checkBounds()
    {

        if(spriteBoat.getY() > 0 - spriteBoat.getHeight())
        {
            boolIsDestroyed = true;
        }
        return  boolIsDestroyed;
    }

}
