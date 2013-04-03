package a01;

import jgame.JGPoint;


public class Main {

	public static void main(String [] args) {
		GSSal.setWorld(new World());
		new Controller(new JGPoint(800,800));
	}
		
}
