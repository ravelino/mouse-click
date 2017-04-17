package br.com.avelino.core;

import java.awt.MouseInfo;
import java.awt.Point;


public class MousePosition {
	
	private MousePosition () {}
	
	private static MousePosition mousePosition;
	
	public static MousePosition getEscutaMouse() {
		if (mousePosition == null) {
			mousePosition = new MousePosition();
		}
		
		return mousePosition;
	}
	
	public int getPositionX() {
		return (int) getPoint().getX();
	}
	
	public int getPositionY() {
		return (int) getPoint().getY();
	}
	
	private Point getPoint(){
		return MouseInfo.getPointerInfo().getLocation();
	}
}
