package com.movieland.mvc.model;

public enum SortType {

    ASC("asc"), DESC ("desc");

    private final String sortTypeValue;

    SortType(String sortTypeValue) {
        this.sortTypeValue = sortTypeValue;
    }

    public String value(){
        return sortTypeValue;
    }
}
