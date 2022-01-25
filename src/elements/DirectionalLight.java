package elements;

import primitives.Vector;

import primitives.Color;
import primitives.Point3D;

public class DirectionalLight extends Light implements LightSource {
	
	private Vector direction;

	public DirectionalLight(Color _intensity, Vector v) {
		super(_intensity);
		this.direction = new Vector(v.getHead());
		}

	@Override
	public Color getIntensity(Point3D p) {
		return intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		return direction.normalized();
	}

	@Override
	public double getDistance(Point3D point) {
		
		return Double.POSITIVE_INFINITY;
	}

	
}
