package com.LD33.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Jack on 22/08/2015.
 */
public class Nessie
{
    private static Texture textureNessie;
    private static Texture textureNessieUnder;
    private static Texture textureSplash;

    private static Sprite spriteNessie;
    private static Sprite spriteSplash;

    private static float floatSpeed = 10f;
    private static boolean boolIsUnder = false;
    private static boolean boolIsSplashing = false;
    private static boolean boolIsInboundsX_Right = true;
    private static boolean boolIsInboundsX_Left = true;
    private static boolean boolIsInboundsY_Up = true;
    private static boolean boolIsInboundsY_Down = true;

    public Nessie()
    {
        textureNessie = new Texture("nessie.png");
        textureNessieUnder = new Texture("nessie_under.png");
        textureSplash = new Texture("splash.png");


        spriteNessie = new Sprite(textureNessie);
        spriteSplash = new Sprite(textureSplash);
    }
    public static void update()
    {
        moveNessie();
        keepInBounds();
    }
    public static void draw(Batch batch)
    {
        if(boolIsUnder)
            spriteNessie.setTexture(textureNessieUnder);
        else spriteNessie.setTexture(textureNessie);

        spriteNessie.draw(batch);

        if(splash())
            spriteSplash.draw(batch);

    }

    public static void moveNessie()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            if(boolIsInboundsY_Up)
                spriteNessie.translateY(floatSpeed);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            if(boolIsInboundsY_Down)
                spriteNessie.translateY(-floatSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            if(boolIsInboundsX_Left)
                spriteNessie.translateX(floatSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            if(boolIsInboundsX_Right)
                spriteNessie.translateX(-floatSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            boolIsUnder = true;
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            boolIsUnder = false;
        }

    }

    public static boolean splash()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT) && !boolIsUnder)
        {
            boolIsSplashing = true;
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT) || boolIsUnder)
        {
            boolIsSplashing = false;
        }
        spriteSplash.setPosition(spriteNessie.getX(),spriteNessie.getY());

        return boolIsSplashing;
    }

    private static void keepInBounds()
    {

        if(spriteNessie.getX() >= Gdx.graphics.getWidth() - spriteNessie.getWidth()/2)
            boolIsInboundsX_Left = false;
        else boolIsInboundsX_Left = true;

        if(spriteNessie.getX() <= 0f - spriteNessie.getWidth()/2)
            boolIsInboundsX_Right = false;
        else boolIsInboundsX_Right = true;

        if(spriteNessie.getY() >= Gdx.graphics.getHeight() - spriteNessie.getHeight()/2)
            boolIsInboundsY_Up = false;
        else boolIsInboundsY_Up = true;
        if(spriteNessie.getY() <= 0f - spriteNessie.getHeight()/2)
            boolIsInboundsY_Down = false;
        else boolIsInboundsY_Down= true;


    }

}
