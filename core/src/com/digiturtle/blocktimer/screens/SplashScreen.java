package com.digiturtle.blocktimer.screens;

import java.util.function.Consumer;

import com.digiturtle.blocktimer.SharedData;
import com.digiturtle.blocktimer.Theme;

public class SplashScreen extends BaseScreen {

	public SplashScreen(Consumer<Class<? extends BaseScreen>> changeScreen) {
		super(changeScreen);
	}

	@Override
	public Theme getTheme() {
		return Theme.DEFAULT;
	}

	@Override
	public void draw() {
		toScreen(HomeScreen.class);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void setup(SharedData sharedData) {
		
	}

	@Override
	public void cleanup(SharedData sharedData) {
		
	}

}
