package shapes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Bead extends JComponent {
	private int x, y, w, h;		// coordinates
	private int xstart;		// starting point on the x-axis 
	private int value;		// 0 or 1
	private double diametre;
	private double radius;
	private Color colour;		// selected colour
	private final Color BLACK=Color.BLACK;// default colour
	private boolean active;		// if value=1, this bead will be considered active
	private static final long serialVersionUID = 1L;
	
	private void setup(int v, int x, int y, int w, int h){
		value=v;
		this.x=x;
		this.y=y;
		this.w=w; 
		this.h=h;
		xstart=x;	// always return to xstart when value=0
		diametre=(w+h)/2;
		radius=diametre/2;
		colour=Color.black;
		active=(value != 0);// if value=1, then active=true
	}
	public Bead() {
		// TODO Auto-generated constructor stub
		value=0;
		x=0;
		y=0;
		w=0;
		h=0;
		setup(value, x, y, w, h);
	}
	public Bead(int v, int x, int y, int w, int h){
		setup(v, x, y, w, h);
	}
	public void paint(Graphics g) {
		super.paint(g);
		// draw in black
		g.setColor(BLACK);
		g.drawOval(x, y, w, h);
		
		// but paint in colours
		g.setColor(colour);
		g.fillOval(x,  y, w, h);
	}
	public void setColour(Color color){
		colour=color;
	}
	public Color getColour(){
		return colour; //=color;
	}
	public double getDiametre(){
		return diametre;
	}
	public double getRadius(){
		return radius;
	}
	public int getValue(){
		return value;
	}
	
	private void setStatus(int value){
		active=(value != 0);
	}
	
	public void setValue(int v){
		value=v;
		setStatus(value);
	}
	
	public int getX(){
		return x;
	}
	public void setX(int x){
		this.x=x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return w;
	}
	public int getHeight() {
		return h;
	}
	
	/** 
	 * @return true if value does not equal 0
	 */	
	public boolean getStatus(){
		return active;
	}
	/**
	 * This starting point is supposed to stay constant
	 * It should always stay the same so that this bead can always
	 * return to its original position Point(x, y).
	 */
	public int getXStart(){
		return xstart;
	}
	/*	
// no getYStart() necessary, because all the beads are going to move
// from left to right, back and forth. So, only its x-value will be changed
	*/
}
