/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Vector;

import primitives.Point3D;

/**
 * @author Batsheva
 *
 */
public interface LightSource {
	public Color getIntensity(Point3D p);
	public Vector getL(Point3D p);
	double getDistance(Point3D point);


}
