package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Sphere extends Geometry {

	private Point3D center;
	private double radius;
	
	public Sphere(Point3D c, double r) {
		center = c;
		radius = r;
	}
	
	@Override
	public Vector getNormal(Point3D p) {
		Vector v = p.substract(center);
		 v = v.normalize();
		 return v;
	}
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		
		    Point3D P0 = ray.getP0();
	        Vector v = ray.getDir();

	        if (P0.equals(center)) {
	            GeoPoint geo = new GeoPoint(this, center.add(v.scale(radius)));
	        	return List.of(geo);
	        }

	        Vector U = center.substract(P0);

	        double tm = alignZero(v.dotProduct(U));
	        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

	        // no intersections : the ray direction is above the sphere
	        if (d >= radius) {
	            return null;
	        }

	        double th = alignZero(Math.sqrt(radius * radius - d * d));
	        double t1 = alignZero(tm - th);
	        double t2 = alignZero(tm + th);

	        if (t1 > 0 && t2 > 0) {
//	            Point3D P1 = P0.add(v.scale(t1));
//	            Point3D P2 = P0.add(v.scale(t2));
	            Point3D P1 =ray.getPoint(t1);
	            Point3D P2 =ray.getPoint(t2);
	            GeoPoint geo1 = new GeoPoint(this, P1);
	            GeoPoint geo2 = new GeoPoint(this, P2);
	        	return List.of(geo1, geo2);
	        }
	        if (t1 > 0) {
//	            Point3D P1 = P0.add(v.scale(t1));
	            Point3D P1 =ray.getPoint(t1);
	            GeoPoint geo1 = new GeoPoint(this, P1);
	            return List.of(geo1);
	        }
	        if (t2 > 0) {
//	            Point3D P2 = P0.add(v.scale(t2));
	            Point3D P2 =ray.getPoint(t2);
	            GeoPoint geo2 = new GeoPoint(this, P2);
	            return List.of(geo2);
	        }
	        return null;
	}

			
	}
		
		
		
		
		  
	
	



