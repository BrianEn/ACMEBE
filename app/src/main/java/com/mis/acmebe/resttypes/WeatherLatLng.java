package com.mis.acmebe.resttypes;

public class WeatherLatLng {
    private float lat;
    private float lon;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public WeatherLatLng(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public WeatherLatLng() {
    }
}
