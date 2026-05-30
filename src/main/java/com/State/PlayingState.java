package com.State;

import com.Model.Track;
import java.util.List;

public class PlayingState implements IPlayerState {

    private PlayerContext context;

    public PlayingState(PlayerContext context) {
        this.context = context;
    }

    @Override
    public void play(Track track) {
        context.setCurrentTrack(track);
        System.out.println("Playing: " + track.getTitle());
    }

    @Override
    public void pause() {
        System.out.println("Pausing playback.");
        context.setState(context.getPausedState());
    }

    @Override
    public void stop() {
        System.out.println("Stopping playback.");
        context.setState(context.getStoppedState());
    }

    @Override
    public void next(List<Track> queue, Track current) {
        Track nextTrack = context.getPlaybackContext().nextTrack(queue, current);
        if (nextTrack != null) {
            System.out.println("Playing next track: " + nextTrack.getTitle());
            play(nextTrack);
        } else {
            System.out.println("No next track available.");
            stop();
        }
    }

    @Override
    public void previous(List<Track> queue, Track current) {
        Track previousTrack = context.getPlaybackContext().previousTrack(queue, current);
        if (previousTrack != null) {
            System.out.println("Playing previous track: " + previousTrack.getTitle());
            play(previousTrack);
        } else {
            System.out.println("No previous track available.");
            stop();
        }
    }
    
}
