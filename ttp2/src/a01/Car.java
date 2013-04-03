package a01;

import com.gigaspaces.admin.cli.GS;

public class Car {

	private Direction dir;
	private double speed;
	private int x;
	private int y;

	public Car(Direction dir, double speed, int x, int y) {
		this.dir = dir;
		this.speed = speed;
		spawn(x, y);
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public void spawn(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move() {
		int tx = (dir == Direction.west2East) ? x + 1 : x;
		int ty = (dir == Direction.north2South) ? y + 1 : y;
		GSSal.moveCar(x, y, tx, ty);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
