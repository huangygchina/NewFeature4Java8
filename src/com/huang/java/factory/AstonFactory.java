package com.huang.java.factory;

import com.huang.java.factory.entity.Aston;

public class AstonFactory extends VehicleFactory {

	@Override
	public Moveable create() {
		return new Aston();
	}

}
