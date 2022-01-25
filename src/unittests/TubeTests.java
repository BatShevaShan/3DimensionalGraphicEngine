package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Tube;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Chaya
 *
 */


public class TubeTests {
	
    /**
  	 * Test method for {@link geometries.Tube#testGetNormal(primitives.Point3D)}.
  	 */
	@Test
	public void testGetNormal() {
		Vector v = new Vector((2/(Math.sqrt(5))),(-1/(Math.sqrt(5))),0);
		Ray r = new Ray(new Vector(0,0,3), new Point3D(1,0,-1));
	    Tube tb = new Tube(r ,5.0);
	    Vector vc = tb.getNormal(new Point3D(3,-1,0));
		assertTrue("getnormal() function doesn't return the normal vector",v.equals(vc));
	}

}

