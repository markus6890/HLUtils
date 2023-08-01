package com.gmail.markushygedombrowski.utils;

import java.util.ArrayList;
import java.util.List;

public class ListHolder {

    private List<String> fangeList = new ArrayList<>();
    private List<String> vagtList = new ArrayList<>();
    private List<String> totalList = new ArrayList<>();

    public List<String> getFangeList() {
        return fangeList;
    }

    public List<String> getVagtList() {
        return vagtList;
    }

    public List<String> getTotalList() {
        return totalList;
    }
    public void removeFange(String name) {
        fangeList.remove(name);
    }
    public void removeVagt(String name) {
        vagtList.remove(name);
    }
    public void removeTotal(String name) {
        totalList.remove(name);
    }
    public void addFange(String name) {
        fangeList.add(name);
    }
    public void addVagt(String name) {
        vagtList.add(name);
    }
    public void addTotal(String name) {
        totalList.add(name);
    }


}
