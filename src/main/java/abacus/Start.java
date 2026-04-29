package abacus;
import javax.swing.JFrame;
import frame.MyFrame;
import frame.UIText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;import java.nio.file.Path;
import java.nio.file.Paths;

/**
* @author Nguyen Viet Tan
* @version %I%
*/
public class Start{
	private static final Logger logger=LogManager.getLogger(Start.class);
	private static String LANGUAGE="de";// by default
	public Start() {
	}

	private static void setLanguageByArguments(String[] args){
		for(int i=0; i < (args.length-1); i++){
		if (args[i].compareTo("--language") == 0) {
			System.out.println("args["+i+"]: "+args[i]);
			if (args[i+1].length() == 2) {
				if ((args[i+1].compareToIgnoreCase("en") == 0)
				|| (args[i+1].compareToIgnoreCase("de") == 0)){
					LANGUAGE=args[i+1];
				} else {
					logger.warn("Unknown language selected");
				}
			}// check if the value has really only 2 letters
		}else{
			logger.warn("Unknown option selected");
		}
		}
	}

	public static void main(String[]args){
		logger.info("Abacus started");

		// accept the user's language choice based on their provided program arguments
		if(args.length > 0){
			setLanguageByArguments(args);
		}else{
			// retrieve the user's language on his machine
			// and decide for them
			String osLang=System.getProperty("user.language");
			if(osLang.equalsIgnoreCase("en")){
				LANGUAGE=osLang;
			}else{
				LANGUAGE="de";
			}
		}

		UIText.setLanguage(LANGUAGE);
		String language=UIText.getLanguage();
		String appName= UIText.getAppName(language);
		JFrame myframe=new MyFrame(appName);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myframe.setVisible(true);
	}
}
