package com.exemplo.guests.model;

public class FeedBack {

    private Boolean Success = true;
    private String Message = "";

    public FeedBack(String message) {
        Message = message;
    }

    public FeedBack(String message,Boolean success) {
        Message = message;
        Success = success;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public String getMessage() {
        return Message;
    }
}
