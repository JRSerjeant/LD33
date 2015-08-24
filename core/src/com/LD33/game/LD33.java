package com.LD33.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class LD33 extends ApplicationAdapter {
	SpriteBatch batch;
    ShapeRenderer shapeRenderer;
	Nessie nessie;
    Sprite green;
    BitmapFont font;
    public Score score;
    Texture loch;

	int numberBoats = 6;
    public List<Boat> listBoat = new ArrayList<>();
	
	@Override
	public void create () {
        score = new Score();
		batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        font = new BitmapFont(Gdx.files.internal("robo.fnt"));
        loch = new Texture("loch.png");

		nessie = new Nessie();
        green = new Sprite(new Texture("green.png"));

	}

	@Override
	public void render () {
        nessie.update(listBoat);

        if(listBoat.size() < numberBoats)
            listBoat.add(new Boat(listBoat,batch));

        listBoat.removeIf(p -> p.checkBounds());

        for (Boat boat:listBoat)
                boat.update();

		Gdx.gl.glClearColor(10 / 255f, 105 / 255f, 148 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(loch, 0f, 0f);
		nessie.draw(batch);
        for (Boat boat:listBoat)
            boat.draw(batch);

        font.getData().setScale(2f, 2f);
        font.draw(batch, Integer.toString(score.score), 10f, 40f);

        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (Boat boat:listBoat)
            boat.drawShapeRenderer(shapeRenderer);
        nessie.drawShapeRenderer(shapeRenderer);
        shapeRenderer.end();

    }

}
