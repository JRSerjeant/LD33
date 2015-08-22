package com.LD33.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LD33 extends ApplicationAdapter {
	SpriteBatch batch;
	Nessie nessie;
	int numberBoats = 1;
    Boat[] arrayBoats = new Boat[numberBoats];
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		nessie = new Nessie();
        for(int i = 0; i < numberBoats;i++)
            arrayBoats[i] = new Boat();

	}

	@Override
	public void render () {
		nessie.update();

        if(arrayBoats.length < numberBoats)
            

        for (Boat boat:arrayBoats)
                boat.update();

		Gdx.gl.glClearColor(10 / 255f, 105 / 255f, 148 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		nessie.draw(batch);

        for (Boat boat:arrayBoats)
            boat.draw(batch);
		batch.end();
	}
}
