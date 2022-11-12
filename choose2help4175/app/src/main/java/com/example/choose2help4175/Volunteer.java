package com.example.choose2help4175;

import java.util.Date;

public class Volunteer {

    private String volunteerId;
    private String volunteerURL;
    private Date volunteerDate;

    public Volunteer(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public Volunteer(String volunteerId, String volunteerURL, Date volunteerDate) {
        this.volunteerId = volunteerId;
        this.volunteerURL = volunteerURL;
        this.volunteerDate = volunteerDate;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getVolunteerURL() {
        return volunteerURL;
    }

    public void setVolunteerURL(String volunteerURL) {
        this.volunteerURL = volunteerURL;
    }

    public Date getVolunteerDate() {
        return volunteerDate;
    }

    public void setVolunteerDate(Date volunteerDate) {
        this.volunteerDate = volunteerDate;
    }
}
