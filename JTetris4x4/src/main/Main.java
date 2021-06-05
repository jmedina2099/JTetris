/**
 * 
 */
package main;

import ui.FramePrincipal;
import ui.PanelTetris;

/**
 * @author jmedina
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println( "JTetris 4x4");
		
		FramePrincipal frame = new FramePrincipal();
		frame.setVisible( true );
		
		PanelTetris panelTetris = frame.getPanelTetris();
		panelTetris.starting();
	}

}
