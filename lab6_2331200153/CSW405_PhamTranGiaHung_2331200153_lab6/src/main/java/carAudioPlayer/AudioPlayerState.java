package carAudioPlayer;

public interface AudioPlayerState {
	public String next(AudioPlayer p);
	public String rewind(AudioPlayer p);
	public String radio(AudioPlayer p);
	public String mp3(AudioPlayer p);
	public String done(AudioPlayer p);
	public String trafficalert(AudioPlayer p);

}
