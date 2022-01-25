package elements;

import primitives.Vector;

import primitives.Point3D;
import primitives.Ray;

import static primitives.Util.isZero;


/**
 * @author Batsheva
 *
 */
public class Camera {

	final private Point3D place;
	final private Vector vTo;
	final private Vector vUp;
	final private Vector vRight;
	private double distance;
    private double width;
    private double height;
	
	/**
	 * @param _place
	 * @param _vTo
	 * @param _vUp
	 */
	public Camera (Point3D _place, Vector _vTo, Vector _vUp)
	{
		 place = _place;

         if (!isZero(_vTo.dotProduct(_vUp))) {
             throw new IllegalArgumentException("vto and vup are not orthogonal");
         }

         vTo = _vTo.normalized();
         vUp = _vUp.normalized();

         vRight = vTo.crossProduct(vUp);

	}
	
	public Point3D getPlace() {
		return place;
	}

	
	public Vector getvT0() {
		return vTo;
	}


	public Vector getvUp() {
		return vUp;
	}

	
	public Vector getvRight() {
		return vRight;
	}
	
	public Camera setViewPlaneSize(double _width, double _height)
	{
		width = _width;
        height = _height;
        return this;
		
	}
	
	public Camera setDistance(double _distance)
	{
		distance = _distance;
        return this;
		
	}
	
	/**
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @return
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i)
	{
		Point3D Pc = place.add(vTo.scale(distance)); //Pc = place + distance * vTo
		 
		double Rx = width / nX; //The width of each pixel
        double Ry = height / nY; //The height of each pixel is

        Point3D Pij = Pc; 

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;


        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(Pij.substract(place), place);
        }
        if (isZero(Xj)) {
            Pij = Pij.add(vUp.scale(Yi));
            return new Ray(Pij.substract(place), place);
        }
        if (isZero(Yi)) {
            Pij = Pij.add(vRight.scale(Xj));
            return new Ray(Pij.substract(place), place);
        }

        Pij = Pij.add(vRight.scale(Xj).add(vUp.scale(Yi)));
        return new Ray(Pij.substract(place), place);

	}


	
	
	
	
	
	
}
