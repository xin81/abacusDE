/**
 * @author Nguyen V. Tan
 * @version %I%
 */
package shapes;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import frame.MyFrame;
import sounds.SoundPlayer;

/**
 * Displays the German abacus
 */
public class MyCanvas extends Canvas {
	private static final long serialVersionUID = 1L;
	private static final Logger logger=LogManager.getLogger(MyCanvas.class);	
	public final static int xMAX=10;
	public final static int yMAX=10;
	public static boolean numVisible=true;	
	private MyFrame myframe;
	private Dimension dimension;	
	private int[][]mat;
	private Bead[][]abacus;
	
	private final int PT=20;
	/*
	 * Calculates the current result of this abacus via a 10x10 matrix.
	 * The actual abacus displays the current state of a matrix.
	 * Each rod represents 10^r, where r donotes the row
	 * from 0 to 9 (note that 10^0=1).
	 */
	private double getSum(int[][]mat){
		int rows=xMAX;
		int cols=yMAX;
		double sum=0;
		for(int i=0; i < rows; i++){
			for(int j=0; j < cols; j++){
				sum+=(mat[i][j]*Math.pow(10, (rows-(i+1))*1.0));				
			}
		}
		return sum;
	}
/*
 * Updates the entire abacus and the whole application based
 * on the results of a mathematical operation
 */	
private void update(){
	int rows=xMAX;
	int cols=yMAX;
	for(int i=0; i < rows; i++) {
		for(int j=0; j < cols; j++) {
			abacus[i][j].setValue(mat[i][j]);
			int xstart=abacus[i][j].getXStart();
			
			// moves all the active beads to the right
			if(abacus[i][j].getStatus()==true){
				if(abacus[i][j].getX()==xstart){
					double diametre=abacus[i][j].getDiametre();
					int xstep=(int)(xstart+(11*diametre));
					abacus[i][j].setX(xstep);;
				}
			}else{// bring all the beads back to its original positions
				// to the left
				if(abacus[i][j].getX()!=xstart){
					abacus[i][j].setX(xstart);
				}
			}
		}
	}	
	repaint();
	
	// calculate the negative result, ...
	double sum=getSum(mat);
	double nsum=0;
	NumberFormat format=DecimalFormat.getInstance(Locale.GERMANY);
	myframe.getJtResult().setText(format.format(sum));
	try {
		// choose the closest "big friend" 10^p to the current result
		double p=0;
		p = (sum == 0) ? 1 : (int)Math.log10(Math.abs(sum)) + 1; // safer code
		double base=Math.pow(10, p);
		
		// and calculate: current sum - "big friend"
		nsum=(sum==0)?0:(sum - base);
		
		// ... and display it
		myframe.getJtNResult().setText(format.format(nsum));
	}catch(ArithmeticException e) {
		// log(0) should never ever happen, since it is already guarded in
		// the code lines above (i. e. nsum=(sum==0)?:0:(sum-base)).
		// Yet if something like that does happen, then ...
		logger.error(e.getMessage());
	}
}
	// initializes the settings for this abacus
	private void setup(){
		// the matrix that stores all the bead values
		// between 0 and 1
		mat=new int[xMAX][yMAX];
		for(int i=0; i < mat.length; i++){
			for(int j=0; j < mat[i].length; j++){
				mat[i][j]=0;
			}
		}
		
		// create 10x10 beads
		int w=30;
		int h=w;
		double diametre=((w+h)/2);
		double radius=diametre/2;
		abacus=new Bead[xMAX][yMAX];		
		int pt=PT;		
		for(int i=0; i < abacus.length; i++) {
			for(int j=0; j < abacus[i].length; j++){
				int value=mat[i][j];
				
				// rotate the abacus by 90°
				// so that the lowest bead values
				// are placed in the bottom
				// from right to left 
				int dx=(j+1);
				int dy=(i+1);
				int x=(int)((dx*diametre)+(pt));
				int y=(int)(dy*(diametre+radius));
				
				abacus[i][j]=new Bead(value,x,y,w,h);
			}
		}
		
		dimension=myframe.getSize();		
		this.setBackground(Color.WHITE);
		this.setSize(dimension);		
	}

	/*
	 * 
	 */
	public MyCanvas(MyFrame myframe) {
		// TODO Auto-generated constructor stub
		this.myframe=myframe;
		setup();
	}
	
	/**
	 * Draws and paints the entire abacus.
	 * Also, number each rod
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		// abacus
		for(int i=0; i < abacus.length; i++){
			int x=0;
			int y=abacus[i][0].getY()+(PT-5);
			
			// number each rod
			if(numVisible==true) {
				g.setColor(Color.BLACK);
				int irow=xMAX-(i+1);
				String str=""+irow+"";
				String name="Arial";
				int style=Font.PLAIN;
				int size=30;
				Font font=new Font(name, style, size);
				g.setFont(font);
				g.drawString(str, x+15, y+13);
			}
			
			// rods
			double radius=abacus[i][0].getRadius();
			double diametre=abacus[i][0].getDiametre();
			
			// rx is a starting point which should change
			int rx=abacus[i][0].getXStart()-(int)(diametre);
			int ry=abacus[i][0].getY()+(int)((radius/2)+(radius/4));
			int rw=(int)((22*diametre));
			int rh=((int)(radius/2)); 
			g.setColor(Color.BLACK);
			g.drawRect((int)((rx+diametre)-rh), ry, (int)(rw-radius), rh);			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect((int)((rx+diametre)-rh), ry, (int)(rw-radius), rh);

			// left beam
			int ry1=abacus[i][0].getY()-((int)((radius/2)+(radius/4)));			
			Color beamColour=new Color(255, 153, 51);
			
			g.setColor(Color.BLACK);
			g.drawRect((int)((rx+diametre)-rh), ry1, rh, rw/13);			
			g.setColor(beamColour);
			g.fillRect((int)((rx+diametre)-rh), ry1, rh, rw/13);
			
			// right beam
			int rx2=(int)(rw-radius);
			int ry2=abacus[i][0].getY()-((int)((radius/2)+(radius/4)));			
			
			g.setColor(Color.BLACK);
			g.drawRect((int)((rx2+diametre)+radius), ry2, (int)(rh-radius), rw/13);			
			g.setColor(beamColour);
			g.fillRect((int)((rx2+diametre)+radius), ry2, (int)(rh-radius), rw/13);
			
			// abacus
			for(int j=0; j < abacus[i].length; j++) {
				if(abacus[i][j].getValue()> 0){
					final Color mygreen=new Color(12, 217, 0);
					abacus[i][j].setColour(mygreen);
				}else{
					abacus[i][j].setColour(Color.RED);
				}
				abacus[i][j].paint(g);
			}
		}		
	}
	/*
	* @param row current row within this matrix which serves for mathematical operations
	* @ matrix stores all the bead values
	*/	
	private int rowSum(int row, int[][] matrix){
		int sum=0;
		for(int j=0; j < matrix[row].length; j++){
			sum+=matrix[row][j];
		}
		return sum;
	}
	
	/**
	 * adds another bead to the right side
	 * @param row current row in this matrix that is being used for a calculation
	 * @param amount the user's demand of beads
	 *
	 */
	public void add(int row, int amount){
		int limit=mat[row].length;// max=10
		int j=(limit-1);
		int count=0;
		int currentSum=rowSum(row, mat);
		if(currentSum < limit) {
			while((j>=0)&&(count < amount)){
				if(mat[row][j]==0) {
					mat[row][j]=1;
					count++;
				}
				j--;
			}
		}else{
			// disable this button once the limit has been reached
			myframe.getJbAdd(row).setEnabled(false);
			logger.info("addition attempt but no bead is available anymore");
		}
		
		update();
		sound();
		if(myframe.getJbSub(row).isEnabled()==false) {
			myframe.getJbSub(row).setEnabled(true);
		}
	}
	/** removes another bead from the right side 
	* @param row @see #add(int, int)
	* @param amount @see #add(int, int)
	*/
	public void subtract(int row, int amount){
		int limit=mat[row].length;
		int j=0;
		int count=0;
		int currentSum=rowSum(row, mat);
		if(currentSum > 0) {
			while((j<limit)&&(count < amount)){
				if(mat[row][j]==1) {
					mat[row][j]=0;
					count++;
				}
				j++;
			}
			}else{// disables this button when no bead is any longer
				// available
			myframe.getJbSub(row).setEnabled(false);
			logger.info("subtraction atttempt but no bead is available anymore");
		}
		
		update();
		sound();
		if(myframe.getJbAdd(row).isEnabled()==false) {
			myframe.getJbAdd(row).setEnabled(true);
		}		
	}
	
	private void sound(){
		SoundPlayer player=new SoundPlayer();
		try {
			player.play();
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * Resets all the textfields, beads
	 * (i. e. brings all the beads back to
	 * its original positions),
	 * and buttons (enable them when they have been disabled)
	 */
	public void reset(){
		int rows=xMAX;
		int cols=yMAX;
		for(int i=0; i < rows; i++){
			myframe.getJtAdd(i).setSelectedIndex(0);
			myframe.getJtSub(i).setSelectedIndex(0);
			if(myframe.getJbAdd(i).isEnabled()==false) {
				myframe.getJbAdd(i).setEnabled(true);
			}
			if(myframe.getJbSub(i).isEnabled()==false) {
				myframe.getJbSub(i).setEnabled(true);
			}
			for(int j=0; j< cols; j++){
				mat[i][j]=0;
			}
		}
		update();
		sound();
	}
	/**
	* @ param i the exact row
	* @ param j the exact column
	* @return the exact bead that is being used in this abacus that is defined by this matrix abacus[i][j]
	*/	
	public Bead getAbacus(int i, int j){
		return abacus[i][j];
	}
}
