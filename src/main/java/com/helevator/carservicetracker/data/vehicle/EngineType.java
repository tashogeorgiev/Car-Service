package com.helevator.carservicetracker.data.vehicle;

public enum EngineType {
    DIESEL("diesel"),
    PETROL("petrol");

    public final String label;

    EngineType(String label) {
        this.label = label;
    }
}
