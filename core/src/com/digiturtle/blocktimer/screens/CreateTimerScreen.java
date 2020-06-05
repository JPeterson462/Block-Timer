package com.digiturtle.blocktimer.screens;

import java.util.function.Consumer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.digiturtle.blocktimer.EventType;
import com.digiturtle.blocktimer.SharedData;
import com.digiturtle.blocktimer.Theme;

public class CreateTimerScreen extends BaseScreen {
	
	private Button create, cancel;
	
	private TextField name;
	
	private SharedData sharedData;

	public CreateTimerScreen(Consumer<Class<? extends BaseScreen>> changeScreen) {
		super(changeScreen);
	}

	@Override
	public Theme getTheme() {
		return Theme.DEFAULT;
	}

	@Override
	public void draw() {
		
	}
	
	public void processEvent(EventType type, String message) {
		if (type == EventType.CONFIRMATION) {
			if (message.contentEquals("Create")) {
				sharedData.setTimerName(name.getText());
				toScreen(EditTimerScreen.class);
			}
			else if (message.contentEquals("Cancel")) {
				sharedData.setTimerName(null);
				toScreen(HomeScreen.class);
			}
		}
	}

	@Override
	public void init() {
		create = createButton("CREATE", EventType.CONFIRMATION, "Create", getTheme().LARGE_FONT, getTheme().BTN_SUCCESS, new Rectangle(.5f, 0f, .5f, .1f), 10, this::processEvent);
		cancel = createButton("CANCEL", EventType.CONFIRMATION, "Cancel", getTheme().LARGE_FONT, getTheme().BTN_DANGER, new Rectangle(0f, 0f, .5f, .1f), 10, this::processEvent);
		name = createTextField(new Rectangle(0.05f, 0.5f, 0.9f, 0.1f), 10, getTheme().DEFAULT_FONT, Color.WHITE);
		getStage().addActor(create);
		getStage().addActor(cancel);
		getStage().addActor(name);
	}

	@Override
	public void setup(SharedData sharedData) {
		this.sharedData = sharedData;
	}

	@Override
	public void cleanup(SharedData sharedData) {
		
	}

}
