package com.movieland.mvc.model;

public enum CurrencyType {

    UAH("uah"), USD ("usd"), EUR ("eur");

    private final String currencyTypeValue;

    CurrencyType(String currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

    public String value(){
        return currencyTypeValue;
    }
}
