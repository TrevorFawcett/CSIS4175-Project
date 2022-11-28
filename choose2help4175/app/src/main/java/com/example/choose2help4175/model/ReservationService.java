package com.example.choose2help4175.model;

import java.util.Date;

public class ReservationService {

    private String reservationSId;
    private String reservationSFirstName;
    private String reservationSLastName;
    private int reservationSPhoneNumber;
    private String reservationSEmail;
    private Date reservationSDate;

    public ReservationService() { }

    public ReservationService(String reservationSId, String reservationSFirstName, String reservationSLastName, int reservationSPhoneNumber, String reservationSEmail, Date reservationSDate) {
        this.reservationSId = reservationSId;
        this.reservationSFirstName = reservationSFirstName;
        this.reservationSLastName = reservationSLastName;
        this.reservationSPhoneNumber = reservationSPhoneNumber;
        this.reservationSEmail = reservationSEmail;
        this.reservationSDate = reservationSDate;
    }

    public String getReservationSId() {
        return reservationSId;
    }

    public void setReservationSId(String reservationSId) {
        this.reservationSId = reservationSId;
    }

    public String getReservationSFirstName() {
        return reservationSFirstName;
    }

    public void setReservationSFirstName(String reservationSFirstName) {
        this.reservationSFirstName = reservationSFirstName;
    }

    public String getReservationSLastName() {
        return reservationSLastName;
    }

    public void setReservationSLastName(String reservationSLastName) {
        this.reservationSLastName = reservationSLastName;
    }

    public int getReservationSPhoneNumber() {
        return reservationSPhoneNumber;
    }

    public void setReservationSPhoneNumber(int reservationSPhoneNumber) {
        this.reservationSPhoneNumber = reservationSPhoneNumber;
    }

    public String getReservationSEmail() {
        return reservationSEmail;
    }

    public void setReservationSEmail(String reservationSEmail) {
        this.reservationSEmail = reservationSEmail;
    }

    public Date getReservationSDate() {
        return reservationSDate;
    }

    public void setReservationSDate(Date reservationSDate) {
        this.reservationSDate = reservationSDate;
    }
}
