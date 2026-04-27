package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shapes.MyCanvas;
/**
 * Handles all the user's mouse and button clicks
 */
public class MyActionListener implements ActionListener {
	private static final Logger logger=LogManager.getLogger(MyActionListener.class);
	private MyFrame myframe;
	private MyCanvas canvas;
	
	public MyActionListener(MyFrame myframe) {
		// TODO Auto-generated constructor stub
		this.myframe=myframe;
		canvas=myframe.getCanvas();
	}
	
	// @return the amount of beads chosen/selected by the user
	private int readAmount(JComboBox<Integer> jt){
		int amount=1;
		if(jt.getSelectedItem()!=null){
			amount=(int)(jt.getSelectedItem());
		}
		return amount;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Since all the buttons and beads are placed in reverse,
		// start from length-1
		int row=(MyCanvas.xMAX-1);
		logger.info("actionPerformed: "+e.getSource().toString());		
		do {
			JButton jbAdd=myframe.getJbAdd(row);
			JComboBox<Integer> jtAdd=myframe.getJtAdd(row);
			JButton jbSub=myframe.getJbSub(row);
			JComboBox<Integer> jtSub=myframe.getJtSub(row);
			
			// user wants to add another bead
			if(e.getSource().equals(jbAdd)==true){
				int amount=readAmount(jtAdd);
				canvas.add(row, amount);
			}
			
			// user wants to remove another bead
			if(e.getSource().equals(jbSub)==true){
				int amount=readAmount(jtSub);
				canvas.subtract(row, amount);
			}
			
			row--;
		}while(row>=0);
		
		if(e.getSource().equals(myframe.getJbReset())==true){
			canvas.reset();
		}
		if(e.getSource().equals(myframe.getJmiClose())==true){
			myframe.close();
		}
		
		// user does not want to see the text fields displaying
		// the result of his maths operations
		if(e.getSource().equals(myframe.getHideResults())==true) {
			boolean hidden=myframe.getHideResults().getState();
			myframe.hideResults(hidden);
		}
		
		// user does not want to any numbering of each rod
		if(e.getSource().equals(myframe.getHideNumbering())==true) {
			boolean hidden=myframe.getHideNumbering().getState();
			MyCanvas.numVisible=!hidden;
			canvas.repaint();
		}		
	}// end of actionPerformed
}
