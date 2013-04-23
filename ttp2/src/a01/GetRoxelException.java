package a01;

public class GetRoxelException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace() {
		System.err.println("Can't get the Roxel!");
	}
	
}

