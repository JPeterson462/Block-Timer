package com.digiturtle.blocktimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public abstract class BaseScreen implements Screen {
	
	int width = 0;

	int height = 0;
	
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;
	
	private Stage stage;
	
	private boolean inited = false;
	
	private HashMap<Rectangle, String> clickToEventMapping = new HashMap<>();
	private ArrayList<Consumer<String>> eventListeners = new ArrayList<>();
	
	public abstract Theme getTheme();
	
	public abstract void draw();
	
	public abstract void init();
	
	public BaseScreen() {
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		stage = new Stage();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void processClick(int x, int y) {
		float percentX = (float) x / (float) width;
		float percentY = (float) y / (float) height;
		for (Rectangle region : clickToEventMapping.keySet()) {
			if (region.contains(percentX, percentY)) {
				for (Consumer<String> listener : eventListeners) {
					listener.accept(clickToEventMapping.get(region));
				}
			}
		}
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
	
	public void text(float x, float y, String text, BitmapFont font, Color color) {
		spriteBatch.begin();
		font.setColor(color);
		font.draw(spriteBatch, text, x * width, (1 - y) * height);
		spriteBatch.end();
	}

	@Override
	public void render(float delta) {
		if (!inited) {
			init();
			inited = true;
		}
		Gdx.gl.glClearColor(getTheme().BACKGROUND[0], getTheme().BACKGROUND[1], getTheme().BACKGROUND[2], 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		fillRect(0, 0, 1, 0.1f, getTheme().HEADER);
		GlyphLayout layout = new GlyphLayout();
		layout.setText(getTheme().LARGE_FONT, getTheme().TITLE);
		int pixelOffsetHeader = ((int) (0.1f * height - layout.height)) / 2;
		float offsetHeader = pixelOffsetHeader / (float) height;
		text(offsetHeader, offsetHeader, getTheme().TITLE, getTheme().LARGE_FONT, Color.WHITE);
		stage.act(delta);
		stage.draw();
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
	
	// Utility methods for UI
	
	public Button createButton(String label, BitmapFont labelFont, Color backgroundColor, Rectangle bounds, int margin, Consumer<Void> onClick) {
		Button button = new TextButton(label, new TextButton.TextButtonStyle() {
			{
				font = labelFont;
				Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
				bgPixmap.setColor(backgroundColor);
				bgPixmap.fill();
				up = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
			}
		});
		button.setBounds(bounds.x * (float) width + margin / 2, bounds.y * (float) height + margin / 2, bounds.width * (float) width - margin, bounds.height * (float) height - margin);
		button.addListener(new ClickListener() {
			@Override
		    public void clicked(InputEvent event, float x, float y) {
		       onClick.accept(null);
		    };
		});
		return button;
	}

}
