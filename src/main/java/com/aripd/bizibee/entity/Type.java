package com.aripd.bizibee.entity;

public enum Type {

    SINGLE_CHOICE("SINGLE_CHOICE"),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE"),
    RANGE_CHOICE("RANGE_CHOICE"),
    PLANOGRAM1("PLANOGRAM1"),
    PLANOGRAM2("PLANOGRAM2"),
    FILE_UPLOAD("FILE_UPLOAD");

    private final String label;

    private Type(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
