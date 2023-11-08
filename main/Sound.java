package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/res/sound/Game.wav ");
        soundURL[1] = getClass().getResource("/res/sound/getcoin.wav");
        soundURL[2] = getClass().getResource("/res/sound/opendoor.wav");
        soundURL[3] = getClass().getResource("/res/sound/Congrat.wav");
        soundURL[4] = getClass().getResource("/res/sound/potionDrink.wav");
        soundURL[5] = getClass().getResource("/res/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/res/sound/receivedamage.wav");
        soundURL[7] = getClass().getResource("/res/sound/swingweapon.wav");
        soundURL[8] = getClass().getResource("/res/sound/pickup.wav");
        soundURL[9] = getClass().getResource("/res/sound/Monfinish.wav");
        soundURL[10] = getClass().getResource("/res/sound/mondyding.wav");
        soundURL[11] = getClass().getResource("/res/sound/powering.wav");
        soundURL[12] = getClass().getResource("/res/sound/diesound.wav");
        soundURL[13] = getClass().getResource("/res/sound/select.wav");  
        soundURL[14] = getClass().getResource("/res/sound/game-start.wav");
        soundURL[15] = getClass().getResource("/res/sound/teleport.wav");
        soundURL[16] = getClass().getResource("/res/sound/hurt.wav");
        soundURL[17] = getClass().getResource("/res/sound/pickbonus.wav");
        soundURL[18] = getClass().getResource("/res/sound/fanfare.wav");
        
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {

        }

    }

    public void play() {

        clip.start();
    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
