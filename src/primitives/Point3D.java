package primitives;
import java.lang.Math; 

/**
 * Class Point3D is the basic class representing a point in Cartesian
 * coordinate system. The class is based on Coordinate,every point contains 3 coordinates in cartesian coordinate system.
 * 
 * @author Chaya malki
 * @version 5780B updated according to new requirements
 */

public class Point3D {
	
	/**
     * Point3D contains 3 coordinates: x,y,z.
     */
	
	Coordinate x;
	Coordinate y;
	Coordinate z;
	
	 /**
     * final static Zero point = (0, 0, 0).
     */
	
	public final static Point3D ZERO = new Point3D(0,0,0);
	
	 /**
     * Point3D constructor receiving 3 coordinate values
     * 
     * @param x Coordinate value
     * @param y Coordinate value
     * @param z Coordinate value
     */
	
	public Point3D(Coordinate x,Coordinate y,Coordinate z){
		this.x = x;
		this.y = y;
		this.z = z;
		
		}
	
	 /**
     * Point3D constructor receiving 3 double values
     * 
     * @param x double value
     * @param y double value
     * @param z double value
     */
	
	public Point3D(double x,double y,double z){
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}
	
	
	
		 /**
     * Vector substract receiving Point3D value and substracts point p from the caller vector
     * @param p Point3D 
     * @return Vector 
     */

	
	
	public Vector substract(Point3D p) {
		Point3D point = new Point3D(0,0,0);
		point = this.substractPoint(p);
		Vector v = new Vector(point);
		return v;
	}
	
		 /**
     * Point3D substracPoint receiving Point3D value and substracts point p from the caller point
     * @param p Point3D 
     * @return Point3D
     */

	
	public Point3D substractPoint(Point3D p) {
		double a,b,c;
		a = this.x.coord - p.x.coord;
		b = this.y.coord - p.y.coord;
		c = this.z.coord - p.z.coord;
		Point3D point = new Point3D(a,b,c);
		return point;
	}
	
	
		 /**
     * Point3D add receiving Point3D value and adds vector v points from the caller point
     * @param v Vector
     * @return Point3D
     */
	
	public Point3D add(Vector v) {
		Point3D point = new Point3D(0,0,0);
		point = this.addPoint(v.head);
		return point;
	}
	
	
		 /**
     * Point3D addPoint receiving Point3D value and adds point p from the caller point
     * @param p Point3D 
     * @return Point3D
     */
	
	public Point3D addPoint(Point3D p) {
		double a,b,c;
		a = this.x.coord + p.x.coord;
		b = this.y.coord + p.y.coord;
		c = this.z.coord + p.z.coord;
		Point3D point = new Point3D(a,b,c);
		return point;
	}
	
	
		 /**
     * double distanceSquared receiving Point3D value and calculates the distance squared between the caller point and point p
     * @param p Point3D 
     * @return double
     */  
	public double distanceSquared(Point3D p) {
		double a;
		Point3D point = new Point3D(0,0,0);
		point = this.substractPoint(p);
		point =point.multPoint(point);
		a = point.x.coord + point.y.coord + point.z.coord;
		return a;
	}
	
		 /**
     * Point3D multPoint receiving Point3D value and calculates the multiplication between the caller point and pint p
     * @param p Point3D 
     * @return Point3D
     */ 
	
	public Point3D multPoint(Point3D p) {
		double a,b,c;
		a = this.x.coord * p.x.coord;
		b = this.y.coord * p.y.coord;
		c = this.z.coord * p.z.coord;
		Point3D point = new Point3D(a,b,c);
		return point;
	}
	
	 /**
     * double distance receiving Point3D value and calculates the distance between the caller point and point p
     * @param p Point3D 
     * @return double
     */ 
	
	public double distance(Point3D p) {
		double a;
		a = distanceSquared(p);
		a = Math.sqrt(a);
		return a;
		
	}
	
	 /**
     * boolean equals receiving Object value and returns true if obj coordinates are equal to caller point
     * @param obj Object
     */ 
	
	@Override
	public boolean equals(Object obj) {
		 if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Point3D point3D = (Point3D) obj;
	        return x.equals(point3D.x) && y.equals(point3D.y) && z.equals(point3D.z);
    }
	
	
	
	@Override
	public String toString() {
		return String.format("(%s,%s,%s)", x.toString(), y.toString(), z.toString());
	}

	public int getX() {
		return (int) x.coord;
	}
	
	public int getY() {
		return (int) y.coord;
	}
	
	public int getZ() {
		return (int) z.coord;
	}
	
	
}