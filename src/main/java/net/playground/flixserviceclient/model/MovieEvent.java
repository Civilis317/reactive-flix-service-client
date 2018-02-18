package net.playground.flixserviceclient.model;

import java.util.Date;

public class MovieEvent {
    private String movieId;
    private Date dateViewed;

    public MovieEvent(String movieId, Date dateViewed) {
        this.movieId = movieId;
        this.dateViewed = dateViewed;
    }

    public MovieEvent() {

    }

    @Override
    public String toString() {
        return "MovieEvent{" +
                "movieId='" + movieId + '\'' +
                ", dateViewed=" + dateViewed +
                '}';
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Date getDateViewed() {
        return dateViewed;
    }

    public void setDateViewed(Date dateViewed) {
        this.dateViewed = dateViewed;
    }
}
