public class Point3d extends Point2d {
	private double zCoord;
	public Point3d ( double x, double y, double z) {
		super(x, y);
		zCoord = z;
	}
	public Point3d () {
		this(0.0, 0.0, 0.0);
	}
	public double getZ () {
		return zCoord;
	}
	public void setZ ( double val) {
		zCoord = val;
	}
	

	public double distanceTo(Point3d A){
		return Math.sqrt(Math.pow(xCoord - A.getX(), 2) + Math.pow(yCoord - A.getY(), 2) + Math.pow(zCoord - A.getZ(), 2));
	}
	public boolean isEqual(Point3d A){
		return this.distanceTo(A) == 0;	
	}	
}