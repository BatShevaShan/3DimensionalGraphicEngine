package elements;

import primitives.Vector;

import primitives.Color;
import primitives.Point3D;

public class PointLight extends Light implements LightSource{
	
	protected Point3D position;
	private double kC = 1d;
	private double kL = 0d;
	private double kQ = 0d;
	
	
	public PointLight(Color _intensity, Point3D p) {
		super(_intensity);
		position = p;
	}
	
	
	public PointLight(Color _intensity, Point3D _position, double _kC, double _kL, double _kQ) {
		super(_intensity);
		this.position = _position;
		this.kC = _kC;
		this.kL = _kL;
		this.kQ = _kQ;
	}
	
	
	public PointLight setkL(Double kL) {
		this.kL = kL;
		return this;
	}
	public PointLight setkQ(Double kQ) {
		this.kQ = kQ;
		return this;
	}
	public PointLight setkC(Double kC) {
		this.kC = kC;
		return this;
	}


	
	public Color getIntensity(Point3D p) {
	
		
		  double d = position.distance(p);
		  double dem = 1 / kC + kL*d + kQ*d*d;
		  Color iL = intensity.scale(dem);
		  return iL;
	}
	
	@Override
	public Vector getL(Point3D p) {
		Vector v =  p.substract(position).normalize();
		return v;
	}


	@Override
	public double getDistance(Point3D point) {
		return position.distance(point);
	}
	
	

	
	

	


	
	

}
