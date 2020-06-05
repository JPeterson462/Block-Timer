package com.digiturtle.blocktimer.screens;

import java.util.function.Consumer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.digiturtle.blocktimer.EventType;
import com.digiturtle.blocktimer.SharedData;
import com.digiturtle.blocktimer.Theme;
import com.digiturtle.blocktimer.Timer;

public class EditTimerScreen extends BaseScreen {
	
	private SharedData sharedData;
	
	private Button save, cancel, delete;

	public EditTimerScreen(Consumer<Class<? extends BaseScreen>> changeScreen) {
		super(changeScreen);
	}

	@Override
	public Theme getTheme() {
		return Theme.DEFAULT;
	}

	@Override
	public void draw() {
		fillRect(0, 0.1f, 1, 0.05f, getTheme().SUBHEADER);
		GlyphLayout layout = new GlyphLayout();
		layout.setText(getTheme().DEFAULT_FONT, "Timer Name: " + sharedData.getTimerName());
		int pixelOffsetHeader = ((int) (0.05f * height - layout.height)) / 2;
		float offsetHeader = pixelOffsetHeader / (float) height;
		text(offsetHeader * 2, 0.1f + offsetHeader, "Timer Name: " + sharedData.getTimerName(), getTheme().DEFAULT_FONT, Color.WHITE);
	}

	public void processEvent(EventType type, String message) {
		if (type == EventType.CONFIRMATION) {
			if (message.contentEquals("Save")) {
				if (sharedData.getSelectedTimer() < 0) {
					Timer timer = new Timer(sharedData.getTimerName(), sharedData.getTimerName().replace(' ', '_'));
					sharedData.getTimers().add(timer);
				} else {
					System.out.println("Editing Timer...");
				}
				sharedData.setTimerName(null);
				toScreen(HomeScreen.class);
			}
			else if (message.contentEquals("Cancel")) {
				sharedData.setTimerName(null);
				toScreen(HomeScreen.class);
			}
			else if (message.contentEquals("Delete")) {
				if (sharedData.getSelectedTimer() >= 0) {
					sharedData.getTimers().remove(sharedData.getSelectedTimer());
					sharedData.setSelectedTimer(-1);
				}
				sharedData.setTimerName(null);
				toScreen(HomeScreen.class);
			}
		}
	}

	@Override
	public void init() {
		save = createButton("SAVE", EventType.CONFIRMATION, "Save", getTheme().LARGE_FONT, getTheme().BTN_SUCCESS, new Rectangle(.5f, 0f, .5f, .1f), 10, this::processEvent);
		cancel = createButton("CANCEL", EventType.CONFIRMATION, "Cancel", getTheme().LARGE_FONT, getTheme().BTN_DEFAULT, new Rectangle(0f, 0f, .5f, .1f), 10, this::processEvent);
		delete = createButton("DELETE", EventType.CONFIRMATION, "Delete", getTheme().DEFAULT_FONT, getTheme().BTN_DANGER, new Rectangle(0.7f, 0.8f, .3f, .05f), 10, this::processEvent);
		getStage().addActor(save);
		getStage().addActor(cancel);
		getStage().addActor(delete);
	}

	@Override
	public void setup(SharedData sharedData) {
		this.sharedData = sharedData;
	}

	@Override
	public void cleanup(SharedData sharedData) {
		// TODO Auto-generated method stub
		
	}

}
