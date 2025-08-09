package com.gmail.markushygedombrowski.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;

public class ListHolder {
    private final Map<Category, Map<Role, List<String>>> categoryMap;
    private final List<String> totalList;

    public ListHolder() {
        this.categoryMap = new EnumMap<>(Category.class);
        for (Category category : Category.values()) {
            Map<Role, List<String>> roleMap = new EnumMap<>(Role.class);
            for (Role role : Role.values()) {
                roleMap.put(role, new ArrayList<>());
            }
            categoryMap.put(category, roleMap);
        }
        this.totalList = new ArrayList<>();
    }

    public void add(Category category, Role role, String name) {
        categoryMap.get(category).get(role).add(name);
    }

    public void remove(Category category, Role role, String name) {
        categoryMap.get(category).get(role).remove(name);
    }

    public boolean contains(Category category, Role role, String name) {
        return categoryMap.get(category).get(role).contains(name);
    }

    public List<String> getList(Category category, Role role) {
        return new ArrayList<>(categoryMap.get(category).get(role));
    }

    public void clear(Category category, Role role) {
        categoryMap.get(category).get(role).clear();
    }

    public void addToTotal(String name) {
        totalList.add(name);
    }

    public void removeFromTotal(String name) {
        totalList.remove(name);
    }

    public boolean containsInTotal(String name) {
        return totalList.contains(name);
    }

    public List<String> getTotalList() {
        return new ArrayList<>(totalList);
    }

    public void removeFromAll(String name) {
        for (Category category : Category.values()) {
            for (Role role : Role.values()) {
                remove(category, role, name);
            }
        }
        removeFromTotal(name);
    }
}