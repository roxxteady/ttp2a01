package a01;

import java.util.concurrent.TimeUnit;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitAlreadyDeployedException;
import org.openspaces.admin.space.SpaceDeployment;
import org.openspaces.core.GigaSpace;

public class Gigatest 
{

	private static Gigatest myGiga;
	private GigaSpace giga;
	
	public static Gigatest getInstance()
	{
		if (myGiga == null)
		{
			myGiga = new Gigatest();
		}
		return myGiga;
	}
	
	private Gigatest()
	{
		try 
		{
		Admin admin = new AdminFactory().createAdmin();
		GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne();
		ProcessingUnit pu = gsm.deploy(new SpaceDeployment("MyGiga").partitioned(1, 1));
		
		pu.waitFor(2, 10, TimeUnit.SECONDS);
		giga = pu.waitForSpace().getGigaSpace();
		}
		catch(ProcessingUnitAlreadyDeployedException e)
		{
			//
		}
	}

	public GigaSpace getGiga() {
		return giga;
	}
}
