/**
 * 
 */
package figure;

import java.awt.Point;

/**
 * @author jmedina
 *
 */
public class Te extends Figure {
	
	private boolean DEBUG = false;

	public Te() {
		if( DEBUG )
			System.out.println( "Te" );
		init();
	}

	private void init() {
		int x = 3;
		int y = 0;
		
		Point[] puntos = new Point[4];
		puntos[0] = new Point(x,y);
		puntos[1] = new Point(x+1,y);
		puntos[2] = new Point(x+1,y-1);
		puntos[3] = new Point(x+2,y);

		for( Point p : puntos ) {
			figura.add(p);
		}
	}

}
