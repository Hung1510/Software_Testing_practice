package carAudioPlayer;

public class MP3Alerted implements AudioPlayerState {

	@Override
	public String next(AudioPlayer p) {
		throw new NullPointerException("No Effect");
	}
	@Override
	public String radio(AudioPlayer p) {
		throw new NullPointerException("No Effect");
	}
	@Override
	public String mp3(AudioPlayer p) {
		throw new NullPointerException("No Effect");
	}
	@Override
	public String done(AudioPlayer p) {
		p.setCurrentState(p.getMP3Playing());
		System.out.println("Braodcasting of traffic information is done.");
		p.playMP3Song();
		return p.getCurrentState().toString();
	}
	@Override
	public String trafficalert(AudioPlayer p) {
		throw new NullPointerException("No Effect");
	}
	@Override
	public String rewind(AudioPlayer p) {
		throw new NullPointerException("No Effect");
	}
	@Override
	public String toString() {
		return "MP3 Alerted";
	}
	
}
