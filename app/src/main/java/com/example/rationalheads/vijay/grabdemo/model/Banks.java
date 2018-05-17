package com.example.rationalheads.vijay.grabdemo.model;

public class Banks {


    private String bankName;
    private String offerName;


    public Banks(String bankName, String offerName) {
        this.bankName = bankName;
        this.offerName = offerName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }
}
