package com.digiturtle.blocktimer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public enum Theme {

	DEFAULT(new float[] { 0.2f, 0.2f, 0.2f }, 
			"Roboto-Regular.ttf", 
			16);
	
	public final float[] BACKGROUND;
	
	public final BitmapFont DEFAULT_FONT;
	
	Theme(final float[] background, final String font, final int defaultFontSize) {
		BACKGROUND = background;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(font));
		DEFAULT_FONT = generator.generateFont(new FreeTypeFontParameter() {
			{
				size = defaultFontSize;
			}
		});
		generator.dispose();
	}
	
}
