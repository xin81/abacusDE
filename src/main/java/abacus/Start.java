package abacus;
import javax.swing.JFrame;
import frame.MyFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
* @author Nguyen V. Tan
* @version %I%
*/
public class Start{
	private static final Logger logger=LogManager.getLogger(Start.class);
	
	public Start(){
		
	}
	public static void main(String[]args){
	// void main(){
		logger.info("Abacus started");
		JFrame myframe=new MyFrame("Abacus");
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myframe.setVisible(true);
	}
}
