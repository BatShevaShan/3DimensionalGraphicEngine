package geometries;

import java.util.List;

import primitives.*;

public class Tube extends Geometry{
	
	protected Ray axisRay;
	protected double radius;

	public Tube(Ray ray, double d) {
		axisRay = ray;
		radius = d;
	}
	
	@Override
	public Vector getNormal(Point3D p) {
	    Vector v = p.substract(axisRay.getP0());
		double t = v.dotProduct(axisRay.getDir());
		v = axisRay.getDir().scale(t);
		Point3D O = axisRay.getP0().add(v);
    	v = p.substract(O);
		v = v.normalize();
		return v;
	}

	
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
