package org.example.learningprogramming.model.dto;

public class ResponseMessage {
    private String message;
    private Object data;
    private String status;

    public ResponseMessage(String message, Object data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public ResponseMessage() {}


    public String getMessage() {
            return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
