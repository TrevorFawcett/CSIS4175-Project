package com.example.choose2help4175.model;

import java.util.Date;

public class Donation {

    private String donationId;
    private String donationURL;
    private Date donationDate;

    public Donation() {  }

    public Donation(String donationId, String donationURL, Date donationDate) {
        this.donationId = donationId;
        this.donationURL = donationURL;
        this.donationDate = donationDate;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public String getDonationURL() {
        return donationURL;
    }

    public void setDonationURL(String donationURL) {
        this.donationURL = donationURL;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }
}
