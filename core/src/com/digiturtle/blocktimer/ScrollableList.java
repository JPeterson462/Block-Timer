package com.digiturtle.blocktimer;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ScrollableList<T extends Actor, D> {

	private ArrayList<T> items;
	
	private ArrayList<D> data;
	
	private ScrollPane scrollPane;
	
	private Rectangle itemBounds;
	
	private int padding;
	
	private ActorFactory<D, T> factory;
	
	public ScrollableList(ScrollPane scrollPane, Rectangle itemBounds, int padding, ActorFactory<D, T> factory) {
		items = new ArrayList<T>();
		data = new ArrayList<D>();
		this.scrollPane = scrollPane;
		this.itemBounds = itemBounds;
		this.padding = padding;
		this.factory = factory;
	}
	
	public ArrayList<D> getData() {
		return data;
	}
	
	public ScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public void refresh() {
		items.clear();
		for (D datum : data) {
			items.add(factory.create(datum));
		}
		Table table = (Table) scrollPane.getActor();
		table.setBounds(scrollPane.getX(), scrollPane.getY(), scrollPane.getWidth(), scrollPane.getHeight());
		table.clearChildren();
		for (T item : items) {
			item.setBounds(itemBounds.x, itemBounds.y, itemBounds.width, itemBounds.height - padding * 2);
			table.add((Actor) item).width(scrollPane.getWidth()).height(100).pad(0, 0, padding, 0);
			table.row();
		}
	}

}
