package com.digiturtle.blocktimer;

import com.badlogic.gdx.graphics.Color;

public class HomeScreen extends BaseScreen {

	@Override
	public Theme getTheme() {
		return Theme.DEFAULT;
	}

	@Override
	public void draw() {
		fillRect(0, 0, .25f, .25f, new float[] {1, 0, 0});
		text(0.05f, 0.05f, "Test", 1, getTheme().DEFAULT_FONT, Color.YELLOW);
	}

}
