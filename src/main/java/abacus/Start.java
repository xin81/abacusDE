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
* @author Nguyen V. Tan
* @version %I%
*/
public class Start{
	private static final Logger logger=LogManager.getLogger(Start.class);
	private static String LANGUAGE="de";// by default
	public Start() {
	}

	private static void setLanguageByFile(String filename){
		String installPath=System.getProperty("java.home");
		try{
		Path appPath = Paths.get(Start.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
		File file=appPath.resolve(filename).toFile();
			if(file.exists()){

				String line=Files.readString(file.toPath());
				String[] data=line.split(" ", 2);
				String option=data[0];
				if(option.compareTo("--language")==0){
					LANGUAGE=data[1];
					IO.println("chosen language: "+LANGUAGE);
				}
			}
		}catch(IOException | URISyntaxException e){
			logger.error(e.getMessage());
		}
	}

	private static void setLanguageByArguments(String[] args){
		if (args[0].compareTo("--language") == 0) {
			if (args[1].length() == 2) {
				if ((args[1].compareToIgnoreCase("en") == 0)
				|| (args[1].compareToIgnoreCase("de") == 0)) {
					UIText.setLanguage(args[0]);
				} else {
					logger.warn("Unknown language selected");
				}
			}// check if the value has really only 2 letters
		}else{
			logger.warn("Unknown option selected");
		}
	}

	public static void main(String[]args){
		logger.info("Abacus started");
		if(args.length > 0){
			setLanguageByArguments(args);
		}else{
			final String FILENAME="../settings/language.txt";
			setLanguageByFile(FILENAME);
		}
		UIText.setLanguage(LANGUAGE);
		String language=UIText.getLanguage();
		String appName= UIText.getAppName(language);
		JFrame myframe=new MyFrame(appName);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myframe.setVisible(true);
	}
}
