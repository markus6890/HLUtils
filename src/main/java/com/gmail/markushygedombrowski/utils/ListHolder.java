package com.gmail.markushygedombrowski.utils;

import java.util.ArrayList;
import java.util.List;

public class ListHolder {

    private List<String> fangeList = new ArrayList<>();
    private List<String> vagtList = new ArrayList<>();
    private List<String> totalList = new ArrayList<>();
    private List<String> aFange = new ArrayList<>();
    private List<String> aVagt = new ArrayList<>();
    private List<String> bFange = new ArrayList<>();
    private List<String> bVagt = new ArrayList<>();
    private List<String> cFange = new ArrayList<>();
    private List<String> cVagt = new ArrayList<>();
    public List<String> getcVagt() {
        return cVagt;
    }

    public void setcVagt(List<String> cVagt) {
        this.cVagt = cVagt;
    }

    public List<String> getcFange() {
        return cFange;
    }

    public void setcFange(List<String> cFange) {
        this.cFange = cFange;
    }

    public List<String> getbVagt() {
        return bVagt;
    }

    public void setbVagt(List<String> bVagt) {
        this.bVagt = bVagt;
    }

    public List<String> getbFange() {
        return bFange;
    }

    public void setbFange(List<String> bFange) {
        this.bFange = bFange;
    }

    public List<String> getaVagt() {
        return aVagt;
    }

    public void setaVagt(List<String> aVagt) {
        this.aVagt = aVagt;
    }

    public List<String> getaFange() {
        return aFange;
    }

    public void setaFange(List<String> aFange) {
        this.aFange = aFange;
    }

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
    public void removeAFange(String name) {
        aFange.remove(name);
    }
    public void removeAVagt(String name) {
        aVagt.remove(name);
    }
    public void removeBFange(String name) {
        bFange.remove(name);
    }
    public void removeBVagt(String name) {
        bVagt.remove(name);
    }
    public void removeCFange(String name) {
        cFange.remove(name);
    }
    public void removeCVagt(String name) {
        cVagt.remove(name);
    }
    public void clearFangeList() {
        fangeList.clear();
    }
    public void clearVagtList() {
        vagtList.clear();
    }
    public void clearTotalList() {
        totalList.clear();
    }
    public void clearAFange() {
        aFange.clear();
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
    public void addAFange(String name) {
        aFange.add(name);
    }
    public void addAVagt(String name) {
        aVagt.add(name);
    }
    public void addBFange(String name) {
        bFange.add(name);
    }
    public void addBVagt(String name) {
        bVagt.add(name);
    }
    public void addCFange(String name) {
        cFange.add(name);
    }
    public void addCVagt(String name) {
        cVagt.add(name);
    }

    public void removeFromALL(String name) {
        removeFange(name);
        removeVagt(name);
        removeTotal(name);
        removeAFange(name);
        removeAVagt(name);
        removeBFange(name);
        removeBVagt(name);
        removeCFange(name);
        removeCVagt(name);
    }



}
