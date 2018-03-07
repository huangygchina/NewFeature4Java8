package com.huang.java.factory.entity;

import com.huang.java.factory.Flyable;

public class Aircraft implements Flyable{

	@Override  
    public void fly(int height) {  
        System.out.println("我是一架客运机，我目前的飞行高度为：" + height + "千米。");  
    } 
}
