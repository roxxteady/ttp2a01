package a01;

import jgame.JGColor;
import jgame.JGObject;
import jgame.JGPoint;
import jgame.platform.JGEngine;
import a01.Car.CarSpawnException;

/** Tutorial example 4: tiles and collision.  Like example 3, only the
 * pac-mans now collide with each other and with tiles.  This example
 * illustrates the use of collision IDs (aka cids or colids).  Both tiles and
 * objects have collision IDs, which can be used to check different kinds of
 * collision between different objects/tiles.
 */
public class Controller extends JGEngine {
		
//	private MyObject[] myo;
	
	private static final long serialVersionUID = 1L;
//	public MyObject player;
	private World world;

	/** Application constructor. */
	public Controller(JGPoint size) {
		world = new World();
		initEngine(size.x,size.y); 		
	}

	/** Applet constructor. */
	public Controller() {
		initEngineApplet();
	}

	public void initCanvas() {
		// we set the background colour to same colour as the splash background
		setCanvasSettings(world.WIDTH,world.HEIGHT,world.ROXELSIZE,world.ROXELSIZE,JGColor.black,new JGColor(255,255,255),null);
	}

	public void initGame() {
		setFrameRate(20,2);
		defineMedia("example3.tbl");
	
		// create some tiles. "#" is our marble tile, "." is an empty space.
		
		generateCar(Direction.north2South, 32, 6, 0);
		generateCar(Direction.north2South, 32, 13, 0);
		generateCar(Direction.north2South, 32, 20, 0);
		generateCar(Direction.north2South, 32, 6, 13);
		generateCar(Direction.north2South, 32, 13, 13);
		generateCar(Direction.north2South, 32, 20, 13);
		generateCar(Direction.west2East, 32, 0, 6);
		generateCar(Direction.west2East, 32, 0, 13);
		generateCar(Direction.west2East, 32, 0, 20);
		generateCar(Direction.west2East, 32, 13, 6);
		generateCar(Direction.west2East, 32, 13, 13);
		generateCar(Direction.west2East, 32, 13, 20);	
		
		for(int tx = 0; tx < (world.WIDTH+1)/7; ++tx)
		{
			for(int ty = 0; ty < (world.HEIGHT+1)/7; ++ty)
			{	
				setTiles(
					tx*7, // tile x index
					ty*7, // tile y index
					new String[] 
					{
						"######",
						"#....#",
						"#....#",
						"#....#",
						"#....#",
						"######",
					}
				);
			}
		}
		
		// define the off-playfield tiles
		setTileSettings(
			"#", // tile that is found out of the playfield bounds
			2,   // tile cid found out of playfield bounds
			0    // which cids to preserve when setting a tile (not used here).
		);
	}
	
	public void generateCar(Direction dir, double speed, int x, int y) {
		try {
			Car car = new Car(dir, speed, x, y);
			GSSal.setCar(car);
			new JCar(car);
			CarThread tcar = new CarThread(car, this.world);
			Thread t = new Thread(tcar);
			t.start();
		} catch (CarSpawnException e) {
			e.printStackTrace();
		}
	}
	
	public void doFrame() {
		moveObjects(null,0);
	}

	
	class JCar extends JGObject {
		
		private Car car;

		JCar (Car car) {
			super("car",true, // name
				car.getPosition().getX() * Controller.this.world.ROXELSIZE,  // xpos
				car.getPosition().getY() * Controller.this.world.ROXELSIZE, // ypos
				1, //  collision ID
				"myanim_l"// name of sprite or animation to use.
			);
			this.car = car;
		}
		
		public void move() {
			this.x = this.car.getPosition().getX() * Controller.this.world.ROXELSIZE;
			this.y = this.car.getPosition().getY() * Controller.this.world.ROXELSIZE;
		}
		
		public void hit_bg(int tilecid) {
		}

		/** Handle collision with other objects. Called by checkCollision. */
		public void hit(JGObject obj) {
		}

	} /* end class MyObject */
	
	
}
