/**
 * 
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import figure.Ele;
import figure.Figure;

/**
 * @author jmedina
 *
 */
public class PanelTetris extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9134781961868473813L;
	
	private int WIDTH = 10;
	private int HEIGHT = 20;
	private int BORDER = 15;
	
	private int[][] malla = new int[WIDTH][HEIGHT];
	private Figure[][] mallaPuntos = new Figure[WIDTH][HEIGHT];

	private boolean stopped = true;

	private Figure figura;

	public PanelTetris() {
		super( true );
		init();
		System.out.println( "PanelTetris" );
	}

	private void init() {
		setBorder( new LineBorder( Color.WHITE, BORDER ));
		setBackground( Color.BLACK );
	}
	
	public boolean enterFigure( Figure fig ) {
		
		if( fig == null ) {
			return false;
		}

		// Enter Game
		for( Point p : this.figura.getFigura() ) {
			try {
				System.out.println( "entering "+p.x+","+p.y );
				this.malla[p.x][p.y] = 1;
				this.mallaPuntos[p.x][p.y] = this.figura;
			} catch ( ArrayIndexOutOfBoundsException e) {
			}
		}
		return true;
	}
	
	private boolean canEnter( Figure fig ) {
		
		if( fig == null ) {
			return false;
		}
		
		boolean canEnter = true;

		boolean parcDentro = false;
		for( Point p : fig.getFigura() ) {
			if( p.y >= 0 ) {
				parcDentro = true;
			}
		}
		if( !parcDentro ) {
			//System.out.println( "!parcDentro="+!parcDentro );
			return false;
		}
		
		for( Point p : fig.getFigura() ) {
			try {
				System.out.println( "canEnter "+p.x+","+p.y );
				if( this.malla[p.x][p.y] == 1 && 
				    !fig.equals( this.mallaPuntos[p.x][p.y] ) ) {
					canEnter = false;
				}
			} catch( ArrayIndexOutOfBoundsException e ) {
			}
		}
		
		System.out.println( "canEnter="+canEnter );
	
		return canEnter;
	}

	public boolean canDown() {
		
		boolean canDown = true;

		if( this.figura == null ) {
			return canDown;
		}
		
		boolean parcDentro = false;
		for( Point p : this.figura.getFigura() ) {
			if( p.y >= 0 ) {
				parcDentro = true;
			}
		}
		if( !parcDentro ) {
			return false;
		}
			
		
		for( Point p : this.figura.getFigura() ) {
			if( p.y < 0 ) {
				continue;
			}
			int yy = p.y+1;
			System.out.println( "x,y="+p.x+","+p.y+" / yy="+yy );
			if( yy < this.malla[p.x].length ) {
				try {
					if( this.malla[p.x][yy] == 1 && 
					   !this.figura.equals( this.mallaPuntos[p.x][yy] ) ) {
						return false;
					}
				} catch( ArrayIndexOutOfBoundsException a ) {
					System.out.println( "ArrayIndexOutOfBoundsException" );
					return false;
				} catch( NullPointerException e ) {
					System.out.println( "NullPointerException" );
					return false;
				}
			} else {
				return false;
			}
 		}
		System.out.println( "*** "+canDown );

		return canDown;
	}
	
	public void down() {
		
		//System.out.println( "down()" );
		
		ArrayList<Point> listaPuntos = this.figura.getFigura();
		for( Point p : listaPuntos ) {
			try {
				this.malla[p.x][p.y] = 0;
				this.mallaPuntos[p.x][p.y] = null;
			} catch( ArrayIndexOutOfBoundsException e ) {
			}
 		}

		int yy;
		for( Point p : listaPuntos ) {
			yy = (p.y+1);
			try {
				this.malla[p.x][yy] = 1;
				this.mallaPuntos[p.x][yy] = this.figura;
			} catch( ArrayIndexOutOfBoundsException e ) {
			}
			p.y = yy;
			//System.out.println( p.x+","+p.y+" / "+yy);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//System.out.println( "paintComponent" );
		
		Rectangle rec = g.getClipBounds();
		int step = (int)Math.round((rec.height-2*BORDER)/20.0);
		int x0 = BORDER;
		int y0 = BORDER;
		
		for( int j=0; j<malla[0].length; j++ ) {
			for( int i=0; i<malla.length; i++ ) {
				if( this.malla[i][j] == 1 ) {
					//System.out.print( "("+i+","+j+")="+malla[i][j]+" " );
					
					g.setColor( Color.red );
					g.fillRect( step*(i+1)-x0, step*(j+1)-y0, step, step);
					
					g.setColor( Color.black );
					g.drawRect( step*(i+1)-x0, step*(j+1)-y0, step, step);
				}
			}
			//System.out.println( "" );
		}
	}

	@Override
	public void run() {
		
		System.out.println( "START" );

		while( !stopped ) {

			if( this.figura != null && canDown() ) {
				down();
			} else { 			
				Ele fig = new Ele();
				if( canEnter( fig ) ) {
					//System.out.println( "1" );
					this.figura = fig;
					enterFigure( fig );
				} else {
					//System.out.println( "2" );
					fix(fig);
					stopped = true;
				}
			}

			repaint(0);
			
			try {
				Thread.sleep( 1000 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
		
		System.out.println( "END" );
		
	}

	private void fix( Figure fig ) {
		
	}

	public void starting() {

		// Start
		if( stopped ) {
			stopped = false;
			Thread thread = new Thread(this);
			thread.start();
		}
		
	}
	
}
