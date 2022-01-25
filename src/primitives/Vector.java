package primitives;

import static java.lang.System.out;

/**
 * Vector class represents a vector in 3D Cartesian coordinate
 * system, composed of a point from (0, 0, 0) 
 * 
 * @author Chaya malki
 */

public class Vector {
	
	/**
	 * Point3D head
	 */
	
	Point3D head ;
	
	 /**
     *Vector constructor receiving 3 coordinates value
     * 
     * @param x Coordinate value
     * @param y Coordinate value
     * @param z Coordinate value
     * @throws IllegalArgumentException if 3 coordinates are equal to point3D ZERO
     */
	
	Vector(Coordinate x,Coordinate y,Coordinate z){
		this.head = new Point3D(x, y, z);
		if(this.head.equals(this.head.ZERO))
			throw new IllegalArgumentException();
		}
	
	  /**
     *Vector constructor receiving 3 doubles value
     * 
     * @param x double value
     * @param y double value
     * @param z double value
     * @throws IllegalArgumentException if 3 doubles are equal to point3D ZERO
     */
	
	public Vector(double x,double y,double z){
		this.head = new Point3D(x, y, z);
		if(this.head.equals(this.head.ZERO))
			throw new IllegalArgumentException();
	}
	
	  /**
     *Vector constructor receiving Point3D
     * 
     * @param p Point3D
     * @throws IllegalArgumentException if p is equal to point3D ZERO
     */
	
	
	public Vector(Point3D p){
		this.head = new Point3D(p.x, p.y, p.z);
		if(this.head.equals(this.head.ZERO))
			throw new IllegalArgumentException();
	}
	
	  /**
     *Vector add receiving Vector adds the caller vector and vector v
     * 
     * @param v Vector
     * @return Vector
     */
	
	public Vector add(Vector v) {
	Point3D point;
	point = this.head.add(v);
	Vector vector = new Vector(point);
	return vector;
	}
	
	  /**
     *Vector substract receiving Vector substracts the vector v to caller vector
     * 
     * @param v Vector
     * @return Vector
     */
	
	
	public Vector substract(Vector v) {
		Vector vector;
		vector = this.head.substract(v.head);
		return vector;
	}
	
	
	  /**
     *Vector scale receiving double and multiplicates vector point to scalar
     * 
     * @param scalar double
     * @return Vector
     */
	
	public Vector scale(double scalar) {
		Vector vector = new Vector(this.head.x.coord*scalar, this.head.y.coord*scalar, this.head.z.coord*scalar);
		return vector;
	}

		  /**
     *double dotProduct receiving Vector and calculates the dot product between the vector caller and v
     * 
     * @param v Vector
     * @return double 
     */  
	
	public double dotProduct(Vector v) {
		Point3D p;
		p = this.head.multPoint(v.head);
		double a;
		a = p.x.coord + p.y.coord + p.z.coord;
		return a;
	}
	
		  /**
     *Vector crossProduct receiving Vector and calculates a creates a new vector that is the cross product between the vector caller and v 
     * 
     * @param v Vector
     * @return Vector
     */  
	
	public Vector crossProduct(Vector v) {
		double a,b,c;
		a = (this.head.y.coord*v.head.z.coord) - (this.head.z.coord*v.head.y.coord);
		b = (this.head.z.coord*v.head.x.coord) - (this.head.x.coord*v.head.z.coord);
		c = (this.head.x.coord*v.head.y.coord) - (this.head.y.coord*v.head.x.coord);
		Vector vector = new Vector(a, b, c);
		return vector;
	}
	
			  /**
     *double lengthSquared calculates the length squared of the vector caller 
     * 
     * @return double
     */   
	
	public double lengthSquared() {
		double a;
		Point3D point = new Point3D(0,0,0);
		point =this.head.multPoint(this.head);
		a = point.x.coord + point.y.coord + point.z.coord;
		return a;
	}
	
	
			  /**
     *double lengthSquared calculates the length of the vector caller 
     * 
     * @return double
     */   
	
	public double length() {
		double a;
		a = this.lengthSquared();
		a = Math.sqrt(a);
		return a;
	}

	
	public Point3D getHead() {
		return head;
	}

	public void setHead(Point3D head) {
		this.head = head;
	}

	
			  /**
     *Vector normalize normalizes the vector caller 
     * 
     * @return Vector
     */    
	
	public Vector normalized() {
		double a =  this.length();
		a = 1/a;
		Vector v = new Vector((this.scale(a)).head);
		this.head = v.head;
		return this;
	}
	
				  /**
     *Vector normalize creates a new vector that is the normalized vector caller 
     * 
     * @return Vector
     */    
	
	public Vector normalize() {
		Vector v = new Vector(this.head.x, this.head.y, this.head.z);
		v = v.normalized();
		return v;
	}
	
     /**
     *boolean equals returns true if obj is equal to the vector caller
     * 
     * @param obj Object
     */ 
	
        @Override
	public boolean equals(Object obj) {
		Vector v;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        else
        	v = (Vector)obj;
        if(this.head.equals(v.head)) {
        return true;
        }
        else 
        	return false;
    }

	@Override
	public String toString() {
		return head.toString();
	}
	
	
}