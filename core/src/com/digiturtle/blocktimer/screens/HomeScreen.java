package com.digiturtle.blocktimer.screens;

import java.util.function.Consumer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.digiturtle.blocktimer.EventType;
import com.digiturtle.blocktimer.ScrollableList;
import com.digiturtle.blocktimer.SharedData;
import com.digiturtle.blocktimer.Theme;
import com.digiturtle.blocktimer.Timer;

public class HomeScreen extends BaseScreen {

	private SharedData sharedData;
	
	private Button createTimer;
	
	private ScrollableList<Button, Timer> timerList;
	
	@Override
	public Theme getTheme() {
		return Theme.DEFAULT;
	}
	
	public HomeScreen(Consumer<Class<? extends BaseScreen>> changeScreen) {
		super(changeScreen);
	}
	
	public void processEvent(EventType event, String message) {
		switch (event) {
		case CREATE_TIMER:
			System.out.println("Creating timer... " + message);
			toScreen(CreateTimerScreen.class);
			break;
		case SELECT_ITEM:
			System.out.println("Selecting item... " + message);
			String[] data = message.split("_");
			sharedData.setSelectedTimer(Integer.parseInt(data[1]));
			sharedData.setTimerName(sharedData.getTimers().get(sharedData.getSelectedTimer()).getName());
			toScreen(EditTimerScreen.class);
			break;
		default:
			break;
		}
	}
	
	public Button createButtonForTimer(Timer timer) {
		return createButton(timer.getName(), EventType.SELECT_ITEM, timer.getInternalId(), 
					getTheme().DEFAULT_FONT, getTheme().BTN_DEFAULT, null, 10, this::processEvent);
	}
	
	public void init() {
		createTimer = createButton("CREATE", EventType.CREATE_TIMER, "CreateTimer", getTheme().LARGE_FONT, getTheme().BTN_SUCCESS, new Rectangle(0f, 0f, 1f, .1f), 10, this::processEvent);
		timerList = createScrollableList(Button.class, Timer.class, new Rectangle(0f, 0.1f, 1f, 0.8f), 10, new Rectangle(0f, 0f, 1f, .15f), this::createButtonForTimer);
		
		getStage().addActor(createTimer);
		getStage().addActor(timerList.getScrollPane());
	}

	@Override
	public void draw() {

	}

	@Override
	public void setup(SharedData sharedData) {
		this.sharedData = sharedData;
		timerList.getData().clear();
		for (int i = 0; i < sharedData.getTimers().size(); i++) {
			timerList.getData().add(sharedData.getTimers().get(i).setInternalId("Timer_" + i));
		}
		timerList.refresh();
	}

	@Override
	public void cleanup(SharedData sharedData) {
		
	}

}
