package com.altamiracorp.securegraph.type;

import com.altamiracorp.securegraph.SecureGraphException;

import java.io.Serializable;

public class GeoPoint implements Serializable, GeoShape {
    private static double EARTH_RADIUS = 6371; // km
    static final long serialVersionUID = 1L;
    private final double latitude;
    private final double longitude;
    private final Double altitude;

    public GeoPoint(double latitude, double longitude, Double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public GeoPoint(double latitude, double longitude) {
        this(latitude, longitude, null);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    @Override
    public boolean within(GeoShape geoShape) {
        throw new SecureGraphException("Not implemented for argument type " + geoShape.getClass().getName());
    }

    // see http://www.movable-type.co.uk/scripts/latlong.html
    public static double distanceBetween(double latitude1, double longitude1, double latitude2, double longitude2) {
        double dLat = toRadians(latitude2 - latitude1);
        double dLon = toRadians(longitude2 - longitude1);
        latitude1 = toRadians(latitude1);
        latitude2 = toRadians(latitude2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(latitude1) * Math.cos(latitude2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    private static double toRadians(double v) {
        return v * Math.PI / 180;
    }
}