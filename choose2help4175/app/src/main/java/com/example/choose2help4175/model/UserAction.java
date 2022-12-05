package com.example.choose2help4175.model;

public class UserAction{

    String userId;
    String userAction;
    String actionDate;

    public UserAction() {

    }

    public UserAction(String userAction, String actionDate) {
        this.userAction = userAction;
        this.actionDate = actionDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }
}
