package com.State;

import com.Model.Track;
import java.util.List;

public class PausedState implements IPlayerState {

    private PlayerContext context;

    public PausedState(PlayerContext context) {
        this.context = context;
    }

    @Override
    public void play(Track track) {
        context.setCurrentTrack(track);
        System.out.println("Resuming playback: " + track.getTitle());
        context.setState(context.getPlayingState());
    }

    @Override
    public void pause() {
        System.out.println("Already paused.");
    }

    @Override
    public void stop() {
        System.out.println("Stopping from paused state.");
        context.setState(context.getStoppedState());
    }

    @Override
    public void next(List<Track> queue, Track current) {
        Track nextTrack = context.getPlaybackContext().nextTrack(queue, current);
        if (nextTrack != null) {
            System.out.println("Playing next track: " + nextTrack.getTitle());
            context.setCurrentTrack(nextTrack);
            context.setState(context.getPlayingState());
        } else {
            System.out.println("No next track available.");
            context.setState(context.getStoppedState());
        }
    }

    @Override
    public void previous(List<Track> queue, Track current) {
        Track previousTrack = context.getPlaybackContext().previousTrack(queue, current);
        if (previousTrack != null) {
            System.out.println("Playing previous track: " + previousTrack.getTitle());
            context.setCurrentTrack(previousTrack);
            context.setState(context.getPlayingState());
        } else {
            System.out.println("No previous track available.");
            context.setState(context.getStoppedState());
        }
    }
}
