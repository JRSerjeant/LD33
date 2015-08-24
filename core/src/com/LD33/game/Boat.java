package com.LD33.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jack on 22/08/2015.
 */
public class Boat {


    static Texture texturePunk;
    private Sprite spritePunk;
    static Texture textureBoat;
    private Sprite spriteBoat;
    static  Texture textureFlash;
    private Sprite spriteFlash;
    static  Texture textureLady;
    private Sprite spriteLady;

    private float floatSpeed = 100f;
    float scale = 4f;
    float floatStartPositionX;
    float floatStartPositionY;
    float floatPositionX;
    float floatPositionY;
    float floatRotation;
    Vector2 boatPosition;
    public Vector2 nessiePosition;
    private  boolean boolIsDestroyed = false;
    List<Boat> listBoat = new ArrayList<>();
    public static int intMasterStartSide = 0;
    int intStartSide;
    int intHealth;
    Rectangle boatBoundingRectangle;

    public float floatHitboxPositionX;
    public float floatHitboxPositionY;
    float floatHitboxPositionOffsetX;
    float floatHitboxPositionOffsetY;
    float floatHitboxSizeOffsetX;
    float floatHitboxSizeOffsetY;
    float floatHitboxHeight;
    float floatHitboxWidth;
    boolean isHit;
    public Rectangle hitBoxRectangle;

    public Boat(List<Boat> listBoat, SpriteBatch b)
    {

        intHealth = 25;
        textureBoat = new Texture("boat.png");
        spriteBoat = new Sprite(textureBoat);
        textureFlash = new Texture("flash.png");
        spriteFlash = new Sprite(textureFlash);
        texturePunk = new Texture("punk.png");
        spritePunk = new Sprite(texturePunk);
        textureLady = new Texture("lady.png");
        spriteLady = new Sprite(textureLady);
        spritePunk.scale(-0.25f);
        spriteBoat.setSize(spriteBoat.getWidth()/scale,spriteBoat.getHeight()/scale);
        intStartSide = intMasterStartSide;
        intMasterStartSide ++;
        hitBoxRectangle = new Rectangle(0f, 0f, 0f, 0f);
        isHit = false;
        boatPosition = new Vector2(spriteBoat.getX(),spriteBoat.getY());

        if (intMasterStartSide == 4)
            intMasterStartSide = 0;

        switch (intStartSide) {
            case 0:
                floatStartPositionX = MathUtils.random(0f+spriteBoat.getWidth(),Gdx.graphics.getWidth()-spriteBoat.getWidth());
                floatStartPositionY = MathUtils.random(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() + 250f);
                floatHitboxPositionOffsetY = spriteBoat.getHeight();
                floatHitboxPositionOffsetX = 0f;
                floatHitboxSizeOffsetX = 0f;
                floatHitboxSizeOffsetY = spriteBoat.getWidth()/2;
                spritePunk.setRotation(110f);
                spriteLady.setRotation(180f);
                floatRotation = 180f;
                break;
            case 1:
                floatStartPositionX = MathUtils.random(Gdx.graphics.getWidth(), Gdx.graphics.getWidth() + 250f);
                floatStartPositionY = MathUtils.random(0f +spriteBoat.getWidth(), Gdx.graphics.getHeight()-spriteBoat.getWidth());
                floatHitboxPositionOffsetY = 0f;
                floatHitboxPositionOffsetX = spriteBoat.getHeight();
                floatHitboxSizeOffsetX = spriteBoat.getWidth()/2f;
                floatHitboxSizeOffsetY = 0f;
                spritePunk.setRotation(172f);
                spriteLady.setRotation(90f);
                floatRotation = 90f;
                break;
            case 2:
                floatStartPositionX = MathUtils.random(0f+spriteBoat.getWidth(),Gdx.graphics.getWidth()-spriteBoat.getWidth());
                floatStartPositionY = MathUtils.random(0f, -500f);
                floatHitboxPositionOffsetY = - spriteBoat.getHeight()/4;
                floatHitboxSizeOffsetX = 0f;
                floatHitboxSizeOffsetY = spriteBoat.getWidth()/2;
                floatHitboxPositionOffsetX = 0f;
                floatRotation = 0f;
                spritePunk.setRotation(72f);
                break;
            case 3:
                floatStartPositionX = MathUtils.random(0f, -500f);
                floatStartPositionY = MathUtils.random(0f + spriteBoat.getHeight(), Gdx.graphics.getHeight() - spriteBoat.getHeight());
                floatHitboxPositionOffsetY = 0f;
                floatHitboxPositionOffsetX = - spriteBoat.getHeight()/4;
                floatHitboxSizeOffsetX = spriteBoat.getWidth()/2;
                floatHitboxSizeOffsetY = 0f;
                floatRotation = 270f;
                spriteLady.setRotation(270f);

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
        boatBoundingRectangle = spriteBoat.getBoundingRectangle();
        hitBoxRectangle.set(floatHitboxPositionX,floatHitboxPositionY,floatHitboxWidth,floatHitboxHeight);
        spritePunk.setPosition(spriteBoat.getX(), spriteBoat.getY());
        spriteLady.setPosition(spriteBoat.getX(),spriteBoat.getY());
        nessiePosition = Nessie.position;
        spriteFlash.setPosition(spriteBoat.getX(), spriteBoat.getY());
        switch(intStartSide)
        {
            case 0:
                spriteBoat.translateY(-floatSpeed * Gdx.graphics.getDeltaTime());
                spritePunk.setPosition(spriteBoat.getX(), spriteBoat.getY() + 35);
                spriteLady.setPosition(spriteBoat.getX()+15, spriteBoat.getY()+75);
                break;
            case 1:
                spriteBoat.translateX(-floatSpeed * Gdx.graphics.getDeltaTime());
                spritePunk.setPosition(spriteBoat.getX() - 25, spriteBoat.getY() + 50);
                spriteLady.setPosition(spriteBoat.getX()+40, spriteBoat.getY()+45);
                break;
            case 2:
                spriteBoat.translateY(floatSpeed * Gdx.graphics.getDeltaTime());
                spriteLady.setPosition(spriteBoat.getX()+15, spriteBoat.getY()+20);
                break;
            case 3:
                spriteBoat.translateX(floatSpeed * Gdx.graphics.getDeltaTime());
                spritePunk.setPosition(spriteBoat.getX() + 35, spriteBoat.getY() + 50);
                spriteLady.setPosition(spriteBoat.getX(), spriteBoat.getY()+45);
                break;
        }

    }

    public void draw(Batch batch)
    {
        spriteBoat.draw(batch);
        spriteLady.draw(batch);
        spritePunk.draw(batch);
        if(isHit) {
            spriteFlash.draw(batch);
            System.out.println("Is HIT!");
            isHit = false;
        }
    }

    public  void drawShapeRenderer(ShapeRenderer shapeRenderer)
    {
        floatHitboxPositionX = boatBoundingRectangle.x + floatHitboxPositionOffsetX;
        floatHitboxPositionY = boatBoundingRectangle.y + floatHitboxPositionOffsetY;
        floatHitboxHeight = spriteBoat.getWidth() - floatHitboxSizeOffsetX;
        floatHitboxWidth = spriteBoat.getWidth() - floatHitboxSizeOffsetY;
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(floatHitboxPositionX, floatHitboxPositionY, boatBoundingRectangle.x, boatBoundingRectangle.y, floatHitboxHeight, floatHitboxWidth, 1f, 1f, 0f);

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

    public void isHit()
    {
        isHit = true;
        intHealth --;
        if(intHealth == 0) {
            Score.addToScore();
            floatSpeed = floatSpeed * 5f;
        }
    }


}
