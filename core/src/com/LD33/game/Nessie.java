package com.LD33.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

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

    private static float floatSpeed = 200f;
    private static boolean boolIsUnder = false;
    private static boolean boolIsSplashing = false;
    private static boolean boolIsInboundsX_Right = true;
    private static boolean boolIsInboundsX_Left = true;
    private static boolean boolIsInboundsY_Up = true;
    private static boolean boolIsInboundsY_Down = true;

    private static float floatHitboxX;
    private static float floatHitboxY;
    private static float floatsHitboxSize;
    private static Rectangle nessieBoundingRectangle;
    private static Rectangle nessieHitBox;

    public static Vector2 position;

    public Nessie()
    {
        textureNessie = new Texture("nessie.png");
        textureNessieUnder = new Texture("nessie_under.png");
        textureSplash = new Texture("headBut.png");


        spriteNessie = new Sprite(textureNessie);
        spriteSplash = new Sprite(textureSplash);
        spriteNessie.setSize(spriteNessie.getWidth()/2.5f,spriteNessie.getHeight()/2.5f);
        spriteNessie.setPosition(Gdx.graphics.getWidth()/2-spriteNessie.getWidth()/2,Gdx.graphics.getHeight()/2-spriteNessie.getHeight()/2);
        floatsHitboxSize = 25f;

        nessieHitBox = new Rectangle(-100f,-100f,0f,0f);

    }
    public static void update(List<Boat> boats)
    {
        nessieBoundingRectangle = spriteNessie.getBoundingRectangle();
        nessieHitBox.set(floatHitboxX,floatHitboxY,floatsHitboxSize,floatsHitboxSize);
        headBut(boats);
        moveNessie();
        keepInBounds();
        position = new Vector2(spriteNessie.getX(),spriteNessie.getY());

    }
    public static void draw(Batch batch)
    {
        if(boolIsUnder)
            spriteNessie.setTexture(textureNessieUnder);
        else spriteNessie.setTexture(textureNessie);
        spriteNessie.setOriginCenter();
        spriteNessie.draw(batch);

    }

    public static void moveNessie()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            if(boolIsInboundsY_Up)
            {
                spriteNessie.translateY(floatSpeed * Gdx.graphics.getDeltaTime());
                spriteNessie.setRotation(0f);
                floatHitboxX = nessieBoundingRectangle.x + (spriteNessie.getWidth()/2 - floatsHitboxSize/2);
                floatHitboxY = nessieBoundingRectangle.y + (spriteNessie.getHeight()-floatsHitboxSize);
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            if(boolIsInboundsY_Down)
            {
                spriteNessie.translateY(-floatSpeed * Gdx.graphics.getDeltaTime());
                spriteNessie.setRotation(180f);
                floatHitboxX = nessieBoundingRectangle.x + (spriteNessie.getWidth()/2 - floatsHitboxSize/2);
                floatHitboxY = nessieBoundingRectangle.y;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            if(boolIsInboundsX_Left)
            {
                spriteNessie.translateX(floatSpeed * Gdx.graphics.getDeltaTime());
                spriteNessie.setRotation(270f);
                floatHitboxX = nessieBoundingRectangle.x + spriteNessie.getHeight() - floatsHitboxSize;
                floatHitboxY = nessieBoundingRectangle.y + (spriteNessie.getWidth()/2 - floatsHitboxSize/2);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            if(boolIsInboundsX_Right)
            {
                spriteNessie.translateX(-floatSpeed * Gdx.graphics.getDeltaTime());
                spriteNessie.setRotation(90f);
                floatHitboxX = nessieBoundingRectangle.x;
                floatHitboxY = nessieBoundingRectangle.y + (spriteNessie.getWidth()/2 - floatsHitboxSize/2);
            }
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            boolIsUnder = true;
        }
        if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            boolIsUnder = false;
        }

    }


    public static void headBut(List<Boat> boats)
    {
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && !boolIsUnder)
        {
            boolIsSplashing = true;
        }
        if(!Gdx.input.isButtonPressed(Input.Buttons.RIGHT) || boolIsUnder)
        {
            boolIsSplashing = false;
        }
        for (Boat b:boats) {
            if(Intersector.overlaps(nessieHitBox,b.hitBoxRectangle)&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                b.isHit();
            }
        }
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

    public static void drawShapeRenderer(ShapeRenderer shapeRenderer)
    {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(floatHitboxX,floatHitboxY,floatsHitboxSize,floatsHitboxSize);

    }
}
