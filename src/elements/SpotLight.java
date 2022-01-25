package elements;

import primitives.Vector;

import primitives.Color;
import primitives.Point3D;

public class SpotLight extends PointLight{
	
	private Vector direction;

	public SpotLight(Color _intensity, Point3D p, Vector v) {
		
		super(_intensity, p);
		this.direction = new Vector(v.getHead());
	}
	
	public SpotLight(Color _intensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ) {
		super(_intensity, _position, _kC, _kL, _kQ);
		this.direction = new Vector(direction.getHead());
	}
	
	
	public Color getIntensity(Point3D p) {
		
		Vector l = new Vector(this.getL(p).getHead());
		direction.normalized();
		l.normalized();
		double d = getDistance(p);
		double scalar = Math.max(0,(direction.dotProduct(l))); 
		return getIntensity().scale(scalar);
		
		
	}
	

	
	
	


	
}
