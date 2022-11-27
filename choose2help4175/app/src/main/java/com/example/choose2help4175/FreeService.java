package com.example.choose2help4175;

public class FreeService {

    private String fServiceId;
    private String fServiceName;
    private int imgFServiceType;
    private String fServiceAddress;
    private String fServiceLocation;
    private String fServiceDate;
    private String fServiceTime;
    private String fServiceDescription;

    public FreeService() { }

//    public FreeService(String fServiceId, String fServiceName, int imgFServiceType, String fServiceAddress, String fServiceLocation, int fServiceDate, int fServiceTime, String fServiceDescription) {
//        this.fServiceId = fServiceId;
//        this.fServiceName = fServiceName;
//        this.imgFServiceType = imgFServiceType;
//        this.fServiceAddress = fServiceAddress;
//        this.fServiceLocation = fServiceLocation;
//        this.fServiceDate = fServiceDate;
//        this.fServiceTime = fServiceTime;
//        this.fServiceDescription = fServiceDescription;
//    }


    public FreeService(int imgFServiceType, String fServiceName, String fServiceDate) {
        this.imgFServiceType = imgFServiceType;
        this.fServiceName = fServiceName;
        this.fServiceDate = fServiceDate;
    }

    public FreeService(String fServiceName, int imgFServiceType, String fServiceAddress, String fServiceLocation, String fServiceDate, String fServiceTime, String fServiceDescription) {
        this.fServiceName = fServiceName;
        this.imgFServiceType = imgFServiceType;
        this.fServiceAddress = fServiceAddress;
        this.fServiceLocation = fServiceLocation;
        this.fServiceDate = fServiceDate;
        this.fServiceTime = fServiceTime;
        this.fServiceDescription = fServiceDescription;
    }

    public FreeService(String fServiceId, String fServiceName, int imgFServiceType, String fServiceAddress, String fServiceLocation, String fServiceDate, String fServiceTime, String fServiceDescription) {
        this.fServiceId = fServiceId;
        this.fServiceName = fServiceName;
        this.imgFServiceType = imgFServiceType;
        this.fServiceAddress = fServiceAddress;
        this.fServiceLocation = fServiceLocation;
        this.fServiceDate = fServiceDate;
        this.fServiceTime = fServiceTime;
        this.fServiceDescription = fServiceDescription;
    }

    public String getfServiceId() {
        return fServiceId;
    }

    public void setfServiceId(String fServiceId) {
        this.fServiceId = fServiceId;
    }

    public String getfServiceName() {
        return fServiceName;
    }

    public void setfServiceName(String fServiceName) {
        this.fServiceName = fServiceName;
    }

    public int getImgFServiceType() {
        return imgFServiceType;
    }

    public void setImgFServiceType(int imgFServiceType) {
        this.imgFServiceType = imgFServiceType;
    }

    public String getfServiceAddress() {
        return fServiceAddress;
    }

    public void setfServiceAddress(String fServiceAddress) {
        this.fServiceAddress = fServiceAddress;
    }

    public String getfServiceLocation() {
        return fServiceLocation;
    }

    public void setfServiceLocation(String fServiceLocation) {
        this.fServiceLocation = fServiceLocation;
    }

    public String getfServiceDate() {
        return fServiceDate;
    }

    public void setfServiceDate(String fServiceDate) {
        this.fServiceDate = fServiceDate;
    }

    public String getfServiceTime() {
        return fServiceTime;
    }

    public void setfServiceTime(String fServiceTime) {
        this.fServiceTime = fServiceTime;
    }

    //    public int getfServiceDate() {
//        return fServiceDate;
//    }
//
//    public void setfServiceDate(int fServiceDate) {
//        this.fServiceDate = fServiceDate;
//    }
//
//    public int getfServiceTime() {
//        return fServiceTime;
//    }
//
//    public void setfServiceTime(int fServiceTime) {
//        this.fServiceTime = fServiceTime;
//    }

    public String getfServiceDescription() {
        return fServiceDescription;
    }

    public void setfServiceDescription(String fServiceDescription) {
        this.fServiceDescription = fServiceDescription;
    }
}
