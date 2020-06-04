package com.digiturtle.blocktimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

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
	
	private Texture colorToTexture(Color color) {
		Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGBA8888);
		bgPixmap.setColor(color);
		bgPixmap.fill();
		return new Texture(bgPixmap);
	}
	
	private Drawable textureToDrawable(Texture texture) {
		return new TextureRegionDrawable(new TextureRegion(texture));
	}
	
	public Button createButton(String label, EventType event, String name, BitmapFont labelFont, Color backgroundColor, Rectangle bounds, int margin, BiConsumer<EventType, String> onClick) {
		Button button = new TextButton(label, new TextButton.TextButtonStyle() {
			{
				font = labelFont;
				up = textureToDrawable(colorToTexture(backgroundColor));
			}
		});
		if (bounds != null) {
			button.setBounds(bounds.x * (float) width + margin / 2, bounds.y * (float) height + margin / 2, bounds.width * (float) width - margin, bounds.height * (float) height - margin);
		}
		button.addListener(new ClickListener() {
			@Override
		    public void clicked(InputEvent ie, float x, float y) {
				onClick.accept(event, name);
		    };
		});
		return button;
	}
	
	public <T extends Actor, D> ScrollableList<T, D> createScrollableList(Class<T> type, Class<D> data, Rectangle regionBounds, int padding, Rectangle itemBounds, ActorFactory<D, T> factory) {
		regionBounds.x *= width;
		regionBounds.width *= width;
		regionBounds.y *= height;
		regionBounds.height *= height;
		itemBounds.x *= width;
		itemBounds.width *= width;
		itemBounds.y *= height;
		itemBounds.height *= height;
		ScrollPaneStyle paneStyle = new ScrollPaneStyle();//TODO
	    paneStyle.background = textureToDrawable(colorToTexture(new Color(0, 0, 0, 0)));
	    paneStyle.vScrollKnob = textureToDrawable(colorToTexture(Color.WHITE));
	    paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
	    Table container = new Table();
	    Table table = new Table();
	    ScrollPane pane = new ScrollPane(table, paneStyle);
	    container.setBounds(regionBounds.x, regionBounds.y, regionBounds.width, regionBounds.height);
	    table.setBounds(regionBounds.x + padding / 2, regionBounds.y + padding / 2, regionBounds.width - padding, regionBounds.height - padding);
	    pane.setBounds(regionBounds.x + padding / 2, regionBounds.y + padding / 2, regionBounds.width - padding, regionBounds.height - padding);
	    container.add(pane).width(regionBounds.width);
	    table.align(Align.top);
	    return new ScrollableList<T, D>(pane, itemBounds, padding, factory);
	}

}
