/**
 * 
 */
package scene;

import java.util.LinkedList;

import java.util.List;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;
import  elements.LightSource;

/**
 * @author Batsheva
 *
 */
public class Scene {
	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getBackground() {
		return background;
	}

	public AmbientLight getAmbientLight() {
		return ambientLight;
	}

	public List<LightSource> getLights() {
		return lights;
	}

	public Geometries geometries;
	

	public List<LightSource> lights = new LinkedList<LightSource>();

	public Scene(String _name)
	{
		super();
		this.name=_name;
		this.geometries=new Geometries();
		this.ambientLight =new AmbientLight();
		this.lights = new LinkedList<LightSource>();
	
	}
	
	public Scene setLights(List<LightSource> l) {
		this.lights = l;
		return this;
	}
	
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	
	public Geometries getGeometries() {
		return geometries;
	}


}
