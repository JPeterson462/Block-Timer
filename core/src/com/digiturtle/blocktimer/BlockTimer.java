package com.digiturtle.blocktimer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.digiturtle.blocktimer.screens.BaseScreen;
import com.digiturtle.blocktimer.screens.CreateTimerScreen;
import com.digiturtle.blocktimer.screens.EditTimerScreen;
import com.digiturtle.blocktimer.screens.HomeScreen;
import com.digiturtle.blocktimer.screens.SplashScreen;

public class BlockTimer extends Game {
	
	private SpriteBatch batch;
	
	private BaseScreen[] screens;
	private int HOME_SCREEN = 0;
	private int CREATE_TIMER_SCREEN = 1;
	private int SPLASH_SCREEN = 2;
	private int EDIT_TIMER_SCREEN = 3;
	private int NUM_SCREENS = 4;
	
	private int activeScreen = -1;
	
	private SharedData sharedData;
	
	public void changeScreen(int nextScreen) {
		if (activeScreen >= 0) {
			screens[activeScreen].cleanup(sharedData);
		}
		activeScreen = nextScreen;
		screens[activeScreen].queue();
		screens[activeScreen].setup(sharedData);
		setScreen(screens[activeScreen]);
		Gdx.input.setInputProcessor(screens[activeScreen].getStage());
	}
	
	public void changeScreenByType(Class<? extends BaseScreen> type) {
		for (int i = 0; i < NUM_SCREENS; i++) {
			if (screens[i].getClass().isAssignableFrom(type)) {
				if (activeScreen >= 0) {
					screens[activeScreen].cleanup(sharedData);
				}
				screens[i].queue();
				screens[i].setup(sharedData);
				setScreen(screens[i]);
				Gdx.input.setInputProcessor(screens[i].getStage());
				activeScreen = i;
				return;
			}
		}
	}
	
	@Override
	public void create () {
		sharedData = new SharedData();
		System.out.println("Loading...");
		screens = new BaseScreen[NUM_SCREENS];
		screens[HOME_SCREEN] = new HomeScreen(this::changeScreenByType);
		screens[CREATE_TIMER_SCREEN] = new CreateTimerScreen(this::changeScreenByType);
		screens[SPLASH_SCREEN] = new SplashScreen(this::changeScreenByType);
		screens[EDIT_TIMER_SCREEN] = new EditTimerScreen(this::changeScreenByType);
		batch = new SpriteBatch();
		changeScreen(SPLASH_SCREEN);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		System.out.println("Saving...");
	}
}
