package com.huang.java.factory;

public class FactoryMain {

	public static void main(String[] args) {
		VehicleFactory factory = new BroomFactory();  
		Moveable moveable = factory.create();  
		moveable.run();
		
		factory = new AstonFactory();
		moveable = factory.create();
		moveable.run();
		
		AbstractFactory factory1 = new Factory1();  
        Flyable flyable = factory1.createFlyable();  
        flyable.fly(1589);
        
        Moveable moveable1 = factory1.createMoveable(Broom.class);
        moveable1.run();
        
        

	}

}
