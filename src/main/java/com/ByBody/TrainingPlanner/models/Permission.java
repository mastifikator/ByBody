package com.ByBody.TrainingPlanner.models;

public enum Permission {
    EXERCISE_READ("developers:read"),
    EXERCISE_WRITE("developers:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
