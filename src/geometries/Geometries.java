package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;

public class Geometries implements Intersectable {
    private List<Intersectable> _intersectables = new LinkedList<>();

    public Geometries(Intersectable... geos) {
        add(geos);
        
    }
    

    public void add(Intersectable... geos) {
        Collections.addAll(_intersectables, geos);
    }

    
    @Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		
    	List<GeoPoint> intersections = null;
    	for (Intersectable item : _intersectables)
    	{
    		
    	var geoIntersections = item.findGeoIntersections(ray);
    	 if(geoIntersections!= null){
  
    	 if(intersections== null){
    		 intersections= new LinkedList<>();
         }

    	 intersections.addAll(geoIntersections);
     }
 
 }
 return intersections;


	}
}