package carAudioPlayer;

public class RadioPlaying implements AudioPlayerState {

	@Override
	public String next(AudioPlayer p) {
		p.playingNextChannel();
		p.playRadio();
		return p.getCurrentState().toString();
	}
	@Override
	public String radio(AudioPlayer p) {
		throw new NullPointerException("No Effect");
	}
	@Override
	public String mp3(AudioPlayer p) {
		p.setCurrentState(p.getMP3Playing());
		p.playMP3Song();
		return p.getCurrentState().toString();
	}
	@Override
	public String done(AudioPlayer p) {
		throw new NullPointerException("No Effect");
	}
	@Override
	public String trafficalert(AudioPlayer p) {
		p.setCurrentState(p.getRadioAlerted());
		System.out.println("Traffic Information is Brodcasted");
		return p.getCurrentState().toString();
	}
	@Override
	public String rewind(AudioPlayer p) {
		p.playingPreviousChannel();
		p.playRadio();
		return p.getCurrentState().toString();
	}
	@Override
	public String toString() {
		return "Radio Playing";
	}
	
}
