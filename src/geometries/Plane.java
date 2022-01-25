package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry{
	
	private Point3D q0;
	private Vector normal;

	public Plane(Point3D a, Point3D b, Point3D c){
		 Vector ab = b.substract(a);
		 Vector ac = c.substract(a);
		 Vector v = ab.crossProduct(ac);
		 v = v.normalize();
		 normal = v;
		 q0 = a;
	}
	
	public Plane(Vector v,  Point3D p){
		normal = v;
		q0 = p;
	}

	@Override
	public Vector getNormal(Point3D p) {
		return normal;
	}
	
	public Vector getNormal() {
		return normal;
	}

	@Override
	public boolean equals(Object obj) {
		Plane p;
        if (obj == null) return false;
        if (!(obj instanceof Plane)) return false;
        else
        	p = (Plane)obj;
		return (q0.equals(p.q0))&&(normal.equals(p.normal));
	}

	@Override
	public String toString() {
		return "point " + q0.toString() + "direction " + normal.toString();
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		 	Point3D P0 = ray.getP0();
	        Vector v = ray.getDir();

	        Vector n = normal;

	        if(q0.equals(P0)){
	            return  null;
	        }

	        Vector P0_Q0 = q0.substract(P0);

	       //numerator
	        double nP0Q0  = alignZero(n.dotProduct(P0_Q0));

	        //
	        if (isZero(nP0Q0 )){
	            return null;
	        }

	        //denominator
	        double nv = alignZero(n.dotProduct(v));

	        // ray is lying in the plane axis
	        if(isZero(nv)){
	            return null;
	        }

	        double  t = alignZero(nP0Q0  / nv);

	        if (t <=0){
	            return  null;
	        }

	        GeoPoint result = new GeoPoint(this, ray.getPoint(t));
	      

	        return List.of(result);
	    }

	
	}
	
	
