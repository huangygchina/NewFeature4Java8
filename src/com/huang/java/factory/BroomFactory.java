package com.huang.java.factory;

public class BroomFactory extends VehicleFactory {
	
	@Override 
    public Moveable create() {  
        return new Broom();  
    }

}
