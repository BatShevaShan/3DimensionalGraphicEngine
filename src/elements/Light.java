package elements;

import primitives.Color;

public abstract class Light {
	
	Color intensity;
	
	protected Light(Color _intensity) {
		this.intensity = _intensity;
	}

	public Color getIntensity() {
		return intensity;
	}
	

}
