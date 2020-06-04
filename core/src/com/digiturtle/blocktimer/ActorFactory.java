package com.digiturtle.blocktimer;

@FunctionalInterface
public interface ActorFactory<I, O> {

	public O create(I input);
	
}
