/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * @author Batsheva
 *
 */
public class ImageWriterTests {

	@Test
	public void test() {
		 ImageWriter imageWriter = new ImageWriter("testblue",800,500);
	        for (int i = 0; i < 800; i++) {
	            for (int j = 0; j < 500; j++) {
	                // 800/16 = 50
	                if (i % 50 == 0) {
	                    imageWriter.writePixel(i, j, Color.BLACK);
	                }
	                // 500/10 = 50
	                else if (j % 50 == 0) {
	                    imageWriter.writePixel(i, j, Color.BLACK);
	                } else {
	                    imageWriter.writePixel(i, j, Color.BLUE);
	                }
	            }
	        }
	        imageWriter.writeToImage();
	    }
	}


