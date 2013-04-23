package a01;

import a01.Car.CarMoveException;


public class CarThread implements Runnable {

	private Car car;
	private World world;
	private boolean runnable;
	
	public CarThread(Car car, World world) {
		this.car = car;
		this.world = world;
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
					if (y >= this.world.HEIGHT - 1) {
						y = -1;
						this.car.setSpeed(Math.max(0.5, Math.random())*64);
					}
					this.car.move(x, y + 1);
				} else {
					if (x >= this.world.WIDTH - 1) {
						x = -1;
						this.car.setSpeed(Math.max(0.5, Math.random())*64);
					}
					this.car.move(x + 1, y);
				}
				Thread.sleep((long) ((1/this.car.getSpeed()) * 10000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			} catch (CarMoveException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
	}
}
