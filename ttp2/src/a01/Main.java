package a01;

import java.util.List;

import org.openspaces.core.GigaSpace;

import com.j_spaces.core.client.SQLQuery;


public class Main {

	public static void main(String[] args) {
		GigaSpace gt = DataGridConnectionUtility.getSpace("MySpace",1,1);
		
		World wrld=new World();
		List<Roxel> listOfRoxels = RoxelFactory.createRoxels(wrld);
		
		gt.writeMultiple(listOfRoxels.toArray());
		gt.write(wrld);
		
		Roxel[] roxel = gt.readMultiple(new SQLQuery<Roxel>(Roxel.class, "y=6"));
		
		System.out.println(roxel.length);
		
		for (int i = 0; i < roxel.length; i++) {
			System.out.println(roxel[i].getX());			
		}
		
		
	}
		
}
