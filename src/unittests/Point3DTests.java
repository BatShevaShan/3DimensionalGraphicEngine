/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author Batsheva
 *
 */
public class Point3DTests {


	Point3D p1 = new Point3D(1, 2, 3);
	Point3D p2 = new Point3D(-2, -4, -6);
	Point3D p3 = new Point3D(0, 3, -2);
	Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
	
	/**
	 * Test method for {@link primitives.Point3D#substract(primitives.Point3D)}.
	 */
	@Test
	public void testSubstract() {
		Vector v = new Vector(3, 6, 9);
		assertEquals("ERROR: add() wrong value", p1.substract(p2), v);
	}


	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Point3D p = new Point3D(-1,-2,-3);
		assertEquals("ERROR: add() wrong value", p1.add(v2), p);
	}


	/**
	 * Test method for {@link primitives.Point3D#distanceSquared(primitives.Point3D)}.
	 */
	@Test
	public void testDistanceSquared() {
		double a = p1.distanceSquared(p2);
		assertTrue("ERROR: distanceSquared() wrong value", isZero(p1.distanceSquared(p2) - 126));
	}


	

}
