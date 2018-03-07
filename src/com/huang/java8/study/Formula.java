/**
 * 
 */
package com.huang.java8.study;

/**
 * @author Hoperun
 *
 */
public interface Formula {
	double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
