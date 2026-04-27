package sounds;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import abacus.Start;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
* The particular sound which is used by this player in order to simulate clicking beads of an abacus is called
* the <a href="https://freesound.org/s/560334/">Battering End of Battery into Wooden Panel 3.wav</a>,
* and it is owned by <a href="https://www.freesound.org">FreeSound.org</a>.
* See also the THIRD_PARTY_NOTICES.txt text file.
*/
public class SoundPlayer {
	private static final Logger logger=LogManager.getLogger(SoundPlayer.class);
	public SoundPlayer() {
	}
	/** 
	* Plays the sound of clicking beads of an abacus
	*/	
	public void play()throws IOException, UnsupportedAudioFileException,
	LineUnavailableException, URISyntaxException {
		String installPath=System.getProperty("java.home");
		Path appPath = Paths.get(Start.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
		String filename="audio/woodenClick.wav";
		// File file = new File(filename);
		File file=appPath.resolve(filename).toFile();
		if(file.exists()) {
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		}else{
			logger.error(filename+" can not be found");
		}		
	}
}
