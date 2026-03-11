package carAudioPlayer;

import java.util.ArrayList;
import java.util.List;


public class AudioPlayer {
    private AudioPlayerState RadioPlaying;
    private AudioPlayerState MP3Playing;
    private AudioPlayerState RadioAlerted;
    private AudioPlayerState MP3Alerted;

    private AudioPlayerState currentState;

    private List<String> RadioChannels = new ArrayList<String>();
    private List<String> CollectionofSongs = new ArrayList<String>();

    int currentChannelno = 0;
    int currentSongno = 0;
    int ChannelNumbers;
    int SongsNumber;

    public AudioPlayer(List<String> radioChannels, List<String> Songs) {
        RadioPlaying = new RadioPlaying();
        MP3Playing = new MP3Playing();
        RadioAlerted = new RadioAlerted();
        MP3Alerted = new MP3Alerted();
        currentState = RadioPlaying;
        RadioChannels = radioChannels;
        CollectionofSongs = Songs;
        ChannelNumbers = radioChannels.size();
        SongsNumber = Songs.size();

    }

    public AudioPlayerState getRadioPlaying() {
        return RadioPlaying;
    }

    public AudioPlayerState getMP3Playing() {
        return MP3Playing;
    }

    public AudioPlayerState getRadioAlerted() {
        return RadioAlerted;
    }

    public AudioPlayerState getMP3Alerted() {
        return MP3Alerted;
    }

    public void setCurrentState(AudioPlayerState currentState) {
        this.currentState = currentState;
    }

    public String next() {
        return currentState.next(this);
    }

    public String radio() {
        return currentState.radio(this);
    }

    public String done() {
        return currentState.done(this);
    }

    public String mp3() {
        return currentState.mp3(this);
    }

    public String trafficalert() {
        return currentState.trafficalert(this);
    }

    public String rewind() {
        return currentState.rewind(this);
    }

    public void playingNextSong() {
        if (currentSongno == SongsNumber - 1)
            currentSongno = 0;
        else
            currentSongno++;

    }

    public void playingPreviousSong() {
        if (currentSongno == 0)
            currentSongno = SongsNumber - 1;
        else
            currentSongno--;

    }

    public void playingNextChannel() {
        if (currentChannelno == ChannelNumbers - 1)
            currentChannelno = 0;
        else
            currentChannelno++;

    }

    public void playingPreviousChannel() {
        if (currentChannelno == 0)
            currentChannelno = ChannelNumbers - 1;
        else
            currentChannelno--;

    }

    public String playMP3Song() {
//        System.out.println(CollectionofSongs.get(currentSongno) + " is palying in MP3"); --wrong output
        System.out.println(CollectionofSongs.get(currentSongno) + " is playing in MP3");
        return CollectionofSongs.get(currentSongno);
    }

    public String playRadio() {
//        System.out.println(RadioChannels.get(currentChannelno) + " is braodcasting on Radio"); --wrong output
        System.out.println(RadioChannels.get(currentChannelno) + " is broadcasting on Radio");
        return RadioChannels.get(currentChannelno);
    }

    public AudioPlayerState getCurrentState() {
        return currentState;
    }

    public List<String> getCollectionofSongs() {
        return CollectionofSongs;
    }

    public int getCurrentChannelno() {
        return currentChannelno;
    }

    public int getCurrentSongno() {
        return currentSongno;
    }

    public int getChannelNumbers() {
        return ChannelNumbers;
    }

    public int getSongsNumber() {
        return SongsNumber;
    }

}
