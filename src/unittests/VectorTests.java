/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Vector;

/**
 * @author Batsheva
 *
 */
public class VectorTests {

	Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		// ============ Equivalence Partitions Tests ============== 
		
		Vector v = new Vector(-1,-2,-3);
		assertEquals("ERROR: add() wrong value", v1.add(v2), v);
	}

	/**
	 * Test method for {@link primitives.Vector#substract(primitives.Vector)}.
	 */
	@Test
	public void testSubstract() {
		Vector v = new Vector(3, 6, 9);
		assertEquals("ERROR: add() wrong value", v1.substract(v2), v);
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		// ============ Equivalence Partitions Tests ==============
		
		assertEquals("ERROR: scale() wrong value", v1.scale(-2), v2);
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
		assertTrue("ERROR: dotProduct() wrong value", isZero(v1.dotProduct(v2)+28));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        assertThrows("crossProduct() for parallel vectors does not throw an exception",
                IllegalArgumentException.class, () -> v1.crossProduct(v2));
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		//==========Equivalence Partitions Tests==========
		
		assertTrue("ERROR: lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		//==========Equivalence Partitions Tests========== 
		
        assertTrue("ERROR: length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
	}


	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalized() {
		//==========Equivalence Partitions Tests==========
		
		 Vector vCopy = new Vector(v1.getHead());
		 Vector vCopyNormalize = vCopy.normalized();
	     assertEquals("normalized() function creates a new vector", vCopy, vCopyNormalize);
	     assertTrue("normalized() result is not a unit vector", isZero(vCopyNormalize.length() - 1));
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalize() {
		// ============ Equivalence Partitions Tests ==============
		
		Vector u = v1.normalize();
		assertEquals("normalizate() function does not create a new vector", u ,v1.normalize());
	}


}