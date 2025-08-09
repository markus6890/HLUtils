package com.gmail.markushygedombrowski.utils;

import java.util.Arrays;

public enum Role {
    FANGE, VAGT;

    public static Role fromString(String text) {
        try {
            return Role.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + text + ". Valid roles are: " + Arrays.toString(Role.values()));
        }
    }

}

