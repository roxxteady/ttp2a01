package a01;

import java.util.ArrayList;
import java.util.List;

public class RoxelFactory {
	
	public static List<Roxel> createRoxels(World world) 
	{
		List<Roxel> listOfRoxels = new ArrayList<Roxel>();
		
		for (int y = 0; y < world.HEIGHT; y++) {
			for (int x = 0; x < world.WIDTH;) {
				if ((y + 1) % 7 == 0 && y != 0)
				{
					listOfRoxels.add(new Roxel(x,y,Direction.toBeDefined,true));
					x++;
				}
				else
				{
					x += 6;
					if (x < world.WIDTH) 
						listOfRoxels.add(new Roxel(x,y,Direction.toBeDefined,true));
					x++;
				}
//				System.out.println("RoxelFactory:" + x + "-" + y);
			}
		}
		return listOfRoxels;
	}
	
}
