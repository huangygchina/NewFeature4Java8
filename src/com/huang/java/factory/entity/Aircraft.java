package com.huang.java.factory.entity;

import com.huang.java.factory.Flyable;

public class Aircraft implements Flyable{

	@Override  
    public void fly(int height) {  
        System.out.println("����һ�ܿ��˻�����Ŀǰ�ķ��и߶�Ϊ��" + height + "ǧ�ס�");  
    } 
}
