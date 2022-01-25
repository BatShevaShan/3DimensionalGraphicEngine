package geometries;

import primitives.*;

/**
 * @author Batsheva
 * Intersectable is an interface that use for the geometries with a normal
 * 
 */
public abstract class Geometry implements Intersectable{

	protected Color emission = Color.BLACK;
	private Material material = new Material();
	
	public Material getMaterial() {
		return material;
	}

	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}

	public Color getEmission() {
		return emission;
	}
	
	public Geometry setEmission(Color emmission) {
		this.emission = emmission;
		return this;
	}
	
	/**
	 * @param p
	 * @return
	 */
	public abstract Vector getNormal(Point3D p);
}

