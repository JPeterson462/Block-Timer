package com.digiturtle.blocktimer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public enum Theme {

	DEFAULT("Block Timer",
			new float[] { 0.25f, 0.25f, 0.25f },
			new float[] { 0.1f, 0.1f, 0.1f },
			new float[] { 0f, 0.72f, 0f },
			new float[] { 0.5f, 0.5f, 0.5f },
			"Roboto-Regular.ttf", 
			16);
	
	public final float[] BACKGROUND;
	public final float[] HEADER;
	public final Color BTN_SUCCESS;
	public final Color BTN_DEFAULT;
	
	public final String TITLE;
	
	public final BitmapFont DEFAULT_FONT;
	public final BitmapFont LARGE_FONT;
	
	Theme(final String title, 
			final float[] background, final float[] header,
			final float[] successBtn, final float[] defaultBtn,
			final String font, final int defaultFontSize) {
		TITLE = title;
		BACKGROUND = background;
		HEADER = header;
		BTN_SUCCESS = new Color(successBtn[0], successBtn[1], successBtn[2], 1);
		BTN_DEFAULT = new Color(defaultBtn[0], defaultBtn[1], defaultBtn[2], 1);
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(font));
		DEFAULT_FONT = generator.generateFont(new FreeTypeFontParameter() {
			{
				size = defaultFontSize;
			}
		});
		LARGE_FONT = generator.generateFont(new FreeTypeFontParameter() {
			{
				size = 32;
			}
		});
		generator.dispose();
	}
	
}
