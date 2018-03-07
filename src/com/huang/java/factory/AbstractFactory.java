package com.huang.java.factory;

public abstract class AbstractFactory {

	public abstract Flyable createFlyable();

	public abstract Moveable createMoveable(Class<?> clazz);

}
