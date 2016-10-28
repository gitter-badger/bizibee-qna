package com.aripd.bizibee.entity;

public enum DecisionType {

    SINGLE_CHOICE("Single Choice"),
    MULTIPLE_CHOICE("Multiple Choice"),
    RANGE_PRODUCT_LISTING("RANGE_PRODUCT_LISTING"),
    SINGLE_CHOICE_PRODUCT_LISTING("SINGLE_CHOICE_PRODUCT_LISTING"),
    MULTIPLE_CHOICE_PRODUCT_LISTING("MULTIPLE_CHOICE_PRODUCT_LISTING");

    private final String label;

    private DecisionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
