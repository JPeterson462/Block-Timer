package com.digiturtle.blocktimer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;

public abstract class BaseScreen implements Screen {
	
	private int width = 0, height = 0;
	
	private ShapeRenderer shapeRenderer;
	private BitmapFont font;
	private SpriteBatch spriteBatch;
	
	public abstract Theme getTheme();
	
	public abstract void draw();
	
	public BaseScreen() {
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		spriteBatch = new SpriteBatch();

	}

	@Override
	public void show() {
		
	}
	
	public void fillRect(float x, float y, float w, float h, float[] fillColor) {
		shapeRenderer.setColor(fillColor[0], fillColor[1], fillColor[2], 1);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.rect(x * width, (1 - y) * height, (x + w) * width, -(y + h) * height);
		shapeRenderer.end();
	}
	
	public void text(float x, float y, String text, float[] textColor, int fontSize) {
		spriteBatch.begin();
		font.getData().setScale(fontSize);
		font.setColor(textColor[0], textColor[1], textColor[2], 1);
		font.draw(spriteBatch, text, x * width, (1 - y) * height - fontSize);
		spriteBatch.end();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(getTheme().BACKGROUND[0], getTheme().BACKGROUND[1], getTheme().BACKGROUND[2], 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		draw();
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
