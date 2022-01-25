package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import geometries.Plane;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;


/**
 * @author Chaya
 *
 */


public class TriangleTests {

	Triangle test = new Triangle(new Point3D(-1, 1, 2), new Point3D(-4, 2, 2), new Point3D(-2, 1, 5));
    Vector v = new Vector((3/(Math.sqrt(91))),(9/(Math.sqrt(91))),(1/(Math.sqrt(91)))); 
    
    /**
	 * Test method for {@link geometries.Triangle#getnormal(primitives.Point3D)}.
	 */
    
	@Test
	public void testGetNormal() {
		
		assertTrue("getnormal() function doesn't return the normal vector",v.equals(test.getNormal(new Point3D(-1, 1, 2))));
	}

}
