package com.aripd.bizibee.entity;

public enum Kind {

    SIMULATION("SIMULATION"),
    QUESTION("QUESTION"),
    NOTIFICATION("NOTIFICATION");

    private final String label;

    private Kind(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
