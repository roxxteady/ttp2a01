package a01;

import java.awt.Point;

public class Car {

	private Direction dir;
	private double speed;
	private int srcx;
	private int srcy;

	public Car(Direction dir, double speed, int x, int y) throws CarSpawnException {
		this.dir = dir;
		this.speed = speed;
		this.srcx = x;
		this.srcy = y;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public void move(int destx, int desty) throws CarMoveException {
//		destx = (dir == Direction.west2East) ? srcx + 1 : srcx;
//		desty = (dir == Direction.north2South) ? srcy + 1 : srcy;
		if (!GSSal.moveCar(srcx, srcy, destx, desty)) {
			throw new CarMoveException();
		}
		srcx = destx;
		srcy = desty;
//		System.out.println(srcx + "-" + srcy + "-" + destx + "-" + desty + "-" + speed);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Point getPosition() {
		return new Point(this.srcx, this.srcy);
	}
	
	class CarSpawnException extends Exception {

		private static final long serialVersionUID = 1L;
		
		@Override
		public void printStackTrace() {
			super.printStackTrace();
			System.err.println("Can't spawn the Car!");
		}
		
	}
	
	class CarMoveException extends Exception {

		private static final long serialVersionUID = 1L;
		
		@Override
		public void printStackTrace() {
			super.printStackTrace();
			System.err.println("Can't move the Car!");
		}
		
	}
	
}
