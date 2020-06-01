package com.digiturtle.blocktimer;

public enum Theme {

	DEFAULT(new float[] { 0.2f, 0.2f, 0.2f });
	
	public final float[] BACKGROUND;
	
	Theme(float[] background) {
		BACKGROUND = background;
	}
	
}
