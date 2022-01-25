package primitives;
import static primitives.Util.isZero;

import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * Ray class represents a ray in 3D Cartesian coordinate
 * system
 * 
 * @author Chaya malki
 */

public class Ray {
	
	/**
	 * Point3D p0
	 * Vector dir
	 */

	Point3D p0;
	Vector dir;
	private static final double DELTA = 0.1;
	
	 /**
     * Ray constructor receiving a vector and Point3D 
     * 
     * @param v Vector
     * @param p Point3D
     */
	
	public Ray(Vector v, Point3D p){
		v = v.normalize();
		if(v.head.equals(v.head.ZERO))
			throw new IllegalArgumentException();
		dir = v;
		p0 = p;
	}
	
	public Ray (Point3D p, Vector d_direction, Vector n_normal)
	{
		this.dir = new Vector(d_direction.head).normalize();
		double nv = n_normal.dotProduct(d_direction);
		if(nv == 0)
			p0 = p;
		else
		{
			Vector normalDelta = n_normal.scale((nv > 0 ? DELTA: -DELTA));
			p0 = p.add(normalDelta);
		}
	}
	
	@Override
	public String toString() {
		return "point " + getP0().toString() + "direction " + dir.toString() ;
	}

	@Override
	public boolean equals(Object obj) {
		Ray r;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        else
        	r = (Ray)obj;
		return (this.getP0().equals(r.getP0()))&&(this.getDir().equals(r.getDir()));
	}

	public Point3D getP0() {
		return p0;
	}

	public void setP0(Point3D p0) {
		this.p0 = p0;
	}

	public Vector getDir() {
		return dir;
	}
	


	   public Point3D getPoint(double delta ){
	        if (isZero(delta)){
	            return  p0;
	        }
	        return p0.add(dir.scale(delta));
	    }
	   
	   
	   public Point3D getTargetPoint(double d) 
		{
			return new Point3D(this.getP0().add(getDir().scale(d)).getX(), this.getP0().add(getDir().scale(d)).getY(), this.getP0().add(getDir().scale(d)).getZ());
			
		}


	   public Point3D findClosestPoint(List<Point3D> pointsList){
	        Point3D result =null;
	        double closestDistance = Double.MAX_VALUE;

	        if(pointsList== null){
	            return null;
	        }

	        for (Point3D p: pointsList) {
	            double temp = p.distance(p0);
	            if(temp < closestDistance){
	                closestDistance =temp;
	                result =p;
	            }
	        }

	        return  result;
	    }
	   
	   public GeoPoint findClosestGeoPoint(List<GeoPoint> pointsList){
	        GeoPoint result =null;
	        double closestDistance = Double.MAX_VALUE;

	        if(pointsList== null){
	            return null;
	        }

	        for (GeoPoint p: pointsList) {
	            double temp = p.point.distance(p0);
	            if(temp < closestDistance){
	                closestDistance =temp;
	                result =p;
	            }
	        }
			return result;


	
	
}}