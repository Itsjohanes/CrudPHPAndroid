package com.UTSmobproGanjil.johannes2002895.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelNotes {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    Notes mNotes;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Notes getHeros() {
        return mNotes;
    }
    public void setHeros(Notes Notes) {
        mNotes = Notes;
    }
}
