package com.tribikram.myapplication.models;

import java.io.Serializable;

/**
 * Created by Tribikram on 4/15/2018.
 */

public class MusicVideo implements Serializable{

    private String title;
    private String artist;
    private String album;
    private double length;

    public MusicVideo(){}

    public MusicVideo(String title, String artist, String album, double length) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
