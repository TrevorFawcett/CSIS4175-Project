package com.example.choose2help4175;

public class FreeService {

    private String fServiceId;
    private String fServiceName;
    private String fServiceType;
    private String fServiceAddress;
    private int fServiceDate;
    private int fServiceTime;
    private String fServiceDescription;

    public FreeService() { }

    public FreeService(String fServiceId, String fServiceName, String fServiceType, String fServiceAddress, int fServiceDate, int fServiceTime, String fServiceDescription) {
        this.fServiceId = fServiceId;
        this.fServiceName = fServiceName;
        this.fServiceType = fServiceType;
        this.fServiceAddress = fServiceAddress;
        this.fServiceDate = fServiceDate;
        this.fServiceTime = fServiceTime;
        this.fServiceDescription = fServiceDescription;
    }

    public String getFServiceId() {
        return fServiceId;
    }

    public void setFServiceId(String fServiceId) {
        this.fServiceId = fServiceId;
    }

    public String getFServiceName() {
        return fServiceName;
    }

    public void setFServiceName(String fServiceName) {
        this.fServiceName = fServiceName;
    }

    public String getFServiceType() {
        return fServiceType;
    }

    public void setFServiceType(String fServiceType) {
        this.fServiceType = fServiceType;
    }

    public String getFServiceAddress() {
        return fServiceAddress;
    }

    public void setFServiceAddress(String fServiceAddress) {
        this.fServiceAddress = fServiceAddress;
    }

    public int getFServiceDate() {
        return fServiceDate;
    }

    public void setFServiceDate(int fServiceDate) {
        this.fServiceDate = fServiceDate;
    }

    public int getFServiceTime() {
        return fServiceTime;
    }

    public void setFServiceTime(int fServiceTime) {
        this.fServiceTime = fServiceTime;
    }

    public String getFServiceDescription() {
        return fServiceDescription;
    }

    public void setFServiceDescription(String fServiceDescription) {
        this.fServiceDescription = fServiceDescription;
    }
}
