package com.example.rationalheads.vijay.grabdemo.model;


public class Festival {

    private String Festnumber;
    private String Festtle;

    public Festival() {
    }

    public Festival(String festnumber, String festtle) {
        Festnumber = festnumber;
        Festtle = festtle;
    }

    public String getFestnumber() {
        return Festnumber;
    }

    public void setFestnumber(String festnumber) {
        Festnumber = festnumber;
    }

    public String getFesttle() {
        return Festtle;
    }

    public void setFesttle(String festtle) {
        Festtle = festtle;
    }
}
