package com.digiturtle.blocktimer;

public class HomeScreen extends BaseScreen {

	@Override
	public Theme getTheme() {
		return Theme.DEFAULT;
	}

	@Override
	public void draw() {
		fillRect(0, 0, .25f, .25f, new float[] {1, 0, 0});
		text(0.05f, 0.05f, "Test", new float[] {0,0,1}, 1);
	}

}
