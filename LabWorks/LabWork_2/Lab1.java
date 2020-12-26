import java.util.Scanner;
public class Lab1 {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int[] a;
		Point3d dotA = new Point3d();
		Point3d dotB = new Point3d();
		Point3d dotC = new Point3d();
		a = new int[3];
		for (int j = 0; j < 3; j++){
			for (int i = 0; i < 3; i++){
			a[i] = in.nextInt();
			}
			switch (j) {
				case 0:
					dotA = new Point3d(a[0], a[1], a[2]);
					break;
				case 1:
					dotB = new Point3d(a[0], a[1], a[2]);
					break;
				case 2:
					dotC = new Point3d(a[0], a[1], a[2]);
					break;
				default:
					break;
			}
		}
		if(dotA.isEqual(dotB) || dotB.isEqual(dotC) || dotC.isEqual(dotA)){
			System.out.println("S = 0!");			
		}else{
			System.out.println("S = " + computeArea(dotA, dotB, dotC));
		}
	}
	public static double computeArea(Point3d A, Point3d B, Point3d C){
		double S, p;
		p = (A.distanceTo(B) + B.distanceTo(C) + C.distanceTo(A)) / 2;
		S = Math.sqrt(p*(p - B.distanceTo(C))*(p - A.distanceTo(C))*(p - A.distanceTo(B)));
		return S;		
	}
}