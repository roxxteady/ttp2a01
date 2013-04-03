package a01;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;


@SpaceClass
public final class World {

	public int BLOCKSINX = 1;
	public int BLOCKSINY = 1;
	public int ROXELSIZE = 16;
	public int WIDTH = 20;
	public int HEIGHT = 20;
	public Integer ssn;
	
	public World(){
		ssn=1;
	}
	
	public void setSsn(Integer ssn)
	{
		System.out.println(ssn);
		this.ssn = ssn;
	}
	
	
	@SpaceId
	public Integer getSsn()
	{
		return this.ssn;
	}
}
