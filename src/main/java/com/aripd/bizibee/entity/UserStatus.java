package com.aripd.bizibee.entity;

public enum UserStatus {

    Unconfirmed("Unconfirmed"),
    Confirmed("Confirmed");

    private final String label;

    private UserStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
