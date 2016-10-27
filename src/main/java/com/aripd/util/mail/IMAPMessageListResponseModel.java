package com.aripd.util.mail;

import java.util.List;

public class IMAPMessageListResponseModel {

    /**
     * TODO success ve message bir liste olarak düşünelim. hata kodları vs.
     */
    private boolean success;

    private String message;

    List<IMAPMessageListModel> list;

    public IMAPMessageListResponseModel() {
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

    public List<IMAPMessageListModel> getList() {
        return list;
    }

    public void setList(List<IMAPMessageListModel> list) {
        this.list = list;
    }

}
