package a01;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class Roxel {
	private int x;
	private int y;
	private Direction dir;
	private boolean free;
	private int ssn;

	public Roxel() {
	}

	public Roxel(int x, int y, Direction dir, boolean free) {
		this.setX(x);
		this.setY(y);
		this.setDir(dir);
		this.setFree(free);
		this.setSsn(new String(x + "-" + y).hashCode());
	}

	@SpaceId
	public int getSsn() {
		return this.ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}

enum Direction {
	north2South, west2East, toBeDefined
}
