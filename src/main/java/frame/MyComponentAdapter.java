package frame;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Prints out the current location and the size of any <code>Window</code>
 * or <code>Dialog</code>. This class helps the programmer to choose
 * a good location of type Point(x, y), and a good area size (width x height).
 */
public class MyComponentAdapter extends ComponentAdapter {
	private static final Logger logger=LogManager.getLogger(MyComponentAdapter.class);
	private JFrame jframe;
	
	// only logs debug information when DEBUG=true
	private static boolean DEBUG=false;

	public MyComponentAdapter(JFrame jframe) {
		// TODO Auto-generated constructor stub
		// by default, DEBUG=false
		// so, no debug information about this class
		// is saved in a log file
		this.jframe=jframe;		
	}
	
	/**
	 * @param on if true, all the debug information will be
	 * saved in a log file called debug.log
	 */
	public MyComponentAdapter(JFrame jframe, boolean on){
		this.jframe=jframe;
		DEBUG=on;
	}

	@Override
	public void componentResized(ComponentEvent e){
		super.componentResized(e);
		if(DEBUG==true) {
			logger.debug("size=["+jframe.getWidth()+" x "+jframe.getHeight()+"]");
		}
	}

	@Override
	public void componentMoved(ComponentEvent e){
		super.componentMoved(e);
		if(DEBUG==true) {
			logger.debug("location=Point("+jframe.getX()+", "+jframe.getY()+")");				
		}
	}
}
