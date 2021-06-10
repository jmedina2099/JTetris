/**
 * 
 */
package figure;

import java.util.Random;

/**
 * @author jmedina
 *
 */
public class FigureFactory {

	public static Figure getFigure() {
		
		Figure figure = null;
		
		Random random = new Random();
		int figureNum = random.nextInt(2);
		
		switch( figureNum ) {
		case 0:
			figure = new Ele();
			break;
		case 1:
			figure = new Te();
			break;
		default:
			System.out.println( "error" );
			break;
		}
				
		return figure;
	}
}
