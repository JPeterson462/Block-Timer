package com.digiturtle.blocktimer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlockTimer extends Game {
	
	private SpriteBatch batch;
	
	private BaseScreen[] screens;
	private int HOME_SCREEN = 0;
	private int NUM_SCREENS = 1;
	
	private int activeScreen = -1;
	
	public void changeScreen(int nextScreen) {
		activeScreen = nextScreen;
		setScreen(screens[activeScreen]);
		Gdx.input.setInputProcessor(screens[activeScreen].getStage());
	}
	
	@Override
	public void create () {
		screens = new BaseScreen[NUM_SCREENS];
		screens[HOME_SCREEN] = new HomeScreen();
		batch = new SpriteBatch();
		changeScreen(HOME_SCREEN);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
