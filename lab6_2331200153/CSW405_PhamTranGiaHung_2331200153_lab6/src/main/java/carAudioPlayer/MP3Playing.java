package carAudioPlayer;

public class MP3Playing implements AudioPlayerState {

	@Override
	public String next(AudioPlayer p) {
		p.playingNextSong();
		p.playMP3Song();
		return p.getCurrentState().toString();
	}
	@Override
	public String radio(AudioPlayer p) {
		p.setCurrentState(p.getRadioPlaying());
		p.playRadio();
		return p.getCurrentState().toString();
	}
	@Override
	public String mp3(AudioPlayer p) {
		throw new NullPointerException("No Effect");
	}
	@Override
	public String done(AudioPlayer p) {
		throw new NullPointerException("No Effect");
	}
	@Override
	public String trafficalert(AudioPlayer p) {
		p.setCurrentState(p.getMP3Alerted());
		System.out.println("Traffic Information is Brodcasted");
		return p.getCurrentState().toString();
	}
	@Override
	public String rewind(AudioPlayer p) {
		p.playingPreviousSong();
		p.playMP3Song();
		return p.getCurrentState().toString();
	}
	@Override
	public String toString() {
		return "MP3 Playing";
	}

}
