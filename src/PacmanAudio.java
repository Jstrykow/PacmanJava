import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class PacmanAudio {
    File file;

    PacmanAudio(){
    }

    public void playDeathSound(String fileName) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        file = new File(fileName);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}
