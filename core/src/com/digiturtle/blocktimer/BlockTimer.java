package com.digiturtle.blocktimer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.digiturtle.blocktimer.screens.CreateTimerScreen;
import com.digiturtle.blocktimer.screens.HomeScreen;

public class BlockTimer extends Game {
	
	private SpriteBatch batch;
	
	private BaseScreen[] screens;
	private int HOME_SCREEN = 0;
	private int CREATE_TIMER_SCREEN = 1;
	private int NUM_SCREENS = 2;
	
	private int activeScreen = -1;
	
	public void changeScreen(int nextScreen) {
		activeScreen = nextScreen;
		setScreen(screens[activeScreen]);
		Gdx.input.setInputProcessor(screens[activeScreen].getStage());
	}
	
	public void changeScreenByType(Class<? extends BaseScreen> type) {
		for (int i = 0; i < NUM_SCREENS; i++) {
			if (screens[i].getClass().isAssignableFrom(type)) {
				setScreen(screens[i]);
				return;
			}
		}
	}
	
	@Override
	public void create () {
		screens = new BaseScreen[NUM_SCREENS];
		screens[HOME_SCREEN] = new HomeScreen(this::changeScreenByType);
		screens[CREATE_TIMER_SCREEN] = new CreateTimerScreen(this::changeScreenByType);
		batch = new SpriteBatch();
		changeScreen(HOME_SCREEN);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
