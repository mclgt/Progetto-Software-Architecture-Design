package com.Model;

import com.DataLayer.IAudioTrack;

/**
 * @brief Rappresenta l'entità del singolo brano musicale.
 *        Modella i dati relativi ad una traccia così come definiti dalla user
 *        story [US-1]
 *        Non contiene la logica di business completa.
 * 
 * @author Michela
 */
public class Track {
    private String title;
    private String author;
    private int year;
    private String genre;
    private int duration;
    private String album;
    private String filePath;

    private IAudioTrack audioSource;

    /**
     * @brief Inizializza una nuova traccia musicale. Sebbene il costruttore sia
     *        pubblico,
     *        si utilizzerà l'apposito Factory Method, in quanto quest'ultimo
     *        implementa anche la verifica
     *        della validità del singolo brano musicale.
     *
     * @param title    Titolo del brano musicale
     * @param author   Autore
     * @param year     Anno di pubblicazione
     * @param genre    genere musicale
     * @param duration durata fisica del brano espressa in secondi
     * @param album    Album di appartenenza del brano
     * @param filePath Percorso del file audio da inserire
     */
    public Track(String title, String author, int year, String genre, int duration, String album, String filePath) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.duration = duration;
        this.album = album;
        this.filePath = filePath;
    }

    // Getter e setter
    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYear() {
        return this.year;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getFilePath(){
        return this.filePath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    /**
     * @brief Imposta la sorgente audio (Proxy) associata a questo brano.
     * @param audioSource L'oggetto che implementa la gestione audio.
     */
    public void setAudioSource(IAudioTrack audioSource){
        this.audioSource = audioSource;
    }

    /**
     * @brief Restituisce la sorgente audio per avviare o fermare la riproduzione.
     * @return IAudioTrack L'interfaccia per controllare l'audio.
     */
    public IAudioTrack getAudioSource(){
        return this.audioSource;
    }

    /**
     * @brief Restituisce la durata del brano formattata come "MM:SS".
     */
    public String getFormattedDuration() {
        int totalSeconds = getDuration();
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

    /**
     * @brief Restituisce una rappresentazione formattata del brano
     * @return String: la rappresentazione del brano
     */
    @Override
    public String toString() {
        return title + " - " + author + "( " + year + ")" + "-" + genre + "(" + duration + "s ), " + album;
    }
}