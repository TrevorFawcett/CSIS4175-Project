package com.example.choose2help4175;

public class Organization {

    private String ozId;
    private String ozName;
    private String ozDescription;
    private int ozPhoneNumber;
    private String ozEmail;
    private String ozAddress;
    private String ozCity;
    private String ozProvince;
    private String ozCountry;
    private String ozPostalCode;

    public Organization() { }

    public Organization(String ozId, String ozName, String ozDescription, int ozPhoneNumber, String ozEmail, String ozAddress, String ozCity, String ozProvince, String ozCountry, String ozPostalCode) {
        this.ozId = ozId;
        this.ozName = ozName;
        this.ozDescription = ozDescription;
        this.ozPhoneNumber = ozPhoneNumber;
        this.ozEmail = ozEmail;
        this.ozAddress = ozAddress;
        this.ozCity = ozCity;
        this.ozProvince = ozProvince;
        this.ozCountry = ozCountry;
        this.ozPostalCode = ozPostalCode;
    }

    public String getOzId() {
        return ozId;
    }

    public void setOzId(String ozId) {
        this.ozId = ozId;
    }

    public String getOzName() {
        return ozName;
    }

    public void setOzName(String ozName) {
        this.ozName = ozName;
    }

    public String getOzDescription() {
        return ozDescription;
    }

    public void setOzDescription(String ozDescription) {
        this.ozDescription = ozDescription;
    }

    public int getOzPhoneNumber() {
        return ozPhoneNumber;
    }

    public void setOzPhoneNumber(int ozPhoneNumber) {
        this.ozPhoneNumber = ozPhoneNumber;
    }

    public String getOzEmail() {
        return ozEmail;
    }

    public void setOzEmail(String ozEmail) {
        this.ozEmail = ozEmail;
    }

    public String getOzAddress() {
        return ozAddress;
    }

    public void setOzAddress(String ozAddress) {
        this.ozAddress = ozAddress;
    }

    public String getOzCity() {
        return ozCity;
    }

    public void setOzCity(String ozCity) {
        this.ozCity = ozCity;
    }

    public String getOzProvince() {
        return ozProvince;
    }

    public void setOzProvince(String ozProvince) {
        this.ozProvince = ozProvince;
    }

    public String getOzCountry() {
        return ozCountry;
    }

    public void setOzCountry(String ozCountry) {
        this.ozCountry = ozCountry;
    }

    public String getOzPostalCode() {
        return ozPostalCode;
    }

    public void setOzPostalCode(String ozPostalCode) {
        this.ozPostalCode = ozPostalCode;
    }
}
