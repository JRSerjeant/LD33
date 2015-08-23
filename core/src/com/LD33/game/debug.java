package com.LD33.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 23/08/2015.
 */
public class debug {
    public ArrayList<debug> theList;
    static BitmapFont font;
    static String messgae;
    static Vector2 position;
    static SpriteBatch batch;

    public debug(SpriteBatch b, String m, Vector2 p)
    {
        messgae = m;
        position = p;
        batch = b;
        font = new BitmapFont(Gdx.files.internal("FifteenNarrow-15.bdf"));
        theList.add(this);
    }

}

