package com.dto.response;

public class CommonResponse {
    private boolean res;
    private int statusCode;
    private String message;

    public CommonResponse() {
    }

    public CommonResponse(boolean res, int statusCode, String message) {
        this.res = res;
        this.statusCode = statusCode;
        this.message = message;
    }

    public boolean getRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static CommonResponse generateResponse(boolean res,int statusCode,String message){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setRes(res);
        commonResponse.setStatusCode(statusCode);
        commonResponse.setMessage(message);
        return commonResponse;
    }
}
