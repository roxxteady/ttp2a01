package a01;

import java.util.ArrayList;
import java.util.List;

public class RoxelFactory {
	
	public static List<Roxel> createRoxels(World wrld) 
	{
		List<Roxel> listOfRoxels = new ArrayList<Roxel>();
		
		for (int y = 0; y < wrld.HEIGHT; y++) {
			for (int x = 0; x < wrld.WIDTH;) {
				if ((y + 1) % 7 == 0 && y != 0)
				{
					listOfRoxels.add(new Roxel(x,y,Direction.toBeDefined,true));
					x++;
				}
				else
				{
					x += 6;
					if (x < wrld.WIDTH) 
						listOfRoxels.add(new Roxel(x,y,Direction.toBeDefined,true));
					x++;
				}
			}
		}
		return listOfRoxels;
	}
	
}
