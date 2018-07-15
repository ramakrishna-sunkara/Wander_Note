package com.wonder.wandernote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNoteResponse {

    @Expose
    @SerializedName("success")
    private boolean success;

    @Expose
    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
