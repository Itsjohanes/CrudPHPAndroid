package com.UTSmobproGanjil.johannes2002895.Model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetNotes {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Notes> listDataNotes;

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

    public List<Notes> getListDataNotes() {
        return listDataNotes;
    }

    public void setListDataNotes(List<Notes> listDataNotes) {
        this.listDataNotes = listDataNotes;
    }
}
