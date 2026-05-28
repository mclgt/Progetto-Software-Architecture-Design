package com.Model;

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
     */
    public Track(String title, String author, int year, String genre, int duration) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.duration = duration;
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

    /**
     * @brief Restituisce una rappresentazione formattata del brano
     * @return String: la rappresentazione del brano
     */
    @Override
    public String toString() {
        return title + " - " + author + "( " + year + ")" + "-" + genre + "(" + duration + "s )";
    }

}
