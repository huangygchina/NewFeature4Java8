package com.huang.java.factory;

import com.huang.java.factory.entity.Aircraft;
import com.huang.java.factory.entity.Aston;

public class Factory1 extends AbstractFactory {

	@Override
	public Flyable createFlyable() {
		// TODO Auto-generated method stub
		return new Aircraft();
	}

	@Override
	public Moveable createMoveable(Class<?> clazz) {
		if(clazz.getName().equals(Aston.class)){
			return new Aston();
		}else {
			return new Broom();
		}
		
	}

}
