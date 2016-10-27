package com.gabiksoft.webapp.utils;

import org.json.JSONObject;

import java.util.HashMap;

public class JSONResponse implements ResponseKeys {

    private JSONObject standartResponse;

    private int errorCode;
    private String errorName;
    private HashMap<String, String> additional;

    public JSONResponse() {
        standartResponse = new JSONObject();
        additional = new HashMap<String, String>();
    }

    public JSONResponse(int errorCode, String errorName) {
        this();
        this.errorCode = errorCode;
        this.errorName = errorName;
    }

    public JSONResponse(int errorCode, String errorName, HashMap<String, String> additional) {
        this();
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.additional = additional;
    }

    public void addResponseProperty(String key, String value){
        additional.put(key, value);
    }

    private void buildResponse(){
        standartResponse.put(RESPONSE_ERROR_CODE, errorCode);
        standartResponse.put(RESPONSE_ERROR_MESSAGE, errorName);
        standartResponse.put(RESPONSE_ADDITIONAL, additional);
    }

    @Override
    public String toString() {
        buildResponse();
        return standartResponse.toString();
    }
}
