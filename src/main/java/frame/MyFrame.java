package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shapes.MyCanvas;
/**
 * Main window that contains all the buttons, drop-down lists,
 * textfields, menu items, and of course the canvas which
 * eventually displays the German abacus
 */
public class MyFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton[] jbAdd;
	private JButton[] jbSub;
	private JButton jbReset;
	
	private JComboBox<Integer>[] jtAdd;
	private JComboBox<Integer>[] jtSub;
	
	private JTextField result;
	private JTextField nresult;
	private MyCanvas canvas;
	private JMenuItem jmiClose;
	private JCheckBoxMenuItem jmiHideResults; 
	private JCheckBoxMenuItem jmiHideNumbering;
	private JMenu jmFile;
	private JMenu jmOptions;
	
	@SuppressWarnings({ "unchecked"})
	private void setup(){
		
		// recommended size by the componentAdapter
		// after some testing
		final int WIDTH=1200;
		final int HEIGHT=645;
		setSize(WIDTH, HEIGHT);
		
		// recommended location (x, y) by the componentAdapter
		final int X=115;
		final int Y=90;
		setLocation(X, Y);
		
		// 
		WindowListener wl=new MyWindowListener(this);
		addWindowListener(wl);
		
		// helps the programmer to find the appropriate
		// size and location for this window
		ComponentAdapter adapter=new MyComponentAdapter(this);
		addComponentListener(adapter);
		
		// displays the actual abacus
		canvas=new MyCanvas(this);
		
		// layouts for all the buttons and textfields
		int row=MyCanvas.xMAX;
		int col=2;
		GridLayout gl=new GridLayout(row,col);
		JPanel jpadd=new JPanel();
		jpadd.setLayout(gl);
		JPanel jpsub=new JPanel();
		jpsub.setLayout(gl);
		String language=UIText.getLanguage();
		// buttons
		ActionListener al=new MyActionListener(this);
		jbAdd=new JButton[row];
		jbSub=new JButton[row];
		jtAdd=new JComboBox[row];
		jtSub=new JComboBox[row];
		jbReset=new JButton(UIText.getReset(language));
		jbReset.addActionListener(al);
		
		// menu items
		jmiClose=new JMenuItem(UIText.getCloseStr(language));
		jmiClose.addActionListener(al);
		
		jmFile=new JMenu(UIText.getFile(language));
		jmFile.add(jmiClose);
		
		jmiHideResults=new JCheckBoxMenuItem(UIText.getHide_Results(language));
		jmiHideResults.addActionListener(al);
		jmiHideNumbering=new JCheckBoxMenuItem(UIText.getHide_NumberingStr(language));
		jmiHideNumbering.addActionListener(al);
		jmOptions=new JMenu(UIText.getOptions(language));
		jmOptions.add(jmiHideResults);
		jmOptions.add(jmiHideNumbering);
		
		// place all the menu items in this menubar
		// and make it visible
		JMenuBar jmbar=new JMenuBar();
		jmbar.add(jmFile);
		jmbar.add(jmOptions);
		this.setJMenuBar(jmbar);
		
		// possible selection for the user
		// to demand a selected amount of beads
		Integer iValue[]=new Integer[row];
		for(int i=0; i < row; i++){
			iValue[i]=(i+1);
		}
		
		// buttons for addition and subtraction
		for(int j=0; j < row; j++) {
			int iName=row-(j+1);
			jbAdd[j]=new JButton("<html>plus 10<sup>"+iName+"</sup></html>");
			jbAdd[j].setName("AddR"+iName);
			jbAdd[j].addActionListener(al);
			jpadd.add(jbAdd[j].getName(),jbAdd[j]);
			
			jtAdd[j]=new JComboBox<Integer>(iValue); 
			jpadd.add(jtAdd[j]);
			
			jbSub[j]=new JButton("<html>minus 10<sup>"+iName+"</sup></html>");
			jbSub[j].setName("SubR"+iName);
			jbSub[j].addActionListener(al);
			jpsub.add(jbSub[j].getName(), jbSub[j]);
			jtSub[j]=new JComboBox<Integer>(iValue);
			jpsub.add(jtSub[j]);
		}
		
		// textfields to display the results of each maths operation
		result=new JTextField("+0");
		nresult=new JTextField("-0");
		Font font=new Font("Arial", Font.BOLD, 28);
		result.setFont(font);
		result.setEnabled(false);
		result.setHorizontalAlignment(JTextField.CENTER);
		
		nresult.setFont(font);
		nresult.setEnabled(false);
		nresult.setHorizontalAlignment(JTextField.CENTER);
		JPanel jpsouth=new JPanel();
		jpsouth.setLayout(new FlowLayout());
		JPanel jpnorth=new JPanel();
		jpnorth.setLayout(new GridLayout(1, 2));
		jpnorth.add(nresult);
		jpnorth.add(result);		
		jpsouth.add(jbReset);
		
		// make it all visible
		add(BorderLayout.NORTH, jpnorth);
		add(BorderLayout.CENTER, canvas);
		add(BorderLayout.EAST, jpadd);
		add(BorderLayout.WEST, jpsub);
		add(BorderLayout.SOUTH, jpsouth);
}
	public MyFrame(String title)throws HeadlessException{
		super(title);
		setup();
	}
	public JButton getJbAdd(int index){
		return jbAdd[index];
	}
	public JComboBox<Integer> getJtAdd(int index){
		return jtAdd[index];
	}
	public JButton getJbSub(int index){
		return jbSub[index];
	}
	public JComboBox<Integer> getJtSub(int index){
		return jtSub[index];
	}
	public MyCanvas getCanvas(){
		return canvas;
	}
	public JTextField getJtResult(){
		return result;
	}
	public JTextField getJtNResult(){
		return nresult;
	}
	public JButton getJbReset(){
		return jbReset;
	}
	public JMenuItem getJmiClose(){
		return jmiClose;
	}
	public JCheckBoxMenuItem getHideResults(){
		return jmiHideResults;
	}
	public JCheckBoxMenuItem getHideNumbering(){
		return jmiHideNumbering;
	}
	public void close(){
		dispose();
		setVisible(false);
		System.exit(EXIT_ON_CLOSE);
	}
	
	/**
	 * Turns the visibility of the results on and off
	 * @param if true, all the results wil be hidden
	 */
	public void hideResults(boolean hide){
		result.setVisible(!hide);
		nresult.setVisible(!hide);
	}
}
