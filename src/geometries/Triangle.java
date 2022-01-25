package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import geometries.Intersectable.GeoPoint;

public class Triangle extends Polygon {  //implements Geometry

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
    	
    Vector v1 = vertices.get(0).substract(ray.getP0());
	Vector v2 = vertices.get(1).substract(ray.getP0());
	Vector v3 = vertices.get(2).substract(ray.getP0());
	Vector N1 = v1.crossProduct(v2).normalize();
	Vector N2 = v2.crossProduct(v3).normalize();
	Vector N3 = v3.crossProduct(v1).normalize();
	
	double sign1 = ray.getDir().dotProduct(N1);//v*N1
	double sign2 = ray.getDir().dotProduct(N2);		//v*N2
	double sign3 = ray.getDir().dotProduct(N3);		//v*N3

	primitives.Util.alignZero(sign1);
	primitives.Util.alignZero(sign2);
	primitives.Util.alignZero(sign3);
	List<GeoPoint> intersection=new ArrayList<GeoPoint>();
	//The point is inside if all v*Ni have the same sign
	if((sign1 > 0 && sign2 > 0 && sign3 > 0) || (sign1 < 0 && sign2 < 0 && sign3 < 0))
	{
		intersection = this.plane.findGeoIntersections(ray);
		if (intersection != null) //to change the geometry type to this
			for (GeoPoint i: intersection)
				i.geometry = this;
		return intersection;
		}
	return null;
}
}