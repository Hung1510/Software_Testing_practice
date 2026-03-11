package carAudioPlayer;

public class RadioAlerted implements AudioPlayerState {

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
		p.setCurrentState(p.getRadioPlaying());
		System.out.println("Braodcasting of traffic information is done.");
		p.playRadio();
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
		return "Radio Alerted";
	}
	
}
