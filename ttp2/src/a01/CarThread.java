package a01;

import a01.Car.CarMoveException;

public class CarThread implements Runnable {

	private Car car;
	private boolean runnable;
	
	public CarThread(Car car) {
		this.car = car;
		this.runnable = true;
	}
	
	public void terminate() {
		this.runnable = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int x;
		int y;
		
		
		while(this.runnable) {
			x = this.car.getPosition().x;
			y = this.car.getPosition().y;
			
			try {
				if (this.car.getDir() == Direction.north2South) {
					this.car.move(x, y + 1);
				} else {
					this.car.move(x + 1, y);
				}
				Thread.sleep((long) ((1/this.car.getSpeed()) * 10000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CarMoveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
