/**
 * 
 */
package ui;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * @author jmedina
 *
 */
public class FramePrincipal extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7131396848291951334L;
	
	private PanelTetris panelTetris;

	public FramePrincipal() {
		super("JTetris 4x4");
		init();
		addKeyListener( this );
	}

	private void init() {
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo( null );
		
		Container contentPane = getContentPane();
		contentPane.setLayout( new BoxLayout(contentPane, BoxLayout.X_AXIS) );
		contentPane.add( Box.createHorizontalGlue() );
		contentPane.add( setPanelTetris(new PanelTetris()) );
		contentPane.add( Box.createHorizontalGlue() );
	}

	public PanelTetris getPanelTetris() {
		return panelTetris;
	}

	public PanelTetris setPanelTetris(PanelTetris panelTetris) {
		this.panelTetris = panelTetris;
		return panelTetris;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		//System.out.println( "keyTyped "+e.getKeyCode() );
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if( e.getKeyCode() == 40 ) {
			getPanelTetris().downFigure();
		}

		//System.out.println( "keyPressed"+e.getKeyCode() );
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		//System.out.println( "keyReleased"+e.getKeyCode() );
	}
}
