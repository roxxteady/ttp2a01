package a01;

import org.openspaces.core.GigaSpace;
import com.j_spaces.core.client.SQLQuery;

public class GSSal {
	private static GigaSpace gt;

	static {
		initGigaspace();
		System.out.println("Gigaspace SAL erstellt.");
	}
	
	private GSSal(){
		
	}
		
	private static void initGigaspace() {
		gt = DataGridConnectionUtility.getSpace("MySpace",1,1);
	}

	public static World getWorld() {
		return gt.readById(World.class, 1);
	}

	public static void setWorld(World world){
		gt.write(world);
	}
	
	public static boolean moveCar(int x, int y, int tx, int ty){
	
		if(gt.take(new SQLQuery<Roxel>(Roxel.class, "x=" + tx + " and y=" + ty + " and free=true")) == null) {
			return false;
		}
		
		GSSal.setRoxel(x, y, Direction.north2South);
		
//		if(gt.take(new SQLQuery<Roxel>(Roxel.class, "x=" + x + " and y=" + y)) == null) {
//			return false;
//		}
		return true;
	}
	
	public static void setRoxel(int x, int y, Direction dir) {
		gt.write(new Roxel(x, y, dir, true));
	}
	
}
