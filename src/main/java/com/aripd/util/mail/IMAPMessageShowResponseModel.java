package com.aripd.util.mail;

public class IMAPMessageShowResponseModel {

    /**
     * TODO success ve message bir liste olarak düşünelim. hata kodları vs.
     */
    private boolean success;

    private String message;

    private IMAPMessageShowModel showModel;

    public IMAPMessageShowResponseModel() {
    }

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

    public IMAPMessageShowModel getShowModel() {
        return showModel;
    }

    public void setShowModel(IMAPMessageShowModel showModel) {
        this.showModel = showModel;
    }

}
