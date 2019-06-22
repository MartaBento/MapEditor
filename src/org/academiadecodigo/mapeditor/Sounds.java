package org.academiadecodigo.mapeditor;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Sounds {

    public Clip saveFile() {

        try {

            Clip audioClip = getAudioClip("FX381.wav");
            audioClip.loop(0);
            return audioClip;

        } catch (Exception ex) {

            System.out.println(ex);

        }
        return null;
    }

    public Clip loadFile() {

        try {

            Clip audioClip = getAudioClip("FX382.wav");
            audioClip.loop(0);
            return audioClip;

        } catch (Exception ex) {

            System.out.println(ex);

        }
        return null;
    }

    public void stopMusic(Clip audioClip) {
        if (audioClip != null) {
            audioClip.stop();
            audioClip.close();
        }
    }

    private Clip getAudioClip(String name) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL url = getClass().getResource("/" + name);

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioStream);
        return clip;
    }
}
