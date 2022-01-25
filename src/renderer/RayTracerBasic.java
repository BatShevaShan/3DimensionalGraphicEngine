package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;

/**
 * @author Batsheva
 *
 */
public class RayTracerBasic extends RayTracerBase {

	private static final double DELTA = 0.1;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double INITIAL_K = 1.0;
    /**
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    
    /**
     * traceRay calculate the color of the closest point from the scene's geometries that intersect with the ray
     */
    @Override
	public Color traceRay(Ray ray)
	{
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? _scene.background : calcColor(closestPoint, ray);
	}
	
	
    private Color calcColor(GeoPoint gp, Ray ray)
   	{
   		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(_scene.getAmbientLight().getIntensity());
   	}
       
      


   	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
   		Color color = intersection.geometry.getEmission();
   		color = color.add(calcLocalEffects(intersection, ray, k));
   		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
   		}

	
	private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
		
		Vector v = ray.getDir().normalize(); 
		Vector n = intersection.geometry.getNormal(intersection.point).normalize();
		double nv = primitives.Util.alignZero(n.dotProduct(v)); 
		if (nv == 0) return Color.BLACK;
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.getnShininess();
		double kd = material.getkD();
		double ks = material.getkS();
		Color color = Color.BLACK;
		
		for (LightSource lightSource : _scene.lights) 
		{
			Vector l = lightSource.getL(intersection.point).normalize(); 
			double nl = primitives.Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv) 
				double ktr = transparency(lightSource, l, n, intersection);
				if (ktr * k > MIN_CALC_COLOR_K) {

				Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
				calcSpecular(ks, l, n,v, nShininess, lightIntensity));
				}}
		}
		
		

		return color;
		
		}


	

	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k)
	{
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr; 
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = calcReflection(geopoint.geometry.getNormal(geopoint.point), geopoint.point, ray);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
			color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
		}
		
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = calcRefraction(geopoint.geometry.getNormal(geopoint.point), geopoint.point, ray);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null)
			color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt));
		}
		return color;
		
	}

	
   


	


	

    
  

    private boolean unshaded(Vector l, Vector n, GeoPoint gp, LightSource ls)
    {
    	Vector lightDirection = l.scale(-1); 
		Ray lightRay = new Ray(gp.point, lightDirection, n);
		List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(lightRay);
		
		if (intersections==null) return true;
		
		double lightDistance = ls.getDistance(gp.point);
		for (GeoPoint i : intersections) 
		{
			if (primitives.Util.alignZero(i.point.distance(gp.point)-lightDistance)<= 0 && i.geometry.getMaterial().getkT() == 0)return false;
		}
		return true;
    }
    
  

    
    
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint)
    {
    	Vector light_direction = l.scale(-1);
		Ray lightRay = new Ray(geoPoint.point, light_direction, n);
		
		List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(lightRay);
		if (intersections==null) return 1.0;
		
		
		double light_distance = ls.getDistance(geoPoint.point);
	
		double ktr = 1.0;
		for (GeoPoint i : intersections) {
		
			if (primitives.Util.alignZero(i.point.distance(geoPoint.point)-light_distance) <= 0) 
			{
				ktr *= i.geometry.getMaterial().getkT();
				if (ktr < MIN_CALC_COLOR_K) return 0.0;
			}
		}
		return ktr;
    }


    private GeoPoint findClosestIntersection(Ray ray)
    {
    	List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(ray);
		
		if(intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);	
    }
    
    
    
    
    
    public Color calcDiffusive(double kD, Vector l, Vector n, Color iL) 
	{
	
		double abs = Math.abs(l.dotProduct(n));
		return iL.scale(kD * abs);		
	}
	

	
	
	public Color calcSpecular(double k_S, Vector l, Vector n, Vector v, double n_Sh, Color i_L)
	{
	
		Vector r = l.add(n.scale(-2 * (l.dotProduct(n))));
		r.normalize();
		double pow =  Math.pow(((v.scale(-1)).dotProduct(r)), n_Sh);
		return i_L.scale(k_S * pow);
	}
    

	private Ray calcReflection(Vector n, Point3D p, Ray ray)
    {
		
		Vector normal = n.normalized(); 
		Vector direction =ray.getDir().normalized();
		Vector r = normal.scale((-2)* direction.dotProduct(normal)); 
        r = r.add(direction);	
        return new Ray(p, r, n);
    }
	
	
	private Ray calcRefraction(Vector n, Point3D p, Ray ray)
    {
		Vector direction =ray.getDir().normalized();
		return new Ray(p, direction, n);
    }

	
}
