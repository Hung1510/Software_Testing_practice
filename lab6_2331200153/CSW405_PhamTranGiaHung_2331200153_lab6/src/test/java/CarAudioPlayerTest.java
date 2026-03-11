import carAudioPlayer.AudioPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CarAudioPlayerTest {
    AudioPlayer player;

    @Before
    public void setUp() {
        List<String> radios = new ArrayList<>();
        radios.add("FM90");
        radios.add("FM99");
        radios.add("FM100");

        List<String> songs = new ArrayList<>();
        songs.add("Song1");
        songs.add("Song2");
        songs.add("Song3");

        player = new AudioPlayer(radios, songs);
    }

    // initial State

    @Test
    public void testInitialStateIsRadioPlaying() {
        assertEquals("Radio Playing", player.getCurrentState().toString());
    }

    //Radio: next / rewind

    @Test
    public void testRadioNextStation() {
        player.next(); //FM90 -> FM99
        String result = player.playRadio();
        assertEquals("FM99", result);
    }

    @Test
    public void testRadioNextStationWrapsAround() {
        player.next(); // FM99
        player.next(); //FM100
        player.next(); //wraps -> FM90
        String result = player.playRadio();
        assertEquals("FM90", result);
    }

    @Test
    public void testRadioRewindStation() {
        player.rewind(); // FM90 -> wraps->  FM100
        String result = player.playRadio();
        assertEquals("FM100", result);
    }

    @Test
    public void testRadioRewindThenNext() {
        player.rewind(); // FM100
        player.next();   // FM90
        String result = player.playRadio();
        assertEquals("FM90", result);
    }

    //Radio -> MP3 transition

    @Test
    public void testRadioToMp3ChangesState() {
        player.mp3();
        assertEquals("MP3 Playing", player.getCurrentState().toString());
    }

    @Test
    public void testRadioToMp3PlaysFirstSong() {
        // mp3()RadioPlaying start from current song (Song1)
        player.mp3();
        String result = player.playMP3Song();
        assertEquals("Song1", result);
    }

    //MP3: next / rewind

    @Test
    public void testMp3NextSong() {
        player.mp3();
        player.next(); // Song1 -> Song2
        String result = player.playMP3Song();
        assertEquals("Song2", result);
    }

    @Test
    public void testMp3NextSongWrapsAround() {
        player.mp3();
        player.next(); // Song2
        player.next(); // Song3
        player.next(); // wraps -> Song1
        String result = player.playMP3Song();
        assertEquals("Song1", result);
    }

    @Test
    public void testMp3PreviousSong() {
        player.mp3();
        player.rewind(); // Song1 -> wraps -> Song3
        String result = player.playMP3Song();
        assertEquals("Song3", result);
    }

    @Test
    public void testMp3RewindThenNext() {
        player.mp3();
        player.rewind(); // Song3
        player.next();   // Song1
        String result = player.playMP3Song();
        assertEquals("Song1", result);
    }

    //MP3 ->Radio transition

    @Test
    public void testMp3ToRadioChangesState() {
        player.mp3();
        player.radio();
        assertEquals("Radio Playing", player.getCurrentState().toString());
    }

    @Test
    public void testMp3ToRadioResumesLastChannel() {
        player.next();  // advance to FM99 while in Radio
        player.mp3();   // switch to MP3
        player.radio(); // back to Radio
        String result = player.playRadio();
        assertEquals("FM99", result);
    }

    //Traffic Alert from Radio

    @Test
    public void testTrafficAlertFromRadioChangesState() {
        player.trafficalert();
        assertEquals("Radio Alerted", player.getCurrentState().toString());
    }

    @Test
    public void testTrafficDoneFromRadioAlertedReturnsToRadio() {
        player.trafficalert();
        player.done();
        assertEquals("Radio Playing", player.getCurrentState().toString());
    }

    @Test
    public void testTrafficDoneFromRadioAlertedResumesChannel() {
        player.next(); // move to FM99
        player.trafficalert();
        player.done();
        String result = player.playRadio();
        assertEquals("FM99", result);
    }

    //Traffic Alert from MP3

    @Test
    public void testTrafficAlertFromMp3ChangesState() {
        player.mp3();
        player.trafficalert();
        assertEquals("MP3 Alerted", player.getCurrentState().toString());
    }

    @Test
    public void testTrafficDoneFromMp3AlertedReturnsToMp3() {
        player.mp3();
        player.trafficalert();
        player.done();
        assertEquals("MP3 Playing", player.getCurrentState().toString());
    }

    @Test
    public void testTrafficDoneFromMp3AlertedResumesSong() {
        player.mp3();
        player.next(); // advance to Song2
        player.trafficalert();
        player.done();
        String result = player.playMP3Song();
        assertEquals("Song2", result);
    }

    //Invalid throw NullPointerException

    @Test(expected = NullPointerException.class)
    public void testRadioButtonHasNoEffectInRadioPlaying() {
        player.radio(); // already in Radio Playing
    }

    @Test(expected = NullPointerException.class)
    public void testMp3ButtonHasNoEffectInMp3Playing() {
        player.mp3();
        player.mp3(); // already in MP3 Playing
    }

    @Test(expected = NullPointerException.class)
    public void testDoneHasNoEffectInRadioPlaying() {
        player.done();
    }

    @Test(expected = NullPointerException.class)
    public void testDoneHasNoEffectInMp3Playing() {
        player.mp3();
        player.done();
    }

    @Test(expected = NullPointerException.class)
    public void testNextHasNoEffectInRadioAlerted() {
        player.trafficalert();
        player.next();
    }

    @Test(expected = NullPointerException.class)
    public void testNextHasNoEffectInMp3Alerted() {
        player.mp3();
        player.trafficalert();
        player.next();
    }

    @Test(expected = NullPointerException.class)
    public void testTrafficAlertHasNoEffectInRadioAlerted() {
        player.trafficalert();
        player.trafficalert();
    }

    @Test(expected = NullPointerException.class)
    public void testTrafficAlertHasNoEffectInMp3Alerted() {
        player.mp3();
        player.trafficalert();
        player.trafficalert();
    }
}