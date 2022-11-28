package com.example.choose2help4175.model;

public class Organization {

    private String ozCode;
    private String ozName;
    private String ozDescription;
    private String ozPhoneNumber;
    private String ozEmail;
    private String ozAddress;
    private String ozCity;
    private String ozProvince;
    private String ozCountry;
    private String ozPostalCode;
    private int ozImages;
    private String ozDonationURL;
    private String ozVolunteerURL;

    public Organization() { }

    public Organization(String ozName, int ozImages) {
        this.ozName = ozName;
        this.ozImages = ozImages;
    }
    public Organization(String ozCode, String ozName, String ozDescription, String ozPhoneNumber, String ozEmail, String ozAddress, String ozCity, String ozProvince, String ozCountry, String ozPostalCode, int ozImages, String ozDonationURL, String ozVolunteerURL) {
        this.ozCode = ozCode;
        this.ozName = ozName;
        this.ozDescription = ozDescription;
        this.ozPhoneNumber = ozPhoneNumber;
        this.ozEmail = ozEmail;
        this.ozAddress = ozAddress;
        this.ozCity = ozCity;
        this.ozProvince = ozProvince;
        this.ozCountry = ozCountry;
        this.ozPostalCode = ozPostalCode;
        this.ozImages = ozImages;
        this.ozDonationURL = ozDonationURL;
        this.ozVolunteerURL = ozVolunteerURL;
    }

    public String getOzCode() {
        return ozCode;
    }

    public void setOzCode(String ozCode) {
        this.ozCode = ozCode;
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

    public String getOzPhoneNumber() {
        return ozPhoneNumber;
    }

    public void setOzPhoneNumber(String ozPhoneNumber) {
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

    public int getOzImages() {
        return ozImages;
    }

    public void setOzImages(int ozImages) {
        this.ozImages = ozImages;
    }

    public String getOzDonationURL() {
        return ozDonationURL;
    }

    public void setOzDonationURL(String ozDonationURL) {
        this.ozDonationURL = ozDonationURL;
    }

    public String getOzVolunteerURL() {
        return ozVolunteerURL;
    }

    public void setOzVolunteerURL(String ozVolunteerURL) {
        this.ozVolunteerURL = ozVolunteerURL;
    }
}
