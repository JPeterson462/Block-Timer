package com.digiturtle.blocktimer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class HomeScreen extends BaseScreen {

	private Button createTimer;
	
	@Override
	public Theme getTheme() {
		return Theme.DEFAULT;
	}
	
	public HomeScreen() {
		
	}
	
	public void init() {
		createTimer = createButton("CREATE", getTheme().LARGE_FONT, getTheme().BTN_SUCCESS, new Rectangle(0f, 0f, 1f, .1f), 10, (in) -> {
			System.out.println("CLICK");
		});
		getStage().addActor(createTimer);
	}

	@Override
	public void draw() {

	}

}
