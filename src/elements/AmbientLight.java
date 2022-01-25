/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author Batsheva
 *
 */
public class AmbientLight extends Light{
	
	/**
	 * @param _IA
	 * @param _KA
	 * @param _intensity
	 */
	public AmbientLight(Color _IA, double _KA)
	{
		super(_IA.scale(_KA));
	}
	
	public AmbientLight() {
		super(Color.BLACK);
    }

}
