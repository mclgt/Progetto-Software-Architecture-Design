package com.State;

import com.Model.Track;
import java.util.List;

public class StoppedState implements IPlayerState {

    private PlayerContext context;

    public StoppedState(PlayerContext context) {
        this.context = context;
    }

    @Override
    public void play(Track track) {
        context.setCurrentTrack(track);
        System.out.println("Starting playback: " + track.getTitle());
        context.setState(context.getPlayingState());
    }

    @Override
    public void pause() {
        System.out.println("Cannot pause: player is stopped.");
    }

    @Override
    public void stop() {
        System.out.println("Already stopped.");
    }

    @Override
    public void next(List<Track> queue, Track current) {
        System.out.println("Cannot skip: player is stopped.");
    }

    @Override
    public void previous(List<Track> queue, Track current) {
        System.out.println("Cannot go back: player is stopped.");
    }
}
