package figure;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Figure {
	
	ArrayList<Point> figura = new ArrayList<Point>();

	public ArrayList<Point> getFigura() {
		return figura;
	}

	public void setFigura(ArrayList<Point> figura) {
		this.figura = figura;
	}
}
