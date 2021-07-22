package com.nostra.nostramovieapps.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse {

    private int status;    //status flag based on CommonConstants
    private String message; //message description
    private Object contents; //real data content
    private long totalRecords; //total records data

    public RestResponse(int status, String message, Object contents, long totalRecords) {
        this.status = status;
        this.message = message;
        this.contents = contents;
        this.totalRecords = totalRecords;
    }
}
