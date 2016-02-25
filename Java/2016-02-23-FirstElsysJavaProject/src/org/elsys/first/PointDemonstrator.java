package org.elsys.first;

import org.elsys.first.point.Point;

public class PointDemonstrator {

	public static void main(String[] args) {
		Point p1 = new Point(1, 1);
		System.out.println(p1);
		
		int i = 5;
		Integer boxed_i = 5; // Boxed primitive
		boolean b = false;
		Boolean boxed_b = false;
		changePoint(p1, i);
		System.out.println(p1);
		System.out.println(i);
		boxed_i = changePoint(p1, boxed_i);
		System.out.println(p1);
		System.out.println(boxed_i);
		boxed_i.intValue();
	}
	
	public static Integer changePoint(Point p, Integer step) {
		System.out.println("Using boxed Integer...");
		p.setX(p.getX() + step);
		p.setY(p.getY() + step);
		step += 1;
		return null;
	}
	
	public static void changePoint(Point p, int step) {
		System.out.println("Using primitive int...");
		p.setX(p.getX() + step);
		p.setY(p.getY() + step);
		step += 1;
	}
}
