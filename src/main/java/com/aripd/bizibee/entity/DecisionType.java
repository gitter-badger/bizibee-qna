package com.aripd.bizibee.entity;

public enum DecisionType {

    SINGLE_CHOICE("SINGLE_CHOICE"),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE"),
    SINGLE_SKU_LISTING("SINGLE_SKU_LISTING"),
    MULTIPLE_SKU_LISTING("MULTIPLE_SKU_LISTING"),
    RANGE_SKU_LISTING("RANGE_SKU_LISTING"),
    PLANOGRAM1("PLANOGRAM1"),
    PLANOGRAM2("PLANOGRAM2"),
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
