package com.Factory;

import com.Model.Track;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/***
 * @brief Classe di test per la validazione della TrackFactory
 *        Si verifica che l'istanziazione dei brani rispetti gli Acceptance
 *        Criteria della [US-1]
 *        osservando sia i casi di successo che la gestione delle eccezioni per
 *        dati non validi.
 */

public class TrackFactoryTest {

    @Test
    public void testCreateTrack_success() {
        Track track = TrackFactory.createTrack("Creep", "Radiohead", 1992, "Alternative Rock", 238,
                "A Night at the opera");
        assertNotNull(track);
        assertEquals("Creep", track.getTitle());
        assertEquals("Radiohead", track.getAuthor());
        assertEquals(1992, track.getYear());
        assertEquals("Alternative Rock", track.getGenre());
        assertEquals(238, track.getDuration());
        assertEquals("A Night at the opera", track.getAlbum());
    }

    @Test
    public void testCreateTrack_noTitle() {
        Exception exc = assertThrows(IllegalArgumentException.class, () -> {
            TrackFactory.createTrack("", "Radiohead", 1992, "Alternative Rock", 238, "A Night at the opera");
        });
        assertTrue(exc.getMessage().contains("Titolo"));
    }

    @Test
    public void testCreateTrack_noAuthor() {
        Exception exc = assertThrows(IllegalArgumentException.class, () -> {
            TrackFactory.createTrack("Creep", "", 1992, "Alternative Rock", 238, "A Night at the opera");
        });
        assertTrue(exc.getMessage().contains("Autore"));
    }

    @Test
    public void testCreateTrack_invalidDuration() {
        Exception exc = assertThrows(IllegalArgumentException.class, () -> {
            TrackFactory.createTrack("Creep", "Radiohead", 1992, "Alternative Rock", -2, "A Night at the opera");
        });
        assertTrue(exc.getMessage().contains("Durata"));
    }

}
