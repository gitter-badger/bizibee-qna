package com.aripd.bizibee.entity;

public enum UserGroup {

    Administrators("Administrators"),
    Rulers("Rulers"),
    Members("Members");

    private final String label;

    private UserGroup(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
