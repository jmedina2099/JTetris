/**
 * 
 */
package figure;

import java.awt.Point;

/**
 * @author jmedina
 *
 */
public class Ele extends Figure {
	
	
	public Ele() {
		init();
	}

	private void init() {
		int x = 4;
		int y = -2;
		
		Point[] puntos = new Point[4];
		puntos[0] = new Point(x,y);
		puntos[1] = new Point(x,y+1);
		puntos[2] = new Point(x,y+2);
		puntos[3] = new Point(x-1,y+2);

		for( Point p : puntos ) {
			figura.add(p);
		}
	}

}
