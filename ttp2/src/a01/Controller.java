package a01;

import java.awt.Point;

import a01.Car.CarMoveException;
import a01.Car.CarSpawnException;

import jgame.JGColor;
import jgame.JGObject;
import jgame.JGPoint;
import jgame.platform.JGEngine;

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
		world = GSSal.getWorld();
		if (world != null)
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
		
		
		for (int i = 0; i < this.world.HEIGHT; i++) {
			GSSal.setRoxel(6, i, Direction.north2South);
		}
		
		for (int i = 0; i < this.world.WIDTH; i++) {
			GSSal.setRoxel(i, 6, Direction.west2East);
		}

		new JCar(6,0,0,64);
		new JCar(6,10,0,32);
		new JCar(0,6,32,0);
		
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
	
	public void doFrame() {
		moveObjects(null,0);
		// check object-object collision
//		checkCollision(
//			1, // cids of objects that our objects should collide with
//			1  // cids of the objects whose hit() should be called
//		);
//		// check object-tile collision
//		checkBGCollision(
//			1+2, // collide with the marble and border tiles
//			1    // cids of our objects
//		);
	}

//	/** Our user-defined object, which now bounces off other object and tiles.*/
//	class MyObject extends JGObject {
//		
//		MyObject () {
//			this(0,0,0,0);
//		}
//		
//		MyObject (int x, int y, double xs, double ys) {
//			super("myobject",true, // name
//				x,  // xpos
//				y, // ypos
//				1, // collision ID
//				"myanim_l"// name of sprite or animation to use.
//			);
//			xspeed = xs;
//			yspeed = ys;
//		}
//		
//		
//
//		/** Update the object. This method is called by moveObjects. */
//		public void move() {
//			//if (xspeed < 0) setGraphic("myanim_r"); else setGraphic("myanim_l");
//			// We don't need to check for the borders of the screen, like
//			// we did in example 3.  Border collision is now handled by hit_bg.
//		}
//		
//		/** Handle collision with background. Called by checkBGCollision.
//		* Tilecid is the combined (ORed) CID of all tiles that this
//		* object collides with.  Note: there are two other variants
//		* of hit_bg available, namely one passing tilecid plus tile
//		* coordinates for each tile that the object collides with, and one
//		* passing the tile range that the object overlaps with at the moment
//		* of collision.  */
//		public void hit_bg(int tilecid) {
//			// Look around to see which direction is free.  If we find a free
//			// direction, move that way.
//			if (!and(checkBGCollision(-xspeed,yspeed),3)) {
////				System.out.println("x - x:" + x + " y:" + y);
//				//				xspeed = -xspeed;
//				x = 0;
//			} else if (!and(checkBGCollision(xspeed,-yspeed),3)) {
////				yspeed = -yspeed;
////				System.out.println("y - x:" + x + " y:" + y);
//				y = 0;
//			} else if (!and(checkBGCollision(xspeed,-yspeed),3)
//			&&         !and(checkBGCollision(-xspeed,-yspeed),3) ) {
//				xspeed = -xspeed;
//				yspeed = -yspeed;
//			}
//			// else do nothing. You might think this case never occurs
//			// (otherwise, why would the object have collided?), but it
//			// does occur because object-object collision might already
//			// have reversed the direction of this object.  This is the kind
//			// of stuff that makes object interaction difficult.
//		}
//
//		/** Handle collision with other objects. Called by checkCollision. */
//		public void hit(JGObject obj) {
//			// As a reaction to an object collision, we bounce in the
//			// direction we came from.  We only do this when the area in that
//			// direction seems clear of other objects, otherwise we might
//			// start oscillating back and forth.
//			// This collision problem is much more difficult than the tile
//			// collision problem, because there may be multiple simultaneous
//			// collisions, and the other objects are also moving at different
//			// speeds.
//			// We look ahead several steps in the opposite direction to see
//			// if any other object is there.
//			if (checkCollision(1,-3*xspeed,-3*yspeed)==0) {
//				// reverse direction
//				xspeed = 0;
//				yspeed = 0;
//			}
//			System.out.println(obj.getName() + " - " + this.getName());
//		}
//
//	} /* end class MyObject */
	
	
	
	
	
	
	
	
	class JCar extends JGObject {
		
		private Car car;
		CarThread rcar;
		Thread tcar;
		
		JCar () {
			this(0,0,0,0);
		}
		
		JCar (int x, int y, double xs, double ys) {
			super("car",true, // name
				x * 16,  // xpos
				y * 16, // ypos
				1, // collision ID
				"myanim_l"// name of sprite or animation to use.
			);
			try {
				this.car = new Car(Direction.north2South, ys, x, y);
			} catch (CarSpawnException e) {
				e.printStackTrace();
			}
			this.rcar = new CarThread(this.car);
			this.tcar = new Thread(this.rcar);
			this.tcar.start();
		}
		
		public void move() {
			double x = this.car.getPosition().getX() * 16;
			double y = this.car.getPosition().getY() * 16;
				
			this.x = x;
			this.y = y;
			
			if ((this.x / 16) >= world.WIDTH - 1) {
				try {
					this.car.move(0, (int) (y / 16));
					this.car.setSpeed(Math.round(Math.max(0.5, Math.random()) * 64));
				} catch (CarMoveException e) {
					e.printStackTrace();
				}
			}
			else if ((this.y / 16) >= world.HEIGHT - 1) {
				try {
					this.car.move((int) (x / 16), 0);
					this.car.setSpeed(Math.round(Math.max(0.5, Math.random()) * 64));
				} catch (CarMoveException e) {
					e.printStackTrace();
				}
				
//				this.rcar.terminate();
//				try {
//					this.tcar.join();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
			
		}
		
		public void hit_bg(int tilecid) {
		}

		/** Handle collision with other objects. Called by checkCollision. */
		public void hit(JGObject obj) {
		}

	} /* end class MyObject */
	
	
}
