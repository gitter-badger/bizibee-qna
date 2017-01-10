package com.aripd.bizibee.model.response;

import org.primefaces.model.UploadedFile;

public class Response6Model {

    private UploadedFile file;

    private byte[] bytes;

    public Response6Model() {
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return ResponseConverter.convert(this).toString();
    }

}
