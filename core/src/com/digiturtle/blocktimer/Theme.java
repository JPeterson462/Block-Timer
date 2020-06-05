package com.digiturtle.blocktimer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public enum Theme {

	DEFAULT("Block Timer",
			new float[] { 0.25f, 0.25f, 0.25f },
			new float[] { 0.35f, 0.35f, 0.35f },
			new float[] { 0.1f, 0.1f, 0.1f },
			new float[] { 0f, 0.72f, 0f },
			new float[] { 0.5f, 0.5f, 0.5f },
			new float[] { 0.847f, 0f, 0.1f },
			"Roboto-Regular.ttf", 
			16);
	
	public final float[] BACKGROUND;
	public final float[] HEADER;
	public final float[] SUBHEADER;
	public final Color BTN_SUCCESS;
	public final Color BTN_DEFAULT;
	public final Color BTN_DANGER;
	
	public final String TITLE;
	
	public final BitmapFont DEFAULT_FONT;
	public final BitmapFont LARGE_FONT;
	public final BitmapFont SMALL_FONT;
	
	Theme(final String title, 
			final float[] background, final float[] header, final float[] subheader,
			final float[] successBtn, final float[] defaultBtn, final float[] dangerBtn,
			final String font, final int defaultFontSize) {
		TITLE = title;
		BACKGROUND = background;
		HEADER = header;
		SUBHEADER = subheader;
		BTN_SUCCESS = new Color(successBtn[0], successBtn[1], successBtn[2], 1);
		BTN_DEFAULT = new Color(defaultBtn[0], defaultBtn[1], defaultBtn[2], 1);
		BTN_DANGER = new Color(dangerBtn[0], dangerBtn[1], dangerBtn[2], 1);
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(font));
		SMALL_FONT = generator.generateFont(new FreeTypeFontParameter() {
			{
				size = 12;
			}
		});
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
