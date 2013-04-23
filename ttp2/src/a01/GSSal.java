package a01;

import java.awt.Point;
import java.util.List;

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
	
	public static boolean moveCar(int x, int y, int tx, int ty) {
		Roxel roxelSrc;
		Roxel roxelDst;
		
//		System.out.println(x + "-" + y + "-" + tx + "-" + ty);
		
		if ((roxelDst = gt.take(new SQLQuery<Roxel>(Roxel.class, "x=" + tx + " and y=" + ty + " and free=true"))) == null) {
			return false;
		}
		
		if (roxelDst.getDir() == Direction.north2South && y == ty) {
			gt.write(roxelDst);
			return false;
		} else if (roxelDst.getDir() == Direction.west2East && x == tx) {
			gt.write(roxelDst);
			return false;
		}
		
		if ((roxelSrc = gt.take(new SQLQuery<Roxel>(Roxel.class, "x=" + x + " and y=" + y + " and free=false"))) == null) {
			gt.write(roxelDst);
			return false;
		}
		
		roxelSrc.setFree(true);
		roxelDst.setFree(false);
		gt.write(roxelSrc);
		gt.write(roxelDst);
		
		return true;
	}
	
	public static void setRoxels(List<Roxel> list) {
		for (int i = 0; i < list.size(); i++) {
			gt.write(list.get(i));
		}
	}
	
	public static boolean resetRoxel(int x, int y, boolean free, Direction dir) {
		Roxel roxel;
		if((roxel = gt.take(new SQLQuery<Roxel>(Roxel.class, "x=" + x + " and y=" + y + " and free=true"))) == null) {
			return false;
		}
		
		roxel.setFree(free);
		roxel.setDir(dir);
		gt.write(roxel);
		
		return true;
	}
	
	public static Roxel getRoxel(int x, int y) throws GetRoxelException {
		Roxel roxel;
		if((roxel = gt.read(new SQLQuery<Roxel>(Roxel.class, "x=" + x + " and y=" + y))) == null) {
			throw new GetRoxelException();
		}
		
		return roxel;
	}
	
	public static boolean setCar(Car car) {
		Roxel roxelDst;
		Point carPos = car.getPosition();
		
		if((roxelDst = gt.take(new SQLQuery<Roxel>(Roxel.class, "x=" + carPos.x + " and y=" + carPos.y + " and free=true"))) == null) {
			return false;
		}
		
		roxelDst.setFree(false);
		gt.write(roxelDst);
		
		return true;
	}
	
}
