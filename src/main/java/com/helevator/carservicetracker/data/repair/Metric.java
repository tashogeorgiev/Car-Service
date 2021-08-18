package com.helevator.carservicetracker.data.repair;

public enum Metric {
    LITER("liter"),
    UNIT("unit");

    public final String label;

    Metric(String label) {
        this.label = label;
    }
}