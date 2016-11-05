package com.aripd.bizibee.entity;

public enum DecisionType {

    SINGLE_CHOICE("Single Choice"),
    MULTIPLE_CHOICE("Multiple Choice"),
    RANGE_SKU_LISTING("RANGE_SKU_LISTING"),
    SINGLE_CHOICE_SKU_LISTING("SINGLE_CHOICE_SKU_LISTING"),
    MULTIPLE_CHOICE_SKU_LISTING("MULTIPLE_CHOICE_SKU_LISTING");

    private final String label;

    private DecisionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
