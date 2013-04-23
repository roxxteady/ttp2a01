package a01;


public class TrafficLightThread implements Runnable {
	
	private boolean runnable;
	private int x;
	private int y;
	
	public TrafficLightThread(int x, int y) {
		this.runnable = true;
		this.x = x;
		this.y = y;
	}
	
	public void terminate() {
		this.runnable = false;
	}
	
	@Override
	public void run() {
		Direction dir = Direction.north2South; 
		
		while (this.runnable) {
			try {
				while(!GSSal.resetRoxel(this.x, this.y, true, dir)) {}
				dir = (dir == Direction.north2South) ? Direction.west2East : Direction.north2South;
				
				Thread.sleep((long) (Math.max(0.5, Math.random()) * 10000));
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
