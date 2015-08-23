package com.LD33.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class LD33 extends ApplicationAdapter {
	SpriteBatch batch;
	Nessie nessie;
	int numberBoats = 3;
    List<Boat> listBoat = new ArrayList<Boat>();
    Sprite green;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		nessie = new Nessie();
        green = new Sprite(new Texture("green.png"));

	}

	@Override
	public void render () {

        nessie.update();

        if(listBoat.size() < numberBoats)
            listBoat.add(new Boat(listBoat,batch));

        listBoat.removeIf(p -> p.checkBounds());

        for (Boat boat:listBoat)
                boat.update();

		Gdx.gl.glClearColor(10 / 255f, 105 / 255f, 148 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
		nessie.draw(batch);
        for (Boat boat:listBoat)
            boat.draw(batch);
        green.draw(batch);
        batch.end();

	}
}
