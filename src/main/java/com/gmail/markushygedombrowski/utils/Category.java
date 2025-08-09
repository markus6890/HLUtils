package com.gmail.markushygedombrowski.utils;

import java.util.Arrays;

public enum Category {
    A,B,C;

    public static Category fromString(String text) {
        try {
            return Category.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + text + ". Valid categories are: " + Arrays.toString(Category.values()));
        }
    }

    }

