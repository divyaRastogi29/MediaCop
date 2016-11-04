package com.follow.me.model;

/**
 * Created by divya on 31/10/16.
 */
public class BaseResponse {

    String message ;

    boolean isSuccessful ;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
